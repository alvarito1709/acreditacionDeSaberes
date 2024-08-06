//const url = "http://localhost:8080/";


let trayectoId = "";


//ESTA FUNCION ESTA HARDCODEADA
function buscarTrayectos(){

    const selectSector = document.getElementById("sectorSeleccionado");

  /*  $.ajax({
        type:'POST',
        url: url + "trayectos/buscarTrayectosPorSector",
        data: {sectorId:selectSector.value},
        success: [function (response){
            $("#trayectoSeleccionado").html(response);
        }]
    })

   */

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

    trayectoId = document.getElementById("trayectoSeleccionado").value;

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

    $.ajax({
        type:'GET',
        url: url + "inscripcion/iniciarCuestionario",
        data: {trayecto:trayectoId},
        success: [function (response){
            $("#modalesParaAgregarContainer").html(response);
            modalContainer.style.display = "flex";
        }]
    })

}




