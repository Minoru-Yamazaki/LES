<template>
  <div>
    <app-nav></app-nav>

    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-3 ml-4 mt-4">
          <app-menu></app-menu>
        </div>
        <div class="col-sm-8">
          <h3 class="m-3">Cartões</h3>
          <div class="row">
            <div
              v-for="cartao in cartoes"
              :key="cartao.id"
              class="card border-dark col-sm-5 m-1"
            >
              <div class="card-header justify-content-between">
                Cartão
                <button
                  v-on:click="alterar(cartao.id)"
                  type="button"
                  class="btn text-secondary"
                >
                  <i class="fa fa-pencil-alt"></i>
                </button>
                <button
                  v-on:click="excluir(cartao.id)"
                  type="button"
                  class="btn text-secondary"
                >
                  <i class="fa fa-trash"></i>
                </button>
              </div>
              <div class="card-body text-dark">
                bandeira: {{ cartao.bandeira }} <br />
                nome impresso: {{ cartao.nomeImpresso }} <br />
                <!--<h5 class="card-title">Dark card title</h5> 
                -->
              </div>
            </div>
            <div class="card border-dark col-sm-5 m-1">
              <div class="card-header text-center">
                Adicionar cartão
              </div>
              <div class="card-body text-dark text-center">
                <!--<h5 class="card-title">Dark card title</h5> -->
                <router-link class="text-secondary" to="/cadastro-cartao">
                  <p class="fa fa-plus-circle fa-2x"></p>
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";
import MenuComponent from "../../components/shared/menu/MenuMinhaContaComponent.vue";
import clienteService from "../../_services/clienteServices.js";

export default {
  name: "Cartoes",
  components: {
    "app-nav": NavComponent,
    "app-menu": MenuComponent,
  },
  data() {
    return {
      usuario: null,
      cartoes: null,
    };
  },
  created() {
    this.atualizarCartoes();
  },
  methods: {
    atualizarCartoes() {
      this.usuario = JSON.parse(localStorage.getItem("cliente"));
      this.cartoes = this.usuario.cartoes;
    },

    excluir(id) {
      const resposta = confirm("Escluir esse cartão?");
      
      if (resposta == true) {
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

        fetch("http://localhost:8080/excluir-cartao", postMethod)
          .then((response) => response.json())
          .then((data) => {
            alert(data.mensagens);
          })
          .then(() => {
            clienteService.consultarCliente()
            .then(() => {
              this.atualizarCartoes();
            });
          });
      }
    },
    alterar(id) {
      localStorage.setItem("idCartao", id);
      this.$router.push({ path: "/minha-conta/alterar-cartao" });
    },
  },
};
</script>
