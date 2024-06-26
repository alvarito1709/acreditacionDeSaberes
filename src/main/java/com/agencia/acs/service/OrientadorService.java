package com.agencia.acs.service;

import com.agencia.acs.entities.Orientador;
import com.agencia.acs.repository.OrientadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrientadorService {

    @Autowired
    OrientadorRepository orientadorRepository;

    public Orientador guardarOrientador(Orientador orientador){return orientadorRepository.save(orientador);}

    public List<Orientador> listarOrientadores(){return orientadorRepository.findAll();}
}
