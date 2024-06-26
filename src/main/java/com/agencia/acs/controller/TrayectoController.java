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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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


        return new ModelAndView("modalCentros :: modalAgregarTrayecto");

    }

    @PostMapping("/crear")
    public ModelAndView crearTrayecto(@RequestBody Trayecto trayecto, Model model){

        List<Centro> centroSet = new ArrayList<>();

        for(int i = 0;i < trayecto.getCentros().size(); i ++){
            List<Centro> centros = trayecto.getCentros();

            centroSet.add(centroService.buscarCentroPorId(centros.get(i).getId()));
        }

        trayecto.setCentros(centroSet);

        Trayecto nuevoTrayecto = trayectoService.guardarTrayectoNuevo(trayecto);
        ResponseEntity.created(URI.create("/trayectos/" + nuevoTrayecto.getId())).body(nuevoTrayecto);

        String tabla = "Trayectos";

        model.addAttribute("tabla", tabla);

        List<Trayecto> trayectos = trayectoService.listarTrayectos();

        model.addAttribute("trayectos", trayectos);


        return new ModelAndView("tablas :: tablaQueCargo");

    }

}
