import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

// Vistas principales
const HomeView = () => import('@/views/HomeView.vue')
const LoginView = () => import('@/views/LoginView.vue')
const RegisterView = () => import('@/views/RegisterView.vue')

// Vistas de administración
const AdminView = () => import('@/views/AdminView.vue')
const AdminDashboard = () => import('@/views/admin/DashboardView.vue')
const UsuariosView = () => import('@/views/admin/UsuariosView.vue')
const ClientesView = () => import('@/views/admin/ClientesView.vue')
const RepartidoresView = () => import('@/views/admin/RepartidoresView.vue')
const FarmaciasView = () => import('@/views/admin/FarmaciasView.vue')
const PedidosView = () => import('@/views/admin/PedidosView.vue')
const ProductosView = () => import('@/views/admin/ProductosView.vue')
const CalificacionesView = () => import('@/views/admin/CalificacionesView.vue')

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView
        },
        {
            path: '/register',
            name: 'register',
            component: RegisterView
        },
        {
            path: '/admin',
            component: AdminView,
            meta: { requiresAuth: true, requiresAdmin: true },
            children: [
                {
                    path: '',
                    name: 'admin',
                    component: AdminDashboard
                },
                {
                    path: 'usuarios',
                    name: 'admin-usuarios',
                    component: UsuariosView
                },
                {
                    path: 'clientes',
                    name: 'admin-clientes',
                    component: ClientesView
                },
                {
                    path: 'repartidores',
                    name: 'admin-repartidores',
                    component: RepartidoresView
                },
                {
                    path: 'farmacias',
                    name: 'admin-farmacias',
                    component: FarmaciasView
                },
                {
                    path: 'pedidos',
                    name: 'admin-pedidos',
                    component: PedidosView
                },
                {
                    path: 'productos',
                    name: 'admin-productos',
                    component: ProductosView
                },
                {
                    path: 'calificaciones',
                    name: 'admin-calificaciones',
                    component: CalificacionesView
                }
            ]
        }
    ]
})

router.beforeEach((to, from, next) => {
    const authStore = useAuthStore()
    authStore.initialize()

    if (to.meta.requiresAuth && !authStore.isAuthenticated) {
        next({ name: 'login' })
        //} else if (to.meta.requiresAdmin && authStore.userType !== 'ADMIN') {
        //    next({ name: 'home' })
    } else {
        next()
    }
})

export default router