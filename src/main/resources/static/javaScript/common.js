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

    const urlTablas = url+"/tablas"

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

    for (let i = 0; i < elementosSeleccionables.length; i++ ){

        elementosSeleccionables[i].addEventListener('click', function (){

            for (let i = 0; i < elementosSeleccionables.length; i ++){
                elementosSeleccionables[i].classList.remove('selected');
            }

            elementosSeleccionables[i].classList.add('selected');

        })
    }

