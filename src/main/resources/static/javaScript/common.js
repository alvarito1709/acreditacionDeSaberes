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
    let idUsuario = {
        idUser:elemento.id
    };

    const urlEliminar = url+"/user/buscarUsuarioPorId/" +elemento.id;

    var httpRequest = {
        method:'POST',
        headers:{
            'content-type':'application/json'
        }
    }

    $.ajax({
        type:'POST',
        url: urlEliminar,

        success: [function (respuesta){
            $("#modalContainer").html(respuesta);
        }]

    })
}

function ocultarAdvertencia(){
    const modal = document.getElementById("modalContainer");

    modal.style.display="none";
}

function eliminarUsuario(elemento){

    console.log(elemento.getAttribute("data"));

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

