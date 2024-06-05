package com.agencia.acs.service;


import com.agencia.acs.entities.Postulante;
import com.agencia.acs.repository.PostulanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostulanteService {

    @Autowired
    PostulanteRepository postulanteRepository;

    public Postulante guardarPostulante(Postulante postulante){return postulanteRepository.save(postulante);}

    public List<Postulante> listarPostulantes(){return postulanteRepository.findAll();}
}
