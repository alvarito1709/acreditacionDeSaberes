package com.agencia.acs.entities;


import javax.persistence.*;
import java.util.UUID;

@Entity
public class Token {

    @Id
    private UUID id;

    private String token;


    private String mail;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
