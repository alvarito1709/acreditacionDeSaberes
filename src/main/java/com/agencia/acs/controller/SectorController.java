package com.agencia.acs.controller;


import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Sector;
import com.agencia.acs.service.SectorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sectores")
public class SectorController {

    @Autowired
    SectorService sectorService;

    @GetMapping("")
    public ModelAndView modalSectores(){

        return new ModelAndView("modalCentros :: modalAgregarSector");
    }

    @RequestMapping("/crear")
    public ModelAndView verSectores(Model model, @RequestBody Sector sector){

        try{
            sectorService.guardarSector(sector);
            ResponseEntity.ok().body("{\"message\": \"Sector creado con éxito\"}");

            List<Sector> sectores = sectorService.listarSectores();

            model.addAttribute("sectores", sectores);

            String tabla = "Sectores";

            model.addAttribute("tabla", tabla);

            return new ModelAndView("tablas:: tablaQueCargo");
        }catch (Exception e){
            new ResponseEntity<>("Error al crear sector", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ModelAndView("tablas :: tablaQueCargo");
        }

    }

    @PostMapping("/listarSectores")
    public ModelAndView listarSectores(@RequestParam(value = "tabla") String tabla, Model model){

        List<Sector> sectores = sectorService.listarSectores();

        model.addAttribute("sectores", sectores);

        model.addAttribute("tabla", tabla);

        return new ModelAndView("tablas :: tablaQueCargo");
    }

    @PostMapping("/crearSector")
    public ModelAndView crearSector(@RequestBody Sector sector, Model model ){

        try{
            sectorService.guardarSector(sector);
            ResponseEntity.ok().body("{\"message\": \"Sector creado con éxito\"}");

            List<Sector> sectores = sectorService.listarSectores();

            model.addAttribute("sectores", sectores);

            String tabla = "Sectores";

            model.addAttribute("tabla", tabla);

            return new ModelAndView("tablas:: tablaQueCargo");
        }catch (Exception e){
            new ResponseEntity<>("Error al crear sector", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ModelAndView("tablas :: tablaQueCargo");
        }
    }

    @PostMapping("/buscarSectorPorId/{id}")
    public ModelAndView buscarSectorPorId(@PathVariable Long id, @RequestParam(value = "tabla") String tabla, Model model){


        Sector sectorOptional = sectorService.buscarSectorPorId(id);

        model.addAttribute("sectores", sectorOptional);

        model.addAttribute("tabla", tabla);

        return new ModelAndView("board :: modalEliminar");
    }

    @DeleteMapping("/borrarCentro/{id}")
    @Transactional
    public ModelAndView borrarCentro(@PathVariable Long id, @RequestParam(value = "tabla") String tabla, Model model){

        sectorService.borrarSector(id);

        model.addAttribute("tabla", tabla);

        List<Sector> sectores = sectorService.listarSectores();

        model.addAttribute("sectores", sectores);

        return new ModelAndView("tablas :: tablaQueCargo");


    }

    @GetMapping("/verSector/{id}")
    @ResponseBody
    public String verSectorParaEditar(@PathVariable Long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Sector sector = sectorService.buscarSectorDTO(id);

        return objectMapper.writeValueAsString(sector);
    }


}
