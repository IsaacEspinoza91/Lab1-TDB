<template>
  <NavBar />
  <div class="auth-container">
    <h1>Registro</h1>
    <form @submit.prevent="handleRegister">
      <div class="form-group">
        <label for="rut">RUT:</label>
        <input type="text" id="rut" v-model="form.rut" required>
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
        </select>
      </div>
      <button type="submit" :disabled="loading">
        {{ loading ? 'Cargando...' : 'Registrarse' }}
      </button>
      <p v-if="error" class="error">{{ error }}</p>
    </form>
  </div>
</template>

<script setup>
import NavBar from '@/components/NavBar.vue'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import axios from 'axios'

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
const router = useRouter()
const authStore = useAuthStore()

const handleRegister = async () => {
  loading.value = true
  error.value = null

  try {
    const response = await axios.post('http://localhost:8080/api/auth/register', form.value)

    authStore.setToken(response.data.token)
    router.push({ name: 'dashboard' })
  } catch (err) {
    error.value = err.response?.data?.message || 'Error al registrarse'
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.auth-container {
  max-width: 500px;
  margin: 80px auto 0;
  padding: 20px;
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