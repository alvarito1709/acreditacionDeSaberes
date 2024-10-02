package com.agencia.acs.service;


import com.agencia.acs.entities.Token;
import com.agencia.acs.entities.User;
import com.agencia.acs.repository.TokenRepository;
import com.agencia.acs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    public String passwordResetTokenGenerator(User user){

        Token token = new Token();

        token.setId(UUID.randomUUID());
        token.setMail(user.getMail());
        token.setToken(token.getId()+user.getMail());

        tokenRepository.save(token);

        return token.getToken();
    }

    public User findUserByToken(String tokenUser){

        Token token = tokenRepository.findByToken(tokenUser);

        User user = userRepository.findByMail(token.getMail());

        return user;
    }
}
