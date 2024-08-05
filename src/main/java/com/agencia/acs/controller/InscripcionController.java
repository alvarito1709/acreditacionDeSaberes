package com.agencia.acs.controller;


import com.agencia.acs.entities.Sector;
import com.agencia.acs.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/inscripcion")
public class InscripcionController {


    @Autowired
    SectorService sectorService;

    @GetMapping("")
    public ModelAndView inscripcion(Model model){
        List<Sector> sectores = sectorService.listarSectores();
        model.addAttribute("sectores", sectores );

        return new ModelAndView("inscripcionATrayectos::inscripcion");
    }
}
