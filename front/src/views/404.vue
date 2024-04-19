<template>
    <div class="container">
      <div class="error_container">
        <div class="error_code">
          <p>
            404
          </p>
        </div>
        <div class="error_name">
            Ups!
          </div>
          <div class="error_description">
            Lo sentimos, la página que buscas no se encuentra.
          </div>
          <button class="action" @click="()=> $router.back()">Volver</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'NotFound'
  
  }
  </script>