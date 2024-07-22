const urlBase = "http://localhost:8080/"




function crearCentro(){
    const urlCrearCentro = urlBase + "centros/crear"


    const nombreCentroNuevo = document.getElementById("nombreCentro");
    const direccionCentroNuevo = document.getElementById("direccionCentro");
    const telefonoCentroNuevo = document.getElementById("telefonoCentro");
    const numeroCentroNuevo = document.getElementById("numeroDeCentro");
    const codigoCentroNuevo = document.getElementById("codigoCentro");
    const cueCentroNuevo = document.getElementById("cueCentro");
    const tipoDeCentroNuevo = document.getElementById("tipoCentro");
    const areaCentroNuevo = document.getElementById("areaCentro");
    const estadoCentroNuevo = document.getElementById("estadoCentro");

    const botonEnviar = document.getElementsByClassName("botonAcreditacion")

    botonEnviar[0].addEventListener('click', ev => {
        ev.preventDefault();
    })



    var data = {
        nombre: nombreCentroNuevo.value,
        direccion: direccionCentroNuevo.value,
        telefono: telefonoCentroNuevo.value,
        numeroDeCentro: numeroCentroNuevo.value,
        codigo: codigoCentroNuevo.value,
        cue: cueCentroNuevo.value,
        tipo:tipoDeCentroNuevo.value,
        area: areaCentroNuevo.value,
        estado: estadoCentroNuevo.value
    }

    let dataString = JSON.stringify(data);



    $.ajax({
        type:'POST',
        headers:{
            'content-type':'application/json'
        },
        url: urlCrearCentro,
        data: dataString,
        success: [function (respuesta){
            $("#tableContainer").html(respuesta);
            cerrarModal();
        }]
    })


}



function cerrarModal(){
    const modal = document.getElementById("modalesParaAgregarContainer")

    modal.style.display = "none";
}


//FUNCION PARA AGREGAR A LA BD DISTINTOS ENTIDADES

function mostrarAgregarModal(elemento){
    let urlMostrarModal = "";

    switch (elemento){
        case 'Centros':
            urlMostrarModal = urlBase + "centros";
            break;

        case 'Trayectos':
            urlMostrarModal = urlBase + "trayectos";
            break;

            case 'Sectores':
            urlMostrarModal = urlBase + "sectores";
            break;

            case 'Modulos':
            urlMostrarModal = urlBase + "modulos";
            break;
    }


    const modalContainer = document.getElementById("modalesParaAgregarContainer");

    $.ajax({
        tipe:'GET',
        url:urlMostrarModal,
        success: function (respuesta){
            $("#modalesParaAgregarContainer").html(respuesta);
            modalContainer.style.display = "flex";
        }
    })
}






//PARA CREAR EL TRAYECTO ESTA FUNCION HACE UN FETCH PARA TRAER UNO A UNO LA LISTA DE CENTROS QUE EL USUARIO SELECCIONO; PARA PODER CREAR UN NUEVO TRAYECTO ASOCIADO A ESTOS.
async function crearTrayecto() {
    const urlCrearCentro = urlBase + "trayectos/crear";

    const nombreNuevo = document.getElementById("nombreTrayecto");
    const condicionesNuevas = document.getElementById("condicionesTrayecto");
    const sectorNuevo = document.getElementById("sectorTrayecto");
    const estadoNuevo = document.getElementById("estadoTrayecto");
    const inputCentros = document.querySelectorAll('input[name="inputCentros"]');

    let centrosNuevos = [];


    var httpConfig = {
        method:'GET',
        accept: 'application/json'
    }

    for (let i = 0; i < inputCentros.length; i++) {
        let centroId = inputCentros[i].id;
        if (inputCentros[i].checked) {
          const centro =await fetch(urlBase + "centros/verCentro/" + centroId, httpConfig)
               .then(response => response.json())
               .catch(error => console.log("hubo un error",  error));
          centrosNuevos.push(centro);
        }
    }




    // Wait for all Centro objects to be fetched before sending the data
    Promise.allSettled(centrosNuevos).then(() => {

        console.log(centrosNuevos);
        const botonEnviar = document.getElementsByClassName("botonAcreditacion")[0];

        botonEnviar.addEventListener('click', ev => {
            ev.preventDefault();
        });

        var data = {
            nombre: nombreNuevo.value,
            condiciones: condicionesNuevas.value,
            sector: { id: sectorNuevo.value },
            estado: estadoNuevo.value,
            centros: centrosNuevos
        };

        let dataString = JSON.stringify(data);

        $.ajax({
            type: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            url: urlCrearCentro,
            data: dataString,
            success: [function (respuesta) {
                $("#tableContainer").html(respuesta);
                cerrarModal();
            }]
        });
    });
}

