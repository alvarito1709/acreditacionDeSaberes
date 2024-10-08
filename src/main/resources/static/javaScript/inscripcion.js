//const url = "http://localhost:8080/";


let trayectoNombre = "";
let trayectoId = 0;
let centroId = 0;


//ESTA FUNCION ESTA HARDCODEADA
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


/*
    switch (selectSector.value){
        case 'Gastronomia':
            $('.gastronomia').each(function() {
                $(this).css('display', 'block');
            });
            $('.energiaElectrica').each(function() {
                $(this).css('display', 'none');
            });
            $('.construcciones').each(function() {
                $(this).css('display', 'none');
            });
            $('.informatica').each(function() {
                $(this).css('display', 'none');
            });
            $('.mecanicaDeMotos').each(function() {
                $(this).css('display', 'none');
            });
            $('.mecanicaAutomotriz').each(function() {
                $(this).css('display', 'none');
            });
            $('.reparacionYMantenimientoDeMáquinasYEquiposElectricosElectronicos').each(function() {
                $(this).css('display', 'none');
            });

            break;

        case 'Energía Eléctrica':
            $('.gastronomia').each(function() {
                $(this).css('display', 'none');
            });
            $('.energiaElectrica').each(function() {
                $(this).css('display', 'block');
            });
            $('.construcciones').each(function() {
                $(this).css('display', 'none');
            });
            $('.informatica').each(function() {
                $(this).css('display', 'none');
            });
            $('.mecanicaDeMotos').each(function() {
                $(this).css('display', 'none');
            });
            $('.mecanicaAutomotriz').each(function() {
                $(this).css('display', 'none');
            });
            $('.reparacionYMantenimientoDeMáquinasYEquiposElectricosElectronicos').each(function() {
                $(this).css('display', 'none');
            });
            break;

            case 'Construcciones':
                $('.gastronomia').each(function() {
                    $(this).css('display', 'none');
                });
                $('.energiaElectrica').each(function() {
                    $(this).css('display', 'none');
                });
                $('.construcciones').each(function() {
                    $(this).css('display', 'block');
                });
                $('.informatica').each(function() {
                    $(this).css('display', 'none');
                });
                $('.mecanicaDeMotos').each(function() {
                    $(this).css('display', 'none');
                });
                $('.mecanicaAutomotriz').each(function() {
                    $(this).css('display', 'none');
                });
                $('.reparacionYMantenimientoDeMáquinasYEquiposElectricosElectronicos').each(function() {
                    $(this).css('display', 'none');
                });
            break;

            case 'Mecánica Automotriz':
                $('.gastronomia').each(function() {
                    $(this).css('display', 'none');
                });
                $('.energiaElectrica').each(function() {
                    $(this).css('display', 'none');
                });
                $('.construcciones').each(function() {
                    $(this).css('display', 'none');
                });
                $('.informatica').each(function() {
                    $(this).css('display', 'none');
                });
                $('.mecanicaDeMotos').each(function() {
                    $(this).css('display', 'none');
                });
                $('.mecanicaAutomotriz').each(function() {
                    $(this).css('display', 'block');
                });
                $('.reparacionYMantenimientoDeMáquinasYEquiposElectricosElectronicos').each(function() {
                    $(this).css('display', 'none');
                });
            break;

            case 'Mecánico de Motos':
                $('.gastronomia').each(function() {
                    $(this).css('display', 'none');
                });
                $('.energiaElectrica').each(function() {
                    $(this).css('display', 'none');
                });
                $('.construcciones').each(function() {
                    $(this).css('display', 'none');
                });
                $('.informatica').each(function() {
                    $(this).css('display', 'none');
                });
                $('.mecanicaDeMotos').each(function() {
                    $(this).css('display', 'block');
                });
                $('.mecanicaAutomotriz').each(function() {
                    $(this).css('display', 'none');
                });
                $('.reparacionYMantenimientoDeMáquinasYEquiposElectricosElectronicos').each(function() {
                    $(this).css('display', 'none');
                });
            break;

            case 'Reparación y mantenimiento de máquinas y equipos eléctricos - electrónicos':
                $('.gastronomia').each(function() {
                    $(this).css('display', 'none');
                });
                $('.energiaElectrica').each(function() {
                    $(this).css('display', 'none');
                });
                $('.construcciones').each(function() {
                    $(this).css('display', 'none');
                });
                $('.informatica').each(function() {
                    $(this).css('display', 'none');
                });
                $('.mecanicaDeMotos').each(function() {
                    $(this).css('display', 'none');
                });
                $('.mecanicaAutomotriz').each(function() {
                    $(this).css('display', 'none');
                });
                $('.reparacionYMantenimientoDeMáquinasYEquiposElectricosElectronicos').each(function() {
                    $(this).css('display', 'block');
                });
            break;

            case 'Informática':
                $('.gastronomia').each(function() {
                    $(this).css('display', 'none');
                });
                $('.energiaElectrica').each(function() {
                    $(this).css('display', 'none');
                });
                $('.construcciones').each(function() {
                    $(this).css('display', 'none');
                });
                $('.informatica').each(function() {
                    $(this).css('display', 'block');
                });
                $('.mecanicaDeMotos').each(function() {
                    $(this).css('display', 'none');
                });
                $('.mecanicaAutomotriz').each(function() {
                    $(this).css('display', 'none');
                });
                $('.reparacionYMantenimientoDeMáquinasYEquiposElectricosElectronicos').each(function() {
                    $(this).css('display', 'none');
                });
            break;
    }


 */
}

