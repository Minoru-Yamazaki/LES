<template>
  <div>
    <app-nav-login></app-nav-login>
    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">
          <h1>Pedidos</h1>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Status</th>
                <th scope="col">Data</th>
                <th scope="col">Valor</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pedido in pedidos" :key="pedido.id">
                <td>{{ pedido.status }}</td>
                <td>{{ pedido.data }}</td>
                <td>{{ pedido.valor }}</td>
                <td>
                  <button
                    v-on:click="visualizarCompra()"
                    type="button"
                    class="btn btn-primary"
                  >
                    ver compra
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
  name: "Pedidos",

  components: {
    "app-nav-login": NavComponent,
  },

  data() {
    return {
      pedidos: [
        {
          id: 1,
          status: "finalizado",
          data: "12/03/2021",
          valor: "R$ 50,90",
        },
        {
          id: 2,
          status: "Em processamento",
          data: "12/09/2021",
          valor: "R$ 26,90",
        },
      ],
    };
  },
  created() {},
  methods: {
    visualizarCompra() {
      this.$router.push({ path: "/detalhes-pedido" });
    },
  },
};
</script>
