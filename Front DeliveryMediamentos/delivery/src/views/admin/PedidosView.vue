<template>
  <div class="pedidos-container">
    <h1>Gestión de Pedidos</h1>

    <div v-if="loadingPedidos" class="loading-overlay">
      <div class="spinner"></div>
      <p>Cargando pedidos...</p>
    </div>

    <button @click="showModal = true" class="add-button" v-if="!loadingPedidos">
      <i class="fas fa-plus"></i> Agregar Pedido
    </button>
    <!-- Tabla de Pedidos -->
    <div class="table-responsive" v-if="!loadingPedidos">

      <div class="table-responsive">
        <table class="pedidos-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Fecha</th>
              <th>Urgencia</th>
              <th>Total Pagado</th>
              <th>Estado de Entrega</th>
              <th>Fecha de Entrega</th>
              <th>Cliente</th>
              <th>Medio de Pago</th>
              <th>Farmacia</th>
              <th>Repartidor</th>
              <th>Alerta</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="pedido in pedidos" :key="pedido.id"
              :class="{ 'pedido-con-alerta': pedidosConAlerta.includes(pedido.id) }">
              <td>{{ pedido.id }}</td>
              <td>{{ pedido.fecha }}</td>
              <td>{{ pedido.urgencia ? 'Sí' : 'No' }}</td>
              <td>{{ pedido.total_pagado }}</td>
              <td>{{ pedido.estado_entrega }}</td>
              <td>{{ pedido.fecha_entrega }}</td>
              <td>{{ pedido.cliente_id }}</td>
              <td>{{ pedido.medio_pago_id }}</td>
              <td>{{ pedido.farmacia_id }}</td>
              <td>{{ pedido.repartidor_id }}</td>
              <td>
                <span v-if="pedidosConAlerta.includes(pedido.id)" class="alerta-text">Si</span>
              </td>
              <td class="actions">
                <button @click="editPedido(pedido)" class="edit-button">
                  <i class="fas fa-edit">Modificar</i>
                </button>
                <button @click="confirmDelete(pedido.id)" class="delete-button">
                  <i class="fas fa-trash">Eliminar</i>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <!-- Tabla de Pedidos -->


      <div v-if="showModal" class="modal-overlay">
        <div class="modal-content">
          <h3>{{ isEditing ? 'Editar Pedido' : 'Nuevo Pedido' }}</h3>
          <form @submit.prevent="isEditing ? updatePedido() : createPedido()">
            <div class="form-group">
              <label>Fecha:</label>
              <input v-model="form.fecha" type="date" required />
            </div>
            <div class="form-group">
              <label>Urgencia:</label>
              <input v-model="form.urgencia" type="checkbox" />
            </div>
            <div class="form-group">
              <label>Total Pagado:</label>
              <input v-model="form.total_pagado" type="number" required />
            </div>
            <div class="form-group">
              <label>Estado de Entrega:</label>
              <input v-model="form.estado_entrega" type="text" required />
            </div>
            <div class="form-group">
              <label>Fecha de Entrega:</label>
              <input v-model="form.fecha_entrega" type="date" required />
            </div>
            <div class="form-group">
              <label>Cliente ID:</label>
              <input v-model="form.cliente_id" type="number" required />
            </div>
            <div class="form-group">
              <label>Medio de Pago ID:</label>
              <input v-model="form.medio_pago_id" type="number" required />
            </div>
            <div class="form-group">
              <label>Farmacia ID:</label>
              <input v-model="form.farmacia_id" type="number" required />
            </div>
            <div class="form-group">
              <label>Repartidor ID:</label>
              <input v-model="form.repartidor_id" type="number" required />
            </div>
            <div class="modal-actions">
              <button type="button" @click="closeModal" class="cancel-button">Cancelar</button>
              <button type="submit" class="save-button">{{
                isEditing ? 'Actualizar' : 'Guardar'
                }}</button>
            </div>
          </form>
        </div>
      </div>

      <div v-if="showDeleteModal" class="modal-overlay">
        <div class="modal-content delete-modal">
          <h3>¿Eliminar pedido?</h3>
          <p>Esta acción no se puede deshacer.</p>
          <div class="modal-actions">
            <button @click="showDeleteModal = false" class="cancel-button">Cancelar</button>
            <button @click="deletePedido" class="delete-button">Eliminar</button>
          </div>
        </div>
      </div>

      <hr v-if="!loadingPedidos" style="margin: 30px 0;">
      <hr style="margin: 30px 0" />

      <div class="medio-pago-urgente-section" v-if="!loadingPedidos">
        <h2>Medio de Pago Más Usado en Pedidos Urgentes</h2>
        <div v-if="loadingMedioPagoUrgente" class="loading-overlay-inline">
          <div class="spinner-small"></div>
          <p>Cargando datos...</p>
        </div>
        <div v-if="medioPagoUrgenteData" class="table-responsive">
          <table class="medio-pago-urgente-table">
            <thead>
              <tr>
                <th>Medio de Pago</th>
                <th>Cantidad de Usos</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{{ medioPagoUrgenteData.medio_pago }}</td>
                <td>{{ medioPagoUrgenteData.cantidad_usos }}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <p v-if="medioPagoUrgenteError" class="error-message">
          Error al cargar el medio de pago más usado.
        </p>
        <p v-if="
          !loadingMedioPagoUrgente &&
          !medioPagoUrgenteData &&
          !medioPagoUrgenteError
        ">
          No hay datos disponibles sobre el medio de pago más usado en urgencias.
        </p>
      </div>
      <hr v-if="!loadingPedidos" style="margin: 30px 0;">
      <hr style="margin: 30px 0" />

      <div class="cliente-mayor-gasto-section" v-if="!loadingPedidos">
        <h2>Cliente con Mayor Gasto en Pedidos Entregados</h2>
        <div v-if="loadingClienteMayorGasto" class="loading-overlay-inline">
          <div class="spinner-small"></div>
          <p>Cargando datos...</p>
        </div>
        <div v-if="clienteMayorGastoData" class="table-responsive">
          <table class="cliente-mayor-gasto-table">
            <thead>
              <tr>
                <th>ID Cliente</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Total Gastado</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{{ clienteMayorGastoData.cliente_id }}</td>
                <td>{{ clienteMayorGastoData.nombre }}</td>
                <td>{{ clienteMayorGastoData.apellido }}</td>
                <td>{{ clienteMayorGastoData.totalgastado }}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <p v-if="clienteMayorGastoError" class="error-message">
          Error al cargar el cliente con mayor gasto.
        </p>
        <p v-if="
          !loadingClienteMayorGasto &&
          !clienteMayorGastoData &&
          !clienteMayorGastoError
        ">
          No hay datos disponibles sobre el cliente con mayor gasto.
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useAuthStore } from '@/stores/auth'
import axios from 'axios'
import api from '@/api'

