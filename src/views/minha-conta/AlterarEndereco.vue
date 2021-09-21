<template>
  <div>
    <app-nav></app-nav>

    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-3 ml-4 mt-4">
          <app-menu></app-menu>
        </div>
        <div class="col-sm-6">
          <h2 class="text-center mb-3">Alterar endereço</h2>

          <form @submit.prevent="alterar()">
            <div class="row">
              <div class="col-sm-3 mb-3">
                <label>CEP</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtCep"
                  id="txtCep"
                  minlength="8"
                  maxlength="8"
                  v-model="endereco.cep"
                  required
                />
              </div>
              <div class="col-sm-9 mb-3">
                <label>Cidade</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtCidade"
                  id="txtCidade"
                  v-model="endereco.cidade"
                  required
                />
              </div>
              <div class="col-sm-3 mb-3">
                <label>Estado</label>
                <select class="form-control" v-model="endereco.estado">
                  <option v-for="estado in estados" :key="estado">
                    {{ estado }}
                  </option>
                </select>
              </div>
              <div class="col-sm-9 mb-3">
                <label>País</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtPais"
                  id="txtPais"
                  v-model="endereco.pais"
                  required
                />
              </div>
              <div class="col-sm-12 mb-3">
                <label>Bairro</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtBairro"
                  id="txtBairro"
                  v-model="endereco.bairro"
                  required
                />
              </div>
              <div class="col-sm-3 mb-3">
                <label>Tipo</label>
                <select
                  class="form-control"
                  name="optEndereco"
                  id="optEndereco"
                  v-model="endereco.tipoLogradouro"
                >
                  <option value="rua">Rua</option>
                  <option value="avenida">Avenida</option>
                </select>
              </div>
              <div class="col-sm-9 mb-3">
                <label>Logradouro</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtLogradouro"
                  id="txtLogradouro"
                  v-model="endereco.logradouro"
                  required
                />
              </div>
              <div class="col-sm-5 mb-3">
                <label>Numero</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtNumero"
                  id="txtNumero"
                  maxlength="10"
                  v-model="endereco.numero"
                  required
                />
              </div>
              <div class="col-sm-7 mb-3">
                <label>Complemento</label>
                <input
                  class="form-control"
                  type="text"
                  name="txtComplemento"
                  id="txtComplemento"
                  v-model="endereco.complemento"
                />
              </div>
              <div class="col-sm-6 mb-3">
                <label>Tipo de endereco</label>
                <select
                  class="form-control"
                  name="optEndereco"
                  id="optEndereco"
                  v-model="endereco.tipoResidencia"
                >
                  <option value="casa">casa</option>
                  <option value="apartamentp">apartamento</option>
                </select>
              </div>
            </div>
            <div class="row mt-3">
              <div class="col-sm-3"></div>
              <div class="col-sm-6">
                <button type="submit" class="btn btn-primary btn-block mb-5">
                  Salvar endereço
                </button>
              </div>
            </div>
          </form>
        </div>
        <div class="col-sm-3"></div>
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";
import MenuComponent from "../../components/shared/menu/MenuMinhaContaComponent.vue";
import clienteService from "../../_services/clienteServices.js";

export default {
  name: "AlterarEndereco",
  components: {
    "app-nav": NavComponent,
    "app-menu": MenuComponent,
  },
  data() {
    return {
      endereco: {
        id: null,
        cidade: null,
        cep: null,
        estado: null,
        pais: null,
        bairro: null,
        tipoLogradouro: null,
        logradouro: null,
        numero: null,
        complemento: null,
        tipoResidencia: null,
      },
      estados: [
        "AC",
        "AL",
        "AP",
        "AM",
        "BA",
        "CE",
        "DF",
        "ES",
        "GO",
        "MA",
        "MT",
        "MS",
        "MG",
        "PA",
        "PB",
        "PR",
        "PE",
        "PI",
        "RJ",
        "RN",
        "RS",
        "RO",
        "RR",
        "SC",
        "SP",
        "SE",
        "TO",
      ],
    };
  },
  created() {
    let usuario = JSON.parse(localStorage.getItem("cliente"));
    this.endereco.id = localStorage.getItem("idEndereco");
    let enderecos = usuario.enderecos;
    for (const endereco of enderecos) {
      if (endereco.id == this.endereco.id) {
        this.endereco.cidade = endereco.cidade;
        this.endereco.cep = endereco.cep;
        this.endereco.estado = endereco.estado;
        this.endereco.pais = endereco.pais;
        this.endereco.bairro = endereco.bairro;
        this.endereco.logradouro = endereco.logradouro;
        this.endereco.tipoLogradouro = endereco.tipoLogradouro;
        this.endereco.numero = endereco.numero;
        this.endereco.complemento = endereco.complemento;
        this.endereco.tipoResidencia = endereco.tipoResidencia;
      }
    }
  },
  methods: {
    alterar() {
      
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(this.endereco), // We send data in JSON format
      };

      fetch("http://localhost:8080/alterar-endereco", postMethod)
        .then((response) => response.json())
        .then((data) => {
          alert(data.mensagens);
        })
        .then(() => {
          clienteService.consultarCliente();
        });
    },
  },
};
</script>
