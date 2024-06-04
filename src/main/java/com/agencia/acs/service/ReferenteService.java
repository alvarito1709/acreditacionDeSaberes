package com.agencia.acs.service;


import com.agencia.acs.entities.Referente;
import com.agencia.acs.repository.ReferenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenteService {

    @Autowired
    ReferenteRepository referenteRepository;

    public Referente guardarReferente(Referente referente){return referenteRepository.save(referente);}
}
