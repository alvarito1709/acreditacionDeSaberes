

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


    gsap.to(tipoDeOficio,{
        duration: 0.1,
        ease:"circ.in",
        display: "none",
        onComplete: () => gsap.to(parent,{
            duration: 0.3,
            ease: "circ.in",
            height: "fit-content"

        })
    })

}