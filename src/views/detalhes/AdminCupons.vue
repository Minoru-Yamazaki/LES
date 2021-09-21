<template>
  <div>
    <app-nav-login></app-nav-login>
    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">
          <h1>Cupons</h1>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Descrição</th>
                <th scope="col">Tipo do cupom</th>
                <th scope="col">Valor</th>
                <th scope="col">validade</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="cupom in cupons" :key="cupom.id">
                <td>{{ cupom.id }}</td>
                <td>{{ cupom.nome }}</td>
                <td>{{ cupom.descricao }}</td>
                <td>{{ cupom.tipoCupom }}</td>
                <td>{{ cupom.valor }}</td>
                <td>{{ cupom.validade }}</td>
                <td>
                  <button
                    v-on:click="alterar(cupom.id)"
                    type="button"
                    class="btn text-secondary"
                  >
                    <i class="fa fa-pencil-alt"></i>
                  </button>
                  <button
                    v-on:click="excluir(cupom.id)"
                    type="button"
                    class="btn text-secondary"
                  >
                    <i class="fa fa-trash"></i>
                  </button>
                </td>
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
  name: "DetalhesOrquidea",

  components: {
    "app-nav-login": NavComponent,
  },

  data() {
    return {
      cupons: [
        {
          id: 1,
          nome: "Dia das Mães",
          descricao: "cupom promocional dos dias das mães",
          tipoCupom: "promocional",
          valor: "R$ 10",
          validade: "20/05/2021",
        },
        {
          id: 2,
          nome: "Troca",
          descricao: "cupom de troca",
          tipoCupom: "troca",
          valor: "R$ 60",
          validade: "--",
        },
      ],
    };
  },
  created() {},
  methods: {
    alterar() {},
    excluir() {},
  },
};
</script>
