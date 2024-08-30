

function calcularTamañoElemento(elemento){


    elemento.style.display = 'block';
    elemento.style.visibility = 'hidden';
    elemento.style.position = 'absolute';

    const height = elemento.offsetHeight;

    elemento.style.display = 'none';
    elemento.style.visibility = '';
    elemento.style.position = '';


    return height;

}

function desplegarListaOficios(sector){
    let sectorId = document.getElementById(sector);
    let tipoDeOficio;
    let flechaQueAparece;

    switch (sector){
        case 'arrowServicios':
            tipoDeOficio = document.getElementById("servicios");
            flechaQueAparece = document.getElementById("arrowServiciosDown");
            break;

        case 'arrowMecanica':
            tipoDeOficio = document.getElementById("mecanica");
            flechaQueAparece = document.getElementById("arrowMecanicaDown");
            break;

        case 'arrowCocina':
            tipoDeOficio = document.getElementById("cocina");
            flechaQueAparece = document.getElementById("arrowCocinaDown");
            break;

        case 'arrowIndumentaria':
            tipoDeOficio = document.getElementById("indumentaria");
            flechaQueAparece = document.getElementById("arrowIndumentariaDown");
            break;

        case 'arrowSistemas':
            tipoDeOficio = document.getElementById("sistemas");
            flechaQueAparece = document.getElementById("arrowSistemasDown");
            break;

    }

    sectorId.style.display = "none"
    flechaQueAparece.style.display = "inline";

    const parent = sectorId.parentElement.parentElement;
    const tamanoElemento = calcularTamañoElemento(tipoDeOficio);

    gsap.to(parent, {
        duration:0.3,
        ease:"circ.out",
        height: tamanoElemento + parent.offsetHeight,
        onComplete: () => gsap.set(tipoDeOficio, {display: "block"})
    })
}

function ocultarListaOficios(sector){
    let sectorId = document.getElementById(sector);
    let padreSectorContainer = sectorId.parentElement;
    let tipoDeOficio;
    let flechaQueAparece;

    switch (sector){
        case 'arrowServiciosDown':
            tipoDeOficio = document.getElementById("servicios");
            flechaQueAparece = document.getElementById("arrowServicios");
            break;

        case 'arrowMecanicaDown':
            tipoDeOficio = document.getElementById("mecanica");
            flechaQueAparece = document.getElementById("arrowMecanica");
            break;

        case 'arrowCocinaDown':
            tipoDeOficio = document.getElementById("cocina");
            flechaQueAparece = document.getElementById("arrowCocina");
            break;

        case 'arrowIndumentariaDown':
            tipoDeOficio = document.getElementById("indumentaria");
            flechaQueAparece = document.getElementById("arrowIndumentaria");
            break;

        case 'arrowSistemasDown':
            tipoDeOficio = document.getElementById("sistemas");
            flechaQueAparece = document.getElementById("arrowSistemas");
            break;

    }

    let parent = sectorId.parentElement.parentElement;

    sectorId.style.display = "none";
    flechaQueAparece.style.display = "inline";
    const tamanoElemento = parent.offsetHeight - calcularTamañoElemento(tipoDeOficio);

    tipoDeOficio.style.display = "none";


    gsap.to(parent,{
        height:tamanoElemento,
        ease: "circ.in",
        duration: .3,

        onComplete: () => gsap.set(parent,{height:"fit-content"})
    })

}

function desplegarListaPasosASeguir(elemento){

    const elementoADesplegar = document.getElementById(elemento);
    let arrow;
    let arrowParaOcultar;
    const elementoPadre = elementoADesplegar.parentElement;

    switch (elemento){

        case 'paso1':
            arrow = document.getElementById("arrowPaso1Down");
            arrowParaOcultar = document.getElementById("arrowPaso1");
            break

        case 'paso2':
            arrow = document.getElementById("arrowPaso2Down");
            arrowParaOcultar = document.getElementById("arrowPaso2");
            break

        case 'paso3':
            arrow = document.getElementById("arrowPaso3Down");
            arrowParaOcultar = document.getElementById("arrowPaso3");
            break

        case 'paso4':
            arrow = document.getElementById("arrowPaso4Down");
            arrowParaOcultar = document.getElementById("arrowPaso4");
            break

        case 'paso5':
            arrow = document.getElementById("arrowPaso5Down");
            arrowParaOcultar = document.getElementById("arrowPaso5");
            break
    }

    arrowParaOcultar.style.display = "none";
    arrow.style.display = "inline";

    let tamanoElemento = calcularTamañoElemento(elementoADesplegar);

    gsap.to(elementoPadre,{
        height:elementoPadre.offsetHeight + tamanoElemento,
        ease: "circ.out",
        duration: .3,

        onComplete: () => gsap.set(elementoADesplegar, {display:"block"})
    })

}

function ocultarListaPasosASeguir(elemento){
    const elementoAOcultar = document.getElementById(elemento);
    let arrow;
    let arrowParaOcultar;
    const elementoPadre = elementoAOcultar.parentElement;

    switch (elemento){

        case 'paso1':
            arrowParaOcultar = document.getElementById("arrowPaso1Down");
            arrow = document.getElementById("arrowPaso1");
            break

        case 'paso2':
            arrowParaOcultar = document.getElementById("arrowPaso2Down");
            arrow = document.getElementById("arrowPaso2");
            break

        case 'paso3':
            arrowParaOcultar = document.getElementById("arrowPaso3Down");
            arrow = document.getElementById("arrowPaso3");
            break

        case 'paso4':
            arrowParaOcultar = document.getElementById("arrowPaso4Down");
            arrow = document.getElementById("arrowPaso4");
            break

        case 'paso5':
            arrowParaOcultar = document.getElementById("arrowPaso5Down");
            arrow = document.getElementById("arrowPaso5");
            break
    }

    arrowParaOcultar.style.display = "none";
    arrow.style.display = "inline";

    let tamanoElemento = elementoPadre.offsetHeight - calcularTamañoElemento(elementoAOcultar);

    gsap.to(elementoPadre,{
        height: tamanoElemento ,
        ease: "circ.in",
        duration: .3,

        onComplete: () => {
            gsap.set(elementoAOcultar, {display: "none"})
            gsap.set(elementoPadre,{height:"fit-content"})
        }
    })
}