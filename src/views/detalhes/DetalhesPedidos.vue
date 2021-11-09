<template>
  <div>
    <app-nav-login></app-nav-login>
    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-1">
          <button v-on:click="voltar()" type="button" class="btn btn-secondary">
            <i class="fa fa-arrow-left"></i>
          </button>
        </div>
        <div class="col-sm-10">
          <h1>Detalhes</h1>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Código</th>
                <th scope="col">Preço</th>
                <th scope="col">Nome</th>
                <th scope="col">Descrição</th>
                <th scope="col">Quantidade</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="produto in compras.produtos" :key="produto.id">
                <td>{{ produto.idProduto }}</td>
                <td>{{ produto.preco }}</td>
                <td>{{ produto.nome }}</td>
                <td>{{ produto.descricao }}</td>
                <td>{{ produto.quantidade }}</td>
                <td>
                  <button
                    class="btn btn-primary"
                    v-show="
                      temTroca(produto.idProduto) && verificaStatus(status)
                    "
                    v-on:click="cdTroca(produto, 'salvar')"
                  >
                    Trocar
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="row" v-if="compras.trocas.length">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">
          <h1>Trocas</h1>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Código do produto</th>
                <th scope="col">Nome</th>
                <th scope="col">Quantidade</th>
                <th scope="col" v-if="verificaStatus(status)">Alterar qtde</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="troca in compras.trocas" :key="troca.id">
                <td>{{ troca.proId }}</td>
                <td>{{ getNomeProduto(troca.proId) }}</td>
                <td>{{ troca.quantidade }}</td>
                <td>
                  <select
                    class="form-control"
                    v-if="troca.qtdeMax > 1 && verificaStatus(status)"
                    v-model="qtdeTemporaria[compras.trocas.indexOf(troca)]"
                  >
                    <option
                      v-for="qtde in geraArray(troca.qtdeMax)"
                      :key="qtde"
                    >
                      {{ qtde }}
                    </option>
                  </select>
                </td>
                <td>
                  <button
                    class="btn btn-warning"
                    v-if="troca.qtdeMax > 1 && verificaStatus(status)"
                    v-on:click="
                      alterarTroca(
                        troca,
                        qtdeTemporaria[compras.trocas.indexOf(troca)]
                      )
                    "
                  >
                    alterar
                  </button>
                  <button
                    class="btn btn-danger ml-3"
                    v-if="verificaStatus(status)"
                    v-on:click="cdTroca(troca, 'excluir')"
                  >
                    cancelar
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
  name: "DetalhesPedidos",

  components: {
    "app-nav-login": NavComponent,
  },

  data() {
    return {
      cliente: null,
      idPedido: null,
      status: null,
      compras: {
        produtos: [],
        trocas: [],
      },
      qtdeTemporaria: [],
    };
  },
  created() {
    this.status = localStorage.getItem("status");
    this.idPedido = localStorage.getItem("idPedido");
    this.consultarCompra();
  },
  methods: {
    verificaStatus(status) {
      if (status == "Entregue" || status == "Troca solicitada") {
        return 1;
      }
      return 0;
    },
    voltar() {
      this.$router.push({ path: "/pedidos" });
    },
    consultarCompra() {
      const json = {
        id: this.idPedido, //JSON.parse(localStorage.getItem("idOrquidea"))
      };

      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(json), // We send data in JSON format
      };

      fetch("http://localhost:8080/consultar-compra", postMethod)
        .then((response) => response.json())
        .then((data) => {
          if (data[0].id != null) {
            this.compras = data[0];
            this.preencheQtdeTemp();
          } else {
            alert(data[0].mensagens);
          }
        });
    },
    preencheQtdeTemp() {
      for (const produto of this.compras.trocas) {
        this.qtdeTemporaria.push(produto.quantidade);
      }
    },
    getNomeProduto(id) {
      for (const produto of this.compras.produtos) {
        if (produto.idProduto == id) {
          return produto.nome;
        }
      }
    },
    temTroca(id) {
      for (const troca of this.compras.trocas) {
        if (troca.proId == id) {
          return 0;
        }
      }
      return 1;
    },
    geraArray(qtdeMax) {
      let vetor = [];
      for (let i = 0; i < qtdeMax; i++) {
        vetor.push(i + 1);
      }
      return vetor;
    },
    cdTroca(troca, operacao) {
      let resposta;
      if (operacao == "salvar") {
        troca = this.modelTroca(troca);
        resposta = confirm("Trocar esse produto?");
      }
      if (operacao == "excluir") {
        troca = { id: troca.id };
        resposta = confirm("Cancelar Pedido?");
      }

      if (resposta) {
        if (operacao == "excluir" && this.ultimoProduto()) {
          this.alteraStatus();
        }
        if (operacao == "salvar" && this.compras.status == "Entregue") {
          this.alteraStatus();
        }

        const postMethod = {
          method: "POST", // Method itself
          headers: {
            "Content-type": "application/json; charset=UTF-8", // Indicates the content
          },
          body: JSON.stringify(troca), // We send data in JSON format
        };

        fetch("http://localhost:8080/" + operacao + "-troca", postMethod).then(
          () => {
            location.reload();
          }
        );
      }
    },
    ultimoProduto() {
      if (this.compras.trocas.length == 1) {
        return 1;
      }
      return 0;
    },
    modelTroca(produto) {
      let troca = {
        proId: produto.idProduto,
        quantidade: produto.quantidade,
        qtdeMax: produto.quantidade,
        preco: produto.preco,
        comId: produto.idCompra,
      };
      return troca;
    },
    alterarTroca(troca, qtde) {
      troca.quantidade = qtde;

      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(troca), // We send data in JSON format
      };

      fetch("http://localhost:8080/alterar-troca", postMethod).then(() => {
        location.reload();
      });
    },
    alterarCompra(compra) {
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(compra), // We send data in JSON format
      };

      fetch("http://localhost:8080/alterar-compra", postMethod).then(() => {
        location.reload();
      });
    },
    alteraStatus() {
      if (this.compras.status == "Entregue") {
        this.compras.status = "Troca solicitada";
        this.alterarCompra(this.compras);
      } else {
        this.compras.status = "Entregue";
        this.alterarCompra(this.compras);
      }
    },
  },
};
</script>
