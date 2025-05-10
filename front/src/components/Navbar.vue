<template>
  <nav>
    <ul>
      <li><router-link to="/">Inicio</router-link></li>
      <li><router-link to="/pedidos">Pedidos</router-link></li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'Navbar',
};
</script>

<style scoped>
  nav {
    background-color: #333;
    padding: 10px;
  }

  ul {
    list-style-type: none;
    display: flex;
    gap: 20px;
  }

  li {
    font-size: 18px;
  }

  a {
    color: white;
    text-decoration: none;
  }

  a:hover {
    text-decoration: underline;
  }
</style>
