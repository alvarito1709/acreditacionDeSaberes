package com.agencia.acs.service;


import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.User;
import com.agencia.acs.repository.UserRepository;
import com.agencia.acs.util.passwordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User guardarUsuario(User user){
      return  userRepository.save(user);
    }

    public User buscarUsuario(Long id){
        return userRepository.findById(id);
    }

    public List<User> listarUsuarios(){ return userRepository.findAll();}

    public void borrarUsuario(Long id){ userRepository.deleteById(id);}

    public User crearPrimerUsuario(){

        User usuario = new User();

        usuario.setPassword(passwordEncoder.passwordEncrypt("123"));
        usuario.setNombre("admin");
        usuario.setRol("ROLE_ADMIN");
        usuario.setMail("admin@mail.com");
        usuario.setDNI(123456L);
        usuario.setUsername("sistemas");

        userRepository.save(usuario);

        return usuario;

    }

    public User buscarUsuarioPorUsername(String username){
        return userRepository.findByUsername(username);
    }

}
