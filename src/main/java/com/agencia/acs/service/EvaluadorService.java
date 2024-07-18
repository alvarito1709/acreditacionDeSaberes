package com.agencia.acs.service;

import com.agencia.acs.DTO.EvaluadorDTO;
import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Entrevistador;
import com.agencia.acs.entities.Evaluador;
import com.agencia.acs.entities.Sector;
import com.agencia.acs.repository.EvaluadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EvaluadorService {

    @Autowired
    EvaluadorRepository evaluadorRepository;

    public Evaluador guardarEvaluador(Evaluador evaluador){return evaluadorRepository.save(evaluador);}

    public Optional<Evaluador> buscarEvaluadorPorId(Long id){return evaluadorRepository.findById(id);}

    public List<Evaluador> listarEvaluadores(){return evaluadorRepository.findAll();}

    public EvaluadorDTO buscarEvaluadorDTO(final Long id){
        final Optional<Evaluador> evaluadorOptional = evaluadorRepository.findById(id);

        if (evaluadorOptional.isEmpty()){
            throw new EntityNotFoundException("Evaluador con id" + id + "no encontrado");
        }

        final Evaluador evaluador = evaluadorOptional.get();

        return new EvaluadorDTO(evaluador.getId(), evaluador.getNombre(), evaluador.getMail(), evaluador.getDNI(), evaluador.getUsername(), evaluador.getRol());
    }

    public Optional<Set<Centro>> buscarCentroPorEvaluador(Long id){
        Optional<Evaluador> evaluadorOptional = evaluadorRepository.findById(id);

        return Optional.ofNullable(evaluadorOptional.get().getCentros());
    }

    public Optional<Set<Sector>> buscarSectoresPorEvaluador(Long id){
        Optional<Evaluador> evaluadorOptional = evaluadorRepository.findById(id);

        return Optional.ofNullable(evaluadorOptional.get().getSectores());
    }
}
