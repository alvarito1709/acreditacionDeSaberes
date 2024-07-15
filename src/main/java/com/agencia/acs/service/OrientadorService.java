package com.agencia.acs.service;

import com.agencia.acs.DTO.CentroDTO;
import com.agencia.acs.DTO.OrientadorDTO;
import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Orientador;
import com.agencia.acs.repository.OrientadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrientadorService {

    @Autowired
    OrientadorRepository orientadorRepository;

    public Orientador guardarOrientador(Orientador orientador){return orientadorRepository.save(orientador);}

    public List<Orientador> listarOrientadores(){return orientadorRepository.findAll();}

    public void editarOrientador(Orientador orientador){orientadorRepository.save(orientador);}

    public Optional<Orientador> buscarOrientadorPorId(Long id){return orientadorRepository.findById(id);}

    public OrientadorDTO buscarOrientadorDTO(final Long id){
        final Optional<Orientador> orientadorOptional = orientadorRepository.findById(id);

        if (orientadorOptional.isEmpty()){
            throw new EntityNotFoundException("Orientador con id" + id + "no encontrado");
        }

        final Orientador orientador = orientadorOptional.get();

        return new OrientadorDTO(orientador.getId(), orientador.getNombre(), orientador.getMail(), orientador.getDNI(), orientador.getUsername(), orientador.getRol());
    }
}
