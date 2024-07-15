package com.agencia.acs.DTO;

import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Orientador;
import com.agencia.acs.entities.Sector;
import com.agencia.acs.entities.User;

import java.io.Serializable;
import java.util.Set;

public class OrientadorDTO extends Orientador implements Serializable {

    final Long id;
    final String nombre;
    final String mail;

    final Long DNI;
    final String username;


    final String rol;


    public OrientadorDTO(Long id, String nombre, String mail, Long DNI, String username, String rol) {
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
