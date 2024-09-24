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

                $.ajax({
                    type:'POST',
                    url: url+"user/enviarCorreoRecuperarContrasena",
                    data:{
                        username:dniUsuarioNuevo.value,
                    },

                    success: function (respuesta){
                        document.getElementById("usuarioNoExiste").style.display = "none";
                        document.getElementById("verificarUsuario").style.display = "none";
                        document.getElementById("mailEnviado").style.display = "flex";
                        console.log("el usuario existe");
                    },
                    catch: function (err){
                        alert("Hubo un error al enviar mail");
                    }
                })

            }
            else {
                document.getElementById("usuarioNoExiste").style.display = "block";
            }
        }]

    })
}