package com.agencia.acs.controller;


import com.agencia.acs.entities.User;

import com.agencia.acs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("")
public class publicController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/info")
    public String info(){ return "info";}

    @GetMapping("/board")
    public String board(){
        return "board";
    }

    @GetMapping("/common")
    public String common(HttpSession http, Model model){


            User user = (User) http.getAttribute("usuariosession");

            System.out.println(user);
            if (user != null) {
                model.addAttribute("usuario", user);
            }
            //if (user != null){
            //var username = user.getUsername();
            //User usuario = repository.findByUsername(username);
            //model.addAttribute("usuario", usuario );
            //}
        return "common";
    }


    @PostMapping("/tablas")
    public ModelAndView tablas(Model model, @RequestParam (value = "tabla") String tabla){

        model.addAttribute("tabla",tabla);

        //if (tabla == "Postulantes"){
          //  List<User> listaPostulantes = userRepository.findByRol("Postulantes");
            //model.addAttribute("postulantes", listaPostulantes);
        //}

       return  new ModelAndView("tablas :: tablaQueCargo");


    }

}
