package com.agencia.acs.controller;


import com.agencia.acs.DTO.CentroDTO;
import com.agencia.acs.entities.*;
import com.agencia.acs.service.*;
import com.agencia.acs.web.CustomUserDetails;
import org.atteo.evo.inflector.English;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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

    @Autowired
    OrientadorService orientadorService;

    @GetMapping("")
    public ModelAndView inscripcion(Model model,
                                    @AuthenticationPrincipal CustomUserDetails user){
        List<Sector> sectores = sectorService.listarSectores();
        model.addAttribute("sectores", sectores );



        Postulante postulanteLogueado = (postulanteService.buscarPostulantePorId(user.getId())).get();

        System.out.println(postulanteLogueado.getId());

        //Verifica si el usuario tiene algun campo vacío y retorna la alerta para que complete sus datos

        if (Objects.equals(postulanteLogueado.getGenero(), "") ||
                Objects.equals(postulanteLogueado.getTelefono(), "") ||
                Objects.equals(postulanteLogueado.getCelular(), "") ||
                Objects.equals(postulanteLogueado.getCuil(), "") ||
                Objects.equals(postulanteLogueado.getCalle(), "") ||
                Objects.equals(postulanteLogueado.getNumeroDeCalle(), "") ||
                Objects.equals(postulanteLogueado.getPiso(), "") ){


            return new ModelAndView("inscripcionATrayectos::datosIncompletos");
        }
        if (Objects.isNull(postulanteLogueado.getGenero()) ||
                Objects.isNull(postulanteLogueado.getTelefono()) ||
                Objects.isNull(postulanteLogueado.getCelular()) ||
                Objects.isNull(postulanteLogueado.getCuil()) ||
                Objects.isNull(postulanteLogueado.getCalle()) ||
                Objects.isNull(postulanteLogueado.getNumeroDeCalle()) ||
                Objects.isNull(postulanteLogueado.getPiso()) ){


            return new ModelAndView("inscripcionATrayectos::datosIncompletos");
        }
        else {
            return new ModelAndView("inscripcionATrayectos::inscripcion");
        }


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
                                           @RequestParam(value = "centroId")Long centroId,
                                           Model model){

        model.addAttribute("nota", nota);
        model.addAttribute("puntajeMaximo",puntajeMaximo);

        Inscripcion inscripcion = new Inscripcion();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = userDetails.getId();

        Optional<Postulante> postulante = postulanteService.buscarPostulantePorId(userId);

        Optional<Trayecto> trayecto = trayectoService.buscarTrayectoPorId(trayectoId);

        Centro centroOptional = centroService.buscarCentroPorId(centroId);

        inscripcion.setPostulante(postulante.get());
        inscripcion.setTrayecto(trayecto.get());
        inscripcion.setEstado(estado);
        inscripcion.setNota(String.valueOf(nota));
        inscripcion.setNotaMaximaDelExamen(String.valueOf(puntajeMaximo));
        inscripcion.setCentro(centroOptional);

        inscripcionService.nuevaInscripcion(inscripcion);

        model.addAttribute("centro", centroOptional);


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

    @GetMapping("/modalAgregarTurnoAcreditacion")
    public ModelAndView mostrarModalParaAgregarTurnoAcreditacion(@RequestParam(value = "idInscripcion") Long idInscripcion,
                                                                 Model model){
        Optional<Inscripcion> inscripcion = inscripcionService.buscarInscripcionPorId(idInscripcion);

        model.addAttribute("inscripcion", inscripcion.get());

        List<Orientador> orientadores = orientadorService.buscarOrientadoresPorCentro(inscripcion.get().getCentro().getId());

        model.addAttribute("orientadores", orientadores);


        return new ModelAndView("modalCentros::modalAgregarAcreditacion");
    }

    @PostMapping("/agregarEntrevista")
    public ModelAndView agregarEntrevista(@RequestParam(value = "inscripcionId") Long inscripcionId,
                                          @RequestParam(value = "entrevistadorId" )Long entrevistadorId,
                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date fechaEntrevista,
                                          Model model){

        Optional<Inscripcion> inscripcionOptional = inscripcionService.buscarInscripcionPorId(inscripcionId);


        Optional<Entrevistador> entrevistadorOptional = entrevistadorService.buscarEntrevistadorPorId(entrevistadorId);

        if (inscripcionOptional.isPresent()){
            Inscripcion inscripcion = inscripcionOptional.get();
            Entrevistador entrevistador = entrevistadorOptional.get();

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
    public ModelAndView buscarEntrevistadores(@RequestParam(value = "inscripcionId")Long inscripcionId, Model model){

        Optional<Inscripcion> inscripcion = inscripcionService.buscarInscripcionPorId(inscripcionId);

        Long centroId = inscripcion.get().getCentro().getId();

        List<Entrevistador> entrevistadores = entrevistadorService.buscarEntrevistadoresPorCentro(centroId);

        model.addAttribute("entrevistadores", entrevistadores);

        return new ModelAndView("modalCentros::entrevistadores");

    }

    @PostMapping("/agregarTurnoAcreditacion")
    public ModelAndView agregarTurnoAcreditacion(@RequestBody Inscripcion inscripcion,
                                                 @RequestParam(value = "inscripcionId") Long inscripcionId,
                                                 Model model){

        return null;
    }
}
