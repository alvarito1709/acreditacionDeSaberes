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
                    url: url+"password/enviarCorreoRecuperarContrasena",
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


function verificarContrasena(){

    const urlCambiarPassword = url + "password/cambiarPassword"

    const id = document.getElementById("idUsuario")

    const validacionPassword = new RegExp('^[A-Za-z0-9.?¿¡!_-]{8,}$')

    const cambiarContrasena1 = document.getElementById("cambiarContrasena1");
    const cambiarContrasena2 = document.getElementById("cambiarContrasena2");

    const passwordIncorrecta = document.getElementById("passwordIncorrecta");
    const passwordNoCoincide = document.getElementById("passwordNoCoincide");

    const formularioCambiarContrasena = document.getElementById("verificarUsuario");
    const contrasenaCambiadaConExito = document.getElementById("mailEnviado");

    if (cambiarContrasena1.value !== cambiarContrasena2.value){
        passwordNoCoincide.style.display= "block";
    }
    else  if (validacionPassword.test(cambiarContrasena1.value) === false ){
        passwordNoCoincide.style.display = "none";
        passwordIncorrecta.style.display = "block";
    }
    else {
        $.ajax({
            type:'POST',
            url: urlCambiarPassword,
            data:{
                password:cambiarContrasena1.value,
                idUsuario:id.value
            },
            success: [function (respuesta){

                console.log(respuesta);

                if (respuesta === true){
                    contrasenaCambiadaConExito.style.display = "flex";
                    formularioCambiarContrasena.style.display = "none";
                }
                else {
                    alert("Hubo un error al tratar de cambiar la contraseña.");
                }


            }],

            catch: function (err){
                    alert("Hubo un error al tratar de cambiar la contraseña.")
            }

        })
    }

}