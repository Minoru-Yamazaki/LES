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
              <tr v-for="produto in compra.produtos" :key="produto.idProduto">
                <td>
                  <router-link
                    :to="{ path: '/detalhes-orquidea/' + produto.idProduto }"
                  >
                    <i>{{ produto.nome }}</i>
                  </router-link>
                </td>
                <td>R$ {{ produto.preco }}</td>
                <td>
                  <button
                    v-on:click="
                      diminuiQtde(
                        compra.produtos.indexOf(produto),
                        produto.preco
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
                      consultarOrquidea(
                        produto.idProduto,
                        compra.produtos.indexOf(produto)
                      )
                    "
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
                      (compra.produtos[
                        compra.produtos.indexOf(produto)
                      ].subTotal =
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
                  <i v-else> {{ calculaSubTotal() }} </i>
                </td>
              </tr>
            </tfoot>
          </table>
          <div class="row mb-3">
            <div class="col-sm-3 text-right">
              <i>código cupom promocional:</i>
            </div>
            <div class="col-sm-2">
              <input
                type="text"
                class="form-control"
                v-model="codigoCupomPromocional"
              />
            </div>
            <div class="col-sm-2">
              <input
                type="text"
                class="form-control"
                v-model="valorCupomPromocional"
                disabled
              />
            </div>
            <div class="col-sm-4">
              <button
                class="btn btn-success"
                v-on:click="consultarCupom(codigoCupomPromocional)"
              >
                <i class="fas fa-plus"></i>
                adicionar
              </button>
              <button
                class="btn btn-danger ml-2"
                v-on:click="removerCupomPromocional()"
              >
                <i class="fas fa-minus"></i>
                remover
              </button>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-sm-3 text-right">
              <i>selecione o CEP:</i>
            </div>
            <div class="col-sm-2">
              <select
                class="form-control"
                v-model="compra.cep"
                v-on:change="calcularFrete()"
              >
                <option
                  v-for="endereco in cliente.enderecos"
                  :key="endereco.id"
                >
                  {{ endereco.cep }}
                </option>
              </select>
            </div>
            <div class="col-sm-2">
              <input
                type="text"
                class="form-control"
                v-model="compra.frete"
                disabled
              />
              <!--  <i class="fa fa-truck"></i> -->
            </div>
            <div class="col-sm-3">
              <router-link to="/cadastro-endereco">
                <i>cadastrar endereço</i>
              </router-link>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-sm-3 text-right">
              <i>selecione um cartão:</i>
              <button
                v-show="!showCartao"
                class="btn btn-light"
                v-on:click="showCartao = true"
              >
                <i class="fas fa-caret-right"></i>
              </button>
              <button
                v-show="showCartao"
                class="btn btn-light"
                v-on:click="showCartao = false"
              >
                <i class="fas fa-caret-down"></i>
              </button>
            </div>
            <div class="col-sm-4">
              <table
                class="row mb-3"
                v-for="cartao in cliente.cartoes"
                :key="cartao.id"
                v-show="showCartao"
              >
                <tr class="col-sm-8">
                  <td>
                    <input
                      type="checkbox"
                      :value="cartao.apelido"
                      v-model="cartoes"
                      v-on:change="verificaCartoesUsados()"
                    />
                    <label> {{ cartao.apelido }} </label>
                  </td>
                </tr>
                <tr class="col-sm-4">
                  <input
                    type="text"
                    class="form-control"
                    v-model="cartao.total"
                    placeholder="12.34"
                    v-on:change="verificaCartoesUsados()"
                  />
                </tr>
              </table>

              <!-- {{ compra }} -->
            </div>
            <div class="col-sm-3">
              <router-link to="/cadastro-cartao">
                <i>cadastrar cartão</i>
              </router-link>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-sm-3 text-right">
              <i>cupom de troca: </i>
              <button
                v-show="!showCupom"
                class="btn btn-light"
                v-on:click="showCupom = true"
              >
                <i class="fas fa-caret-right"></i>
              </button>
              <button
                v-show="showCupom"
                class="btn btn-light"
                v-on:click="showCupom = false"
              >
                <i class="fas fa-caret-down"></i>
              </button>
            </div>
            <div class="col-sm-4">
              <table
                class="row mb-3"
                v-for="cupom in cliente.cupons"
                :key="cupom.id"
                v-show="showCupom"
              >
                <tr class="col-sm-8">
                  <td>
                    <input
                      type="checkbox"
                      :value="cupom.nome"
                      v-model="cupons"
                      v-on:change="verificaCuponsUsados()"
                    />
                    <label> {{ cupom.nome }} </label>
                  </td>
                </tr>
                <tr class="col-sm-4">
                  <input
                    type="text"
                    class="form-control"
                    :value="cupom.valor"
                    disabled
                  />
                </tr>
              </table>
            </div>
            <div class="row mb-1 text-center"></div>
          </div>

          <div class="row mb-3">
            <div class="col-sm-7 "></div>
            <div class="col-sm-2 text-right">
              <b>Total: R$ </b>
            </div>
            <div class="col-sm-1">
              <i v-if="total">{{ total }}</i>
              <i v-else> {{ calculaTotal() }} </i>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-sm-9 text-right"></div>
            <div class="col-sm-3">
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
import clienteService from "../../_services/clienteServices.js";

export default {
  name: "Carrinho",

  components: {
    "app-nav-login": NavLogin,
  },

  data() {
    return {
      compra: {
        status: "Aprovada",
        data: null,
        valor: 0,
        frete: 0,
        cidade: null,
        estado: null,
        pais: null,
        bairro: null,
        tipoLogradouro: null,
        logradouro: null,
        numero: null,
        complemento: null,
        tipoResidencia: null,
        cep: null,
        cliId: null,
        cupons: [],
        produtos: [],
        cartoes: [],
      },
      orquideas: [],
      cupons: [],
      cartoes: [],
      cartoesCadastrados: [
        { numero: "1321312313213213", ativo: true },
        { numero: "8979398619886519", ativo: false },
        { numero: "5646545642198896", ativo: true },
      ],
      valorCupomPromocional: null,
      codigoCupomPromocional: null,
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
      //dropdwon
      showCupom: false,
      showCartao: false,
    };
  },
  created() {
    this.carregaInfos();
    this.addVarTotal();
  },
  methods: {
    setId(id) {
      localStorage.setItem("idOrquidea", id);
    },
    verificaCartoesUsados() {
      this.compra.cartoes = [];
      for (const cartao of this.cartoes) {
        for (const cartaoCliente of this.cliente.cartoes) {
          if (cartao == cartaoCliente.apelido) {
            this.adicionaCartao(cartaoCliente);
          }
        }
      }
    },
    adicionaCartao(cartao) {
      let Cartao = {
        numero: cartao.numero,
        bandeira: cartao.bandeira,
        total: cartao.total,
        idCartao: cartao.id,
      };
      if (cartao.total != 0) {
        this.compra.cartoes.push(Cartao);
      }
    },
    verificaCuponsUsados() {
      let cupomPromo;
      for (const cupom of this.compra.cupons) {
        if (cupom.tipoCupom == "promocional") {
          cupomPromo = cupom;
        }
      }
      this.compra.cupons = [];

      if (cupomPromo) {
        this.compra.cupons.push(cupomPromo);
      }

      for (const cupom of this.cupons) {
        //adiciona cupons a lista de compra, conforme o array cupons
        for (const cupomCliente of this.cliente.cupons) {
          if (cupom == cupomCliente.nome) {
            this.adicionaCupom(cupomCliente);
          }
        }
      }
      this.calculaTotal();
    },
    adicionaCupom(cupom) {
      let Cupom = {
        nome: cupom.nome,
        descricao: cupom.descricao,
        tipoCupom: cupom.tipoCupom,
        valor: cupom.valor,
        idCupom: cupom.id,
        validade: cupom.validade,
      };
      if (cupom.tipoCupom == "promocional") {
        if (!this.temCupomPromocional()) {
          this.valorCupomPromocional = cupom.valor;
          this.compra.cupons.push(Cupom);
          alert("cupom adicionado");

          this.calculaTotal();
        } else {
          alert("Já existe um cupom promocinal");
        }
      } else {
        this.compra.cupons.push(Cupom);
      }
    },
    temCupomPromocional() {
      for (const cup of this.compra.cupons) {
        if (cup.tipoCupom == "promocional") {
          return 1;
        }
      }
      return 0;
    },
    carregaInfos() {
      try {
        this.cliente = JSON.parse(localStorage.getItem("cliente"));
        this.compra.produtos = JSON.parse(localStorage.getItem("produtos"));
        this.compra.cliId = this.cliente.id;
      } catch (error) {
        console.log(error);
      }
    },
    addVarTotal() {
      for (const cartao of this.cliente.cartoes) {
        cartao.total = 0;
      }
    },
    finalizaCompra() {
      if (this.calcularFrete() && this.verificaCartao()) {
        this.preencheEndereco(this.compra.cep);
        this.salvarCompra();
      }
    },
    salvarCompra() {
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(this.compra), // We send data in JSON format
      };

      fetch("http://localhost:8080/salvar-compra", postMethod)
        .then((response) => response.json())
        .then((data) => {
          var mensagem = "";

          for (var x in data.mensagens) {
            mensagem += "\n" + data.mensagens[x];
          }
          alert(mensagem);
        })
        .then(() => {
          clienteService.consultarCliente();
        });
    },
    verificaCartao() {
      let mensagem = "";
      for (const cartao of this.compra.cartoes) {
        for (const cartaoCadastrado of this.cartoesCadastrados) {
          if (
            cartao.numero == cartaoCadastrado.numero &&
            cartaoCadastrado.ativo == false
          ) {
            mensagem += "cartão " + cartao.numero + " recusado\n";
          }
        }
      }
      if (mensagem) {
        alert(mensagem);
        return 0;
      }
      return 1;
    },
    calcularFrete() {
      this.compra.frete = 0;
      for (const frete of this.fretes) {
        if (this.compra.cep == frete.cep) {
          this.compra.frete = frete.valor;
          this.calculaTotal();
          return 1;
        }
      }
      if (this.compra.frete == 0) {
        alert("CEP Inválido");
        this.calculaTotal();
        return 0;
      }
    },
    aumentaQtde(index, preco) {
      this.compra.produtos[index].quantidade++;
      this.compra.produtos[index].subTotal =
        preco * this.compra.produtos[index].quantidade;
      this.calculaSubTotal();
      this.calculaTotal();
      localStorage.setItem("compra", JSON.stringify(this.compra));
      localStorage.setItem("produtos", JSON.stringify(this.compra.produtos));
    },
    diminuiQtde(index, preco) {
      if (this.compra.produtos[index].quantidade > 1) {
        this.compra.produtos[index].quantidade--;
        this.compra.produtos[index].subTotal =
          preco * this.compra.produtos[index].quantidade;
        this.calculaSubTotal();
        this.calculaTotal();
        localStorage.setItem("compra", JSON.stringify(this.compra));
        localStorage.setItem("produtos", JSON.stringify(this.compra.produtos));
      }
    },
    calculaTotal() {
      let valorCupons = 0;
      for (const cupom of this.compra.cupons) {
        valorCupons += cupom.valor;
      }
      this.total = this.compra.valor + this.compra.frete - valorCupons;
      return this.total;
    },
    calculaSubTotal() {
      this.compra.valor = 0;
      try {
        for (const produto of this.compra.produtos) {
          this.compra.valor += produto.subTotal;
        }
      } catch (error) {
        return 0;
      }
    },
    excluir(index) {
      this.compra.produtos.splice(index, 1);
      this.calculaSubTotal();
      this.calculaTotal();
      localStorage.setItem("compra", JSON.stringify(this.compra));
      localStorage.setItem("produtos", JSON.stringify(this.compra.produtos));
    },
    consultarOrquidea(id, index) {
      const json = {
        id: id,
      };

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
            let qtdeAtual = this.compra.produtos[index].quantidade;
            if (qtdeAtual < data[0].quantidade) {
              this.aumentaQtde(index, data[0].valorVenda);
            } else {
              alert("Quantidade insuficiente no estoque");
            }
          } else {
            alert(data[0].mensagens);
          }
        });
    },
    consultarCupom(codigo) {
      if (codigo) {
        const json = {
          codigo: codigo,
        };

        const postMethod = {
          method: "POST", // Method itself
          headers: {
            "Content-type": "application/json; charset=UTF-8", // Indicates the content
          },
          body: JSON.stringify(json), // We send data in JSON format
        };

        fetch("http://localhost:8080/consultar-admcupom", postMethod)
          .then((response) => response.json())
          .then((data) => {
            if (data[0].id != null) {
              this.adicionaCupom(data[0]);
            } else {
              alert(data[0].mensagens);
            }
          });
      }
    },
    removerCupomPromocional() {
      for (const indice in this.compra.cupons) {
        if (this.compra.cupons[indice].tipoCupom == "promocional") {
          this.compra.cupons.splice(indice, 1);
        }
      }
      this.valorCupomPromocional = 0;
      this.codigoCupomPromocional = null;
      this.calculaTotal();
    },
    preencheEndereco(cep) {
      for (const endereco of this.cliente.enderecos) {
        if (cep == endereco.cep) {
          this.compra.cidade = endereco.cidade;
          this.compra.estado = endereco.estado;
          this.compra.pais = endereco.pais;
          this.compra.bairro = endereco.bairro;
          this.compra.tipoLogradouro = endereco.tipoLogradouro;
          this.compra.logradouro = endereco.logradouro;
          this.compra.numero = endereco.numero;
          this.compra.complemento = endereco.complemento;
          this.compra.tipoResidencia = endereco.tipoResidencia;
          this.compra.cep = endereco.cep;
        }
      }
    },
  },
};
</script>
