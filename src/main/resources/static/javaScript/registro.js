//const urlBase = 'http://localhost:8080/';
const urlBase = 'https://inscripcionesagencia.bue.edu.ar/acreditaciondesaberes/';

const nombreUsuario = document.getElementById("nombreUsuarioNuevo");
const emailUsuario = document.getElementById("emailUsuarioNuevo");
const dniNuevo = document.getElementById("dniUsuarioNuevo");
const password = document.getElementById("passwordUsuarioNuevo");
const tipoDeUsuario = "ROLE_POSTULANTE";


function validarDatosPostulantes(){
    const nombre = document.getElementById("nombreUsuarioNuevo");
    const email = document.getElementById("emailUsuarioNuevo");
    const dni = document.getElementById("dniUsuarioNuevo");
    const password = document.getElementById("passwordUsuarioNuevo");
    const confirmacionPassword = document.getElementById("repetirContraseña");
    const terminos = document.getElementById("terminos");

    const validacionNombre = new RegExp('^[A-Za-z]+$');
    const validacionEmail = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$');
    const validacionDNI = new RegExp('^\\d{7,8}$');
    const validacionPassword = new RegExp('^[A-Za-z0-9.?¿¡!_-]{8,}$')

    const alertaNombre = document.getElementById("alertaNombre");
    const alertaEmail = document.getElementById("alertaEmail");
    const alertaDNI = document.getElementById("alertaDNI");
    const alertaPassword = document.getElementById("alertaPassword");
    const alertaPasswordConfirmacion = document.getElementById("alertaPasswordConfirmacion");
    const alertaTerminos = document.getElementById("alertaTerminos");


    if(validacionNombre.test(nombre.value.replace(/\s/g, '')) === false){
        alertaNombre.style.display = "block";
    }else {
        alertaNombre.style.display = "none";
    }
    if (validacionEmail.test(email.value) === false){
        alertaEmail.style.display = "block";
    }else {
        alertaEmail.style.display = "none";
    }
    if (validacionDNI.test(dni.value) === false){
        alertaDNI.style.display = "block";
    }else {
        alertaDNI.style.display = "none";
    }
    if (validacionPassword.test(password.value) === false){
        alertaPassword.style.display = "block";
    }else {
        alertaPassword.style.display = "none"
    }
    if (password.value !== confirmacionPassword.value){
        alertaPasswordConfirmacion.style.display = "block";
    }else {
        alertaPasswordConfirmacion.style.display = "none"
    }

    if (!terminos.checked){
        alertaTerminos.style.display = "block";
    }else {
        alertaTerminos.style.display = "none";
    }

    if (validacionNombre.test(nombre.value.replace(/\s/g, '')) === true && validacionEmail.test(email.value) === true &&
        validacionDNI.test(dni.value) === true && validacionPassword.test(password.value) === true && password.value === confirmacionPassword.value && terminos.checked){


        registrarPostulante()

    }


}

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