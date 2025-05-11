<script setup>
import { ref, onMounted } from 'vue';

const pedidos = ref([]);

onMounted(async () => {
  const res = await fetch('http://localhost:8080/api/pedidos');
  pedidos.value = await res.json();
});
</script>

<template>
  <div>
    <h2>Listado de Pedidos</h2>
    <ul>
      <li v-for="pedido in pedidos" :key="pedido.id">
        Pedido #{{ pedido.id }} - Cliente ID: {{ pedido.idCliente }} - Estado: {{ pedido.estado }}
      </li>
    </ul>
  </div>
</template>
