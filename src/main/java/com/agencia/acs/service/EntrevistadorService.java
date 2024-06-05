package com.agencia.acs.service;

import com.agencia.acs.entities.Entrevistador;
import com.agencia.acs.repository.EntrevistadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrevistadorService {

    @Autowired
    EntrevistadorRepository entrevistadorRepository;

    public Entrevistador guardarEntrevistador(Entrevistador entrevistador){ return entrevistadorRepository.save(entrevistador);}

    public List<Entrevistador> listarEntrevistadores(){return entrevistadorRepository.findAll();}
}
