package com.agencia.acs.controller;

import com.agencia.acs.entities.Centro;
import com.agencia.acs.service.CentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/centros")
public class CentroController {

    @Autowired
    CentroService centroService;

    @GetMapping("")
    public ModelAndView modalCentros(Model model){


        return new ModelAndView("modalCentros :: modalesDeAgregar");

    }

    @PostMapping("/crear")
    public ModelAndView crearCentro(@RequestBody Centro centro, Model model){

        try{
            centroService.guardarNuevoCentro(centro);
            ResponseEntity.ok().body("{\"message\": \"Centro creado con éxito\"}");

            List<Centro> centros = centroService.listarCentros();

            model.addAttribute("centros", centros);

            String tabla = "Centros";

            model.addAttribute("tabla", tabla);

            return new ModelAndView("tablas:: tablaQueCargo");
        }catch (Exception e){
             new ResponseEntity<>("Error al crear centro", HttpStatus.INTERNAL_SERVER_ERROR);
             return new ModelAndView("tablas :: tablaQueCargo");
        }
    }

    @PostMapping("/listarCentros")
    public ModelAndView listarCentros(@RequestParam(value = "tabla") String tabla, Model model){

        List<Centro> centros = centroService.listarCentros();

        model.addAttribute("centros", centros);

        model.addAttribute("tabla", tabla);

        return new ModelAndView("tablas :: tablaQueCargo");
    }

}
