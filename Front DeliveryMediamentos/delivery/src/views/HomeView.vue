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
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding-top: 60px;
  background-image: url('/images/background-home.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  /* Efecto parallax opcional */
  position: relative;
}

/* Capa oscura semitransparente para mejorar legibilidad */
.main-content::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 0;
}

.welcome-container {
  max-width: 800px;
  padding: 3rem;
  background-color: rgba(255, 255, 255, 0.85);
  border-radius: 12px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
  text-align: center;
  position: relative;
  /* Para que aparezca sobre el pseudo-elemento */
  z-index: 1;
  backdrop-filter: blur(2px);
  /* Efecto de vidrio esmerilado */
}

.welcome-title {
  font-size: clamp(2.5rem, 5vw, 4rem);
  color: #1a237e;
  margin-bottom: 1.5rem;
  font-weight: 700;
}

.welcome-subtitle {
  font-size: 1.5rem;
  color: #333;
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