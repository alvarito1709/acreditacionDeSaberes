package com.agencia.acs.service;


import com.agencia.acs.entities.Centro;
import com.agencia.acs.repository.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@PersistenceContext
public class CentroService {

    @Autowired
    CentroRepository centroRepository;

    public void  guardarNuevoCentro(Centro centro){ centroRepository.save(centro);}

    public List<Centro> listarCentros(){return centroRepository.findAll();}

    public Centro buscarCentroPorId(Long id){return  centroRepository.findById(id);}

    public void borrarCentro(Long id){ centroRepository.deleteById(id);}

    public Set<Centro> buscarVariosCentros(Set<Centro> centros){
        List<Long> ids = new ArrayList<>();

        for(Centro centro : centros){
            ids.add(centro.getId());
        }
        return centroRepository.findAllById(ids);
    }
}
