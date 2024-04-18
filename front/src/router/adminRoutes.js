import AdminView from "@/mudules/admin/views/AdminView.vue";

export default [
    {
        path: 'PanelAdmin',
        name: 'adminView',
        component: AdminView,
        meta: {
            title:'Panel Admil',
            role: 'admin'
        }
    }
]