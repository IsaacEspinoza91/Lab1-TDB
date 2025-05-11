<template>
    <div class="dashboard">
        <h1>Resumen del Sistema</h1>
        <div class="stats-grid">
            <router-link to="/admin/usuarios" class="stat-card clickable-card">
                <h3>Usuarios</h3>
                <div v-if="loading" class="loading-indicator">
                    <div class="loader"></div>
                    <span>Cargando...</span>
                </div>
                <p v-else>{{ usuariosCount }}</p>
            </router-link>

            <router-link to="/admin/farmacias" class="stat-card clickable-card">
                <h3>Farmacias</h3>
                <div v-if="loading" class="loading-indicator">
                    <div class="loader"></div>
                    <span>Cargando...</span>
                </div>
                <p v-else>{{ farmaciasCount }}</p>
            </router-link>

            <router-link to="/admin/pedidos" class="stat-card clickable-card">
                <h3>Pedidos del Mes</h3>
                <div v-if="loading" class="loading-indicator">
                    <div class="loader"></div>
                    <span>Cargando...</span>
                </div>
                <p v-else>{{ pedidosMesCount }}</p>
            </router-link>

            <router-link to="/admin/productos" class="stat-card clickable-card">
                <h3>Productos</h3>
                <div v-if="loading" class="loading-indicator">
                    <div class="loader"></div>
                    <span>Cargando...</span>
                </div>
                <p v-else>{{ productosCount }}</p>
            </router-link>
        </div>
    </div>
</template>


<script setup>
import { ref, onMounted } from 'vue'
import api from '@/api'

const farmaciasCount = ref(0)
const usuariosCount = ref(0)
const productosCount = ref(0)
const pedidosMesCount = ref(0)
const loading = ref(true)


const fetchData = async () => {
    try {
        loading.value = true

        // Ejecutar ambas peticiones en paralelo
        const [farmaciasRes, usuariosRes, productosRes, pedidosRes] = await Promise.all([
            api.get('/farmacias/contar'),
            api.get('/usuarios/contar'),
            api.get('/productos/contar'),
            api.get('/pedidos/contar-mes')
        ])

        farmaciasCount.value = farmaciasRes.data.count
        usuariosCount.value = usuariosRes.data.count
        productosCount.value = productosRes.data.count
        pedidosMesCount.value = pedidosRes.data.count

    } catch (error) {
        console.error('Error al cargar datos:', error)
        // Puedes manejar errores individuales aquí si lo prefieres
    } finally {
        loading.value = false
    }
}


onMounted(() => {
    //fetchFarmaciasCount()
    //fetchUsuariosCount()
    //fetchProductosCount()
    fetchData()
})
</script>

<style scoped>
.stats-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 1.5rem;
    margin-top: 2rem;
}

.stat-card {
    background: white;
    padding: 1.5rem;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
    transition: transform 0.3s;
}

.clickable-card {
    cursor: pointer;
    text-decoration: none;
    color: inherit;
}

.clickable-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    background-color: #f8f9fa;
}

.stat-card:hover {
    transform: translateY(-5px);
}

.stat-card h3 {
    color: #555;
    margin-bottom: 0.5rem;
    font-size: 1.2rem;
}

.stat-card p {
    font-size: 2.5rem;
    font-weight: bold;
    color: #1a237e;
    margin: 1rem 0;
}

.stat-link {
    display: inline-block;
    padding: 0.5rem 1rem;
    background-color: #1a237e;
    color: white;
    text-decoration: none;
    border-radius: 4px;
    font-size: 0.9rem;
    transition: background-color 0.3s;
}

.stat-link:hover {
    background-color: #303f9f;
}
</style>