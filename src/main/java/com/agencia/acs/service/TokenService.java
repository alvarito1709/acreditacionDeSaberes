package com.agencia.acs.service;


import com.agencia.acs.entities.Token;
import com.agencia.acs.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    public Token buscarPorToken(String token){
        return tokenRepository.findByToken(token);
    }
}
