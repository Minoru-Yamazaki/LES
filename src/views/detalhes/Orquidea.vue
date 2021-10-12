<template>
  <div>
    <app-nav-login></app-nav-login>

    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-1 ml-4 mt-4"></div>
        <div class="col-sm-5">
          <img
            :src="orquidea.imagens[0].foto"
            class="mb-3 mt-5"
            width="500"
            height="500"
          />
        </div>
        <div class="col-sm-5">
          <div class="card border-dark col-sm-11 mb-3 mt-5">
            <h2 class="">{{ orquidea.nome }}</h2>
            <h5>R$ {{ orquidea.valorVenda }}</h5>
            <p>Código: {{ orquidea.id }}</p>
            <i>Gênero: {{ orquidea.genero }}</i>
            <i>Tipo: {{ orquidea.tipo }}</i>
            <i>Tamanho: {{ orquidea.tamanho }}</i>
            <i>clima: {{ orquidea.clima }}</i>
            <i>Sombreamento: {{ orquidea.sombreamento }}</i>
            <i>Floracao: {{ orquidea.tempoFloracao }}</i>
            <i>Umidade Ambiente: {{ orquidea.umidadeAmbiente }}</i>
            <i>Descricao: {{ orquidea.descricao }}</i>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";

export default {
  name: "DetalhesOrquidea",
  props: ['id'],

  components: {
    "app-nav-login": NavComponent,
  },

  data() {
    return {
      orquidea: null,
    };
  },
  created() {
    const json = {
      id: this.$route.params.id, //JSON.parse(localStorage.getItem("idOrquidea"))
    };
    /* Consulta Orquideas */
    const postMethod = {
      method: "POST", // Method itself
      headers: {
        "Content-type": "application/json; charset=UTF-8", // Indicates the content
      },
      body: JSON.stringify(json), // We send data in JSON format
    };

    fetch("http://localhost:8080/consultar-orquidea", postMethod)
      .then((response) => response.json())
      .then((data) => {
        if (data[0].id != null) {
          this.orquidea = data[0];
        } else {
          alert(data[0].mensagens);
        }
      });
  },
  methods: {},
};
</script>
