import AdminView from "@/modules/admin/views/AdminView.vue";

export default [
    {
        path: 'PanelAdmin',
        name: 'adminView',
        component: AdminView,
        meta: {
            title:'Panel Admil',
            role: 'admin',
            requireAuth: true
        }
    }
]