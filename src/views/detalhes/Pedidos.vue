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
              <tr v-for="pedido in cliente.pedidos" :key="pedido.id">
                <td>{{ pedido.status }}</td>
                <td>{{ pedido.data }}</td>
                <td>R$ {{ pedido.valor }}</td>
                <td>
                  <button
                    v-on:click="
                      visualizarCompra(cliente.pedidos.indexOf(pedido))
                    "
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
      cliente: null,
    };
  },
  created() {
    this.cliente = JSON.parse(localStorage.getItem("cliente"));
  },
  methods: {
    visualizarCompra(index) {
      localStorage.setItem("indexPedido", index);
      this.$router.push({ path: "/detalhes-pedido" });
    },
  },
};
</script>
