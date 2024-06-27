package com.agencia.acs.service;


import com.agencia.acs.entities.Modulo;
import com.agencia.acs.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloService {

    @Autowired
    ModuloRepository moduloRepository;

    public List<Modulo> listarModulos(){return moduloRepository.findAll();}

    public Modulo crearModulo(Modulo modulo){return moduloRepository.save(modulo);}

}
