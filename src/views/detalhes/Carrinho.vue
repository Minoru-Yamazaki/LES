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
              <tr v-for="produto in compra.produtos" :key="produto.pro_id">
                <td>{{ produto.nome }}</td>
                <td>R$ {{ produto.preco }}</td>
                <td>
                  <button
                    v-on:click="
                      diminuiQtde(
                        compra.produtos.indexOf(produto),
                        produtos.preco
                      )
                    "
                    type="button"
                    class="btn"
                  >
                    <i class="fa fa-minus-square"></i>
                  </button>
                  <input
                    type="number"
                    min="1"
                    v-model="
                      compra.produtos[compra.produtos.indexOf(produto)]
                        .quantidade
                    "
                    readonly
                  />
                  <button
                    v-on:click="
                      aumentaQtde(
                        compra.produtos.indexOf(produto),
                        produto.preco
                      )
                    "
                    type="button"
                    class="btn"
                  >
                    <i class="fa fa-plus-square"></i>
                  </button>
                </td>
                <td>
                  <i v-if="produto.sub_total"> {{ produto.sub_total }} </i>
                  <i v-else>
                    {{
                      (compra.produtos[
                        compra.produtos.indexOf(produto)
                      ].sub_total =
                        compra.produtos[compra.produtos.indexOf(produto)]
                          .quantidade * produto.preco)
                    }}
                  </i>
                </td>
                <td>
                  <button
                    v-on:click="excluir(compra.produtos.indexOf(produto))"
                    type="button"
                    class="btn"
                  >
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
                  <i v-if="compra.valor">{{ compra.valor }}</i>
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
              <select class="form-control" v-model="compra.cep">
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
      compra: {
        status: "Em processamento",
        data: null,
        valor: 0,
        frete: 0,
        cidade: null,
        estado: null,
        pais: null,
        bairro: null,
        tipo_logradouro: null,
        logradouro: null,
        numero: null,
        complemento: null,
        tipo_residencia: null,
        cep: null,
        cli_id: null,
        cupons: [],
        produtos: [],
      },
      orquideas: [],
      produtos: [
        {
          id: 1,
          preco: 20,
          descricao: "",
          quantidade: 2,
          subTotal: 0,
        },
      ],

      total: 0,
      cliente: null,
      fretes: [
        { cep: "08753445", valor: 10 },
        { cep: "08770330", valor: 5 },
        { cep: "08710290", valor: 7.5 },
        { cep: "08715350", valor: 3.6 },
        { cep: "08717080", valor: 8 },
        { cep: "08710280", valor: 3.8 },
        { cep: "08773160", valor: 12.8 },
        { cep: "08773010", valor: 6.4 },
        { cep: "08717090", valor: 6.7 },
        { cep: "08715470", valor: 4.2 },
        { cep: "08730150", valor: 6.9 },
      ],
    };
  },
  created() {
    this.cliente = JSON.parse(localStorage.getItem("cliente"));

    try {
      this.compra.produtos = JSON.parse(localStorage.getItem("produtos"));
    } catch (error) {
      console.log(error);
    }
  },
  methods: {
    finalizaCompra() {},
    calcularFrete() {
      this.compra.frete = 0;
      for (const frete of this.fretes) {
        if (this.compra.cep == frete.cep) {
          this.compra.frete = frete.valor;
          alert("valor do frete R$: " + frete.valor);
        }
      }
      if (this.compra.frete == 0) {
        alert("CEP Inválido");
      }
    },
    aumentaQtde(index, preco) {
      this.compra.produtos[index].quantidade++;
      this.compra.produtos[index].sub_total =
        preco * this.compra.produtos[index].quantidade;
      this.calculaTotal();
      localStorage.setItem("compra", JSON.stringify(this.compra));
      localStorage.setItem("produtos", JSON.stringify(this.compra.produtos));
    },
    diminuiQtde(index, preco) {
      if (this.compra.produtos[index].quantidade > 1) {
        this.compra.produtos[index].quantidade--;
        this.compra.produtos[index].sub_total =
          preco * this.compra.produtos[index].quantidade;
        this.calculaTotal();
        localStorage.setItem("compra", JSON.stringify(this.compra));
        localStorage.setItem("produtos", JSON.stringify(this.compra.produtos));
      }
    },
    calculaTotal() {
      this.compra.valor = 0;
      try {
        for (const produto of this.compra.produtos) {
          this.compra.valor += produto.sub_total;
        }
      } catch (error) {
        return 0;
      }
    },
    excluir(index) {
      this.compra.produtos.splice(index, 1);
      this.calculaTotal();
      localStorage.setItem("compra", JSON.stringify(this.compra));
      localStorage.setItem("produtos", JSON.stringify(this.compra.produtos));
    },
  },
};
</script>
