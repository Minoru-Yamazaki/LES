<template>
  <div>
    <app-nav v-if="!logado"></app-nav>
    <app-nav-login v-if="logado"></app-nav-login>

    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-2">
          <form>
            <div class="mb-3">
              <i>orquídea:</i>
              <input
                class="form-control"
                type="text"
                placeholder="Cattleya"
                v-model="orquidea.nome"
              />
            </div>
            <div class="mb-3">
              <i>preço:</i>
              <select class="form-control" v-model="precoEscolhido">
                <option v-for="valor in precos" :key="valor">
                  até R$ {{ valor }}
                </option>
              </select>
            </div>
            <div class="mb-3">
              <i>gênero:</i>
              <select class="form-control" v-model="orquidea.genero">
                <option v-for="genero in listas.generos" :key="genero">
                  {{ genero }}
                </option>
              </select>
            </div>
            <div class="mb-3">
              <i>tipo:</i>
              <select class="form-control" v-model="orquidea.tipo">
                <option value="híbrido">Híbrido</option>
                <option value="espécie">Espécie</option>
              </select>
            </div>
            <div class="mb-3">
              <i>tamanho:</i>
              <select class="form-control" v-model="orquidea.tamanho">
                <option v-for="tamanho in listas.tamanhos" :key="tamanho">
                  {{ tamanho }}
                </option>
              </select>
              {{ orquidea.tamanho }}
            </div>
            <div class="mb-3">
              <i>cor:</i>
              <select class="form-control" v-model="orquidea.cor">
                <option v-for="cor in listas.cores" :key="cor">
                  {{ cor }}
                </option>
              </select>
              {{ orquidea.cor }}
            </div>
            <button type="submit" class="btn btn-primary btn-block ">
              Pesquisar
            </button>
          </form>
        </div>
        <div class="col-sm-9">
          <div class="row">
            <div v-for="n in teste" :key="n" class="card border-light col-sm-3">
              <img src="../../assets/imagens/Vanda.jpg" alt="some text" />
              <div class="card-body">
                <p class="card-text">
                  Vanda.
                </p>
                <button type="button" @click="adicionarCarrinho(n)" class="btn">
                  <i class="fa fa-shopping-cart"></i>
                </button>
                <button type="button" @click="detalhes(n)" class="btn">
                  <i class="fa fa-info"></i>
                </button>
                <i class="h5 ml-2">R$ 45</i>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavLogin from "../../components/shared/nav/NavComponent.vue";
import Nav from "../../components/shared/nav/Nav.vue";

export default {
  name: "HomeComponent",

  components: {
    "app-nav-login": NavLogin,
    "app-nav": Nav,
  },

  data() {
    return {
      orquidea: {
        nome: null,
        genero: null,
        valorVenda: null,
        tipo: null,
        tamanho: null,
        cor: null,
      },
      listas: {
        generos: ["cattleya"],
        tamanhos: ["1", "2", "3"],
        cores: ["branco", "amarelo"],
      },
      precos: [20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300],
      precoEscolhido: null,
      teste: [1, 2, 3, 4, 5],
      logado: false,
      login: {
        id: null,
      },
      carrinho: [],
    };
  },
  created() {
    let login = JSON.parse(localStorage.getItem("login"));
    try {
      this.login.id = login.id;
      this.logado = true;
    } catch (error) {
      this.logado = false;
    }
  },

  methods: {
    adicionarCarrinho(id) {
      if (this.carrinho.indexOf(id) == -1) {
        this.carrinho.push(id);
      }
      localStorage.setItem("idsOrquidea", JSON.stringify(this.carrinho));
    },
    detalhes(id) {
      localStorage.setItem("idOrquidea", id);
      this.$router.push({ path: "/detalhes-orquidea" });
    },
  },
};
</script>
