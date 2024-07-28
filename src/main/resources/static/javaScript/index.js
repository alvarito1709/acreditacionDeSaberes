

document.addEventListener("DOMContentLoaded", function() {
    // Muestra el loader
    document.getElementById("loaderContainer").style.display = "flex";
    document.getElementById("bodyCargado").style.display = "none";

    // Simula una carga de 2 segundos
    setTimeout(function() {
        // Oculta el loader y muestra el contenido
        document.getElementById("loaderContainer").style.display = "none";
        document.getElementById("bodyCargado").style.display = "block";
    }, 3000);
});