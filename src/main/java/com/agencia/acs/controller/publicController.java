package com.agencia.acs.controller;


import com.agencia.acs.entities.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class publicController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

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
    
}
