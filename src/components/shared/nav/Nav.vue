<template>
  <nav class="navbar bg-dark navbar-dark justify-content-between mb-3">
      <i class="fa fa-home fa-2x text-white"></i>
      <div class="mt-3 text-white">
        <router-link class="mx-3 text-white" to="/login">Login</router-link>
        <router-link class="mx-3 text-white" to="/cadastro"
          >Cadastrar</router-link
        >
      </div>
    </nav>
</template>

<script>
export default {
  name: "Nav",
  data() {
    return {
    }
  },
  created(){
  },
  methods: {
  },
};
</script>