const authStore = useAuthStore()
const pedidos = ref([])
const loadingPedidos = ref(true)
const showModal = ref(false)
const showDeleteModal = ref(false)
const isEditing = ref(false)
const currentPedidoId = ref(null)

const form = ref({
  fecha: '',
  urgencia: false,
  total_pagado: '',
  estado_entrega: '',
  fecha_entrega: '',
  cliente_id: '',
  medio_pago_id: '',
  farmacia_id: '',
  repartidor_id: '',
})

const medioPagoUrgenteData = ref(null)
const loadingMedioPagoUrgente = ref(true)
const medioPagoUrgenteError = ref(null)

const clienteMayorGastoData = ref(null)
const loadingClienteMayorGasto = ref(true)
const clienteMayorGastoError = ref(null)

const pedidosConAlerta = ref([])
const loadingAlertas = ref(false)
const errorAlertas = ref(null)

const fetchPedidosConAlerta = async () => {
  loadingAlertas.value = true
  pedidosConAlerta.value = []
  errorAlertas.value = null
  try {
    const response = await api.get('/pedidos/pedidos-con-alerta')
    pedidosConAlerta.value = response.data
    console.log('Pedidos con alerta:', pedidosConAlerta.value)
  } catch (error) {
    console.error('Error al obtener pedidos con alerta:', error)
    errorAlertas.value = 'Error al cargar las alertas de pedidos.'
  } finally {
    loadingAlertas.value = false
  }
}

