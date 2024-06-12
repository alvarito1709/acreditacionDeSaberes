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


function mostrarAgregarCentro(){
    let urlMostrarModalCentro = urlBase + "centros";

    const modalContainer = document.getElementById("modalesParaAgregarContainer");

    $.ajax({
        tipe:'GET',
        url:urlMostrarModalCentro,
        success: function (respuesta){
            $("#modalesParaAgregarContainer").html(respuesta);
            modalContainer.style.display = "flex";
        }
    })
}