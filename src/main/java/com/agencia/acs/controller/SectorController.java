package com.agencia.acs.controller;


import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Sector;
import com.agencia.acs.service.SectorService;
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

}
