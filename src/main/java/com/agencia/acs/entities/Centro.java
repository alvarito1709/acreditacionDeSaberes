package com.agencia.acs.entities;


import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "Centro.detail", attributeNodes = {@NamedAttributeNode("trayectos"), @NamedAttributeNode("orientadores")})
public class Centro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    private String telefono;

    private Long numeroDeCentro;

    private Long codigo;

    private Long cue;

    private String tipo;

    private String area;

    private String estado;


    @JsonIgnore
    @ManyToMany(mappedBy = "centros", fetch = FetchType.LAZY)
    private List<Trayecto> trayectos;

    @JsonIgnore
    @ManyToMany(mappedBy = "centros", fetch = FetchType.LAZY)
    private List<Orientador> orientadores;

    @JsonIgnore
    @ManyToMany(mappedBy = "centros", fetch = FetchType.LAZY)
    private List<Entrevistador> entrevistadores;


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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getNumeroDeCentro() {
        return numeroDeCentro;
    }

    public void setNumeroDeCentro(Long numeroDeCentro) {
        this.numeroDeCentro = numeroDeCentro;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCue() {
        return cue;
    }

    public void setCue(Long cue) {
        this.cue = cue;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
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
}
