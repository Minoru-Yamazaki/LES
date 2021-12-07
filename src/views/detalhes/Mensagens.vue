<template>
  <div>
    <app-nav-login></app-nav-login>
    <div class="container">
      <div class="alert alert-danger" role="alert" v-if="!mensagens">
        Você não possui mensagens.
      </div>
      <div
        class="alert alert-primary"
        role="alert"
        v-for="mensagem in mensagens"
        :key="mensagem.id"
      >
        <div>
          {{ mensagem.data }}
        </div>
        {{ mensagem.mensagem }}
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";

export default {
  name: "Mensagens",
  components: {
    "app-nav-login": NavComponent,
  },

  data() {
    return {
      login: null,
      mensagens: null,
    };
  },
  created() {
    this.carregaInfos();
  },
  methods: {
    carregaInfos() {
      this.login = JSON.parse(localStorage.getItem("login"));
      this.consultaMensagens(this.login.id);
    },
    consultaMensagens(id) {
      const idCliente = {
        cliId: id,
      };
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(idCliente), // We send data in JSON format
      };

      fetch("http://localhost:8080/consultar-mensagem", postMethod)
        .then((response) => response.json())
        .then((data) => {
          if (data[0].id != null) {
            this.mensagens = data;
          } /*else {
            alert(data[0].mensagens);
          }*/
        });
    },
    formataData(data) {
      let date = new Date(data);
      let dataFormatada =
        date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
      return dataFormatada;
    },
  },
};
</script>
