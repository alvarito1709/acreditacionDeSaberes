package com.agencia.acs.web;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private Long id;
    private String nombre;
    private String mail;
    private Long DNI;
    private String username;
    private String password;
    private ArrayList<GrantedAuthority> rol;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long id, String nombre, String mail, Long DNI,
                             String username, String password, ArrayList<GrantedAuthority> rol) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.DNI = DNI;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.authorities = rol;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public ArrayList<GrantedAuthority> getRol() {
        return rol;
    }

    public Long getId() {
        return id;
    }

    public Long getDNI() {
        return DNI;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}