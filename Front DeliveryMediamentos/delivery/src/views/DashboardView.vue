<template>
  <NavBar />
  <div class="dashboard-container">
    <h1>Dashboard</h1>
    <p>Bienvenido a tu área privada</p>
    <p>Tu token JWT es: {{ authStore.token }}</p>
  </div>
</template>

<script setup>
import NavBar from '@/components/NavBar.vue'
import { useAuthStore } from '@/stores/auth'

const authStore = useAuthStore()
</script>

<style scoped>
.dashboard-container {
  max-width: 800px;
  margin: 80px auto 0;
  padding: 20px;
}

.dashboard {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}
</style>