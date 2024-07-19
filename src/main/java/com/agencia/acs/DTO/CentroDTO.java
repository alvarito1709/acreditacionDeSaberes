package com.agencia.acs.DTO;

import com.agencia.acs.entities.Centro;

import java.io.Serializable;


public class CentroDTO extends Centro implements Serializable{

    final Long id;

    final String nombre;

    final String direccion;
    final String telefono;
    final Long numeroDeCentro;
    final Long codigo;
    final Long cue;
    final String tipo;
    final String area;
    final String estado;



    public CentroDTO(Long id, String nombre, String direccion,
                     String telefono, Long numeroDeCentro, Long codigo, Long cue,
                     String tipo, String area, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numeroDeCentro = numeroDeCentro;
        this.codigo = codigo;
        this.cue = cue;
        this.tipo = tipo;
        this.area = area;
        this.estado = estado;
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
    public String getDireccion() {
        return direccion;
    }

    @Override
    public String getTelefono() {
        return telefono;
    }

    @Override
    public Long getNumeroDeCentro() {
        return numeroDeCentro;
    }

    @Override
    public Long getCodigo() {
        return codigo;
    }

    @Override
    public Long getCue() {
        return cue;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public String getArea() {
        return area;
    }

    @Override
    public String getEstado() {
        return estado;
    }
}
