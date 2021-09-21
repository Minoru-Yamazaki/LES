<template>
  <div>
    <h2 class="text-danger">Minha Conta</h2>
    <div>
      <router-link class="text-secondary h4" to="/minha-conta">
        <p class="fas fa-user"></p>
        <i class="ml-2">cadastro</i>
      </router-link>
    </div>
    <div>
      <router-link class="text-secondary h4" to="/minha-conta/endereco">
        <p class="fa fa-map-marked-alt"></p>
        <i class="ml-2">endereços</i>
      </router-link>
    </div>
    <div>
      <router-link class="text-secondary h4" to="/minha-conta/cartoes">
        <p class="fa fa-credit-card"></p>
        <i class="ml-2">cartões</i>
      </router-link>
    </div>
    <div>
      <router-link class="text-secondary h4" to="">
        <p class="fa fa-ticket-alt"></p>
        <i class="ml-2">cupons</i>
      </router-link>
    </div>
    <div>
      <router-link class="text-secondary h4" to="/pedidos">
        <p class="fa fa-box-open"></p>
        <i class="ml-2">pedidos</i>
      </router-link>
    </div>
    <div>
      <router-link class="text-secondary h4" to="/minha-conta/alterar-senha">
        <p class="fa fa-lock"></p>
        <i class="ml-2">alterar senha</i>
      </router-link>
    </div>
    <div>
      <button
        type="button"
        class="btn text-secondary"
        v-on:click="excluirCadastro()"
      >
        <p class="fa fa-user-times h4"></p>
        <i class="h4">excluir cadastro</i>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "MenuMinhaContaComponent",

  data() {
    return {};
  },

  created() {},

  methods: {
    excluirCadastro() {
      const resposta = confirm("Escluir cadastro?");

      if (resposta == true) {
        let cliente = JSON.parse(localStorage.getItem("cliente"));
        let id = cliente.id;

        let json = {
          id: id,
        };

        const postMethod = {
          method: "POST", // Method itself
          headers: {
            "Content-type": "application/json; charset=UTF-8", // Indicates the content
          },
          body: JSON.stringify(json), // We send data in JSON format
        };

        fetch("http://localhost:8080/excluir-cliente", postMethod)
          .then((response) => response.json())
          .then((data) => {
            alert(data.mensagens);
          })
          .then(() => {
            this.$router.push({ path: "/#/" });
            this.exluirCookies();
          });
      }
    },
    exluirCookies() {
      localStorage.removeItem("login");
      localStorage.removeItem("cliente");
      localStorage.removeItem("idEndereco");
      localStorage.removeItem("idCartao");
    },
  },
};
</script>
