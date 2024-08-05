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





