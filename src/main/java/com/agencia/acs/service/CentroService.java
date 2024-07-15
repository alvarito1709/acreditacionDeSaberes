package com.agencia.acs.service;


import com.agencia.acs.DTO.CentroDTO;
import com.agencia.acs.entities.Centro;
import com.agencia.acs.repository.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public CentroDTO buscarCentroDTO(final Long id){
        final Optional<Centro> centroOptional = Optional.ofNullable(centroRepository.findById(id));

        if (centroOptional.isEmpty()){
            throw new EntityNotFoundException("Centro con id" + id + "no encontrado");
        }

        final Centro centro = centroOptional.get();

        return new CentroDTO(centro.getId(), centro.getNombre(), centro.getDireccion(), centro.getTelefono(), centro.getNumeroDeCentro(), centro.getCodigo(), centro.getCue(), centro.getTipo(), centro.getArea(), centro.getEstado());
    }

    public Set<Centro> buscarVariosCentros(Set<Centro> centros){
        List<Long> ids = new ArrayList<>();

        for(Centro centro : centros){
            ids.add(centro.getId());
        }
        return centroRepository.findAllById(ids);
    }

    public Centro buscarCentroFull(Long id){return centroRepository.findfullCentro(id);}
}
