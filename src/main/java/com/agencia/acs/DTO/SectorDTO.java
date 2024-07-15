package com.agencia.acs.DTO;

import com.agencia.acs.entities.Sector;

import java.io.Serializable;

public class SectorDTO extends Sector implements Serializable {

    final Long id;

    final String nombre;

    final String descripcion;

    final String estado;

    public SectorDTO(Long id, String nombre, String descripcion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String getEstado() {
        return estado;
    }
}
