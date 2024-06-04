const urlBase = 'http://localhost:8080/';

const nombreUsuario = document.getElementById("nombreUsuarioNuevo");
const emailUsuario = document.getElementById("emailUsuarioNuevo");
const dniNuevo = document.getElementById("dniUsuarioNuevo");
const password = document.getElementById("passwordUsuarioNuevo");
const tipoDeUsuario = "ROLE_POSTULANTE";


function registrarPostulante(){
    const url = urlBase+"user/agregar/postulante"

    var data = {
        nombre: nombreUsuario.value,
        mail: emailUsuario.value,
        dni:dniNuevo.value,
        username: dniNuevo.value,
        password:password.value,
        rol:tipoDeUsuario.value,
    }

    var jsonString = JSON.stringify(data);

    var httpConfig = {
        method:'POST',
        headers:{
            'content-type':'application/json'
        },
        body:jsonString
    }

    fetch(url, httpConfig).then(response =>response.json().then(result =>{
        console.log("respuesta del servidor" + response);
    }))
}