//const url2 = "http://localhost:8080/";
const url2 = "https://inscripcionesagencia.bue.edu.ar/acreditaciondesaberes/";




function editarUsuario(){

    const idUsuario = document.getElementById("idUsuarioEditar");
    const nombreUsuario = document.getElementById("nombreUsuarioEditar");
    const correoUsuario = document.getElementById("correoUsuarioEditar");
    const dniUsuario = document.getElementById("dniEditar");
    const usernameUsuario = document.getElementById("usernameUsuarioEditar");
    const passwordUsuario = document.getElementById("passwordUsuarioEditar");
    const tipoDeUsuario = document.getElementById("tipoDeUsuarioEditar");

    console.log(idUsuario.value);


    const urlEditar = url2 + "user/guardarUsuario/"+idUsuario.value;

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


function editarDatosPostulante(){

    const urlTablas = url2+"user/editarDatosPostulantes"

    const idPostulante = document.getElementById("postulanteId");
    const nombrePostulante = document.getElementById("nombrePostulante");
    const dniPostulante = document.getElementById("dniPostulante");
    const generoPostulante = document.getElementById("generoPostulante");
    const telefonoPostulante = document.getElementById("telefonoPostulante");
    const celularPostulante = document.getElementById("celularPostulante");
    const emailPostulante = document.getElementById("emailPostulante");
    const cuilPostulante = document.getElementById("cuilPostulante");
    const nacionalidadDelPostulante = document.getElementById("nacionalidadDelPostulante");
    const provinciaPostulante = document.getElementById("provinciaPostulante");
    const localidadPostulante = document.getElementById("localidadPostulante");
    const callePostulante = document.getElementById("callePostulante");
    const numeroDeCallePostulante = document.getElementById("numeroDeCallePostulante");
    const pisoPostulante = document.getElementById("pisoPostulante");
    const postulanteRol = document.getElementById("postulanteRol");
    const postulantePassword = document.getElementById("postulantePassword");
    const postulanteUsername = document.getElementById("postulanteUsername");
    const postulanteEstado = document.getElementById("postulanteEstado");



    let data = {
        id:idPostulante.value,
        nombre:nombrePostulante.value,
        dni:dniPostulante.value,
        genero:generoPostulante.value,
        estado:postulanteEstado.value,
        telefono:telefonoPostulante.value,
        celular:celularPostulante.value,
        mail:emailPostulante.value,
        email:emailPostulante.value,
        cuil:cuilPostulante.value,
        nacionalidad:nacionalidadDelPostulante.value,
        provincia: provinciaPostulante.value,
        localidad: localidadPostulante.value,
        calle: callePostulante.value,
        numeroDeCalle: numeroDeCallePostulante.value,
        piso: pisoPostulante.value,
        rol: postulanteRol.value,
        password: postulantePassword.value,
        username: postulanteUsername.value
    }

    const dataString = JSON.stringify(data);

    $.ajax({
        type:'POST',
        headers:{
            'content-type':'application/json'
        },
        url: urlTablas,
        data:dataString,
        success: [function (respuesta){
            $("#tableContainer").html(respuesta);
        }]

    })
}