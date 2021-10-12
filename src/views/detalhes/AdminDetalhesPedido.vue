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
                <td>{{ produto.id }}</td>
                <td>{{ produto.preco }}</td>
                <td>{{ produto.nome }}</td>
                <td>{{ produto.descricao }}</td>
                <td>{{ produto.quantidade }}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="col-sm-1"></div>

        <div class="col-sm-2 text-right">
          <i>Data:</i>
        </div>
        <div class="col-sm-10 text-left">
          <i> {{ compra.data }} </i>
        </div>
        <div class="col-sm-2 text-right">
          <i>Valor:</i>
        </div>
        <div class="col-sm-10 text-left">
          <i> {{ compra.valorOrquideas }} </i>
        </div>
        <div class="col-sm-2 text-right">
          <i>Status:</i>
        </div>
        <div class="col-sm-3">
          <select class="form-control" v-model="compra.status">
            <option v-for="status in situacoes" :key="status">
              {{ status }}
            </option>
          </select>
        </div>
        <div class="col-sm-2">
          <button
            v-on:click="salvar()"
            type="button"
            class="btn btn-primary form-control"
          >
            salvar
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";

export default {
  name: "AdminDetalhesPedido",

  components: {
    "app-nav-login": NavComponent,
  },

  data() {
    return {
      situacoes: [
        "EM PROCESSAMENTO",
        "PAGAMENTO REALIZADO",
        "EM TRANSPORTE",
        "ENTREGA REALIZADA",
        "TROCA SOLICITADA",
        "TROCA AUTORIZADA",
        "TROCA REJEITADA",
        "TROCA ACEITA",
        "TROCA EFETUADA",
        "CANCELAMENTO SOLICITADO",
        "CANCELAMENTO REJEITADO",
        "CANCELAMENTO ACEITO",
        "CANCELAMENTO EFETUADO",
      ],
      compra: {
        id: 1,
        status: "TROCA SOLICITADA",
        data: "23/12/20",
        valor: "R$ 106,8",
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
      },
    };
  },
  created() {},
  methods: {
    salvar() {},
    voltar() {
      this.$router.push({ path: "/admin-pedidos" });
    },
  },
};
</script>
