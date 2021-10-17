<template>
  <div>
    <app-nav></app-nav>
    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-4 ml-4 mt-4"></div>
        <div class="col-sm-4">
          <h2 class="text-center mb-3">Novo cartão</h2>
          <form @submit.prevent="salvar()">
            <div class="row">
              <div class="col-sm-12 mb-3">
                <label>Apelido</label>
                <input
                  class="form-control"
                  type="text"
                  placeholder="Nubank"
                  v-model="cartao.apelido"
                  required
                />
              </div>
              <div class="col-sm-12 mb-3">
                <label>Numero do Cartão</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtNumero"
                  id="txtNumero"
                  minlength="16"
                  maxlength="16"
                  v-model="cartao.numero"
                  required
                />
              </div>
              <div class="col-sm-12 mb-3">
                <label>Nome impresso no cartão</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtNome"
                  id="txtNome"
                  maxlength="20"
                  v-model="cartao.nomeImpresso"
                  required
                />
              </div>
              <div class="col-sm-6 mb-3">
                <label>Bandeira</label>
                <select
                  class="form-control"
                  name="optBandeira"
                  id="optBandeira"
                  v-model="cartao.bandeira"
                  required
                >
                  <option value="masterCard">MasterCard</option>
                  <option value="elo">Elo</option>
                </select>
              </div>
              <div class="col-sm-6 mb-3">
                <label>CVV</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtCvv"
                  id="txtCvv"
                  minlength="3"
                  maxlength="3"
                  v-model="cartao.codigoSeguranca"
                  required
                />
              </div>
            </div>
            <button type="submit" class="btn btn-primary btn-block mt-3 mb-3">
              Cadastrar
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";
import clienteService from "../../_services/clienteServices.js";

export default {
  name: "CadastroComponent",

  components: {
    "app-nav": NavComponent,
  },

  data() {
    return {
      cartao: {
        apelido: null,
        numero: null,
        nomeImpresso: null,
        codigoSeguranca: null,
        bandeira: null,
        idCliente: null,
        preferencial: 0,
      },
    };
  },
  created() {
    let usuario = JSON.parse(localStorage.getItem("cliente"));
    this.cartao.idCliente = usuario.id;
  },

  methods: {
    salvar() {
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(this.cartao), // We send data in JSON format
      };

      fetch("http://localhost:8080/salvar-cartao", postMethod)
        .then((response) => response.json())
        .then((data) => {
          var mensagem = "";
          for (var x in data.mensagens) {
            if (data.mensagens[x] == "Salvo com sucesso") {
              window.location.href = "/#/login";
            }
            mensagem += "\n" + data.mensagens[x];
          }
          alert(mensagem);
        })
        .then(() => {
          clienteService.consultarCliente().then(() => {
            this.$router.push({ path: "/minha-conta/cartoes" });
          });
        });
    },
  },
};
</script>
