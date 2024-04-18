import ClientsView from "@/mudules/client/views/ClientsView.vue";

export default [
    {
        component: {
            render(c){
                return c("router-view")
            }
        },
        meta: {title: "" },
        children:[
          {
            path: 'PanelUser',
            name: 'userView',
            component: ClientsView,
            meta: {
                title:'Panel Cliente',
                role: 'client'
            }
          }
        ]
    }
]