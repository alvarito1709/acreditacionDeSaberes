//const url = "http://localhost:8080/"
const url = "https://inscripcionesagencia.bue.edu.ar/acreditaciondesaberes/"

const nombreUsuario = document.getElementById("nombreUsuarioNuevo");
const correoUsuario = document.getElementById("correoUsuarioNuevo");
const dniNUevo = document.getElementById("dniNuevo");
const userName = document.getElementById("dniNuevo");
const password = document.getElementById("passwordUsuarioNuevo");
const tipoDeUsuario = document.getElementById("tipoDeUsuario");

function crearUsuario(){
    let urlCrear = url+"user/agregar"

    const usuarioExistente = document.getElementById("usuarioExistente");

    if (usuarioExistente){
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