function mostrarModalInscripcion(){

    const trayectoSeleccionado = document.getElementById("trayectoSeleccionado");

    const modalContainer = document.getElementById("modalesParaAgregarContainer");

    if (trayectoSeleccionado.value === ""){
        alert("Seleccione un trayecto")
    }else {
        $.ajax({
            type:'GET',
            url: url + "inscripcion/modal",
            success: [function (response){
                $("#modalesParaAgregarContainer").html(response);
                modalContainer.style.display = "flex";
            }]
        })
    }

}

function ocultarModalInscripcion(){
    const modalContainer = document.getElementById("modalesParaAgregarContainer");

    modalContainer.style.display = "none";
}


function llevarAlCuestionario(){

    const modalContainer = document.getElementById("modalesParaAgregarContainer");

    trayectoNombre = document.getElementById("trayectoSeleccionado").value;

    var listaDesplegable = document.getElementById("trayectoSeleccionado");

    var elementoSeleccionado = listaDesplegable.selectedOptions[0];

    trayectoId = elementoSeleccionado.getAttribute("data-id");

    let desplegableCentros = document.getElementById("centroSeleccionado");

    centroId = desplegableCentros.selectedOptions[0].value;




    $.ajax({
        type:'GET',
        url: url + "inscripcion/vistaCuestionario",
        success: [function (response){
            $("#tableContainer").html(response);
            modalContainer.style.display = "none";
        }]
    })

}

function mostrarCuestionario(){

    const modalContainer = document.getElementById("modalesParaAgregarContainer");

    const trayectoSinTildes = trayectoNombre.normalize("NFD").replace(/[\u0300-\u036f]/g, "")

    $.ajax({
        type:'GET',
        url: url + "inscripcion/iniciarCuestionario",
        data: {trayecto:trayectoSinTildes.toLowerCase().split(' ').join('')},
        success: [function (response){
            $("#modalesParaAgregarContainer").html(response);
            modalContainer.style.display = "flex";
        }]
    })

}

function filtrarCentros(){


    const elementoSeleccionado = document.getElementById("trayectoSeleccionado").selectedOptions[0];
    const idTrayecto = elementoSeleccionado.getAttribute("data-id");


    $.ajax({
        type:'POST',
        url: url + "centros/filtrarCentrosParaInscripcion",
        data: {trayectoId: idTrayecto},
        success: [function (response){
            $("#centroSeleccionado").html(response);
        }]
    })
}

function buscarDireccionCentro(){


    const urlDireccion = url+"centros/direccionCentro";
    const idCentroSeleccionado = document.getElementById("centroSeleccionado").selectedOptions[0].value;

    $.ajax({
        type:'POST',
        url: urlDireccion,
        data: {idCentro: idCentroSeleccionado},
        success: [function (response){
            $("#direccion").html(response);
        }]
    })
}




