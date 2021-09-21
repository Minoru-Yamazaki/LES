export default {
  consultarCliente,
  consultarCartoesCookie,
  consultarEnderecosCookie
}

function consultarCliente() {
  const login = JSON.parse(localStorage.getItem("login"));
  const someData = {
    id: login.id,
  };
  const postMethod = {
    method: "POST", // Method itself
    headers: {
      "Content-type": "application/json; charset=UTF-8", // Indicates the content
    },
    body: JSON.stringify(someData), // We send data in JSON format
  };

  return fetch("http://localhost:8080/consultar-cliente", postMethod)
    .then((response) => response.json())
    .then((data) => {
      if (data[0].id != null) {
        localStorage.setItem("cliente", JSON.stringify(data[0]));

      } else {
        alert(data[0].mensagens);
      }
    });
}

function consultarCartoesCookie() {
  let cliente = JSON.parse(localStorage.getItem("cliente"));

  return cliente.cartoes;
}

function consultarEnderecosCookie() {
  let cliente = JSON.parse(localStorage.getItem("cliente"));

  return cliente.enderecos;
}

