package com.agencia.acs.controller;


import com.agencia.acs.entities.Inscripcion;
import com.agencia.acs.entities.Postulante;
import com.agencia.acs.entities.Sector;
import com.agencia.acs.entities.Trayecto;
import com.agencia.acs.service.InscripcionService;
import com.agencia.acs.service.PostulanteService;
import com.agencia.acs.service.SectorService;
import com.agencia.acs.service.TrayectoService;
import com.agencia.acs.web.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
}
