//const url = "http://localhost:8080/"
const url = "https://inscripcionesagencia.bue.edu.ar/acreditaciondesaberes/"




const listaDesplegable = document.getElementById("listaEditables");
const elementosSeleccionables = document.getElementsByClassName("puedeSeleccionarse");

function desplegarLista(){

    if (listaDesplegable.style.display === 'block' || listaDesplegable.style.display === ''){
        listaDesplegable.style.transition = 'height 1s ease'
        listaDesplegable.style.display = 'none';
    }else{
        listaDesplegable.style.transition = 'height 1s ease'
        listaDesplegable.style.display = 'block';
    }
}



function mostrarTablas(tabla){

    let urlTablas = url+"tablas"
    let idUsuario;

    switch (tabla){
        case 'Usuarios':
            urlTablas = url+"user/listarUsuarios"

            break;

            case 'Centros':
            urlTablas = url+"centros/listarCentros"

            break;

            case 'Sectores':
            urlTablas = url+"sectores/listarSectores"

            break;

            case 'Modulos':
            urlTablas = url+"modulos/listarModulos"

            break;

            case 'Mis Datos':

                const listaDatosPostulantes = document.getElementById("mostrarDatosPostulante");

                idUsuario = listaDatosPostulantes.getAttribute("data");

                break

    }



    $.ajax({
        type:'POST',
        url: urlTablas,
        data:{
            tabla:tabla,
            id:idUsuario
        },
        success: [function (respuesta){
            $("#tableContainer").html(respuesta);
        }]

    })

}

function mostrarTableroDeInscripcion(){

    const urlTablas = url+"inscripcion"

    $.ajax({
        type:'GET',
        url: urlTablas,
        success: [function (respuesta){
            $("#tableContainer").html(respuesta);
        }]

    })

}

function mostrarAdvertencia(elemento){
    const modal = document.getElementById("modalContainer");
    modal.style.display = "flex";
    let tabla = elemento.getAttribute('data');

    let  urlEliminar =''
    console.log(tabla);

    switch (tabla){
        case 'Usuarios':
           urlEliminar = url+"user/buscarUsuarioPorId/" +elemento.id;
          break;

        case 'Centros':

            urlEliminar = url + 'centros/buscarCentroPorId/' + elemento.id;

            break;

        case 'Sectores':
            urlEliminar = url+"sectores/buscarSectorPorId/" +elemento.id;
            break

        case 'Trayectos':
            urlEliminar = url+"trayectos/buscarTrayectoPorId/" +elemento.id;
            break

    }




    $.ajax({
        type:'POST',
        url: urlEliminar,
        data: {
            tabla: tabla
        },

        success: [function (respuesta){
            $("#modalContainer").html(respuesta);
        }]

    })
}

function ocultarAdvertencia(){
    const modal = document.getElementById("modalContainer");

    modal.style.display="none";
}

function ocultarModalAgregar(){
    const modal = document.getElementById("modalesParaAgregarContainer");

    modal.style.display = "none";
}

function eliminarElemento(elemento){

    const idElemento = elemento.getAttribute("data");
    const tablas = elemento.getAttribute("data-value");
    let urlEliminar = "";

    switch (tablas){
            case 'Usuarios':
                urlEliminar = url+"user/borrarUsuario/" + idElemento;
                break;

            case 'Centros':
                urlEliminar = url+"centros/borrarCentro/" + idElemento;
                break;

            case 'Sectores':
                urlEliminar = url+"sectores/borrarCentro/" + idElemento;

                break;

            case 'Trayectos':
            urlEliminar = url+"trayectos/borrarTrayectos/" + idElemento;

            break;
    }

    $.ajax({
        type:'DELETE',
        url: urlEliminar,
        data:{
            tabla:tablas
        },

        success: function (respuesta){
            $("#tableContainer").html(respuesta);
        }
    })

}


function buscarCentrosOSectorDeUsuario(id, tipoDeUsuario){
    var urlFetch = url + "user/buscarCentrosPorUsuario"

    const userId = id;
    const tabla = tipoDeUsuario;



    $.ajax({
        type:'GET',
        url: urlFetch,
        data:{
            idUsuario: userId,
            tabla:tabla
        },

        success: function (respuesta){
            $("#tableContainer").html(respuesta);
        }
    })
}

// ESTA FUNCION ES PARA HACER APARECER EL MODAL PARA AGREGAR CENTROS Y SECTORES A UN USUARIO

function mostrarModalParaAgregarCentrosAUsuario(usuario, elemento){
    const urlMostrarModal = url + 'user/mostrarModalParaAgregarCentro/' + elemento.id;

    const modalContainer = document.getElementById("modalesParaAgregarContainer");


    $.ajax({
        type:'POST',
        url: urlMostrarModal,


        success: function (respuesta){
            $("#modalesParaAgregarContainer").html(respuesta);
            modalContainer.style.display = "flex";
        }
    })
}


function mostrarModalParaEditar(elemento, tabla){
    let urlEditar;

    const modalContainer = document.getElementById("modalesParaAgregarContainer");


    switch (tabla){
        case 'Centros':

            urlEditar = url + "centros/modalEdicionCentro/" + elemento.id;

            break;

        case 'Trayectos':
            urlEditar = url + "trayectos/modalEdicionTrayecto/" + elemento.id;
            break;

        case 'Sectores':
            urlEditar = url + "modalEdicionSector/" + elemento.id;
            break;

            case 'Usuarios':
            urlEditar = url + "user/editar/" + elemento.id;
            break;
    }

    $.ajax({
        type:'GET',
        url:urlEditar,

        success: function (respuesta){
            $("#modalesParaAgregarContainer").html(respuesta);
            modalContainer.style.display = "flex";
        }
    })
}




//FUNCION PARA CAMBIAR EL BACKGROUND COLOR DEL ELEMENTO SELECCIONADO EN LA LISTA
    for (let i = 0; i < elementosSeleccionables.length; i++ ){

        elementosSeleccionables[i].addEventListener('click', function (){

            for (let i = 0; i < elementosSeleccionables.length; i ++){
                elementosSeleccionables[i].classList.remove('selected');
            }

            elementosSeleccionables[i].classList.add('selected');

        })
    }

