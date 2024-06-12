package com.agencia.acs.service;


import com.agencia.acs.entities.Centro;
import com.agencia.acs.repository.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroService {

    @Autowired
    CentroRepository centroRepository;

    public Centro guardarNuevoCentro(Centro centro){return centroRepository.save(centro);}

    public List<Centro> listarCentros(){return centroRepository.findAll();}
}
