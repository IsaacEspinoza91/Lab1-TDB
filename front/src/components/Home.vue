<script setup>
// Puedes agregar lógica aquí si es necesario
</script>

<template>
  <div class="home">
    <!-- Navbar -->
    <nav class="navbar">
      <router-link to="/home">Inicio</router-link>
      <router-link to="/home/resumen-pedidos-cliente">Resumen Pedidos por Cliente</router-link>
      <router-link to="/home/desempeno-repartidor">Desempeño por Repartidor</router-link>
      <router-link to="/home/farmacias-mayor-volumen">Farmacias con Mayor Volumen</router-link>

      <!-- Nuevos enlaces de autenticación -->
      <div style="margin-left: auto;">
        <router-link to="/iniciarSesion">Iniciar Sesión</router-link>
        <router-link to="/crearUsuario">Crear Usuario</router-link>
      </div>
    </nav>

    <!-- Contenido principal -->
    <main>
      <h1>Sistema de Delivery de Medicamentos</h1>
      <router-view></router-view>
    </main>
  </div>
</template>

<style scoped>
.home {
  font-family: Arial, sans-serif;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.navbar {
  background-color: #2c3e50;
  padding: 1rem;
  display: flex;
  gap: 1rem;
  margin-bottom: 2rem;
  border-radius: 8px;
  align-items: center;
}

.navbar a {
  text-decoration: none;
  color: #ecf0f1;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: all 0.3s ease;
  white-space: nowrap;
}

.navbar a:hover {
  background-color: #34495e;
  transform: translateY(-2px);
}

.navbar a.router-link-exact-active {
  background-color: #42b983;
  color: white;
  font-weight: bold;
}

main {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

h1 {
  color: #2c3e50;
  margin-bottom: 20px;
}
</style>