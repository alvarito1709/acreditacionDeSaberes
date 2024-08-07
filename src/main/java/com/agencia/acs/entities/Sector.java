package com.agencia.acs.entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private String estado;

    @JsonIgnore
    @ManyToMany(mappedBy = "sectores", fetch = FetchType.LAZY)
    private List<Orientador> orientadores;

    @JsonIgnore
    @ManyToMany(mappedBy = "sectores", fetch = FetchType.LAZY)
    private List<Entrevistador> entrevistadores;

    @JsonIgnore
    @ManyToMany(mappedBy = "sectores", fetch = FetchType.LAZY)
    private List<Evaluador> evaluadores;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }

    public List<Entrevistador> getEntrevistadores() {
        return entrevistadores;
    }

    public void setEntrevistadores(List<Entrevistador> entrevistadores) {
        this.entrevistadores = entrevistadores;
    }

    public List<Evaluador> getEvaluadores() {
        return evaluadores;
    }

    public void setEvaluadores(List<Evaluador> evaluadores) {
        this.evaluadores = evaluadores;
    }
}
