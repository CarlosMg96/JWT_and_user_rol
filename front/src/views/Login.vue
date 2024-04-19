<template>
  <div class="shape">
    <b-row class="w-100 justify-content-center">
      <b-col class="" cols="12" lg="5" md="8">
        <form @submit.prevent="login">
          <div class="login">
            <div class="body-login">
              <h1>Inicio de sesión</h1>
              <div class="form-group mt-3">
                <label for="email">Correo electrónico</label>
                <BInput v-model="username" id="email" aria-describedby="emailHelp" class="form-control"
                       placeholder="Ingresa tu correo electrónico"
                       type="email"/>
              </div>
              <div class="form-group mt-3">
                <label for="password">Contraseña</label>
                <BInput v-model="password" id="password" class="form-control" placeholder="Contraseña" type="password"/>
              </div>
              <b-alert show variant="danger" v-if="badCredentials">Credenciales incorrectas</b-alert>
            </div>
            <div class="text-center">
              <BButton block variant="outline-primary" type="submit"><b> Iniciar sesión </b></BButton>
              <p class="mt-3"><b-link  to="/home">Página de inicio</b-link></p>
            </div>
          </div>
        </form>
      </b-col>
    </b-row>
  </div>
</template>
<script>
import {login} from "@/service/service-http";

export default {
  name: 'LoginView',
  data() {
    return {
      username: '',
      password: '',
      badCredentials: false
    }
  },
  methods: {
    async login() {
      const credentials = {
        username: this.username,
        password: this.password
      }
      const {status} = await login(credentials);
      if (status !== 200){
        this.badCredentials = true;
      }
    }
  }
}
</script>

<style scoped>

.shape {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  background-image: url('@/assets/background.jpg');
  backdrop-filter: contrast(0.5);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
}

.login {
  width: 100%;
  min-height: 50vh;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-direction: column;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  border-radius: 15px;
  background-color: rgba(255, 255, 255, 0.4);
  --webkit-backdrop-filter: blur(17px);
  backdrop-filter: blur(17px);
  color: white;
  animation: fall 2s ease-in-out;
  animation-fill-mode: forwards;
}

@keyframes fall {
  from{
    transform: translateY(-100px);
    opacity: 0;
  }
  to{
    transform: translateY(0);
    opacity: 1;
  }
}

.form-control {
  background-color: rgba(255, 255, 255, 0.4);
  color: white;
}

.form-control::placeholder {
  color: white;
  font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif;
}

.form-control:focus {
  background-color: rgba(255, 255, 255, 0.6);
  font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif;
  color: black;
  border: none;
}
</style>