const fetchMedioPagoUrgente = async () => {
  loadingMedioPagoUrgente.value = true
  medioPagoUrgenteData.value = null
  medioPagoUrgenteError.value = null
  try {
    const response = await api.get('/pedidos/medio-pago-urgente')
    medioPagoUrgenteData.value = response.data
  } catch (error) {
    console.error('Error al obtener el medio de pago más usado:', error)
    medioPagoUrgenteError.value = 'Hubo un error al cargar los datos.'
  } finally {
    loadingMedioPagoUrgente.value = false
  }
}

const fetchClienteMayorGasto = async () => {
  loadingClienteMayorGasto.value = true
  clienteMayorGastoData.value = null
  clienteMayorGastoError.value = null
  try {
    const response = await api.get('/pedidos/cliente-mas-gastador')
    clienteMayorGastoData.value = response.data
  } catch (error) {
    console.error('Error al obtener el cliente con mayor gasto:', error)
    clienteMayorGastoError.value =
      'Hubo un error al cargar los datos del cliente con mayor gasto.'
  } finally {
    loadingClienteMayorGasto.value = false
  }
}

// Obtener pedidos
const fetchPedidos = async () => {
  try {
    const response = await api.get('/pedidos')
    pedidos.value = response.data
  } catch (error) {
    console.error('Error al obtener pedidos:', error)
  } finally {
    loadingPedidos.value = false
  }
}

// Crear pedido
const createPedido = async () => {
  try {
    await api.post('/pedidos', form.value)
    await fetchPedidos()
    closeModal()
    alert('Pedido creado exitosamente')
  } catch (error) {
    console.error('Error al crear pedido:', error)
    alert('Error al crear pedido')
  }
}

// Editar pedido
const editPedido = (pedido) => {
  form.value = {
    fecha: pedido.fecha,
    urgencia: pedido.urgencia,
    total_pagado: pedido.total_pagado,
    estado_entrega: pedido.estado_entrega,
    fecha_entrega: pedido.fecha_entrega,
    cliente_id: pedido.cliente_id,
    medio_pago_id: pedido.medio_pago_id,
    farmacia_id: pedido.farmacia_id,
    repartidor_id: pedido.repartidor_id,
  }
  currentPedidoId.value = pedido.id
  isEditing.value = true
  showModal.value = true
}

// Actualizar pedido
const updatePedido = async () => {
  try {
    await api.put(`/pedidos/${currentPedidoId.value}`, form.value)
    await fetchPedidos()
    closeModal()
    alert('Pedido actualizado exitosamente')
  } catch (error) {
    console.error('Error al actualizar pedido:', error)
    alert('Error al actualizar pedido')
  }
}

// Confirmar eliminación
const confirmDelete = (id) => {
  currentPedidoId.value = id
  showDeleteModal.value = true
}

// Eliminar pedido
const deletePedido = async () => {
  try {
    await api.delete(`/pedidos/${currentPedidoId.value}`)
    await fetchPedidos()
    showDeleteModal.value = false
    alert('Pedido eliminado exitosamente')
  } catch (error) {
    console.error('Error al eliminar pedido:', error)
    alert('Error al eliminar pedido')
  }
}

// Cerrar modal
const closeModal = () => {
  showModal.value = false
  isEditing.value = false
  form.value = {
    fecha: '',
    urgencia: false,
    total_pagado: '',
    estado_entrega: '',
    fecha_entrega: '',
    cliente_id: '',
    medio_pago_id: '',
    farmacia_id: '',
    repartidor_id: '',
  }
  currentPedidoId.value = null
}

// Cargar datos al montar el componente
onMounted(async () => {
  await Promise.all([
    fetchPedidos(),
    fetchMedioPagoUrgente(),
    fetchClienteMayorGasto(),
    fetchPedidosConAlerta(),
  ])
})
</script>

<style scoped>
.pedidos-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 30px;
}

.pedidos-container h2 {
  text-align: center;
  margin-bottom: 40px;
}

.loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.spinner {
  border: 4px solid #f3f3f3;
  border-top: 4px solid #3498db;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}

.add-button {
  background-color: #1a237e;
  color: white;
  padding: 12px 18px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-bottom: 30px;
  display: inline-flex;
  align-items: center;
  gap: 8px;
}

.table-responsive {
  overflow-x: auto;
}

.pedidos-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 30px;
  border-spacing: 0 10px;
}

