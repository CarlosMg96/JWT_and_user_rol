import VueRouter from 'vue-router'
import publicRoutes from "@/router/publicRoutes";
import privateRoutes from "@/router/privateRoutes";
import util from "@/utils/util"
import Unautorized from "@/views/Unautorized.vue";

const routes = [

    {
        path: '',
        redirect: {name: 'home'},
        meta: {
            requireAuth: false
        }
    },

    ...publicRoutes.map(route => {
        route.meta.requireAuth = false
        return {...route}
    }),
    ...privateRoutes.map(route => {
        route.meta.requireAuth = true
        return {...route}
    }),

    {
        name: "unauthorized",
        path: "unauthorized",
        component: Unautorized,
        meta: {
            title: "Unautorized",
            requireAuth: false
        }
    },
    {
        path: '/**',
        name: '404',
        component: () => import('@/views/404.vue'),
        meta: {
            title: "404",
            requireAuth: false
        }
    },


]

const router = new VueRouter({
    mode: 'history',
    base: '/',
    routes
})

router.beforeEach(async (to, from, next) => {
    const authRequired = to.meta.requireAuth
    const loggedIn = util.getToken()

    if (authRequired && !loggedIn) {
        return next({name: "login"})
    }
    if (loggedIn) {
        const role = await util.getRoleNameBytoken()

        if (role !== undefined && role !== null && role !== "") {
            if (to.meta.role.toLowerCase() !== role.toLowerCase()) {
                return next({name: "unauthorized"})
            }
        } else {
            return next({name: "login"})
        }
        next();
    }
    if (loggedIn && to.path.includes( "login" )) {
        const role = await util.getRoleNameBytoken()
        if (role.toLowerCase() === "admin") return next({name: "adminView"})
        if (role.toLowerCase() === "client") return next({name: "userView"})
        localStorage.removeItem("token")
        return next({name: "login"})
    }
    next()
})


export default router