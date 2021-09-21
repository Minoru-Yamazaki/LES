<template>
  <div>
    <app-nav-login></app-nav-login>
    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-3 ml-4 mt-4"></div>
        <div class="col-sm-6">
          <h2 class="text-center mb-3">Cadastrar Cupom</h2>
          <form @submit.prevent="salvar()">
            <div class="row">
              <div class="col-sm-6 mb-3">
                <label>Nome</label>
                <input
                  class="form-control"
                  type="text"
                  v-model="cupom.nome"
                  required
                />
              </div>
              <div class="col-sm-6 mb-3">
                <label>Tipo de cupom</label>
                <select class="form-control" v-model="cupom.tipoCupom" required>
                  <option v-for="tipo in tipos" :key="tipo">
                    {{ tipo }}
                  </option>
                </select>
              </div>
              <div class="col-sm-6 mb-3">
                <label>valor</label>
                <input
                  class="form-control"
                  type="text"
                  v-model="cupom.valor"
                  required
                />
              </div>
              <div class="col-sm-6 mb-3">
                <label>validade</label>
                <input
                  class="form-control"
                  type="date"
                  v-model="cupom.validade"
                  required
                />
              </div>
              <div class="col-sm-12 mb-3">
                <label>Descrição</label>
                <input
                  class="form-control"
                  type="text"
                  v-model="cupom.descricao"
                  required
                />
              </div>
            </div>
            <button type="submit" class="btn btn-primary btn-block mt-3 mb-3">
              Cadastrar
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavComponent from "../../components/shared/nav/NavComponent.vue";

export default {
  name: "cadastroCupons",

  components: {
    "app-nav-login": NavComponent,
  },

  data() {
    return {
      cupom: {
        nome: null,
        descricao: null,
        tipoCupom: null,
        valor: null,
        validade: null,
      },
      tipos: ["troca", "promocional"],
    };
  },
  created() {},
  methods: {},
};
</script>
