//const url = "http://localhost:8080/";


function buscarTrayectos(){

    const selectSector = document.getElementById("sectorSeleccionado");

    $.ajax({
        type:'POST',
        url: url + "trayectos/buscarTrayectosPorSector",
        data: {sectorId:selectSector.value},
        success: [function (response){
            $("#trayectoSeleccionado").html(response);
        }]
    })

}

function mostrarModalInscripcion(){

    const modalContainer = document.getElementById("modalesParaAgregarContainer");
    $.ajax({
        type:'GET',
        url: url + "inscripcion/modal",
        success: [function (response){
            $("#modalesParaAgregarContainer").html(response);
            modalContainer.style.display = "flex";
        }]
    })
}

function ocultarModalInscripcion(){
    const modalContainer = document.getElementById("modalesParaAgregarContainer");

    modalContainer.style.display = "none";
}





