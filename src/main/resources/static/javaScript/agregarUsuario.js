const url = "http://localhost:8080"

const nombreUsuario = document.getElementById("nombreUsuarioNuevo");
const correoUsuario = document.getElementById("correoUsuarioNuevo");
const dniNUevo = document.getElementById("dniNuevo");
const userName = document.getElementById("usernameUsuarioNuevo");
const password = document.getElementById("passwordUsuarioNuevo");
const tipoDeUsuario = document.getElementById("tipoDeUsuario");

function crearUsuario(){
    const urlCrear = url+"/user/agregar"

    console.log(nombreUsuario.value);

    var data = {
        nombre: nombreUsuario.value,
        mail: correoUsuario.value,
        dni:dniNUevo.value,
        username: userName.value,
        password:password.value,
        rol:tipoDeUsuario.value
    }

    var jsonString = JSON.stringify(data);

    var httpConfig = {
        method:'POST',
        headers:{
            'content-type':'application/json'
        },
        body:jsonString
    }

    fetch(urlCrear, httpConfig).then(response =>response.json().then(result =>{
        console.log("respuesta del servidor" + response);
    }))

}