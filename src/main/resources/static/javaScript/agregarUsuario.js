



function crearUsuario(){
    let urlCrear = url+"user/agregar"

    const nombreUsuario = document.getElementById("nombreUsuarioNuevo");
    const correoUsuario = document.getElementById("correoUsuarioNuevo");
    const dniNUevo = document.getElementById("dniNuevo");
    const userName = document.getElementById("dniNuevo");
    const password = document.getElementById("passwordUsuarioNuevo");
    const tipoDeUsuario = document.getElementById("tipoDeUsuario");

    const usuarioExistente = document.getElementById("usuarioExistente");

    if (usuarioExistente.style.display === "block"){
        alert("El usuario ya existe");
    }

    else {

        switch (tipoDeUsuario.value){

            case "ROLE_ADMIN":
                urlCrear = url+"user/agregar"
                break;

            case "ROLE_POSTULANTE":
                urlCrear = url+"user/agregar/postulante"
                break;

            case "ROLE_USER":
                urlCrear = url+"user/agregar"
                break;

            case "ROLE_ENTREVISTADOR":
                urlCrear = url + "user/agregar/entrevistador"
                break;

            case "ROLE_ORIENTADOR":
                urlCrear = url + "user/agregar/orientador"
                break;

            case "ROLE_EVALUADOR":
                urlCrear = url + "user/agregar/evaluador"
                break;

            case "ROLE_REFERENTE":
                urlCrear = url + "user/agregar/referente"
                break;
        }

        var data = {
            nombre: nombreUsuario.value,
            mail: correoUsuario.value,
            dni:dniNUevo.value,
            username: userName.value,
            password:password.value,
            rol:tipoDeUsuario.value,
        }

        var jsonString = JSON.stringify(data);

        console.log(jsonString);

        var httpConfig = {
            method:'POST',
            headers:{
                'content-type':'application/json'
            },
            body:jsonString
        }

        fetch(urlCrear, httpConfig).then(response =>{
            if (response.ok){
                alert("Usuario creado con éxito.");
                cerrarModal();
            }
        }).catch(error =>{
            alert("Hubo un error al crear el usuario, verifique los datos.")
        })

    }



}

function verificarUsuario(){

    const urlVerificar = url+"user/verificarUsuario"

    const user = document.getElementById("dniNuevo");


    $.ajax({
        type:'POST',
        url: urlVerificar,
        data:{
            username:user.value,
        },
        success: [function (respuesta){
            console.log(respuesta)
            if (respuesta === true){
                document.getElementById("usuarioExistente").style.display = "block";
            }
            else {
                document.getElementById("usuarioExistente").style.display = "none";
            }
        }]

    })


}


function validarDatosUsuarioNuevo(){


    const nombreUsuarioNuevo = document.getElementById("nombreUsuarioNuevo");
    const correoUsuarioNuevo = document.getElementById("correoUsuarioNuevo");
    const dniNuevo = document.getElementById("dniNuevo");
    const passwordUsuarioNuevo = document.getElementById("passwordUsuarioNuevo");
    const tipoDeUsuario = document.getElementById("tipoDeUsuario");

    const validacionNombre = new RegExp('^[A-Za-z]+$');
    const validacionEmail = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$');
    const validacionDNI = new RegExp('^\\d{7,11}$');
    const validacionPassword = new RegExp('^[A-Za-z0-9.?¿¡!_-]{8,}$')


    const alertaNombre = document.getElementById("alertaNombre");
    const alertaEmail = document.getElementById("alertaEmail");
    const alertaDNI = document.getElementById("alertaDNI");
    const alertaPassword = document.getElementById("alertaPassword");

    if(validacionNombre.test(nombreUsuarioNuevo.value.replace(/\s/g, '')) === false){
        alertaNombre.style.display = "block";
    }else {
        alertaNombre.style.display = "none";
    }
    if (validacionEmail.test(correoUsuarioNuevo.value) === false){
        alertaEmail.style.display = "block";
    }else {
        alertaEmail.style.display = "none";
    }
    if (validacionDNI.test(dniNuevo.value) === false){
        alertaDNI.style.display = "block";
    }else {
        alertaDNI.style.display = "none";
    }
    if (validacionPassword.test(passwordUsuarioNuevo.value) === false){
        alertaPassword.style.display = "block";
    }else {
        alertaPassword.style.display = "none"
    }


    if (validacionNombre.test(nombreUsuarioNuevo.value.replace(/\s/g, '')) === true && validacionEmail.test(correoUsuarioNuevo.value) === true &&
        validacionDNI.test(dniNuevo.value) === true && validacionPassword.test(passwordUsuarioNuevo.value) === true){


        crearUsuario()

    }

}
