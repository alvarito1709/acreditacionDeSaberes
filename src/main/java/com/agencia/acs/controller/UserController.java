package com.agencia.acs.controller;


import com.agencia.acs.DTO.OrientadorDTO;
import com.agencia.acs.entities.*;
import com.agencia.acs.service.*;
import com.agencia.acs.util.passwordEncoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

    @Autowired
    CentroService centroService;

    @Autowired
    SectorService sectorService;

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
    public ModelAndView obtenerUsuarioParaEliminar(Model model, @PathVariable Long id, @RequestParam(value = "tabla") String tabla){
        User usuario = userService.buscarUsuario(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("tabla", tabla);
        return new ModelAndView("board :: modalEliminar");
    }

    @DeleteMapping("/borrarUsuario/{id}")
    @Transactional
    public ModelAndView borrarUsuario(Model model, @PathVariable Long id, @RequestParam(value = "tabla")String tabla){

        userService.borrarUsuario(id);

        model.addAttribute("tabla", tabla);

        List<User> usuarios = userService.listarUsuarios();

        model.addAttribute("usuarios", usuarios);

        return new ModelAndView("tablas :: tablaQueCargo");


    }

    @PostMapping("/mostrarModalParaAgregarCentro/{id}")
    public ModelAndView buscarUsuarioParaAgregarCentro(@PathVariable Long id, Model model){

        User usuario = userService.buscarUsuario(id);

        model.addAttribute("usuario", usuario);

        List<Centro> centros = centroService.listarCentros();

        model.addAttribute("centros", centros);

        List<Sector> sectores = sectorService.listarSectores();

        model.addAttribute("sectores", sectores);

        return new ModelAndView("modalCentros :: modalAgregarCentrosAUsuarios");

    }

    @PutMapping("/agregarCentroAOrientador/{centroid}/{sectorid}")
    @Transactional
    public ModelAndView agregarCentroAOrientador(Model model ,
                                              @RequestBody Orientador orientador,
                                              @PathVariable Long centroid,
                                              @PathVariable Long sectorid){


        Optional<Orientador> orientadorOptional = orientadorService.buscarOrientadorPorId(orientador.getId());


        orientadorOptional.ifPresent(value -> orientador.setId(value.getId()));

        Set<Centro> centrosDelOrientador = orientadorService.buscarOrientadorPorId(orientador.getId()).get().getCentros();
        Set<Sector> sectoresDelOrientador = orientadorService.buscarOrientadorPorId(orientador.getId()).get().getSectores();


        centrosDelOrientador.add(centroService.buscarCentroPorId(centroid));
        sectoresDelOrientador.add(sectorService.buscarSectorPorId(sectorid));

        orientador.setCentros(centrosDelOrientador);
        orientador.setSectores(sectoresDelOrientador);

        orientadorService.guardarOrientador(orientador);

           List<Orientador> orientadores = orientadorService.listarOrientadores();
            model.addAttribute("orientadores", orientadores);

            String tabla = "Orientadores";

        model.addAttribute("tabla", tabla);

        return new ModelAndView("tablas :: tablaQueCargo");
    }

    @PutMapping("/agregarCentroAEntrevistador/{centroid}/{sectorid}")
    @Transactional
    public ModelAndView agregarCentroAEntrevistador(Model model ,
                                                    @RequestBody Entrevistador entrevistador,
                                              @PathVariable Long centroid,
                                              @PathVariable Long sectorid) {


        Optional<Entrevistador> entrevistadorOptional = entrevistadorService.buscarEntrevistadorPorId(entrevistador.getId());


        entrevistadorOptional.ifPresent(value -> entrevistador.setId(value.getId()));

        Set<Centro> centrosDelEntrevistador = entrevistadorService.buscarEntrevistadorPorId(entrevistador.getId()).get().getCentros();
        Set<Sector> sectoresDelEntrevistador = entrevistadorService.buscarEntrevistadorPorId(entrevistador.getId()).get().getSectores();


        centrosDelEntrevistador.add(centroService.buscarCentroPorId(centroid));
        sectoresDelEntrevistador.add(sectorService.buscarSectorPorId(sectorid));

        entrevistador.setCentros(centrosDelEntrevistador);
        entrevistador.setSectores(sectoresDelEntrevistador);

        entrevistadorService.guardarEntrevistador(entrevistador);

           List<Entrevistador> entrevistadores = entrevistadorService.listarEntrevistadores();
            model.addAttribute("entrevistadores", entrevistadores);

            String tabla = "Entrevistadores";

        model.addAttribute("tabla", tabla);

        return new ModelAndView("tablas :: tablaQueCargo");
    }

    @PutMapping("/agregarCentroAEvaluador/{centroid}/{sectorid}")
    @Transactional
    public ModelAndView agregarCentroAEvaluador(Model model ,
                                                    @RequestBody Evaluador evaluador,
                                              @PathVariable Long centroid,
                                              @PathVariable Long sectorid) {


        Optional<Evaluador> evaluadorOptional = evaluadorService.buscarEvaluadorPorId(evaluador.getId());


        evaluadorOptional.ifPresent(value -> evaluador.setId(value.getId()));

        Set<Centro> centrosDelEvaluador = evaluadorService.buscarEvaluadorPorId(evaluador.getId()).get().getCentros();
        Set<Sector> sectoresDelEvaluador = evaluadorService.buscarEvaluadorPorId(evaluador.getId()).get().getSectores();


        centrosDelEvaluador.add(centroService.buscarCentroPorId(centroid));
        sectoresDelEvaluador.add(sectorService.buscarSectorPorId(sectorid));

        evaluador.setCentros(centrosDelEvaluador);
        evaluador.setSectores(sectoresDelEvaluador);

        evaluadorService.guardarEvaluador(evaluador);

           List<Evaluador> evaluadores = evaluadorService.listarEvaluadores();
            model.addAttribute("evaluadores", evaluadores);

            String tabla = "Evaluadores";

        model.addAttribute("tabla", tabla);

        return new ModelAndView("tablas :: tablaQueCargo");
    }

    @GetMapping("/buscarUsuarioParaEditar/{id}")
    @ResponseBody
    public String buscarUsuarioParaEditar(@PathVariable Long id) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        User usuario = userService.buscarUsuario(id);

        return objectMapper.writeValueAsString(usuario);

    }

    @GetMapping("/buscarOrientadorParaEditar/{id}")
    @ResponseBody
    public String buscarOrientadorParaEditar(@PathVariable Long id) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Optional<Orientador> orientador = Optional.ofNullable(orientadorService.buscarOrientadorDTO(id));

        return objectMapper.writeValueAsString(orientador.get());

    }

    @GetMapping("/buscarEntrevistadorParaEditar/{id}")
    @ResponseBody
    public String buscarEntrevistadorParaEditar(@PathVariable Long id) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Optional<Entrevistador> entrevistador = Optional.ofNullable(entrevistadorService.buscarEntrevistadorDTO(id));

        return objectMapper.writeValueAsString(entrevistador.get());

    }

    @GetMapping("/buscarEvaluadorParaEditar/{id}")
    @ResponseBody
    public String buscarEvaluadorParaEditar(@PathVariable Long id) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Optional<Evaluador> evaluadorOptional = Optional.ofNullable(evaluadorService.buscarEvaluadorDTO(id));

        return objectMapper.writeValueAsString(evaluadorOptional.get());

    }


    @GetMapping("/buscarCentrosPorUsuario")
    public ModelAndView buscarCentrosDeUsuario(@RequestParam (value = "idUsuario")Long idUsuario,
                                               @RequestParam(value = "tabla") String tabla ,Model model) {


        Optional<Set<Centro>> centroOptional = null;
        Optional<Set<Sector>> sectorOptional = null;

        if (tabla.equals("Orientadores")) {
            centroOptional = orientadorService.buscarCentrosPorOrientador(idUsuario);

            List<Orientador> orientadores = orientadorService.listarOrientadores();

            model.addAttribute("orientadores", orientadores);
        }

        if (tabla.equals("Entrevistadores")){
            centroOptional = entrevistadorService.buscarCentrosPorEntrevistador(idUsuario);
            sectorOptional = entrevistadorService.buscarSectoresPorEntrevistador(idUsuario);

            List<Entrevistador> entrevistadores = entrevistadorService.listarEntrevistadores();

            if (sectorOptional.isPresent()) {
                Set<Sector> sectorSet = sectorOptional.get();

                model.addAttribute("sectores", sectorSet);
            }

            model.addAttribute("entrevistadores", entrevistadores);
        }

        if (tabla.equals("Evaluadores")){
            centroOptional = evaluadorService.buscarCentroPorEvaluador(idUsuario);
            sectorOptional = evaluadorService.buscarSectoresPorEvaluador(idUsuario);

            List<Evaluador> evaluadores = evaluadorService.listarEvaluadores();

            model.addAttribute("evaluadores", evaluadores);

            if (sectorOptional.isPresent()) {
                Set<Sector> sectorSet = sectorOptional.get();

                model.addAttribute("sectores", sectorSet);
            }
        }


        if (centroOptional.isPresent()) {
            Set<Centro> centroSet = centroOptional.get();

            model.addAttribute("centros", centroSet);
        }


        model.addAttribute("tabla", tabla);

        return new ModelAndView("tablas :: tablaQueCargo");

    }

}
