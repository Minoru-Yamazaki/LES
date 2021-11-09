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
              <tr v-for="cupom in cupons" :key="cupom.id">
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
      login: null,
      cupons: null,
    };
  },
  created() {
    this.login = JSON.parse(localStorage.getItem("login"));
    this.consultarCupons();
  },
  methods: {
    consultarCupons() {
      const cupom = {
        cliId: this.login.id,
      };
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(cupom), // We send data in JSON format
      };

      fetch("http://localhost:8080/consultar-cupom", postMethod)
        .then((response) => response.json())
        .then((data) => {
          if (data[0].id != null) {
            this.cupons = data;
          }
        });
    },
  },
};
</script>
