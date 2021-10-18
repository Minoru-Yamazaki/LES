<template>
  <div>
    <app-nav-login></app-nav-login>
    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-1">
          <button v-on:click="voltar()" type="button" class="btn btn-secondary">
            <i class="fa fa-arrow-left"></i>
          </button>
        </div>
        <div class="col-sm-10">
          <h1>Detalhes</h1>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Código</th>
                <th scope="col">Preço</th>
                <th scope="col">Nome</th>
                <th scope="col">Descrição</th>
                <th scope="col">Quantidade</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="produto in compra.produtos" :key="produto.id">
                <td>{{ produto.idProduto }}</td>
                <td>{{ produto.preco }}</td>
                <td>{{ produto.nome }}</td>
                <td>{{ produto.descricao }}</td>
                <td>{{ produto.quantidade }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";

export default {
  name: "DetalhesPedidos",

  components: {
    "app-nav-login": NavComponent,
  },

  data() {
    return {
      cliente: null,
      idPedido: null,
      compra: {
        produtos: [],
      },
    };
  },
  created() {
    this.idPedido = localStorage.getItem("idPedido");
    this.consultarCompra();
  },
  methods: {
    voltar() {
      this.$router.push({ path: "/pedidos" });
    },
    consultarCompra() {
      const json = {
        id: this.idPedido, //JSON.parse(localStorage.getItem("idOrquidea"))
      };

      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(json), // We send data in JSON format
      };

      fetch("http://localhost:8080/consultar-compra", postMethod)
        .then((response) => response.json())
        .then((data) => {
          if (data[0].id != null) {
            this.compra = data[0];
          } else {
            alert(data[0].mensagens);
          }
        });
    },
  },
};
</script>
