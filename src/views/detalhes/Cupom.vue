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
                <th scope="col">Cupom</th>
                <th scope="col">Descricao</th>
                <th scope="col">Tipo</th>
                <th scope="col">Valor</th>
                <th scope="col">Validade</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="cupom in cliente.cupons" :key="cupom.id">
                <td>{{ cupom.nome }}</td>
                <td>{{ cupom.descricao }}</td>
                <td>{{ cupom.tipoCupom }}</td>
                <td>{{ cupom.valor }}</td>
                <td>{{ cupom.validade }}</td>
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
  name: "Cupom",

  components: {
    "app-nav-login": NavComponent,
  },

  data() {
    return {
      cliente: null,
    };
  },
  created() {
    this.cliente = JSON.parse(localStorage.getItem("cliente"));
  },
  methods: {},
};
</script>
