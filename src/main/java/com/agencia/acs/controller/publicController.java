package com.agencia.acs.controller;


import com.agencia.acs.entities.*;

import com.agencia.acs.repository.UserRepository;
import com.agencia.acs.service.*;
import com.agencia.acs.web.CustomUserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("")
public class publicController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostulanteService postulanteService;

    @Autowired
    EntrevistadorService entrevistadorService;

    @Autowired
    EvaluadorService evaluadorService;

    @Autowired
    OrientadorService orientadorService;

    @Autowired
    TrayectoService trayectoService;

    @Autowired
    CentroService centroService;

    @Autowired
    InscripcionService inscripcionService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "errorType", required = false) String errorType) {
        if (error != null) {
            if ("username".equals(errorType)) {
                model.addAttribute("usernameError", "Nombre de usuario inválido");
            } else if ("credentials".equals(errorType)) {
                model.addAttribute("passwordError", "Usuario o contraseña inválidos");
                model.addAttribute("usernameError", "Usuario o contraseña inválidos");
            } else {
                model.addAttribute("loginError", "Credenciales inválidas");
            }
        }
        return "login";
    }

    @GetMapping("/info")
    public String info(){ return "info";}

    @GetMapping("/registro")
    public String regitro(){ return "registro";}

    @GetMapping("/board")
    public String board(HttpSession http, Model model,
                        @AuthenticationPrincipal CustomUserDetails usuarioLogueado) {

        List<GrantedAuthority> autoridades = usuarioLogueado.getRol();

        boolean isPostulante = autoridades.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_POSTULANTE"));

        if (isPostulante) {
            Optional<Postulante> postulanteOptional = postulanteService.buscarPostulantePorId(usuarioLogueado.getId());

            postulanteOptional.ifPresent(postulante -> {
                model.addAttribute("postulante", postulante);
            });
        }

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
    public ModelAndView tablas(Model model, @RequestParam (value = "tabla") String tabla, @RequestParam(value = "id", required = false)Long id) throws JsonProcessingException {

        model.addAttribute("tabla",tabla);

        Optional<List<Inscripcion>> inscripciones;
        Authentication auth;
        CustomUserDetails userDetails;
        Long userId;

        ObjectMapper objectMapper = new ObjectMapper();

        switch(tabla){



            case "Postulantes":

            case "Usuarios Postulantes":
                List<Postulante> listaPostulantes = postulanteService.listarPostulantes();
                model.addAttribute("postulantes", listaPostulantes);
                break;

            case "Entrevistadores":
                List<Entrevistador> listaEntrevistadores = entrevistadorService.listarEntrevistadores();
                model.addAttribute("entrevistadores", listaEntrevistadores);
                break;

            case "Evaluadores":
                List<Evaluador> listaEvaluadores = evaluadorService.listarEvaluadores();
                model.addAttribute("evaluadores", listaEvaluadores);

                break;

            case "Orientadores":
                List<Orientador> listaOrientadores = orientadorService.listarOrientadores();
                model.addAttribute("orientadores", listaOrientadores);
                break;

            case "Trayectos":
                List<Trayecto> listarTrayectos = trayectoService.listarTrayectos();
                List<Centro> listaCentros = centroService.listarCentros();
                model.addAttribute("centros", listaCentros);
                model.addAttribute("trayectos", listarTrayectos);
                break;

            case "Mis Datos":

                Optional<Postulante> postulante = postulanteService.buscarPostulantePorId(id);
                model.addAttribute("postulantes", postulante.get());

                break;

            case "Entrevistas":
                inscripciones = Optional.ofNullable(inscripcionService.listarInscripciones());
                model.addAttribute("inscripciones", inscripciones.get());
                break;

            case "Mis Trayectos":
                auth = SecurityContextHolder.getContext().getAuthentication();
                userDetails = (CustomUserDetails) auth.getPrincipal();
                userId = userDetails.getId();

                inscripciones = Optional.ofNullable(inscripcionService.buscarInscripcionPorPostulante(userId));
                model.addAttribute("inscripciones", inscripciones.get());
                break;

            case "Mis Entrevistas":
                auth = SecurityContextHolder.getContext().getAuthentication();
                userDetails = (CustomUserDetails) auth.getPrincipal();
                userId = userDetails.getId();

                inscripciones = Optional.ofNullable(inscripcionService.buscarInscripcionesPorEntrevistador(userId));
                model.addAttribute("inscripciones", inscripciones.get());
                break;

            case "Mis Postulantes":
                auth = SecurityContextHolder.getContext().getAuthentication();
                userDetails = (CustomUserDetails) auth.getPrincipal();
                userId = userDetails.getId();

                List<Postulante> postulantes = inscripcionService.buscarPostulantesPorEntrevistadorEnLaInscripcion(userId);
                model.addAttribute("postulantes", postulantes);
                break;

            case "Incripciones del Centro":

                auth = SecurityContextHolder.getContext().getAuthentication();
                userDetails = (CustomUserDetails) auth.getPrincipal();

                Optional<Orientador> orientador = orientadorService.buscarOrientadorPorId(userDetails.getId());


                Set<Centro> centrosDelOrientador = orientador.get().getCentros();

                List<Inscripcion> inscripcionesDelOrientador = new ArrayList<>();

                for(Centro centro : centrosDelOrientador){
                    List<Inscripcion> inscripcionesDelCentro = inscripcionService.buscarInscripcionesPorCentro(centro.getId());

                    inscripcionesDelOrientador.addAll(inscripcionesDelCentro);

                }
                
                inscripciones = Optional.of(inscripcionesDelOrientador);
                model.addAttribute("inscripciones", inscripciones.get());

                break;

        }
/*
        if (Objects.equals(tabla, "Postulantes") || Objects.equals(tabla, "Usuarios Postulantes")){
            List<Postulante> listaPostulantes = postulanteService.listarPostulantes();
            model.addAttribute("postulantes", listaPostulantes);
        }

        if (Objects.equals(tabla, "Entrevistadores")){
            List<Entrevistador> listaEntrevistadores = entrevistadorService.listarEntrevistadores();
            model.addAttribute("entrevistadores", listaEntrevistadores);
        }

        if (Objects.equals(tabla, "Evaluadores")){
            List<Evaluador> listaEvaluadores = evaluadorService.listarEvaluadores();
            model.addAttribute("evaluadores", listaEvaluadores);
        }

        if (Objects.equals(tabla, "Orientadores")){
            List<Orientador> listaOrientadores = orientadorService.listarOrientadores();
            model.addAttribute("orientadores", listaOrientadores);
        }

        if (Objects.equals(tabla, "Trayectos")){
            List<Trayecto> listarTrayectos = trayectoService.listarTrayectos();
            List<Centro> listaCentros = centroService.listarCentros();
            model.addAttribute("centros", listaCentros);
            model.addAttribute("trayectos", listarTrayectos);
        }

        if (Objects.equals(tabla, "Mis Datos")){
            Optional<Postulante> postulante = postulanteService.buscarPostulantePorId(id);
            model.addAttribute("postulantes", postulante.get());
        }

        if (Objects.equals(tabla, "Entrevistas")){
            Optional<List<Inscripcion>> inscripciones = Optional.ofNullable(inscripcionService.listarInscripciones());
            model.addAttribute("inscripciones", inscripciones.get());
        }

        if (Objects.equals(tabla, "Mis Trayectos")){

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            Long userId = userDetails.getId();

            Optional<List<Inscripcion>> inscripciones = Optional.ofNullable(inscripcionService.buscarInscripcionPorPostulante(userId));
            model.addAttribute("inscripciones", inscripciones.get());
        }

        if (Objects.equals(tabla, "Mis Entrevistas")){

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            Long userId = userDetails.getId();

            Optional<List<Inscripcion>> inscripciones = Optional.ofNullable(inscripcionService.buscarInscripcionesPorEntrevistador(userId));
            model.addAttribute("inscripciones", inscripciones.get());
        }

        if (Objects.equals(tabla, "Mis Postulantes")){

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            Long userId = userDetails.getId();

            List<Postulante> postulantes = inscripcionService.buscarPostulantesPorEntrevistadorEnLaInscripcion(userId);
            model.addAttribute("postulantes", postulantes);
        }


 */


       return  new ModelAndView("tablas :: tablaQueCargo");


    }

    @GetMapping("/recuperarContrasena")
    public String recuperarContrasena(){


        return "recuperarContraseña";
    }

    @GetMapping("/mail")
    public String mail(){
        return "mail";
    }

    @GetMapping("/iniciarProyecto")
    public void iniciarProyecto(){

        userService.crearPrimerUsuario();
    }



}
