import adminRoutes from "./adminRoutes";
import clientRoutes from "./clientRoutes";
import CentralPanel from "@/modules/home/views/CentralPanel.vue";

export default [
    {
        children:[
            ...adminRoutes.map(route => {
                route.meta.requireAuth = true
                return {...route}
            }),
            ...clientRoutes.map(route => {
                route.meta.requireAuth = true
                return {...route}
            }),
            {
                path: '/central-panel',
                name: 'central-panel',
                component: CentralPanel,
                meta:{
                    title: 'Panel Central'
                }
            }
        ]
    }
]