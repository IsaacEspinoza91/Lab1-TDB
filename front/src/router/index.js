import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/Home.vue';
import Pedidos from '@/views/Pedidos.vue';
import IniciarSesion from "../components/IniciarSesion.vue";

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
  },
  {
    path: '/pedidos',
    name: 'pedidos',
    component: Pedidos,
  },
  {
    path: '/iniciarSesion',
    name: 'iniciarSesion',
    component: IniciarSesion,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
