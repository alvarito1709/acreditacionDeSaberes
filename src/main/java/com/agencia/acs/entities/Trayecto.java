package com.agencia.acs.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Trayecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String condiciones;


    @ManyToOne
    private Sector sector;

    private String estado;

    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "trayecto_centro",
            joinColumns = {@JoinColumn(name = "trayecto_id")},
            inverseJoinColumns = {@JoinColumn(name = "centro_id")}
    )
    List<Centro> centros;



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

    public void setEstado(String activo) {
        this.estado = estado;
    }

    public List<Centro> getCentros() {
        return centros;
    }

    public void setCentros(List<Centro> centros) {
        this.centros = centros;
    }
}
