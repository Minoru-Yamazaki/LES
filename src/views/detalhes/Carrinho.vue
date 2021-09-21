<template>
  <div>
    <app-nav-login></app-nav-login>
    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Produto</th>
                <th scope="col">Preço unitário</th>
                <th scope="col">Quantidade</th>
                <th scope="col">Subtotal</th>
                <th scope="col">Excluir</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="produto in produtos" :key="produto.id">
                <td>{{ produto.descricao }}</td>
                <td>R$ {{ produto.preco }}</td>
                <td>
                  <button
                    v-on:click="diminuiQtde(produtos.indexOf(produto))"
                    type="button"
                    class="btn"
                  >
                    <i class="fa fa-minus-square"></i>
                  </button>
                  <input
                    type="number"
                    min="1"
                    v-model="produto.quantidade"
                    readonly
                  />
                  <button
                    v-on:click="aumentaQtde(produtos.indexOf(produto))"
                    type="button"
                    class="btn"
                  >
                    <i class="fa fa-plus-square"></i>
                  </button>
                </td>
                <td>
                  <i v-if="produto.subTotal"> {{ produto.subTotal }} </i>
                  <i v-else>
                    {{
                      (produto.subTotal = produto.quantidade * produto.preco)
                    }}
                  </i>
                </td>
                <td>
                  <button v-on:click="excluir(n)" type="button" class="btn">
                    <i class="fa fa-trash"></i>
                  </button>
                </td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <td></td>
                <td></td>
                <td></td>
                <td><b>Subtotal:</b></td>
                <td>
                  <i v-if="total">{{ total }}</i>
                  <i v-else> {{ calculaTotal() }} </i>
                </td>
              </tr>
            </tfoot>
          </table>
          <div class="row mb-3">
            <div class="col-sm-2 text-right">
              <i>selecione o CEP:</i>
            </div>
            <div class="col-sm-2">
              <select class="form-control" v-model="frete.cep">
                <option
                  v-for="endereco in cliente.enderecos"
                  :key="endereco.id"
                >
                  {{ endereco.cep }}
                </option>
              </select>
            </div>
            <div class="col-sm-2">
              <button
                type="button"
                class="btn btn-primary form-control"
                v-on:click="calcularFrete()"
              >
                <i class="fa fa-truck"></i>
                calcular
              </button>
            </div>
            <div class="col-sm-3">
              <router-link to="/cadastro-endereco">
                <i>cadastrar endereço</i>
              </router-link>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-sm-2 text-right">
              <i>selecione um cartão:</i>
            </div>
            <div class="col-sm-2">
              <select class="form-control">
                <option v-for="cartao in cliente.cartoes" :key="cartao.id">
                  {{ cartao.numero }}
                </option>
              </select>
            </div>
            <div class="col-sm-2">
              <input type="text" class="form-control" placeholder="R$ 12.34" />
            </div>
            <div class="col-sm-3">
              <router-link to="/cadastro-cartao">
                <i>cadastrar cartão</i>
              </router-link>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-sm-2 text-right">
              <i>selecione um cupom:</i>
            </div>
            <div class="col-sm-2">
              <select class="form-control">
                <option v-for="cartao in cliente.cartoes" :key="cartao.id">
                  {{ cartao.numero }}
                </option>
              </select>
            </div>
            <div class="col-sm-2">
              <input type="text" class="form-control" value="50" />
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-sm-7 "></div>
            <div class="col-sm-2 text-right">
              <b>Total: R$ </b>
            </div>
            <div class="col-sm-1">
              {{ total }}
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-sm-9 text-right"></div>
            <div class="col-sm-2">
              <button
                v-on:click="finalizaCompra()"
                type="button"
                class="btn btn-success"
              >
                <i class="fa fa-check"></i>
                finalizar compra
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavLogin from "../../components/shared/nav/NavComponent.vue";

export default {
  name: "Carrinho",

  components: {
    "app-nav-login": NavLogin,
  },

  data() {
    return {
      produtos: [
        {
          id: 1,
          descricao: "Vanda",
          preco: 59.9,
          quantidade: 1,
          subTotal: 0,
        },
        {
          id: 2,
          descricao: "Epidendrum",
          preco: 10,
          quantidade: 1,
          subTotal: 0,
        },
        {
          id: 3,
          descricao: "phalaenopsis",
          preco: 29.9,
          quantidade: 1,
          subTotal: 0,
        },
      ],
      total: 0,
      cliente: null,
      frete: {
        cep: null,
      },
    };
  },
  created() {
    this.cliente = JSON.parse(localStorage.getItem("cliente"));
  },
  methods: {
    finalizaCompra() {},
    calcularFrete() {},
    aumentaQtde(index) {
      this.produtos[index].quantidade++;
      this.produtos[index].subTotal =
        this.produtos[index].preco * this.produtos[index].quantidade;
      this.calculaTotal();
    },
    diminuiQtde(index) {
      if (this.produtos[index].quantidade > 1) {
        this.produtos[index].quantidade--;
        this.produtos[index].subTotal =
          this.produtos[index].preco * this.produtos[index].quantidade;
        this.calculaTotal();
      }
    },
    calculaTotal() {
      this.total = 0;
      for (const produto of this.produtos) {
        this.total += produto.subTotal;
      }
    },
    excluir(n) {
      this.numeros.splice(this.numeros.indexOf(n), 1);
    },
  },
};
</script>
