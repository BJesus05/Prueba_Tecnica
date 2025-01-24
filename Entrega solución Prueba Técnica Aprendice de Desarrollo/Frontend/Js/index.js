const modal = document.getElementById('loginModal');
const btnloginInit = document.getElementById("loginBtn");
const closeBtn = document.querySelector('.close-button');
const loginForm = document.getElementById('loginForm');
const loginButton = document.querySelector('.modal-login-button');
const userNameMenu = document.getElementById("userName");


btnloginInit.onclick = function () {
  modal.style.display = "flex";
};

closeBtn.onclick = function () {
  modal.style.display = "none";
};

window.onclick = function (event) {
  if (event.target === modal) {
    modal.style.display = "none";
  }
};

function isValidEmail(email) {
  const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
  return emailRegex.test(email);
}


loginForm.onsubmit = async function (event) {
  event.preventDefault();
  loginButton.textContent = "Cargando...";

  const email = document.getElementById('emailInput').value;
  const password = document.getElementById('passwordInput').value;

  if (!isValidEmail(email)) {
    alert("El correo no tiene un formato válido.");
    return;
  }


  if (!email || !password) {
    alert("Por favor, complete todos los campos.");
    loginButton.textContent = "Acceder";
    return;
  }


  login("http://localhost:8080/api/login",email, password)
  .then((response) => {

  if (response.success) {
    modal.style.display = "none"; 
    btnloginInit.style.display = "none"; 
    userNameMenu.textContent = response.name; 
    userNameMenu.style.display = "inline"; 
    
  } else {
    alert("Error: " + response.message);
  }
  })
  .catch((error) => {
    alert(error.message);
  });


  loginButton.textContent = "Acceder";
};


document.getElementById('registerForm').onsubmit = async function (event) {
  event.preventDefault();

  const firstName = document.getElementById('firstName').value;
  const lastName = document.getElementById('lastName').value;
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  const confirmPassword = document.getElementById('confirmPassword').value;
  const terms = document.getElementById('terms').checked;
  const privacy = document.getElementById('privacy').checked;

  if (password.toUpperCase().trim() !== confirmPassword.toUpperCase().trim()) {
    alert("Las contraseñas no coinciden");
    return;
  }

  if (!isValidEmail(email)) {
    alert("El correo no tiene un formato válido.");
    return;
  }

  if (!terms || !privacy) {
    alert("Debes aceptar los términos y condiciones y la política de privacidad");
    return;
  }

  const userData = {
    firstName: firstName,
    lastName: lastName,
    email: email,
    password: password,
    confirmPassword: confirmPassword
  };

  try {
    const response = await fetch("http://localhost:8080/api/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(userData)
    });

    const result = await response.json();

    if (result.success) {
      alert(result.message);
    } else {
      alert("Error: " + result.message);
    }
  } catch (error) {
    console.error("Error de red:", error);
    alert("Ocurrió un error. Intente nuevamente.");
  }
};


async function login(url, email, password) {
  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ email, password }),  
    });

    const datos = await response.json();

    if (!response.ok) {
      throw new Error(`Error: ${datos.message}`);
    }

    return datos;

  } catch (error) {
    throw new Error(`${error.message}`);
  }
}

