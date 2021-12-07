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
          <h3 class="m-3">Endereços</h3>
          <div class="row">
            <div
              v-for="endereco in enderecos"
              :key="endereco.id"
              class="card border-dark col-sm-5 m-1"
            >
              <div class="card-header justify-content-between">
                {{ endereco.apelido }}
                <button
                  v-on:click="alterar(endereco.id)"
                  type="button"
                  class="btn text-secondary"
                >
                  <i class="fa fa-pencil-alt"></i>
                </button>
                <button
                  v-on:click="excluir(endereco.id)"
                  type="button"
                  class="btn text-secondary"
                >
                  <i class="fa fa-trash"></i>
                </button>
              </div>
              <div class="card-body text-dark">
                cidade: {{ endereco.cidade }} <br />
                lograd.: {{ endereco.logradouro }} <br />
                cep: {{ endereco.cep }} <br />
                numero: {{ endereco.numero }} <br />
                <!--<h5 class="card-title">Dark card title</h5> 
                -->
              </div>
            </div>
            <div class="card border-dark col-sm-5 m-1">
              <div class="card-header text-center">
                Adicionar endereço
              </div>
              <div class="card-body text-dark text-center">
                <!--<h5 class="card-title">Dark card title</h5> -->
                <router-link class="text-secondary" to="/cadastro-endereco">
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
  name: "EnderecoComponent",
  components: {
    "app-nav": NavComponent,
    "app-menu": MenuComponent,
  },
  data() {
    return {
      usuario: null,
      enderecos: null,
    };
  },
  created() {
    this.atualizarEnderecos();
  },
  methods: {
    atualizarEnderecos() {
      this.usuario = JSON.parse(localStorage.getItem("cliente"));
      this.enderecos = this.usuario.enderecos;
    },

    excluir(id) {
      const resposta = confirm("Excluir esse endereço?");
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

        fetch("http://localhost:8080/excluir-endereco", postMethod)
          .then((response) => response.json())
          .then((data) => {
            alert(data.mensagens);
          })
          .then(() => {
            clienteService.consultarCliente().then(() => {
              this.atualizarEnderecos();
            });
          });
      }
    },
    alterar(id) {
      localStorage.setItem("idEndereco", id);
      this.$router.push({ path: "/minha-conta/alterar-endereco" });
    },
  },
};
</script>
