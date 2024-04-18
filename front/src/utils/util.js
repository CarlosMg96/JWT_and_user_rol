import jwtDecode from "jwt-decode"

const getRoleNameBytoken = async () => {
    try {
     const tokenDecrypted = getToken();
     const { role : {name}} = jwtDecode(tokenDecrypted)
     return name
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