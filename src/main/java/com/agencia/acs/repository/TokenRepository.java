package com.agencia.acs.repository;


import com.agencia.acs.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository  extends JpaRepository<Token, String> {

    Token findByToken(String token);
}
