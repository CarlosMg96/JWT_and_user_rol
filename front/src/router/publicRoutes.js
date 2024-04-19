import Login from "@/views/Login.vue";
import LandingPage from "@/modules/home/views/LandingPage.vue";

export default [
    {
        path: '/home',
        name: 'home',
        component: LandingPage,
        meta: {
            title: "Home",
            requireAuth: false
        },
    },

    {
        path: '/login',
        name: 'login',
        component: Login,
        meta: {
            title: "Login",
            requireAuth: false
        },
    },
]