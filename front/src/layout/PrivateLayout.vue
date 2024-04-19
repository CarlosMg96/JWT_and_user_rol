

<template>
  <div class="w-100 h-100 bg-dark text-white">
    <div class="nav-bar">
      <div class="item" v-for="(item, ind) in items" :key="ind" @click="goTo(item.route)"> <span>{{item.name}}</span></div>
    </div>
    <router-view />
  </div>
</template>
<script>
export default {
  name: 'PrivateLayout',
  data() {
    return {
      items: [
        {name: 'Home', route: 'home'},
        {name: 'Panel central', route: 'central-panel'},
        {name: 'Panel admin', route: 'adminView'},
        {name: 'Panel usuario', route: 'userView'}
      ]
    }
  },
  methods: {
    goTo(route) {
      if (this.$route.name === route) return
      this.$router.push({name: route})
    }
  }
}

</script>
<style scoped>
  .nav-bar{
    background-color: rgba(255, 255, 255, 0.4);
    --webkit-backdrop-filter: blur(17px);
    backdrop-filter: blur(17px);
    color: white;
    padding: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .item{
    border-radius: 15px;
    margin: 0 20px;
    padding: 10px 30px;
    transition: all 0.3s ease-in-out;
  }

  .item:hover{
    cursor: pointer;
    background-color: white;
    color: black;
  }
</style>