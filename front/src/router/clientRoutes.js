import ClientsView from "@/modules/client/views/ClientsView.vue";

export default [
    {
        path: 'PanelUser',
        name: 'userView',
        component: ClientsView,
        meta: {
            title: 'Panel Cliente',
            role: 'ROLE_USER',
            requireAuth: true
        }
    }
]