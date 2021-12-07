<template>
  <div>
    <app-nav></app-nav>

    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-4 ml-4 mt-4">
          <app-menu></app-menu>
        </div>

        <div class="col-sm-4">
          <h2 class="text-center mb-3">Cadastro</h2>
          <form @submit.prevent="alterarCliente()">
            <div class="mb-3">
              <label>CPF</label>
              <input
                class="form-control"
                type="text"
                placeholder="12345678910"
                name="txtCpf"
                id="txtCpf"
                minlength="11"
                maxlength="11"
                pattern="^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}"
                v-model="cliente.cpf"
                required
              />
            </div>
            <div class="mb-3">
              <label>Nome Completo</label>
              <input
                class="form-control"
                type="text"
                name="txtNome"
                id="txtNome"
                v-model="cliente.nome"
                required
              />
            </div>
            <div class="mb-3">
              <label>Sexo:</label>
              <input
                class="ml-2"
                type="radio"
                value="masculino"
                v-model="cliente.sexo"
              />
              <label class="ml-2">Masculino</label>
              <input
                class="ml-2"
                type="radio"
                value="feminino"
                v-model="cliente.sexo"
              />
              <label class="ml-2">Feminino</label>
            </div>
            <div class="mb-3">
              <label>Data de Nascimento</label>
              <input
                class="form-control"
                type="date"
                name="dtNascimento"
                id="dtNascimento"
                v-model="cliente.nascimento"
                required
              />
            </div>
            <div class="mb-3">
              <label>Telefone</label>
              <input
                class="form-control"
                type="text"
                name="Telefone"
                id="txtTelefone"
                value="fixo"
                v-model="cliente.telefone"
                required
              />
            </div>
            <div class="mb-3">
              <label>Tipo de telefone:</label>
              <input
                class="ml-2"
                type="radio"
                id="fixo"
                value="fixo"
                v-model="cliente.tipoTelefone"
              />
              <label class="ml-2" for="fixo">Fixo</label>
              <input
                class="ml-2"
                type="radio"
                id="celular"
                value="celular"
                v-model="cliente.tipoTelefone"
              />
              <label class="ml-2" for="celular">Celular</label>
            </div>
            <div class="text-center mt-3 mb-5">
              <button type="submit" class="btn btn-primary btn-block" id="btnSalvar">
                Salvar
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";
import MenuComponent from "../../components/shared/menu/MenuMinhaContaComponent.vue";
import clienteService from "../../_services/clienteServices.js";

export default {
  name: "MinhaContaComponent",
  components: {
    "app-nav": NavComponent,
    "app-menu": MenuComponent,
  },
  data() {
    return {
      cliente: {
        id: null,
        cpf: null,
        nome: null,
        sexo: null,
        nascimento: null,
        telefone: null,
        tipoTelefone: null,
        totalGasto: null,
        ranking: null,
      },
    };
  },
  created() {
    let usuario = JSON.parse(localStorage.getItem("cliente"));
    this.cliente.id = usuario.id;
    this.cliente.cpf = usuario.cpf;
    this.cliente.nome = usuario.nome;
    this.cliente.sexo = usuario.sexo;
    this.cliente.nascimento = usuario.nascimento;
    this.cliente.telefone = usuario.telefone;
    this.cliente.tipoTelefone = usuario.tipoTelefone;
    this.cliente.totalGasto = usuario.totalGasto;
    this.cliente.ranking = usuario.ranking;
  },
  methods: {
    alterarCliente() {
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(this.cliente), // We send data in JSON format
      };

      fetch("http://localhost:8080/alterar-cliente", postMethod)
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
  },
};
</script>
