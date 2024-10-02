package com.agencia.acs.controller;


import com.agencia.acs.entities.Token;
import com.agencia.acs.entities.User;
import com.agencia.acs.service.MailService;
import com.agencia.acs.service.PasswordResetService;
import com.agencia.acs.service.TokenService;
import com.agencia.acs.service.UserService;
import com.agencia.acs.util.passwordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

@Controller
@RequestMapping("/password")
public class PasswordResetController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @Autowired
    PasswordResetService passwordResetService;

    @Autowired
    TokenService tokenService;




    @PostMapping("/enviarCorreoRecuperarContrasena")
    @ResponseBody
    public void enviarCorreoContrasena(@RequestParam(value = "username") String username, Model model){


        System.out.println(username);
        User user = userService.buscarUsuarioPorUsername(username);

        String tokenUrl =  "https://inscripcionesagencia.bue.edu.ar/acreditaciondesaberes/password/recuperar-contrasena/" + passwordResetService.passwordResetTokenGenerator(user);

        model.addAttribute("usuario", user);

        mailService.recuperarContrasena(user.getMail(), "Recuperación de Cuenta.", "mail", user, tokenUrl);
    }

    @GetMapping("/recuperar-contrasena/{token}")
    public String mostrarFormularioResetContrasena(@PathVariable("token") String token, Model model){

        Token tokenBuscado = tokenService.buscarPorToken(token);

        if (tokenBuscado != null){

            User user = userService.buscarUsuarioPorMail(tokenBuscado.getMail());

            model.addAttribute("usuario", user);

            return "templateRecuperacionContrasena";
        }

        return null;
    }

    @PostMapping("/cambiarPassword")
    @Transactional
    @ResponseBody
    public Boolean cambiarPassword(@RequestParam(value = "password") String password,@RequestParam(value = "idUsuario") Long idUsuario){

        try{
            User user = userService.buscarUsuario(idUsuario);

            String passEncriptada = passwordEncoder.passwordEncrypt(password);

            user.setPassword(passEncriptada);
            user.setId(idUsuario);

            userService.guardarUsuario(user);

            return true;
        }catch (Error err){
            System.out.println(err);
            return false;
        }

    }
}
