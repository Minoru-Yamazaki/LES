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
              <tr v-for="produto in produtos" :key="produto.id">
                <td>{{ produto.id }}</td>
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
      produtos: [
        {
          id: 1,
          preco: "R$ 50,90",
          nome: "Laelia",
          descricao: "Laelia Crispa",
          quantidade: 1,
        },
        {
          id: 2,
          preco: "R$ 55,9",
          nome: "Brassavola Tuberculata",
          descricao: "Brassavola Tuberculata Branca Adulta",
          quantidade: 2,
        },
      ],
    };
  },
  created() {},
  methods: {
    voltar() {
      this.$router.push({ path: "/pedidos" });
    },
  },
};
</script>
