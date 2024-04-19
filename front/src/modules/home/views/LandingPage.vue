<template>
  <div class="home">
    <div class="content">
      <h1>Bienvenido a la página principal</h1>
      <p>Estamos encantados de tenerte aquí. Nuestra página te ofrece una variedad de servicios y recursos. Explora y
        descubre todo lo que tenemos para ti.</p>
      <div class="mt-3">
        <BButton class="w-auto" to="/login" variant="outline-primary"><b> Comencemos </b></BButton>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LandingPage',

}
</script>

<style scoped>

.home {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url('@/assets/home.jpg');
  background-size: cover;
  background-position: center;
  --webkit-backdrop-filter: blur(17px);
  backdrop-filter: blur(17px);
  color: white;
}

.content {
  text-align: center;
  font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif;
  width: 100%;
  padding: 20px;
  min-height: 30vh;
  background-color: rgba(0, 0, 0, 0.2);
  backdrop-filter: blur(10px);
  display: flex;
  flex-direction: column;
  justify-content: center;
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
</style>
  