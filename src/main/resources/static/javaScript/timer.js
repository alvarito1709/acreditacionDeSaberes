
let minutos = 30;

let segundos = 0;

let contenedorMinutos;
let contenedorSegundos



let intervalo;
let tiempoDeIntervalo;

let segundosRestantes = 1800; // 30 minutos * 60 segundos/minuto


function iniciarCronometro() {
    intervalo = setInterval(() => {
        segundosRestantes--;
        const minutos = Math.floor(segundosRestantes / 60);
        const segundos = segundosRestantes % 60;
        document.getElementById('timer').textContent = `${minutos}:${segundos.toString().padStart(2, '0')}`;

        if (segundosRestantes === 0) {
            clearInterval(intervalo);
            alert('¡Tiempo agotado!');
            validarTrayecto()
            // Aquí puedes agregar código para ejecutar una acción cuando el tiempo se agote

        }
    }, 1000);
}

function detenerCronometro() {
    clearInterval(intervalo);
}
