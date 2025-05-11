<template>
  <NavBar />
  <div class="auth-container">
    <div v-if="showSuccess" class="success-message">
      <h3>¡Registro exitoso!</h3>
      <p>Tu cuenta ha sido creada correctamente.</p>
      <router-link to="/login" class="success-link">Ir a Iniciar Sesión</router-link>
    </div>

    <div v-else class="auth-card">
      <h1 class="auth-title">Registro</h1>
      <form @submit.prevent="handleRegister">
        <div class="form-group">
          <label for="rut">RUT:</label>
          <input
              type="text"
              id="rut"
              v-model="form.rut"
              @input="formatRut"
              maxlength="10"
              placeholder="12345678-9"
              pattern="[0-9-]+"
              required
          >
        </div>
        <div class="form-group">
          <label for="nombre">Nombre:</label>
          <input type="text" id="nombre" v-model="form.nombre" required>
        </div>
        <div class="form-group">
          <label for="apellido">Apellido:</label>
          <input type="text" id="apellido" v-model="form.apellido" required>
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="form.email" required>
        </div>
        <div class="form-group">
          <label for="telefono">Teléfono:</label>
          <input type="tel" id="telefono" v-model="form.telefono" required>
        </div>
        <div class="form-group">
          <label for="password">Contraseña:</label>
          <input type="password" id="password" v-model="form.password" required>
        </div>
        <div class="form-group">
          <label for="tipo">Tipo:</label>
          <select id="tipo" v-model="form.tipo" required>
            <option value="CLIENTE">Cliente</option>
            <option value="ADMIN">Administrador</option>
            <option value="REPARTIDOR">Repartidor</option>
          </select>
        </div>

        <button type="submit" :disabled="loading">
          {{ loading ? 'Registrando...' : 'Registrarse' }}
        </button>
        <p v-if="error" class="error">{{ error }}</p>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import axios from 'axios'
import NavBar from '@/components/NavBar.vue'

const form = ref({
  rut: '',
  nombre: '',
  apellido: '',
  email: '',
  telefono: '',
  password: '',
  tipo: 'CLIENTE'
})

const loading = ref(false)
const error = ref(null)
const showSuccess = ref(false)
const router = useRouter()
const authStore = useAuthStore()

const formatRut = (event) => {
  // Solo permite números y guión
  form.value.rut = event.target.value.replace(/[^0-9-]/g, '');

  // Limita a 10 caracteres
  if (form.value.rut.length > 10) {
    form.value.rut = form.value.rut.substring(0, 10);
  }
}

const handleRegister = async () => {
  loading.value = true
  error.value = null

  try {
    const response = await axios.post('http://localhost:8080/api/auth/register', form.value)

    // Mostrar mensaje de éxito en lugar de redirigir directamente
    showSuccess.value = true

    // Limpiar el formulario
    form.value = {
      rut: '',
      nombre: '',
      apellido: '',
      email: '',
      telefono: '',
      password: '',
      tipo: 'CLIENTE'
    }
  } catch (err) {
    error.value = err.response?.data?.message || 'Error al registrarse'
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.success-message {
  background-color: white;
  padding: 2rem;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.success-message h3 {
  color: #2e7d32;
  font-size: 1.5rem;
  margin-bottom: 1rem;
}

.success-link {
  display: inline-block;
  margin-top: 1.5rem;
  padding: 0.75rem 1.5rem;
  background-color: #1a237e;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.success-link:hover {
  background-color: #303f9f;
}

.auth-container {
  max-width: 500px;
  margin: 80px auto 0;
  padding: 20px;
}

.auth-card {
  background: white;
  border-radius: 12px;
  padding: 2rem;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
}

.register {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 15px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 5px;
}

input,
select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>