.pedidos-table th,
.pedidos-table td {
  padding: 10px 15px;
  text-align: left;
  border-bottom: 1px solid #f0f0f0;
}

.pedidos-table thead th {
  background-color: #f8f8f8;
  font-weight: bold;
  padding: 12px 10px;
  border-bottom: 2px solid #ccc;
}

.pedidos-table tbody tr {
  background-color: white;
  border-radius: 4px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
}

.pedidos-table tbody tr:last-child td {
  border-bottom: none;
}

.pedidos-table th:nth-child(2),
/* Fecha */
.pedidos-table td:nth-child(2) {
  min-width: 120px;
}

.pedidos-table th:nth-child(6),
/* Fecha de Entrega */
.pedidos-table td:nth-child(6) {
  min-width: 120px;
}

.actions {
  display: flex;
  gap: 10px;
}

.edit-button,
.delete-button {
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  border: none;
  font-size: 0.9em;
}

.edit-button {
  background-color: #ffc107;
  color: #212529;
}

.edit-button:hover {
  background-color: #e0a800;
}

.delete-button {
  background-color: #f44336;
  color: white;
}

.delete-button:hover {
  background-color: #e53935;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  width: 100%;
  max-width: 600px;
}

.modal-content h3 {
  margin-top: 0;
  margin-bottom: 20px;
  text-align: center;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 6px;
  font-weight: bold;
  font-size: 0.95em;
}

.form-group input[type="text"],
.form-group input[type="number"],
.form-group input[type="date"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
  font-size: 0.9em;
}

.form-group input[type="checkbox"] {
  margin-right: 8px;
}

.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 15px;
}

.cancel-button,
.save-button {
  padding: 10px 15px;
  border-radius: 4px;
  cursor: pointer;
  border: none;
}

.cancel-button {
  background-color: #6c757d;
  color: white;
}

.cancel-button:hover {
  background-color: #5a6268;
}

.save-button {
  background-color: #28a745;
  color: white;
}

.save-button:hover {
  background-color: #218838;
}

.delete-modal {
  background-color: #f9f9f9;
  border-radius: 8px;
  padding: 30px;
  text-align: center;
}

.delete-modal h3 {
  margin-top: 0;
  margin-bottom: 15px;
}

.delete-modal p {
  margin-bottom: 20px;
  font-size: 1.1em;
}

.delete-modal .modal-actions {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.medio-pago-urgente-section {
  margin-top: 30px;
  padding: 20px;
  border: 1px solid #eee;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.medio-pago-urgente-section h2 {
  margin-top: 0;
  margin-bottom: 20px;
  text-align: center;
}

.medio-pago-urgente-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.medio-pago-urgente-table th,
.medio-pago-urgente-table td {
  padding: 10px 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.medio-pago-urgente-table thead th {
  background-color: #f8f8f8;
  font-weight: bold;
}

.medio-pago-urgente-table tbody tr:last-child td {
  border-bottom: none;
}

.loading-overlay-inline {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 15px;
}

.spinner-small {
  border: 3px solid #f3f3f3;
  border-top: 3px solid #3498db;
  border-radius: 50%;
  width: 25px;
  height: 25px;
  animation: spin 1.5s linear infinite;
  margin-right: 10px;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}

.error-message {
  color: red;
  margin-top: 10px;
  text-align: center;
}

.cliente-mayor-gasto-section {
  margin-top: 30px;
  padding: 20px;
  border: 1px solid #eee;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.cliente-mayor-gasto-section h2 {
  margin-top: 0;
  margin-bottom: 20px;
  text-align: center;
}

.cliente-mayor-gasto-table {
  width: 100%;
  border-collapse: collapse;
}

.cliente-mayor-gasto-table th,
.cliente-mayor-gasto-table td {
  padding: 10px 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.cliente-mayor-gasto-table thead th {
  background-color: #f8f8f8;
  font-weight: bold;
}

.pedido-con-alerta {
  background-color: #ffdddd !important;
}

.alerta-text {
  color: red;
  font-weight: bold;
}
</style>