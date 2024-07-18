package com.agencia.acs.DTO;

import com.agencia.acs.entities.Entrevistador;

import java.io.Serializable;

public class EntrevistadorDTO extends Entrevistador implements Serializable {


    final Long id;
    final String nombre;
    final String mail;

    final Long DNI;
    final String username;


    final String rol;


    public EntrevistadorDTO(Long id, String nombre, String mail, Long DNI, String username, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.DNI = DNI;
        this.username = username;
        this.rol = rol;;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public Long getDNI() {
        return DNI;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public String getRol() {
        return rol;
    }
}
