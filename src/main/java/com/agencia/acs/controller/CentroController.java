package com.agencia.acs.controller;

import com.agencia.acs.DTO.CentroDTO;
import com.agencia.acs.entities.Centro;
import com.agencia.acs.service.CentroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/centros")
public class CentroController {

    @Autowired
    CentroService centroService;

    @GetMapping("")
    public ModelAndView modalCentros(Model model){


        return new ModelAndView("modalCentros :: modalAgregarCentro");

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

    @PostMapping("/buscarCentroPorId/{id}")
    public ModelAndView buscarCentroParaEliminar(@PathVariable Long id, @RequestParam(value = "tabla") String tabla,
                                                 Model model){

       Centro centroOptional = centroService.buscarCentroPorId(id);

       model.addAttribute("tabla", tabla);

       model.addAttribute("centros", centroOptional);

       return new ModelAndView("board :: modalEliminar");
    }

    @DeleteMapping("/borrarCentro/{id}")
    @Transactional
    public ModelAndView borrarCentro(@PathVariable Long id, Model model, @RequestParam(value = "tabla") String tabla){

        centroService.borrarCentro(id);

        model.addAttribute("tabla", tabla);

        List<Centro> centros = centroService.listarCentros();

        model.addAttribute("centros", centros);

        return new ModelAndView("tablas :: tablaQueCargo");

    }

    @GetMapping("/verCentro/{id}")
    @ResponseBody
    public String buscarCentroDtoPorId(@PathVariable Long id) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        CentroDTO centroDTO =centroService.buscarCentroDTO(id);


        return objectMapper.writeValueAsString(centroDTO) ;
    }

}
