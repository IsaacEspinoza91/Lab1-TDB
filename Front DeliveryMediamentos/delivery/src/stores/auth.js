import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAuthStore = defineStore('auth', () => {
    const token = ref(null)
    const isAuthenticated = ref(false)

    function setToken(newToken) {
        token.value = newToken
        isAuthenticated.value = true
        localStorage.setItem('token', newToken)
    }

    function clearToken() {
        token.value = null
        isAuthenticated.value = false
        localStorage.removeItem('token')
    }

    function initialize() {
        const savedToken = localStorage.getItem('token')
        if (savedToken) {
            token.value = savedToken
            isAuthenticated.value = true
        }
    }

    return { token, isAuthenticated, setToken, clearToken, initialize }
})