import jwtDecode from "jwt-decode"

const getRoleNameBytoken = async () => {
    try {
        const token = getToken();
        const {role} = jwtDecode(token)
        console.log(role)
        return role
    } catch (error) {
        removeToken()
    }
}

const getToken = () => {
    return localStorage.getItem("token")
}

const removeToken = () => {
    localStorage.removeItem("token")
}


export default {
    getRoleNameBytoken,
    getToken,
    removeToken,
}