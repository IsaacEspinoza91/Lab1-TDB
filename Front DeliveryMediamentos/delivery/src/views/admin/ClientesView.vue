<template>
  <div class="clientes-container">
    <h1>Lista de Clientes</h1>

    <!-- Cargando Clientes -->
    <div v-if="loading" class="loading-overlay">
      <div class="spinner"></div>
      <p>Cargando clientes...</p>
    </div>

    <!-- Tabla de Clientes --
    <div v-if="!loading" class="table-responsive">
      <table class="clientes-table">
        <thead>
          <tr>
            <th>ID Usuario</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Dirección</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cliente in clientes" :key="cliente.usuarioId">
            <td>{{ cliente.usuarioId }}</td>
            <td>{{ cliente.nombre }}</td>
            <td>{{ cliente.email }}</td>
            <td>{{ cliente.direccion }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    >

    <!-- Resumen de Pedidos por Cliente -->
    <div v-if="resumenPedidosData" class="resumen-pedidos">
      <h2>Resumen de Pedidos</h2>
      <table class="resumen-pedidos-table">
        <thead>
          <tr>
            <th>Cliente ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Cantidad de Pedidos</th>
            <th>Monto Total</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="resumen in resumenPedidosData" :key="resumen.cliente_id">
            <td>{{ resumen.cliente_id }}</td>
            <td>{{ resumen.nombre }}</td>
            <td>{{ resumen.apellido }}</td>
            <td>{{ resumen.email }}</td>
            <td>{{ resumen.cantidad_pedidos }}</td>
            <td>{{ resumen.monto_total }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useAuthStore } from '@/stores/auth'
import api from '@/api'

const authStore = useAuthStore() // Inicializar el store de autenticación
const clientes = ref([]) // Lista de clientes
const resumenPedidosData = ref(null) // Resumen de pedidos por cliente
const loading = ref(true) // Estado de carga de clientes
const loadingResumenPedidos = ref(true) // Estado de carga del resumen de pedidos

// Función para obtener los clientes
const fetchClientes = async () => {
  try {
    const response = await api.get('/clientes/detallado', {
      headers: {
        Authorization: `Bearer ${authStore.token}` // Usar el token del store
      }
    })
    clientes.value = response.data
  } catch (error) {
    console.error('Error al obtener clientes:', error)
    alert('No se pudo cargar la lista de clientes.')
  } finally {
    loading.value = false
  }
}

// Función para obtener el resumen de pedidos
const fetchResumenPedidos = async () => {
  loadingResumenPedidos.value = true
  resumenPedidosData.value = null
  try {
    const response = await api.get('/clientes/resumen-pedidos')
    resumenPedidosData.value = response.data
  } catch (error) {
    console.error('Error al obtener el resumen de pedidos:', error)
    alert('No se pudo cargar el resumen de pedidos por cliente.')
  } finally {
    loadingResumenPedidos.value = false
  }
}

// Llamamos las funciones cuando el componente se monta
onMounted(() => {
  fetchClientes()
  fetchResumenPedidos()
})
</script>

<style scoped>
.clientes-container {
  max-width: 900px;
  margin: 20px auto;
  padding: 30px;
}

.clientes-container h1 {
  text-align: center;
  margin-bottom: 30px;
}

/* Estilo de la tabla de clientes */
.table-responsive {
  overflow-x: auto;
  margin-top: 20px;
}

.clientes-table,
.resumen-pedidos-table {
  width: 100%;
  border-collapse: collapse;
}

.clientes-table th,
.clientes-table td,
.resumen-pedidos-table th,
.resumen-pedidos-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.clientes-table thead th,
.resumen-pedidos-table thead th {
  background-color: #f8f8f8;
  font-weight: bold;
}

/* Estilo de la tabla de resumen de pedidos */
.resumen-pedidos {
  margin-top: 30px;
}

.resumen-pedidos-table {
  margin-top: 20px;
}

.loading-overlay {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 150px;
}

.spinner {
  border: 4px solid #f3f3f3;
  border-top: 4px solid #3498db;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  animation: spin 2s linear infinite;
  margin-right: 10px;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
