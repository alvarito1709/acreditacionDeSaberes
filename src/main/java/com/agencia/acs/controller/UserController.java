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

    @PutMapping("/agregarCentro/{centroid}/{sectorid}")
    @Transactional
    public ModelAndView agregarCentroAUsuario(Model model ,@RequestBody Orientador orientador,
                                              @PathVariable Long centroid,
                                              @PathVariable Long sectorid) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writeValueAsString(orientador));

        Optional<Orientador> orientadorOptional = orientadorService.buscarOrientadorPorId(orientador.getId());


        orientadorOptional.ifPresent(value -> orientador.setId(value.getId()));

        Set<Centro> centrosDelOrientador = orientadorService.buscarOrientadorPorId(orientador.getId()).get().getCentros();
        Set<Sector> sectoresDelOrientador = orientadorService.buscarOrientadorPorId(orientador.getId()).get().getSectores();


        System.out.println(objectMapper.writeValueAsString(centroService.buscarCentroDTO(centroid)));
        System.out.println(objectMapper.writeValueAsString(sectorService.buscarSectorDTO(sectorid)));

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


    @GetMapping("/buscarCentrosPorUsuario")
    public ModelAndView buscarCentrosDeUsuario(@RequestParam (value = "idUsuario")Long idUsuario,
                                               @RequestParam(value = "tabla") String tabla ,Model model){

        Optional<Set<Centro>> centroOptional = orientadorService.buscarCentrosPorOrientador(idUsuario);

        List<Orientador> orientadores = orientadorService.listarOrientadores();

        if(centroOptional.isPresent()){
            Set<Centro> centroSet = centroOptional.get();

            model.addAttribute("centros", centroSet);
        }

        model.addAttribute("orientadores", orientadores);

        model.addAttribute("tabla", tabla);

        return new ModelAndView("tablas :: tablaQueCargo");

    }

}
