<script setup>
import { ref, onMounted } from 'vue';

const clientes = ref([]);

onMounted(async () => {
  const usuario = JSON.parse(localStorage.getItem("usuario"));
  const token = usuario?.token;

  if (!token) {
    console.error("Token no encontrado. El usuario debe iniciar sesión.");
    return;
  }

  try {
    const res = await fetch('http://localhost:8080/api/clientes', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (!res.ok) {
      throw new Error(`Error al obtener clientes: ${res.status}`);
    }

    clientes.value = await res.json();
  } catch (err) {
    console.error(err.message);
  }
});
</script>

<template>
  <div>
    <h2>Listado de Clientes</h2>
    <ul>
      <li v-for="cliente in clientes" :key="cliente.id">
        {{ cliente.nombre }} - {{ cliente.rut }}
      </li>
    </ul>
  </div>
</template>
