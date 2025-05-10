import { createRouter, createWebHistory } from 'vue-router';
import IniciarSesion from "../components/IniciarSesion.vue";
import CrearUsuario from "../components/CrearUsuario.vue";

const routes = [
  {
    path: '/iniciarSesion',
    name: 'iniciarSesion',
    component: IniciarSesion,
  },
  {
    path: '/crearUsuario',
    name: 'crearUsuario',
    component: CrearUsuario,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
