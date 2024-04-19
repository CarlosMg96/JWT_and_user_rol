import api from "@/config/http-client.gateway";
import util from "@/utils/util";
import router from "@/router";
export const login = async (req)=>{
    try{
        const {data, status} = await api.doPost('/auth/login', req)
        console.log(data)
        localStorage.setItem('token', data)
        const role = await util.getRoleNameBytoken()
        if (role === "ROLE_ADMIN") await router.push({name: "adminView"})
        if (role === "ROLE_USER") await router.push({name: "userView"})
        return {status}
    }catch(error){
        return error.response
    }
}