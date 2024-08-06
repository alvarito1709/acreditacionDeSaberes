package com.agencia.acs.service;


import com.agencia.acs.entities.Inscripcion;
import com.agencia.acs.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionService {
    @Autowired
    InscripcionRepository inscripcionRepository;


    public Inscripcion nuevaInscripcion(Inscripcion inscripcion){return inscripcionRepository.save(inscripcion);}
}
