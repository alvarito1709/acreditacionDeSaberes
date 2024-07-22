const url = "http://localhost:8080/";

const idUsuario = document.getElementById("idUsuarioEditar").value;
const nombreUsuario = document.getElementById("nombreUsuarioEditar");
const correoUsuario = document.getElementById("correoUsuarioEditar");
const dniUsuario = document.getElementById("dniEditar");
const usernameUsuario = document.getElementById("usernameUsuarioEditar");
const passwordUsuario = document.getElementById("passwordUsuarioEditar");
const tipoDeUsuario = document.getElementById("tipoDeUsuarioEditar");


function editarUsuario(){

    console.log(idUsuario);


    const urlEditar = url + "user/guardarUsuario/"+idUsuario;

    var data = {
        nombre: nombreUsuario.value,
        mail: correoUsuario.value,
        dni:dniUsuario.value,
        username:usernameUsuario.value,
        password:passwordUsuario.value,
        rol:tipoDeUsuario.value
    }

    const requestConfig = {
        method:'POST',
        headers:{
            'content-type':'application/json'
        } ,
        body:JSON.stringify(data),

    }

    //HACE FALTA PROBAR ESTA API Y CONSTRUIR SU CONTROLADOR!!!!!!!!!!!!

    fetch(urlEditar, requestConfig).then(response => response.json().then(result =>{
        console.log(result);
    }))


}