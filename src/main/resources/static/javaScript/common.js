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



function desplegarListaMobile(){

    const lista = document.getElementById("listaDesplegableMobile");
    const pageHeader = document.getElementById("pageHeader");
    const mobileMenuContainer = document.getElementById("mobileMenuContainer");

    let altura = pageHeader.offsetHeight + mobileMenuContainer.offsetHeight;

    if (lista.style.display === 'block' || lista.style.display === ''){
        lista.style.transition = 'height 1s ease'
        lista.style.display = 'none';
    }else{
        lista.style.transition = 'height 1s ease'
        lista.style.top = altura + "px";
        lista.style.display = 'block';
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
    let idusuario;

    let  urlEliminar =''

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

        case 'Entrevistadores':
            urlEliminar = url+"centros/buscarCentroPorRelacionUser/" +elemento.id;
            idusuario =  elemento.getAttribute('data-idusuario');
            break

        case 'Orientadores':
            urlEliminar = url+"centros/buscarCentroPorRelacionUser/" +elemento.id;
            idusuario =  elemento.getAttribute('data-idusuario');
            break

        case 'Evaluadores':
            urlEliminar = url+"centros/buscarCentroPorRelacionUser/" +elemento.id;
            idusuario =  elemento.getAttribute('data-idusuario');
            break

    }




    $.ajax({
        type:'POST',
        url: urlEliminar,
        data: {
            tabla: tabla,
            idusuario: idusuario
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

    let usuarioId;

    let alerta = "";

    switch (tablas){
            case 'Usuarios':
                urlEliminar = url+"user/borrarUsuario/" + idElemento;
                alerta = "No se pudo eliminar al usuario.";
                break;

            case 'Centros':
                urlEliminar = url+"centros/borrarCentro/" + idElemento;
                alerta = "Verifique que no hayan usuarios o trayectos asociados.";
                break;

            case 'Sectores':
                urlEliminar = url+"sectores/borrarCentro/" + idElemento;
                alerta = "Verifique que no haya trayectos o usuarios asociados.";

                break;

            case 'Trayectos':
            urlEliminar = url+"trayectos/borrarTrayectos/" + idElemento;
            alerta = "Verifique que no haya centros o módulos asociados.";

            break;

            case 'Entrevistadores':
            urlEliminar = url+"user/eliminarCentroDeUsuario/" + idElemento;
            alerta = "Hubo un problema al eliminar el centro asociado.";
            usuarioId = elemento.getAttribute("data-idUsuario");

            break;

            case 'Orientadores':
            urlEliminar = url+"user/eliminarCentroDeUsuario/" + idElemento;
            alerta = "Hubo un problema al eliminar el centro asociado.";
            usuarioId = elemento.getAttribute("data-idUsuario");

            break;

            case 'Evaluadores':
            urlEliminar = url+"user/eliminarCentroDeUsuario/" + idElemento;
            alerta = "Hubo un problema al eliminar el centro asociado.";
            usuarioId = elemento.getAttribute("data-idUsuario");

            break;
    }


    $.ajax({
        type:'DELETE',
        url: urlEliminar,
        data:{
            tabla:tablas,
            usuarioId: usuarioId
        },

        success: function (respuesta){
            $("#tableContainer").html(respuesta);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 500) {
                alert(alerta);
            } else {
                console.error('Ocurrió un error:', textStatus, errorThrown);
            }
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
            urlEditar = url + "sectores/modalEdicionSector/" + elemento.id;
            break;

            case 'Usuarios':
            urlEditar = url + "user/editar/" + elemento.id;
            break;

            case 'Usuarios Postulantes':
            urlEditar = url + "user/editar/postulante/" + elemento.id;
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


function mostrarModalParaAgregarTurnoAPostulante(id){



    const urlModal = url+"inscripcion/modalAgregarTurnoEntrevista";
    const modalContainer = document.getElementById("modalesParaAgregarContainer");

    $.ajax({
        type:'GET',
        url:urlModal,
        data:{
            idInscripcion:id
        },

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


    function buscarPaises(){
        fetch('https://restcountries.com/v3.1/all')
            .then(response => response.json())
            .then(data => {

                data.sort((a, b) => a.name.common.localeCompare(b.name.common))

                // Para crear una lista desplegable en HTML, puedes iterar sobre los datos y crear opciones
                const selectElement = document.getElementById('nacionalidadDelPostulante');
                data.forEach(country => {
                    const option = document.createElement('option');
                    option.value = country.name.common; // Código ISO de 2 letras
                    option.text = country.name.common; // Nombre común del país
                    selectElement.appendChild(option);
                });
            })
            .catch(error => console.error('Error:', error));
    }




async function obtenerProvincias() {
    fetch('https://apis.datos.gob.ar/georef/api/provincias?campos=id,nombre')
        .then(response => response.json())
        .then(data => {

            data.provincias.sort((a, b) => a.nombre.localeCompare(b.nombre))

            // Para crear una lista desplegable en HTML, puedes iterar sobre los datos y crear opciones
            const selectElement = document.getElementById('provinciaPostulante');
            data.provincias.forEach(provincia => {
                const option = document.createElement('option');
                option.value = provincia.nombre; // Código ISO de 2 letras
                option.text = provincia.nombre; // Nombre común del país
                option.setAttribute('data', provincia.id);
                selectElement.appendChild(option);
            });
        })
        .catch(error => console.error('Error:', error));
}

    function listarMunicipios(){
        const selectedElement = document.getElementById('provinciaPostulante');
        const optionIndex = selectedElement.selectedIndex;

        const option = selectedElement.children[optionIndex];

        const localidadSelect = document.getElementById('localidadPostulante');

        while(localidadSelect.firstChild){
            localidadSelect.removeChild(localidadSelect.firstChild);
        }

        fetch('https://apis.datos.gob.ar/georef/api/municipios?provincia=' + option.getAttribute('data') + '&campos=id,nombre&max=100')
            .then(response => response.json())
            .then(data => {

                data.municipios.sort((a, b) => a.nombre.localeCompare(b.nombre))

                // Para crear una lista desplegable en HTML, puedes iterar sobre los datos y crear opciones

                data.municipios.forEach(municipio => {
                    const elementoOption = document.createElement('option');
                    elementoOption.value = municipio.nombre; // Código ISO de 2 letras
                    elementoOption.text = municipio.nombre; // Nombre común del país
                    localidadSelect.appendChild(elementoOption);
                });
            })
            .catch(error => console.error('Error:', error));


    }

    function mostrarModalParaAgregarTurnoAcreditacion(id){

        const urlModal = url+"inscripcion/modalAgregarTurnoAcreditacion";
        const modalContainer = document.getElementById("modalesParaAgregarContainer");

        $.ajax({
            type:'GET',
            url:urlModal,
            data:{
                idInscripcion:id
            },

            success: function (respuesta){
                $("#modalesParaAgregarContainer").html(respuesta);
                modalContainer.style.display = "flex";
            }
        })

    }

    function cerrarInfoDatos(){
        const modal = document.getElementById("modalPrimerIngresoPostulanteContainer");

        if (modal.style.display === "none"){
            modal.style.display = "flex";
        }else {
            modal.style.display = "none";
        }
    }

