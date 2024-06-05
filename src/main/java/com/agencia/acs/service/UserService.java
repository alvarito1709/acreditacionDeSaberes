package com.agencia.acs.service;


import com.agencia.acs.entities.User;
import com.agencia.acs.repository.UserRepository;
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
}
