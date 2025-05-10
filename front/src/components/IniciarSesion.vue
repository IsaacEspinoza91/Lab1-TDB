<template>
  <div class="form-container">
    <h2>Login</h2>
    <form @submit.prevent="iniciarSesion">
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="usuarioLogin.email" required />
      </div>
      <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" v-model="usuarioLogin.password" required />
      </div>
      <button type="submit">Iniciar Sesión</button>
    </form>

    <button @click="redirigirRegistro" class="boton-registrar">Registrarse</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      usuarioLogin: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    async iniciarSesion() {
      try {
        const response = await axios.post("http://localhost:8080/api/usuarios/iniciarSesion", null, {
          params: {
            email: this.usuarioLogin.email,
            password: this.usuarioLogin.password,
          },
        });

        console.log("Respuesta del backend:", response.data);

        if (response.data) {
          const usuario = {
            id: response.data.id,
            tipo: response.data.tipo,
            email: this.usuarioLogin.email,
          };

          if (!usuario.id) {
            alert("Error al procesar el inicio de sesion. Falta la ID del usuario.");
            return;
          }

          alert("Sesion iniciada correctamente.");
          localStorage.setItem("usuario", JSON.stringify(usuario));
          window.location.href = "/";
        }
      } catch (error) {
        console.error("Error al iniciar sesion:", error);
        alert("Error al iniciar sesion. Verifica tus credenciales.");
      }
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

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

input {
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
  margin-top: 10px;
}

button:hover {
  background-color: #0056b3;
}

.boton-registrar {
  background-color: #28a745;
  margin-top: 10px;
}

.boton-registrar:hover {
  background-color: #218838;
}
</style>
