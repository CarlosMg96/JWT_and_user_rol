import Login from "@/views/Login.vue";
import LandingPage from "@/modules/home/views/LandingPage.vue";

export default [
    {
        path: '/',
        component: LandingPage,
        meta: {
            title: "Home"
        },
    },

    {
        path: '/login',
        component: Login,
        meta: {
            title: "Login"
        },
    },
]