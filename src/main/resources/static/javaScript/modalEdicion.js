const url1 = "http://localhost:8080/"

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