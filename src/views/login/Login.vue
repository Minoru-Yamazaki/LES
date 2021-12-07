<template>
  <div>
    <nav class="navbar bg-dark navbar-dark justify-content-between mb-3">
      <a>
        <router-link to="/"
          ><i class="fa fa-home fa-2x text-white"></i
        ></router-link>
      </a>
    </nav>

    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <form @submit.prevent="consultarLogin()">
        <div class="row">
          <div class="col-sm-4"></div>
          <div class="col-sm-4">
            <h2 class="text-center m-3">Login</h2>
            <div class="mb-5">
              <label>Email</label>
              <input
                class="form-control"
                type="email"
                placeholder="minoru@gmail.com"
                name="email"
                id="email"
                required
                v-model="login.email"
              />
            </div>
            <div class="mb-5">
              <label>Senha</label>
              <input
                class="form-control"
                type="password"
                name="senha"
                id="senha"
                required
                v-model="login.senha"
              />
            </div>
            <button type="submit" class="btn btn-primary btn-block mb-5" id="btnContinuar">
              <!-- v-on:click="teste()" -->
              Continuar
            </button>
            <p class="text-center mb-3 mt-3">
              Não tem cadastro?
              <router-link to="/cadastro">Cadastre-se</router-link>
            </p>
          </div>
          <div class="col-sm-4"></div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import clienteService from "../../_services/clienteServices.js";

export default {
  name: "LoginComponent",
  data() {
    return {
      login: {
        email: null,
        senha: null,
      },
      teste: null,
    };
  },

  methods: {
    consultarLogin() {
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(this.login), // We send data in JSON format
      };

      fetch("http://localhost:8080/consultar-login", postMethod)
        .then((response) => response.json())
        .then((data) => {
          if (data[0].id != null) {
            localStorage.setItem("login", JSON.stringify(data[0]));
            clienteService.consultarCliente().then(() => {
              window.location = "/";
            });
          } else {
            alert(data[0].mensagens);
          }
        });
      //var login = JSON.parse(this.$cookie.get('login'));
      //console.log(login);
    },

    consultarCliente() {
      const login = JSON.parse(localStorage.getItem("login"));
      const someData = {
        id: login.id,
      };
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(someData), // We send data in JSON format
      };

      return fetch("http://localhost:8080/consultar-cliente", postMethod)
        .then((response) => response.json())
        .then((data) => {
          if (data[0].id != null) {
            localStorage.setItem("cliente", JSON.stringify(data[0]));
          } else {
            alert(data[0].mensagens);
          }
        });
    },
  },
};
</script>
