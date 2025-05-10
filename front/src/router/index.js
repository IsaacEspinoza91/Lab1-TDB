import { createRouter, createWebHistory } from 'vue-router';
import IniciarSesion from "../components/IniciarSesion.vue";
import CrearUsuario from "../components/CrearUsuario.vue";
import Home from "../components/Home.vue";

const routes = [
  // Ruta raíz redirige a /home
  {
    path: '/',
    redirect: '/home'
  },

  // Rutas de autenticación
  {
    path: '/iniciarSesion',
    name: 'iniciarSesion',
    component: IniciarSesion
  },
  {
    path: '/crearUsuario',
    name: 'crearUsuario',
    component: CrearUsuario
  },

  // Nueva ruta principal en /home
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children: [
      {
        path: 'resumen-pedidos-cliente',
        name: 'ResumenPedidosCliente',
        component: () => import('../views/ResumenPedidosCliente.vue'),
        meta: { title: 'Resumen por Cliente' }
      },
      {
        path: 'desempeno-repartidor',
        name: 'DesempenoRepartidor',
        component: () => import('../views/DesempenoRepartidor.vue'),
        meta: { title: 'Desempeño de Repartidores' }
      },
      {
        path: 'farmacias-mayor-volumen',
        name: 'FarmaciasMayorVolumen',
        component: () => import('../views/FarmaciasMayorVolumen.vue'),
        meta: { title: 'Farmacias con Mayor Volumen' }
      }
    ]
  },

  // Redirección para rutas no encontradas
  {
    path: '/:pathMatch(.*)*',
    redirect: '/home'
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;