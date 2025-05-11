<template>
    <div class="farmacias-container">
        <h2>Gestión de Farmacias</h2>

        <div v-if="loading" class="loading-overlay">
            <div class="spinner"></div>
            <p>Cargando farmacias...</p>
        </div>

        <!-- Botón para agregar nueva farmacia -->
        <button @click="showModal = true" class="add-button">
            <i class="fas fa-plus"></i> Agregar Farmacia
        </button>

        <!-- Tabla de farmacias -->
        <div class="table-responsive">
            <table class="farmacias-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Ubicación</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="farmacia in farmacias" :key="farmacia.id">
                        <td>{{ farmacia.id }}</td>
                        <td>{{ farmacia.nombre }}</td>
                        <td>{{ farmacia.lugar }}</td>
                        <td class="actions">
                            <button @click="editFarmacia(farmacia)" class="edit-button">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button @click="confirmDelete(farmacia.id)" class="delete-button">
                                <i class="fas fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Modal para agregar/editar -->
        <div v-if="showModal" class="modal-overlay">
            <div class="modal-content">
                <h3>{{ isEditing ? 'Editar Farmacia' : 'Nueva Farmacia' }}</h3>
                <form @submit.prevent="isEditing ? updateFarmacia() : createFarmacia()">
                    <div class="form-group">
                        <label>Nombre:</label>
                        <input v-model="form.nombre" type="text" required>
                    </div>
                    <div class="form-group">
                        <label>Ubicación:</label>
                        <input v-model="form.lugar" type="text" required>
                    </div>
                    <div class="modal-actions">
                        <button type="button" @click="closeModal" class="cancel-button">Cancelar</button>
                        <button type="submit" class="save-button">
                            {{ isEditing ? 'Actualizar' : 'Guardar' }}
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Modal de confirmación para eliminar -->
        <div v-if="showDeleteModal" class="modal-overlay">
            <div class="modal-content delete-modal">
                <h3>¿Eliminar farmacia?</h3>
                <p>Esta acción no se puede deshacer.</p>
                <div class="modal-actions">
                    <button @click="showDeleteModal = false" class="cancel-button">Cancelar</button>
                    <button @click="deleteFarmacia" class="delete-button">Eliminar</button>
                </div>
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
const farmacias = ref([])
const showModal = ref(false)
const showDeleteModal = ref(false)
const isEditing = ref(false)
const currentFarmaciaId = ref(null)

const form = ref({
    nombre: '',
    lugar: ''
})

// Configuración de Axios con el token JWT
const apix = axios.create({
    baseURL: 'http://localhost:8080/api'
})

apix.interceptors.request.use(config => {
    if (authStore.token) {
        config.headers.Authorization = `Bearer ${authStore.token}`
    }
    return config
})

// Obtener todas las farmacias
const fetchFarmacias = async () => {
    try {
        const response = await api.get('/farmacias') // Usa la instancia configurada
        farmacias.value = response.data
    } catch (error) {
        console.error('Detalles del error:', {
            message: error.message,
            response: error.response,
            request: error.request,
            config: error.config
        })
    } finally {
        loading.value = false
    }
}

// Crear nueva farmacia
const createFarmacia = async () => {
    try {
        await api.post('/farmacias', form.value)
        await fetchFarmacias()
        closeModal()
        alert('Farmacia creada exitosamente')
    } catch (error) {
        console.error('Error al crear farmacia:', error)
        alert('Error al crear farmacia')
    }
}

// Editar farmacia
const editFarmacia = (farmacia) => {
    form.value = {
        nombre: farmacia.nombre,
        lugar: farmacia.lugar
    }
    currentFarmaciaId.value = farmacia.id
    isEditing.value = true
    showModal.value = true
}

// Actualizar farmacia
const updateFarmacia = async () => {
    try {
        await api.put(`/farmacias/${currentFarmaciaId.value}`, form.value)
        await fetchFarmacias()
        closeModal()
        alert('Farmacia actualizada exitosamente')
    } catch (error) {
        console.error('Error al actualizar farmacia:', error)
        alert('Error al actualizar farmacia')
    }
}

// Confirmar eliminación
const confirmDelete = (id) => {
    currentFarmaciaId.value = id
    showDeleteModal.value = true
}

// Eliminar farmacia
const deleteFarmacia = async () => {
    try {
        await api.delete(`/farmacias/${currentFarmaciaId.value}`)
        await fetchFarmacias()
        showDeleteModal.value = false
        alert('Farmacia eliminada exitosamente')
    } catch (error) {
        console.error('Error al eliminar farmacia:', error)
        alert('Error al eliminar farmacia')
    }
}

// Cerrar modal
const closeModal = () => {
    showModal.value = false
    isEditing.value = false
    form.value = { nombre: '', lugar: '' }
    currentFarmaciaId.value = null
}

// Cargar farmacias al montar el componente
onMounted(() => {
    fetchFarmacias()
})
</script>

<style scoped>
.farmacias-container {
    padding: 20px;
    max-width: 1200px;
    margin: 0 auto;
}

.add-button {
    background-color: #1a237e;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-bottom: 20px;
    display: flex;
    align-items: center;
    gap: 8px;
}

.add-button:hover {
    background-color: #303f9f;
}

.farmacias-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

.farmacias-table th,
.farmacias-table td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: left;
}

.farmacias-table th {
    background-color: #f2f2f2;
    font-weight: bold;
}

.farmacias-table tr:nth-child(even) {
    background-color: #f9f9f9;
}

.farmacias-table tr:hover {
    background-color: #f1f1f1;
}

.actions {
    display: flex;
    gap: 10px;
}

.edit-button,
.delete-button {
    padding: 6px 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
}

.edit-button {
    background-color: #ffc107;
    color: #212529;
}

.edit-button:hover {
    background-color: #e0a800;
}

.delete-button {
    background-color: #dc3545;
    color: white;
}

.loading-overlay {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(255, 255, 255, 0.8);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}

.spinner {
    border: 5px solid #f3f3f3;
    border-top: 5px solid #1a237e;
    border-radius: 50%;
    width: 50px;
    height: 50px;
    animation: spin 1s linear infinite;
}

.delete-button:hover {
    background-color: #c82333;
}

/* Estilos para los modales */
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
    padding: 25px;
    border-radius: 8px;
    width: 100%;
    max-width: 500px;
}

.delete-modal {
    max-width: 400px;
    text-align: center;
}

.form-group {
    margin-bottom: 15px;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

.form-group input {
    width: 100%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
}

.modal-actions {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
    margin-top: 20px;
}

.cancel-button,
.save-button {
    padding: 8px 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
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
</style>