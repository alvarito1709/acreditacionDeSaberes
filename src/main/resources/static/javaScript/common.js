const url = "http://localhost:8080"


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

    let urlTablas = url+"/tablas"

    switch (tabla){
        case 'Usuarios':
            urlTablas = url+"/user/listarUsuarios"

            break;

            case 'Centros':
            urlTablas = url+"/centros/listarCentros"

            break;

            case 'Sectores':
            urlTablas = url+"/sectores/listarSectores"

            break;

            case 'Modulos':
            urlTablas = url+"/modulos/listarModulos"

            break;

    }


    $.ajax({
        type:'POST',
        url: urlTablas,
        data:{
            tabla:tabla
        },
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
           urlEliminar = url+"/user/buscarUsuarioPorId/" +elemento.id;
          break;

        case 'Centros':

            urlEliminar = url + '/centros/buscarCentroPorId/' + elemento.id;

            break;

        case 'Sectores':
            urlEliminar = url+"/sectores/buscarSectorPorId/" +elemento.id;
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
            urlEliminar = url+"/user/borrarUsuario/" + idElemento;
            break;

        case 'Centros':
            urlEliminar = url+"/centros/borrarCentro/" + idElemento;
            break;

        case 'Sectores':
            urlEliminar = url+"/sectores/borrarCentro/" + idElemento;

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
    var urlFetch = url + "/user/buscarCentrosPorUsuario"

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




//FUNCION PARA CAMBIAR EL BACKGROUND COLOR DEL ELEMENTO SELECCIONADO EN LA LISTA
    for (let i = 0; i < elementosSeleccionables.length; i++ ){

        elementosSeleccionables[i].addEventListener('click', function (){

            for (let i = 0; i < elementosSeleccionables.length; i ++){
                elementosSeleccionables[i].classList.remove('selected');
            }

            elementosSeleccionables[i].classList.add('selected');

        })
    }

