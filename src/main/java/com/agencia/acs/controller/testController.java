package com.agencia.acs.controller;


import com.agencia.acs.entities.Sector;
import com.agencia.acs.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class testController {

    @Autowired
    SectorService sectorService;

    @GetMapping("")
    public String test(){
        return "testModulos";
    }



}
