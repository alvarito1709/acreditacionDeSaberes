const url = "http://localhost:8080"

const nombreUsuario = document.getElementById("nombreUsuarioNuevo");
const correoUsuario = document.getElementById("correoUsuarioNuevo");
const dniNUevo = document.getElementById("dniNuevo");
const userName = document.getElementById("usernameUsuarioNuevo");
const password = document.getElementById("passwordUsuarioNuevo");
const tipoDeUsuario = document.getElementById("tipoDeUsuario");

function crearUsuario(){
    let urlCrear = url+"/user/agregar"

    switch (tipoDeUsuario.value){

        case "ROLE_ADMIN":
            urlCrear = url+"/user/agregar"
            break;

        case "ROLE_POSTULANTE":
            urlCrear = url+"/user/agregar/postulante"
            break;

        case "ROLE_USER":
            urlCrear = url+"/user/agregar"
            break;

        case "ROLE_ENTREVISTADOR":
            urlCrear = url + "/user/agregar/entrevistador"
            break;

        case "ROLE_ORIENTADOR":
            urlCrear = url + "/user/agregar/orientador"
            break;

        case "ROLE_EVALUADOR":
            urlCrear = url + "/user/agregar/evaluador"
            break;

        case "ROLE_REFERENTE":
            urlCrear = url + "/user/agregar/referente"
            break;
    }



    console.log(nombreUsuario.value);

    var data = {
        nombre: nombreUsuario.value,
        mail: correoUsuario.value,
        dni:dniNUevo.value,
        username: userName.value,
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

    fetch(urlCrear, httpConfig).then(response =>response.json().then(result =>{
        console.log("respuesta del servidor" + response);
    }))

}
