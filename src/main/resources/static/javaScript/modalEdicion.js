const url1 = "http://localhost:8080/"

function editarCentro(){

    const idCentro = document.getElementById("idCentroEdicion");
    const nombreNuevo = document.getElementById("nombreCentroEdicion");
    const direccionNueva = document.getElementById("direccionCentroEdicion");
    const telefonoNuevo = document.getElementById("telefonoCentroEdicion");
    const numeroDeCentroNuevo = document.getElementById("numeroDeCentroEdicion");
    const codigoNuevo = document.getElementById("codigoCentroEdicion");
    const cueNuevo = document.getElementById("cueCentroEdicion");
    const tipoNuevo = document.getElementById("tipoCentroEdicion");
    const areaNueva = document.getElementById("areaCentroEdicion");
    const estadoNuevo = document.getElementById("estadoCentroEdicion");

    const urlEditar = url1 + "centros/editarCentro" + idCentro;

    console.log("hasta aca llega");


    $.ajax({
        type:'PUT',
        url: urlEditar,
        data:{
            id: idCentro.value,
            nombre: nombreNuevo.value,
            direccion: direccionNueva.value,
            telefono: telefonoNuevo.value,
            numeroDeCentro: numeroDeCentroNuevo.value,
            codigo: codigoNuevo,
            cue: cueNuevo,
            tipo: tipoNuevo,
            area: areaNueva,
            estado: estadoNuevo
        },
        success: [function (respuesta){
            $("#tableContainer").html(respuesta);
            cerrarModal();
        }]
    })
}