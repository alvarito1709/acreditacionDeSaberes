const url1 = "http://localhost:8080/"
//const url1 = "https://inscripcionesagencia.bue.edu.ar/acreditaciondesaberes/"

function editarCentro(){

    var idCentro = document.getElementById("idCentroEdicion");

    var urlEditar = url1 + "centros/editarCentro/" + idCentro.value;


    var nombreNuevo = document.getElementById("nombreCentroEdicion");
    var direccionNueva = document.getElementById("direccionCentroEdicion");
    var telefonoNuevo = document.getElementById("telefonoCentroEdicion");
    var numeroDeCentroNuevo = document.getElementById("numeroDeCentroEdicion");
    var codigoNuevo = document.getElementById("codigoCentroEdicion");
    var cueNuevo = document.getElementById("cueCentroEdicion");
    var tipoNuevo = document.getElementById("tipoCentroEdicion");
    var areaNueva = document.getElementById("areaCentroEdicion");
    var estadoNuevo = document.getElementById("estadoCentroEdicion");

    let data = {
        id: idCentro.value,
        nombre: nombreNuevo.value,
        direccion: direccionNueva.value,
        telefono: telefonoNuevo.value,
        numeroDeCentro: numeroDeCentroNuevo.value,
        codigo: codigoNuevo.value,
        cue: cueNuevo.value,
        tipo: tipoNuevo.value,
        area: areaNueva.value,
        estado: estadoNuevo.value
    }

    const dataString = JSON.stringify(data)


    $.ajax({
        type:'POST',
        url: urlEditar,
        headers:{
            'content-type':'application/json'
        },
        data: dataString,
        success: [function (respuesta){
            $("#tableContainer").html(respuesta);
            cerrarModal();
        }]
    })

    console.log("hasta aca llega");
}

async function editarTrayecto(){
    const idTrayectoEditado = document.getElementById("idTrayectoNuevo");
    const nombreTrayectoEditado = document.getElementById("nombreTrayecto");
    const sectorTrayectoEditado = document.getElementById("sectorTrayecto");
    const estadoTrayectoEditado = document.getElementById("estadoTrayecto");
    const condicionesTrayectoEditado = document.getElementById("condicionesTrayecto");
    const inputCentros = document.querySelectorAll('input[name="inputCentros"]');

    const urlEditarTrayecto = url1 +"trayectos/editarTrayecto/"+ idTrayectoEditado.value;


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


    Promise.allSettled(centrosNuevos).then(() => {

        const botonEnviar = document.getElementsByClassName("botonAcreditacion")[0];

        botonEnviar.addEventListener('click', ev => {
            ev.preventDefault();
        });

        var data = {
            id: idTrayectoEditado.value,
            nombre: nombreTrayectoEditado.value,
            estado: estadoTrayectoEditado.value,
            condiciones: condicionesTrayectoEditado.value,
            sector: { id: sectorTrayectoEditado.value },
            centros: centrosNuevos
        };

        let dataString = JSON.stringify(data);

        console.log(data);

        $.ajax({
            type: 'PUT',
            headers: {
                'content-type': 'application/json'
            },
            url: urlEditarTrayecto,
            data: dataString,
            success: [function (respuesta) {
                $("#tableContainer").html(respuesta);
                cerrarModal();
            }]
        });
    });
}