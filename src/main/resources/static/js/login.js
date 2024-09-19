// Inicio de sesión
async function login() {
  let url = URL_SERVER + "auth/login";

  // Devolver los valores de los campos de texto
  let email = document.getElementById("txtEmail").value;
  let password = document.getElementById("txtPassword").value;

  // Crear un objeto con los valores de los campos de texto
  let data = {
    email: email,
    password: password,
  };

  // Configurar la petición
  let config = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  };

  // Realizar la petición
  let response = await fetch(url, config);
  let token = await response.text();

  // Guardar el token en sessionStorage
  sessionStorage.token = token;
  // Redirigir a la página de clientes
  window.location.href = "/customers.html";
}

// Metodo para cerrar sesión
function logout() {
  sessionStorage.token = null;
  // Redirigir a la página de inicio de sesión
  window.location.href = "/login.html";
}
