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
                <td>R$ {{ pedido.valor + pedido.frete }}</td>
                <td>
                  <button
                    v-on:click="visualizarCompra(pedido.id, pedido.status)"
                    type="button"
                    class="btn btn-primary"
                  >
                    ver compra
                  </button>
                </td>
                <td>
                  <button
                    v-if="verificaStatus(pedido.status)"
                    v-on:click="
                      alterarCompra(pedido, 'Cancelamento solicitado')
                    "
                    type="button"
                    class="btn btn-danger ml-3"
                  >
                    cancelar pedido
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
      pedidos: null,
      cliente: null,
    };
  },
  created() {
    this.carregaInfos();
    this.consultarCompra();
  },
  methods: {
    visualizarCompra(id, status) {
      localStorage.setItem("idPedido", id);
      localStorage.setItem("status", status);
      this.$router.push({ path: "/detalhes-pedido" });
    },
    carregaInfos() {
      try {
        this.cliente = JSON.parse(localStorage.getItem("cliente"));
      } catch (error) {
        console.log(error);
      }
    },
    consultarCompra(compra) {
      console.log(this.cliente.id);
      if (!compra) {
        compra = {
          cliId: this.cliente.id,
        };
      }
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(compra), // We send data in JSON format
      };

      fetch("http://localhost:8080/consultar-compra", postMethod)
        .then((response) => response.json())
        .then((data) => {
          if (data[0].id != null) {
            this.pedidos = data;
          }
        });
    },
    verificaStatus(status) {
      if (status == "Aprovada" || status == "Separando pedido") {
        return 1;
      }
      return 0;
    },
    alterarCompra(compra, status) {
      const resposta = confirm("Cancelar Pedido?");

      if (resposta) {
        compra.status = status;

        const postMethod = {
          method: "POST", // Method itself
          headers: {
            "Content-type": "application/json; charset=UTF-8", // Indicates the content
          },
          body: JSON.stringify(compra), // We send data in JSON format
        };

        fetch("http://localhost:8080/alterar-compra", postMethod)
          .then((response) => response.json())
          .then((data) => {
            alert(data.mensagens[0]);
          });
      }
    },
  },
};
</script>
