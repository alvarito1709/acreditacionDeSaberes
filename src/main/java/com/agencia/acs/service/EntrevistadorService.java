package com.agencia.acs.service;

import com.agencia.acs.DTO.EntrevistadorDTO;
import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Entrevistador;
import com.agencia.acs.repository.EntrevistadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EntrevistadorService {

    @Autowired
    EntrevistadorRepository entrevistadorRepository;

    public Entrevistador guardarEntrevistador(Entrevistador entrevistador){ return entrevistadorRepository.save(entrevistador);}

    public Optional<Entrevistador> buscarEntrevistadorPorId(Long id){return entrevistadorRepository.findById(id);}
    public List<Entrevistador> listarEntrevistadores(){return entrevistadorRepository.findAll();}

    public EntrevistadorDTO buscarEntrevistadorDTO(final Long id){
        final Optional<Entrevistador> entrevistadorOptional = entrevistadorRepository.findById(id);

        if (entrevistadorOptional.isEmpty()){
            throw new EntityNotFoundException("Entrevistador con id" + id + "no encontrado");
        }

        final Entrevistador entrevistador = entrevistadorOptional.get();

        return new EntrevistadorDTO(entrevistador.getId(), entrevistador.getNombre(), entrevistador.getMail(), entrevistador.getDNI(), entrevistador.getUsername(), entrevistador.getRol());
    }

    public Optional<Set<Centro>> buscarCentrosPorEntrevistador(Long id){
        Optional<Entrevistador> entrevistadorOptional = entrevistadorRepository.findById(id);

        return Optional.ofNullable(entrevistadorOptional.get().getCentros());
    }
}
