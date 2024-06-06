package com.agencia.acs.controller;


import com.agencia.acs.entities.*;
import com.agencia.acs.service.*;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PostulanteService postulanteService;

    @Autowired
    ReferenteService referenteService;

    @Autowired
    OrientadorService orientadorService;

    @Autowired
    EvaluadorService evaluadorService;

    @Autowired
    EntrevistadorService entrevistadorService;

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

    @PostMapping("/agregar/postulante")
    public ResponseEntity<String> nuevoPostulante(@RequestBody Postulante postulante){
        try{
            String passwordVieja = postulante.getPassword();
            String passwordNueva = passwordEncoder.passwordEncrypt(passwordVieja);
            postulante.setPassword(passwordNueva);

            userService.guardarUsuario(postulante);
            return ResponseEntity.ok().body("{\"message\": \"Usuario creado con éxito\"}");
        }catch (Exception e){
            return new ResponseEntity<>("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar/entrevistador")
    public ResponseEntity<String> nuevoEntrevistador(@RequestBody Entrevistador entrevistador){
        try{

            String passwordVieja = entrevistador.getPassword();
            String passwordNueva = passwordEncoder.passwordEncrypt(passwordVieja);
            entrevistador.setPassword(passwordNueva);

            userService.guardarUsuario(entrevistador);
            return ResponseEntity.ok().body("{\"message\": \"Usuario creado con éxito\"}");

        }catch (Exception e){

            return new ResponseEntity<>("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar/evaluador")
    public ResponseEntity<String> nuevoEvaluador(@RequestBody Evaluador evaluador){

        try{
            String passwordVieja = evaluador.getPassword();
            String passwordNueva = passwordEncoder.passwordEncrypt(passwordVieja);
            evaluador.setPassword(passwordNueva);

            userService.guardarUsuario(evaluador);
            return ResponseEntity.ok().body("{\"message\": \"Usuario creado con éxito\"}");
        } catch (Exception e){
            return new ResponseEntity<>("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/agregar/orientador")
    public ResponseEntity<String> nuevoOrientador(@RequestBody Orientador orientador){

        try{

            String passwordVieja = orientador.getPassword();
            String passwordNueva = passwordEncoder.passwordEncrypt(passwordVieja);
            orientador.setPassword(passwordNueva);

            userService.guardarUsuario(orientador);
            return ResponseEntity.ok().body("{\"message\": \"Usuario creado con éxito\"}");

        }catch (Exception e){
            return new ResponseEntity<>("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar/referente")
    public ResponseEntity<String> nuevoReferente(@RequestBody Referente referente){

        try{
            String passwordVieja = referente.getPassword();
            String passwordNueva = passwordEncoder.passwordEncrypt(passwordVieja);
            referente.setPassword(passwordNueva);

            userService.guardarUsuario(referente);
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

    @PostMapping("/listarUsuarios")
    public ModelAndView listarUsuarios(Model model, @RequestParam (value = "tabla") String tabla){
        List<User> usuarios = userService.listarUsuarios();

        model.addAttribute("usuarios", usuarios);

        model.addAttribute("tabla", tabla);

        return new ModelAndView("tablas :: tablaQueCargo");

    }

    @PostMapping("/buscarUsuarioPorId/{id}")
    public ModelAndView obtenerUsuarioParaEliminar(Model model, @PathVariable Long id){
        User usuario = userService.buscarUsuario(id);
        model.addAttribute("usuario", usuario);
        return new ModelAndView("board :: modalEliminar");
    }

}
