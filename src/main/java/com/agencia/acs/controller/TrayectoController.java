package com.agencia.acs.controller;

import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Sector;
import com.agencia.acs.entities.Trayecto;
import com.agencia.acs.service.CentroService;
import com.agencia.acs.service.SectorService;
import com.agencia.acs.service.TrayectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.*;

@Controller
@RequestMapping("/trayectos")
public class TrayectoController {

    @Autowired
    CentroService centroService;

    @Autowired
    SectorService sectorService;

    @Autowired
    TrayectoService trayectoService;

    @GetMapping("")
    public ModelAndView modalTrayectos(Model model){

        List<Centro> centros = centroService.listarCentros();
        model.addAttribute("centros", centros);

        List<Sector> sectores = sectorService.listarSectores();
        model.addAttribute("sectores", sectores);

        model.addAttribute("nuevoTrayecto", new Trayecto());


        return new ModelAndView("modalCentros :: modalAgregarTrayecto");

    }

    @PostMapping("/crear")
    public ModelAndView crearTrayecto(@RequestBody Trayecto trayecto, Model model){



        Trayecto nuevoTrayecto = trayectoService.guardarTrayectoNuevo(trayecto);
        ResponseEntity.created(URI.create("/trayectos/" + nuevoTrayecto.getId())).body(nuevoTrayecto);

        String tabla = "Trayectos";

        model.addAttribute("tabla", tabla);

        List<Trayecto> trayectos = trayectoService.listarTrayectos();

        model.addAttribute("trayectos", trayectos);


        return new ModelAndView("tablas :: tablaQueCargo");

    }

    @PostMapping("/buscarTrayectoPorId/{id}")
    public ModelAndView buscarTrayectoPorId(@PathVariable Long id, @RequestParam(value = "tabla") String tabla,
                                                 Model model){

        Optional<Trayecto> trayectoOptional = trayectoService.buscarTrayectoPorId(id);

        model.addAttribute("tabla", tabla);

        model.addAttribute("trayectos", trayectoOptional.get());

        return new ModelAndView("board :: modalEliminar");
    }

    @DeleteMapping("/borrarTrayectos/{id}")
    @Transactional
    public ModelAndView borrarTrayecto(@PathVariable Long id,
                                       @RequestParam(value = "tabla") String tabla,
                                       Model model){

        trayectoService.borrarTrayecto(id);

        model.addAttribute("tabla", tabla);

        List<Trayecto> trayectos = trayectoService.listarTrayectos();

        model.addAttribute("trayectos", trayectos);

        return new ModelAndView("tablas :: tablaQueCargo");

    }

}
