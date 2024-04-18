package com.agencia.acs.controller;


import com.agencia.acs.entities.User;
import com.agencia.acs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String formularioUsuario(){
        return "agregarUsuario";
    }

    @PostMapping("/agregar")
    public void nuevoUsuario(@RequestBody User user){

        userService.guardarUsuario(user);

    }

}
