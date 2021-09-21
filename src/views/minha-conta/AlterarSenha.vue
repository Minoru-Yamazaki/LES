<template>
  <div>
    <app-nav></app-nav>

    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-4 ml-4 mt-4">
          <app-menu></app-menu>
        </div>
        <div class="col-sm-4">
          <form @submit.prevent="alterar()">
            <h2 class="text-center mb-5">Alteração dados do Login</h2>
            <div class="mb-3">
              <label>Email</label>
              <input
                class="form-control"
                type="email"
                name="txtEmail"
                id="txtEmail"
                v-model="login.email"
                required
              />
            </div>
            <div class="mb-3">
              <label>Nova senha</label>
              <input
                class="form-control"
                type="password"
                name="pwdSenha"
                id="pwdSenha"
                v-model="login.senha"
                required
              />
            </div>
            <div class="mb-5">
              <label>Confirmar nova senha</label>
              <input
                class="form-control"
                type="password"
                name="pwdConfirmaSenha"
                id="pwdConfirmaSenha"
                v-model="login.senhaConfirm"
                required
              />
            </div>

            <button type="submit" class="btn btn-primary btn-block mb-5">
              Salvar
            </button>
          </form>
        </div>
        <div class="col-sm-3"></div>
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";
import MenuComponent from "../../components/shared/menu/MenuMinhaContaComponent.vue";
import clienteService from "../../_services/clienteServices.js";

export default {
  name: "AlterarSenha",
  components: {
    "app-nav": NavComponent,
    "app-menu": MenuComponent,
  },
  data() {
    return {
      login: {
        id: null,
        email: null,
        senha: null,
        senhaConfirm: null,
      },
    };
  },

  created() {
    let login = JSON.parse(localStorage.getItem("login"));
    this.login.id = login.id;
    this.login.email = login.email;
  },

  methods: {
    alterar() {
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(this.login), // We send data in JSON format
      };

      fetch("http://localhost:8080/alterar-login", postMethod)
        .then((response) => response.json())
        .then((data) => {
          alert(data.mensagens);
        })
        .then(() => {
          clienteService.consultarCliente(); //.then(() => {this.$router.push({ path: "/minha-conta" });});
        });
    },
  },
};
</script>