function crearSector(){
    const urlCrearSector = urlBase+"sectores/crearSector"


    const nombreSector = document.getElementById("nombreSector");
    const descripcionSector = document.getElementById("descripcionSector");
    const estadoSector = document.getElementById("estadoSector");


    const botonEnviar = document.getElementsByClassName("botonAcreditacion")

    botonEnviar[0].addEventListener('click', ev => {
        ev.preventDefault();
    })



    var data = {
        nombre:nombreSector.value,
        descripcion:descripcionSector.value,
        estado:estadoSector.value
    }

    let dataString = JSON.stringify(data);



    $.ajax({
        type:'POST',
        headers:{
            'content-type':'application/json'
        },
        url: urlCrearSector,
        data: dataString,
        success: [function (respuesta){
            $("#tableContainer").html(respuesta);
            cerrarModal();
        }]
    })

}

function crearModulo(){

    const urlCrearSector = urlBase+"modulos/crearModulo"

    const nombreModulo = document.getElementById("nombreModulo");
    const descripcionModulo = document.getElementById("descripcionModulo");
    const estadoModulo = document.getElementById("estadoModulo");
    const trayectoModulo = document.getElementById("trayectoModulo");



    const botonEnviar = document.getElementsByClassName("botonAcreditacion")

    botonEnviar[0].addEventListener('click', ev => {
        ev.preventDefault();
    })



    var data = {
        modulo: nombreModulo.value,
        descripcion: descripcionModulo.value,
        estado: estadoModulo.value,
        trayecto: { id: trayectoModulo.value}
    }

    let dataString = JSON.stringify(data);



    $.ajax({
        type:'POST',
        headers:{
            'content-type':'application/json'
        },
        url: urlCrearSector,
        data: dataString,
        success: [function (respuesta){
            $("#tableContainer").html(respuesta);
            cerrarModal();
        }]
    })
}


async function agregarCentroAUsuario(elemento){
let urlAgregarCentro = urlBase + "user/agregarCentro";

const rol = elemento.getAttribute("data");

const usuario = document.getElementById("nombreUsuario");
const centroNuevo = document.getElementById("centrosParaUsuario");
const sectorNuevo = document.getElementById("sectoresParaUsuario");

let promesas = [];

switch (rol){
    case 'ROLE_ORIENTADOR':

        urlAgregarCentro = urlBase + "user/agregarCentroAOrientador";

        promesas = [
            fetch(urlBase+"user/buscarOrientadorParaEditar/"+usuario.value)
                .then(response => response.json()),
            fetch(urlBase+"centros/verCentro/"+centroNuevo.value)
                .then(response =>response.json()),
            fetch(urlBase+"sectores/verSector/"+sectorNuevo.value)
                .then(response =>response.json())
        ]
        break;

    case 'ROLE_ENTREVISTADOR':

        urlAgregarCentro = urlBase + "user/agregarCentroAEntrevistador";

        promesas = [
            fetch(urlBase+"user/buscarEntrevistadorParaEditar/"+usuario.value)
                .then(response => response.json()),
            fetch(urlBase+"centros/verCentro/"+centroNuevo.value)
                .then(response =>response.json()),
            fetch(urlBase+"sectores/verSector/"+sectorNuevo.value)
                .then(response =>response.json())
        ]

        break;

        case 'ROLE_EVALUADOR':

            urlAgregarCentro = urlBase + "user/agregarCentroAEvaluador";

        promesas = [
            fetch(urlBase+"user/buscarEvaluadorParaEditar/"+usuario.value)
                .then(response => response.json()),
            fetch(urlBase+"centros/verCentro/"+centroNuevo.value)
                .then(response =>response.json()),
            fetch(urlBase+"sectores/verSector/"+sectorNuevo.value)
                .then(response =>response.json())
        ]

        break;
}




    Promise.all(promesas).then(resultados =>{

        const entidadUsuario = resultados[0];
        const entidadCentro = resultados[1];
        const entidadSector = resultados[2];

        
        $.ajax({
            type:'PUT',
            headers:{
                'content-type':'application/json'
            },
            url: urlAgregarCentro +"/" + entidadCentro.id + "/" + entidadSector.id,
            data: JSON.stringify(entidadUsuario),
            success: [function (respuesta){
                $("#tableContainer").html(respuesta);
                cerrarModal();
            }]
        })

    })


}