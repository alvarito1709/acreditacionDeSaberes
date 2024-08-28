

function validarTrayecto(){

    const modalContainer = document.getElementById("modalesParaAgregarContainer");

    const preguntas = document.getElementsByClassName("respuestaSelect");
    let puntaje = 0;
    let puntajeMinimo = preguntas.length * 0.8;
    let estado = "";

       for(let i = 0; i < preguntas.length; i++){
           let respuesta = preguntas[i].value;
        if (respuesta === 'VERDADERO') {
            puntaje++;
        }
       }

       if (puntaje > puntajeMinimo){
           estado = "Aprobado sin turno"
       }else {
           estado = "Desaprobado"
       }


       $.ajax({
           type:'POST',
           url: url+"inscripcion/enviarCuestionario",
           data:{
               nota:puntaje,
               estado: estado,
               puntajeMaximo:preguntas.length,
               trayectoId:trayectoId

           },
           success: [function (respuesta){
               $("#tableContainer").html(respuesta);
               modalContainer.style.display = "none";
           }]

       })

}