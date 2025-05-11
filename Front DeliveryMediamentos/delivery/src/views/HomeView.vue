<template>
  <NavBar />
  <main class="main-content">
    <div class="welcome-container">
      <h1 class="welcome-title">Bienvenido al sistema</h1>
      <p class="welcome-subtitle">Gestión integral de pedidos farmacéuticos</p>
    </div>
  </main>
</template>

<script setup>
import NavBar from '@/components/NavBar.vue'
</script>

<style scoped>
.main-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  text-align: center;
  padding-top: 60px;
  background-color: #f5f5f5;
  background: linear-gradient(135deg, #f5f7fa 0%, #e4e8eb 100%);
}

.welcome-container {
  max-width: 800px;
  padding: 2rem;
  background: white;
  border-radius: 12px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.05);
}

.welcome-title {
  font-size: 3.5rem;
  color: #1a237e;
  margin-bottom: 1rem;
  font-weight: 700;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
  animation: fadeIn 1s ease-in-out;
}

.welcome-subtitle {
  font-size: 1.5rem;
  color: #555;
  max-width: 600px;
  line-height: 1.6;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }

  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>