package com.agencia.acs.controller;


import com.agencia.acs.entities.*;
import com.agencia.acs.service.*;
import com.agencia.acs.web.CustomUserDetails;
import org.atteo.evo.inflector.English;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/inscripcion")
public class InscripcionController {


    @Autowired
    SectorService sectorService;

    @Autowired
    InscripcionService inscripcionService;

    @Autowired
    PostulanteService postulanteService;

    @Autowired
    TrayectoService trayectoService;

    @Autowired
    CentroService centroService;

    @Autowired
    EntrevistadorService entrevistadorService;

    @GetMapping("")
    public ModelAndView inscripcion(Model model){
        List<Sector> sectores = sectorService.listarSectores();
        model.addAttribute("sectores", sectores );

        return new ModelAndView("inscripcionATrayectos::inscripcion");
    }

    @GetMapping("/modal")
    public ModelAndView modal(){
        return new ModelAndView("inscripcionATrayectos::modalDeInscripcion");
    }

    @GetMapping("/vistaCuestionario")
    public ModelAndView vistaCuestionario(Model model){
        return new ModelAndView("inscripcionATrayectos::vistaCuestionario");
    }

    @GetMapping("/iniciarCuestionario")
    public  ModelAndView iniciarCuestionario(@RequestParam(value = "trayecto") String trayecto){

       return new ModelAndView("testModulos::"+trayecto);

    }


    @PostMapping("/enviarCuestionario")
    public ModelAndView enviarCuestionario(@RequestParam(value = "nota")Long nota,
                                           @RequestParam(value = "estado") String estado,
                                           @RequestParam(value = "puntajeMaximo")Long puntajeMaximo,
                                           @RequestParam(value = "trayectoId")Long trayectoId,
                                           Model model){

        model.addAttribute("nota", nota);
        model.addAttribute("puntajeMaximo",puntajeMaximo);

        Inscripcion inscripcion = new Inscripcion();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = userDetails.getId();

        Optional<Postulante> postulante = postulanteService.buscarPostulantePorId(userId);

        Optional<Trayecto> trayecto = trayectoService.buscarTrayectoPorId(trayectoId);

        inscripcion.setPostulante(postulante.get());
        inscripcion.setTrayecto(trayecto.get());
        inscripcion.setEstado(estado);
        inscripcion.setNota(String.valueOf(nota));
        inscripcion.setNotaMaximaDelExamen(String.valueOf(puntajeMaximo));

        inscripcionService.nuevaInscripcion(inscripcion);


        if (Objects.equals(estado, "Aprobado sin turno")){
            return new ModelAndView("inscripcionATrayectos::aprobado");
        }else {

            return new ModelAndView("inscripcionATrayectos::desaprobado");
        }


    }

    @GetMapping("/modalAgregarTurnoEntrevista")
    public ModelAndView mostrarModalParaAsignarTurnoEntrevista(@RequestParam(value = "idInscripcion") Long idInscripcion,
                                                               Model model){

        Optional<Inscripcion> inscripcion = inscripcionService.buscarInscripcionPorId(idInscripcion);

        model.addAttribute("inscripcion", inscripcion.get());

        Trayecto trayecto = inscripcion.get().getTrayecto();

        model.addAttribute("trayecto", trayecto);

        Set<Centro> centros =  trayecto.getCentros();

        model.addAttribute("centros", centros);


        return new ModelAndView("modalCentros::modalAgregarEntrevista");
    }

    @PostMapping("/agregarEntrevista")
    public ModelAndView agregarEntrevista(@RequestParam(value = "inscripcionId") Long inscripcionId,
                                          @RequestParam(value = "centroId") Long centroId,
                                          @RequestParam(value = "entrevistadorId" )Long entrevistadorId,
                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date fechaEntrevista,
                                          Model model){

        Optional<Inscripcion> inscripcionOptional = inscripcionService.buscarInscripcionPorId(inscripcionId);

        Optional<Centro> centroOptional = Optional.ofNullable(centroService.buscarCentroPorId(centroId));

        Optional<Entrevistador> entrevistadorOptional = entrevistadorService.buscarEntrevistadorPorId(entrevistadorId);

        if (inscripcionOptional.isPresent()){
            Inscripcion inscripcion = inscripcionOptional.get();
            Centro centro = centroOptional.get();
            Entrevistador entrevistador = entrevistadorOptional.get();

            inscripcion.setCentro(centro);
            inscripcion.setEntrevistador(entrevistador);
            inscripcion.setEstado("Con Turno Entrevista");
            inscripcion.setFechaEntrevista(fechaEntrevista);

            inscripcionService.nuevaInscripcion(inscripcion);
        }

        model.addAttribute("tabla", "Entrevistas");

        List<Inscripcion> inscripciones = inscripcionService.listarInscripciones();
        model.addAttribute("inscripciones", inscripciones);

        return new ModelAndView("tablas :: tablaQueCargo");
    }

    @GetMapping("/buscarEntrevistadores")
    public ModelAndView buscarEntrevistadores(@RequestParam(value = "centroId")Long centroId, Model model){

        List<Entrevistador> entrevistadores = entrevistadorService.buscarEntrevistadoresPorCentro(centroId);

        model.addAttribute("entrevistadores", entrevistadores);

        return new ModelAndView("modalCentros::entrevistadores");

    }
}
