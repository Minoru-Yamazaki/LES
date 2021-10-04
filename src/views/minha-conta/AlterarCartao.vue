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
          <h2 class="text-center mb-3">Alterar cartão</h2>

          <form @submit.prevent="alterar()">
            <div class="row">
              <div class="col-sm-12 mb-3">
                <label>Apelido</label>
                <input
                  class="form-control"
                  placeholder="Nubank"
                  type="text"
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
  name: "AlterarCartao",
  components: {
    "app-nav": NavComponent,
    "app-menu": MenuComponent,
  },
  data() {
    return {
      cartao: {
        id: null,
        apelido: null,
        numero: null,
        nomeImpresso: null,
        codigoSeguranca: null,
        bandeira: null,
        preferencial: 0,
      },
    };
  },
  created() {
    let usuario = JSON.parse(localStorage.getItem("cliente"));
    this.cartao.id = localStorage.getItem("idCartao");
    let cartoes = usuario.cartoes;
    for (const cartao of cartoes) {
      if (cartao.id == this.cartao.id) {
        this.cartao.apelido = cartao.apelido;
        this.cartao.numero = cartao.numero;
        this.cartao.nomeImpresso = cartao.nomeImpresso;
        this.cartao.codigoSeguranca = cartao.codigoSeguranca;
        this.cartao.bandeira = cartao.bandeira;
      }
    }
  },
  methods: {
    alterar() {
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(this.cartao), // We send data in JSON format
      };

      fetch("http://localhost:8080/alterar-cartao", postMethod)
        .then((response) => response.json())
        .then((data) => {
          alert(data.mensagens);
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
