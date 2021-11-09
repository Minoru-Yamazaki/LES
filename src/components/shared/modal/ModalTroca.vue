<template>
  <div class="container">
    <!-- Trigger/Open The Modal -->
    <button class="btn btn-primary" v-on:click="modal()">Trocar</button>

    <div id="myModal" class="modal">
      <!-- Modal content -->
      <div class="row mt-5">
        <div class="col-sm-4 ml-4 mt-4"></div>
        <div class="col-sm-4">
          <div class="modal-content">
            <span class="close text-right" v-on:click="fechar()">&times;</span>
            <div>
              <label>Quantidade</label>
              <select class="form-control" v-model="status">
                <option v-for="qtde in geraArray()" :key="qtde">
                  {{ qtde }}
                </option>
              </select>
              <button
                class="btn btn-primary form-control mt-4"
                v-on:click="alterarCompra()"
              >
                Alterar
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModalTroca",
  props: {
    qtdeMax: {
      type: Number,
    },
  },
  components: {},
  data() {
    return {
      compra: null,
      statusCompra: [
        "Aprovada",
        "Separando pedido",
        "Enviado p/ transportadora",
        "Em trânsito",
        "Entregue",
      ],
      status: null,
    };
  },
  created() {
    this.consultarCompra();
  },
  methods: {
    modal() {
      let modal = document.getElementById("myModal");
      modal.style.display = "block";
    },
    fechar() {
      let modal = document.getElementById("myModal");
      modal.style.display = "none";
    },
    consultarCompra() {
      const compra = {
        id: this.id,
      };
      const postMethod = {
        method: "POST", // Method itself
        headers: {
          "Content-type": "application/json; charset=UTF-8", // Indicates the content
        },
        body: JSON.stringify(compra), // We send data in JSON format
      };

      fetch("http://localhost:8080/consultar-compra", postMethod)
        .then((response) => response.json())
        .then((data) => {
          if (data[0].id != null) {
            this.compra = data[0];
            this.preencheStatus();
          } else {
            alert(data[0].mensagens);
          }
        });
    },
    preencheStatus() {
      this.status = this.compra.status;
    },
    alterarCompra() {
      const statusDiferente = this.compra.status != this.status ? true : false;
      if (statusDiferente) {
        const resposta = confirm(
          "Alterar de: '" +
            this.compra.status +
            "'\npara: '" +
            this.status +
            "' ?"
        );

        if (resposta) {
          this.compra.status = this.status;
          const postMethod = {
            method: "POST", // Method itself
            headers: {
              "Content-type": "application/json; charset=UTF-8", // Indicates the content
            },
            body: JSON.stringify(this.compra), // We send data in JSON format
          };

          fetch("http://localhost:8080/alterar-compra", postMethod)
            .then((response) => response.json())
            .then(() => {
              //alert(data.mensagens[0]);
              this.fechar();
              location.reload();
            });
        }
      } else {
        this.fechar();
      }
    },
    geraArray() {
      let vetor = [];
      for (let i = 0; i < this.qtdeMax; i++) {
        vetor.push(i + 1);
      }
      return vetor;
    },
  },
};
</script>

<style>
/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
