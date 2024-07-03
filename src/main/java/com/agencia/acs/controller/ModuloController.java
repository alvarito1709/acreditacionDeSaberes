package com.agencia.acs.controller;


import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Modulo;
import com.agencia.acs.entities.Trayecto;
import com.agencia.acs.service.ModuloService;
import com.agencia.acs.service.TrayectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/modulos")
public class ModuloController {

    @Autowired
    ModuloService moduloService;

    @Autowired
    TrayectoService trayectoService;

    @GetMapping("")
    public ModelAndView modalModulos(Model model){

        List<Trayecto> trayectos = trayectoService.listarTrayectos();

        model.addAttribute("trayectos", trayectos);


        return new ModelAndView("modalCentros :: modalAgregarModulos");

    }

    @PostMapping("/listarModulos")
    public ModelAndView listarModulos(@RequestParam(value = "tabla") String tabla, Model model){

        List<Modulo> modulos = moduloService.listarModulos();

        List<Trayecto> trayectos = trayectoService.listarTrayectos();

        model.addAttribute("trayectos", trayectos);

        model.addAttribute("modulos", modulos);

        model.addAttribute("tabla", tabla);

        return new ModelAndView("tablas :: tablaQueCargo");
    }

    @PostMapping("/crearModulo")
    public ModelAndView crearModulo(@RequestBody Modulo modulo, Model model){

        Optional<Trayecto> trayectoOptional = trayectoService.buscarTrayectoPorId(modulo.getTrayecto().getId());

        if (trayectoOptional.isPresent()){
            modulo.setTrayecto(trayectoOptional.get());
        }

        try{


            moduloService.crearModulo(modulo);
            ResponseEntity.ok().body("{\"message\": \"Modulo creado con éxito\"}");

            List<Modulo> modulos = moduloService.listarModulos();

            model.addAttribute("modulos", modulos);

            String tabla = "Modulos";

            model.addAttribute("tabla", tabla);

            return new ModelAndView("tablas:: tablaQueCargo");
        } catch (Exception e){
            new ResponseEntity<>("Error al crear modulo", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ModelAndView("tablas :: tablaQueCargo");
        }

    }
}
