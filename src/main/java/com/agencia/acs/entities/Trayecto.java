package com.agencia.acs.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Trayecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String estado;


    @Size(max = 1000)
    private String condiciones;


    @ManyToOne
    private Sector sector;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "trayecto_centro",
            joinColumns = {@JoinColumn(name = "trayecto_id")},
            inverseJoinColumns = {@JoinColumn(name = "centro_id")}
    )
    @JsonIgnoreProperties("trayectos")
    private Set<Centro> centros;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<Centro> getCentros() {
        return centros;
    }

    public void setCentros(Set<Centro> centros) {
        this.centros = centros;
    }
}
