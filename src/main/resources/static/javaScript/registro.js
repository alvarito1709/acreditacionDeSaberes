//const urlBase = 'http://localhost:8080/';
const urlBase = 'https://inscripcionesagencia.bue.edu.ar/acreditaciondesaberes/';

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
        rol:tipoDeUsuario
    }

    var jsonString = JSON.stringify(data);

    var httpConfig = {
        method:'POST',
        headers:{
            'content-type':'application/json'
        },
        body:jsonString
    }

    fetch(url, httpConfig).then(response => {
        if (response.ok){
            const modal = document.getElementById("modalContainer");

            modal.style.display = "flex";
        }
    }).catch(error => alert("Hubo un error al crear tu usuario, verifica los datos ingresados. Cod:" + error))
}