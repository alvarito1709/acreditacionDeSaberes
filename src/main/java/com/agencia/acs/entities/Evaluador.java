package com.agencia.acs.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Evaluador extends User{

    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "evaluador_centros",
            joinColumns = {@JoinColumn(name = "evaluador_id")},
            inverseJoinColumns = {@JoinColumn(name = "centro_id")}
    )
    @JsonIgnoreProperties("entrevistadores")
    private Set<Centro> centros;

    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "evaluador_sectores",
            joinColumns = {@JoinColumn(name = "evaluador_id")},
            inverseJoinColumns = {@JoinColumn(name = "sector_id")}
    )
    @JsonIgnoreProperties("entrevistadores")
    private Set<Sector> sectores;

    public Set<Centro> getCentros() {
        return centros;
    }

    public void setCentros(Set<Centro> centros) {
        this.centros = centros;
    }



    public Set<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(Set<Sector> sectores) {
        this.sectores = sectores;
    }

    public void borrarCentro(Centro centro){
        centros.remove(centro);
    }
}
