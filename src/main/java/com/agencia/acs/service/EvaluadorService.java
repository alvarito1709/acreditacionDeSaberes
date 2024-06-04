package com.agencia.acs.service;

import com.agencia.acs.entities.Evaluador;
import com.agencia.acs.repository.EvaluadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluadorService {

    @Autowired
    EvaluadorRepository evaluadorRepository;

    public Evaluador guardarEvaluador(Evaluador evaluador){return evaluadorRepository.save(evaluador);}
}
