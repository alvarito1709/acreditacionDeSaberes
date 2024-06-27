package com.agencia.acs.service;

import com.agencia.acs.entities.Trayecto;
import com.agencia.acs.repository.TrayectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrayectoService {

    @Autowired
    TrayectoRepository trayectoRepository;
    public List<Trayecto> listarTrayectos(){ return trayectoRepository.findAll();}

    public Trayecto guardarTrayectoNuevo(Trayecto trayecto){return trayectoRepository.save(trayecto);}

    public Optional<Trayecto> buscarTrayectoPorId(Long id){return trayectoRepository.findById(id);}

}
