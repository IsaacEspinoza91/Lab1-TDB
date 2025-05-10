<template>
  <div class="form-container">
    <h2>Crear Usuario</h2>
    <form @submit.prevent="crearUsuario">
      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="nuevoUsuario.nombre" required />
      </div>

      <div class="form-group">
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" v-model="nuevoUsuario.apellido" required />
      </div>

      <div class="form-group">
        <label for="rut">RUT:</label>
        <input type="text" id="rut" v-model="nuevoUsuario.rut" required />
      </div>

      <div class="form-group">
        <label for="email">Correo electrónico:</label>
        <input type="email" id="email" v-model="nuevoUsuario.email" required />
      </div>

      <div class="form-group">
        <label for="telefono">Celular:</label>
        <input type="text" id="telefono" v-model="nuevoUsuario.telefono" required @input="formatearCelular" />
      </div>

      <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" v-model="nuevoUsuario.password" required />
      </div>

      <div class="form-group">
        <label for="tipo">Rol:</label>
        <select id="tipo" v-model="nuevoUsuario.tipo" required>
          <option value="CLIENTE">Cliente</option>
          <option value="ADMIN">Administrador</option>
          <option value="REPARTIDOR">Repartidor</option>
        </select>
      </div>

      <button type="submit">Crear Usuario</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      nuevoUsuario: {
        nombre: "",
        apellido: "",
        rut: "",
        email: "",
        telefono: "",
        password: "",
        tipo: "CLIENTE",
      },
    };
  },
  methods: {
    formatearCelular(event) {
      let celular = event.target.value.replace(/[^+\d]/g, "");
      if (!celular.startsWith("+")) {
        celular = "+" + celular;
      }
      if (celular.length > 15) {
        celular = celular.slice(0, 15);
      }
      this.nuevoUsuario.telefono = celular;
    },
    async crearUsuario() {
      try {
        //validar celular
        const celularRegex = /^\+569\d{8}$/;
        if (!celularRegex.test(this.nuevoUsuario.telefono)) {
          alert("El número de celular debe tener el formato +569XXXXXXXX");
          return;
        }

        //enviar datos al backend
        const response = await axios.post("http://localhost:8080/api/usuarios", this.nuevoUsuario);

        if (response.data.includes("El correo ya está registrado")) {
          alert(response.data);
        } else {
          alert("Usuario creado con éxito.");
          this.limpiarFormulario();
          this.$router.push("/iniciarSesion");
        }
      } catch (error) {
        console.error("Error al crear usuario:", error);
        alert("Error: " + error.message);
      }
    },
    limpiarFormulario() {
      this.nuevoUsuario = {
        nombre: "",
        apellido: "",
        rut: "",
        email: "",
        telefono: "",
        password: "",
        tipo: "CLIENTE",
      };
    },
  },
};
</script>

<style scoped>
.form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
  padding: 20px;
  max-width: 400px;
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 5px;
}

h2 {
  margin-bottom: 20px;
}

form {
  width: 100%;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

input,
select {
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px;
  font-size: 16px;
  color: white;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
}

button:hover {
  background-color: #0056b3;
}
</style>
