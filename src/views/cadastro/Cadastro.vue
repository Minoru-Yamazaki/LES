<template>
  <div>
    <nav class="navbar bg-dark navbar-dark justify-content-between mb-3">
      <router-link to="/"
        ><i class="fa fa-home fa-2x text-white"></i
      ></router-link>
    </nav>

    <div class="container-fluid">
      <!-- Control the column width, and how they should appear on different devices -->
      <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
          <h2 class="text-center mb-3">Cadastre-se</h2>
          <form @submit.prevent="salvar()">
            <div class="mb-3">
              <label>Email</label>
              <input
                class="form-control"
                type="email"
                placeholder="minoru@gmail.com"
                name="txtEmail"
                id="txtEmail"
                v-model="cliente.login.email"
                required
              />
            </div>
            <div class="mb-3">
              <label>Senha</label>
              <input
                class="form-control"
                type="password"
                name="pwdSenha"
                id="pwdSenha"
                v-model="cliente.login.senha"
                required
              />
            </div>
            <div class="mb-3">
              <label>Confirmar Senha</label>
              <input
                class="form-control"
                type="password"
                name="pwdConfSenha"
                id="pwdConfSenha"
                v-model="cliente.login.senhaConfirm"
                required
              />
            </div>
            <div class="mb-3">
              <label>CPF</label>
              <input
                class="form-control"
                type="text"
                placeholder="12345678910"
                name="txtCpf"
                id="txtCpf"
                v-model="cliente.cpf"
                minlength="11"
                maxlength="11"
                pattern="^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}"
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
              <label class="mr-3">Sexo:</label>
              <input
                class="mr-1"
                type="radio"
                id="masculino"
                name="sexo"
                value="masculino"
                v-model="cliente.sexo"
              />
              <label class="mr-3" for="masculino">Masculino</label>
              <input
                class="mr-1"
                type="radio"
                id="feminino"
                name="sexo"
                value="feminino"
                v-model="cliente.sexo"
              />
              <label for="feminino">Feminino</label><br />
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
                minlength="11"
                maxlength="11"
                name="Telefone"
                id="txtTelefone"
                v-model="cliente.telefone"
                required
              />
            </div>
            <div class="mb-3">
              <label class="mr-3">Tipo de telefone:</label>
              <input
                class="mr-1"
                type="radio"
                id="fixo"
                name="tipoTelefone"
                value="fixo"
                v-model="cliente.tipoTelefone"
              />
              <label class="mr-3" for="fixo">Fixo</label>
              <input
                class="mr-1"
                type="radio"
                id="celular"
                name="tipoTelefone"
                value="celular"
                v-model="cliente.tipoTelefone"
              />
              <label for="celular">Celular</label><br />
            </div>
            <div class="text-center mt-3 mb-5">
              <button
                type="submit"
                class="btn btn-primary btn-block"
                id="btnCadastrar"
              >
                Criar seu Cadastro
              </button>
              <br />
              Já tem um cadastro?
              <router-link to="/login">Entrar</router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CadastroComponent",
  data() {
    return {
      cliente: {
        cpf: null,
        nome: null,
        sexo: null,
        nascimento: null,
        telefone: null,
        tipoTelefone: null,
        login: {
          email: null,
          senha: null,
          senhaConfirm: null,
        },
      },
    };
  },

  methods: {
    salvar() {
      //console.log(JSON.stringify(this.login))

      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(this.cliente), // We send data in JSON format
      };

      fetch("http://localhost:8080/salvar-cliente", postMethod)
        .then((response) => response.json())
        .then((data) => {
          var mensagem = "";
          for (var x in data.mensagens) {
            if (data.mensagens[x] == "Salvo com sucesso") {
              window.location.href = "/#/login";
            }
            mensagem += "\n" + data.mensagens[x];
          }
          alert(mensagem);
        });
    },
  },
};
</script>
