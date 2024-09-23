//const url = "http://localhost:8080/";
const url = "https://inscripcionesagencia.bue.edu.ar/acreditaciondesaberes/";

function verificarUsuarioPerdido(){
    const urlVerificar = url + "user/verificarUsuario"

    const dniUsuarioNuevo = document.getElementById("verificarDNI");

    $.ajax({
        type:'POST',
        url: urlVerificar,
        data:{
            username:dniUsuarioNuevo.value,
        },
        success: [function (respuesta){

            if (respuesta === true){
                document.getElementById("usuarioNoExiste").style.display = "none";
                console.log("el usuario existe")
            }
            else {
                document.getElementById("usuarioNoExiste").style.display = "block";
            }
        }]

    })
}