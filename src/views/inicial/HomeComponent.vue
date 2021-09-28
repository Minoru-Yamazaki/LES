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
                v-model="orchid.nome"
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
              <select class="form-control" v-model="orchid.genero">
                <option v-for="genero in listas.generos" :key="genero">
                  {{ genero }}
                </option>
              </select>
            </div>
            <div class="mb-3">
              <i>tipo:</i>
              <select class="form-control" v-model="orchid.tipo">
                <option value="híbrido">Híbrido</option>
                <option value="espécie">Espécie</option>
              </select>
            </div>
            <div class="mb-3">
              <i>tamanho:</i>
              <select class="form-control" v-model="orchid.tamanho">
                <option v-for="tamanho in listas.tamanhos" :key="tamanho">
                  {{ tamanho }}
                </option>
              </select>
              {{ orchid.tamanho }}
            </div>
            <div class="mb-3">
              <i>cor:</i>
              <select class="form-control" v-model="orchid.cor">
                <option v-for="cor in listas.cores" :key="cor">
                  {{ cor }}
                </option>
              </select>
              {{ orchid.cor }}
            </div>
            <button type="submit" class="btn btn-primary btn-block ">
              Pesquisar
            </button>
          </form>
        </div>
        <div class="col-sm-9">
          <div class="row">
            <div
              v-for="orquidea in orquideas"
              :key="orquidea.id"
              class="card border-light col-sm-3"
            >
              <img :src="orquidea.imagens[0].foto" height="200" />
              <div class="card-body">
                <p class="card-text">
                  {{ orquidea.nome }}
                </p>
                <button
                  type="button"
                  @click="adicionarCarrinho(orquidea.id)"
                  class="btn"
                >
                  <i class="fa fa-shopping-cart"></i>
                </button>
                <button
                  type="button"
                  @click="detalhes(orquidea.id)"
                  class="btn"
                >
                  <i class="fa fa-info"></i>
                </button>
                <i class="h5 ml-2">R$ {{ orquidea.valorVenda }}</i>
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
      src: null,
      orchid: {
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
      orquideas: null,

      produtos: []
    };
  },
  created() {
    let login = JSON.parse(localStorage.getItem("login"));

    let produtos = JSON.parse(localStorage.getItem("produtos"));
    if (produtos != null) {
      this.produtos = produtos;
    }

    try {
      this.login.id = login.id;
      this.logado = true;
    } catch (error) {
      this.logado = false;
    }
    const json = {
      ativo: 1,
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
          this.orquideas = data;
        } else {
          alert(data[0].mensagens);
        }
      });
  },

  methods: {
    adicionarCarrinho(id) {
      let tem = false;

      for (let produtos of this.produtos) {
        if (produtos.pro_id == id) {
          tem = true;
        }
      }
      if (!tem) {
        this.consultarOrquidea(id);
        alert("Produto adicionado");
      }

    },
    consultarOrquidea(id) {
      const json = {
        id: id
      }

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
            let produto = {
              pro_id: data[0].id,
              preco: data[0].valorVenda,
              nome: data[0].nome,
              descricao: data[0].descricao,
              quantidade: 1,
              sub_total: data[0].valorVenda
            };
            this.produtos.push(produto);
            localStorage.setItem("produtos", JSON.stringify(this.produtos))
          } else {
            alert(data[0].mensagens);
          }
        });
      //var login = JSON.parse(this.$cookie.get('login'));
      //console.log(login);
    },
    detalhes(id) {
      localStorage.setItem("idOrquidea", id);
      this.$router.push({ path: "/detalhes-orquidea" });
    },

  },
};
</script>
