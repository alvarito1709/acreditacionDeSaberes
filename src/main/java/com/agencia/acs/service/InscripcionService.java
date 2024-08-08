package com.agencia.acs.service;


import com.agencia.acs.entities.Inscripcion;
import com.agencia.acs.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {
    @Autowired
    InscripcionRepository inscripcionRepository;


    public Inscripcion nuevaInscripcion(Inscripcion inscripcion){return inscripcionRepository.save(inscripcion);}

    public List<Inscripcion> listarInscripciones(){return inscripcionRepository.findAll();}

    public List<Inscripcion> buscarInscripcionPorPostulante(Long id){return inscripcionRepository.findAllByPostulanteId(id);}
}
