const url = "http://localhost:8080"


const listaDesplegable = document.getElementById("listaEditables");
const contenedores = document.getElementsByClassName("itemContainer");
const lista = document.getElementsByClassName("listItem");

function desplegarLista(){

    if (listaDesplegable.style.display === 'block' || listaDesplegable.style.display === ''){
        listaDesplegable.style.transition = 'height 1s ease'
        listaDesplegable.style.display = 'none';
    }else{
        listaDesplegable.style.transition = 'height 1s ease'
        listaDesplegable.style.display = 'block';
    }
}


//FALTA TERMINAR ESTA API PARA HACER EL MODELANDVIEW EN EL CONTROLADOR
function mostrarTablas(){

    const urlTablas = url+"/tablas"

    $.ajax({

    })

}

