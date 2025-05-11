<template>
  <NavBar />
  <div class="auth-container">
    <div class="auth-card">
      <h1 class="auth-title">Iniciar Sesión</h1>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="form.email" required>
        </div>
        <div class="form-group">
          <label for="password">Contraseña:</label>
          <input type="password" id="password" v-model="form.password" required>
        </div>
        <button type="submit" :disabled="loading">
          {{ loading ? 'Cargando...' : 'Iniciar Sesión' }}
        </button>
        <p v-if="error" class="error">{{ error }}</p>
      </form>
    </div>
  </div>
</template>


<script setup>
import NavBar from '@/components/NavBar.vue'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import axios from 'axios'

const form = ref({
  email: '',
  password: ''
})

const loading = ref(false)
const error = ref(null)
const router = useRouter()
const authStore = useAuthStore()

const handleLogin = async () => {
  loading.value = true
  error.value = null

  try {
    const response = await axios.post('http://localhost:8080/api/auth/login', {
      email: form.value.email,
      password: form.value.password
    })

    authStore.setToken(response.data.token)
    router.push({ name: 'dashboard' })
  } catch (err) {
    error.value = err.response?.data?.message || 'Error al iniciar sesión'
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.auth-background {
  min-height: 100vh;
  padding-top: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url('/images/background-home.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  position: relative;
}

.auth-container {
  max-width: 400px;
  margin: 80px auto 0;
  padding: 20px;
  padding: 2rem 1rem;
}

.auth-card {
  background: white;
  border-radius: 12px;
  padding: 2rem;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
}

.login {
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

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.error {
  color: red;
  margin-top: 10px;
}

/* Media queries para responsive */
@media (max-width: 768px) {
  .auth-card {
    padding: 1.5rem;
  }
}
</style>