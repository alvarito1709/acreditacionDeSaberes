package com.agencia.acs.controller;


import com.agencia.acs.entities.User;
import com.agencia.acs.service.UserService;
import com.agencia.acs.util.passwordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

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
    public ResponseEntity<String> nuevoUsuario(@RequestBody User user){

        try{
            String passwordVieja = user.getPassword();
            String passwordNueva = passwordEncoder.passwordEncrypt(passwordVieja);
            user.setPassword(passwordNueva);
            userService.guardarUsuario(user);
            return ResponseEntity.ok().body("{\"message\": \"Usuario creado con éxito\"}");

        }catch (Exception e){
            return new ResponseEntity<>("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/editar/{id}")
    public ModelAndView formularioEditar(@PathVariable Long id, Model model){

        try{
          User usuario = userService.buscarUsuario(id);
            if (!(usuario == null)){
                model.addAttribute("usuario",usuario);
            }
        }catch (Exception e){
           new ResponseEntity<>("Error al buscar usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ModelAndView("editarUsuario");
    }

    @PostMapping("/guardarUsuario/{id}")
    @Transactional
    public ResponseEntity<User> editarUsuario(@RequestBody User user, @PathVariable Long id){

        Optional<User> usuarioOptional = Optional.ofNullable(userService.buscarUsuario(id));

        if (!usuarioOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        user.setId(usuarioOptional.get().getId());

        user.setPassword(passwordEncoder.passwordEncrypt(user.getPassword()));

        userService.guardarUsuario(user);

        return ResponseEntity.noContent().build();

    }

}
