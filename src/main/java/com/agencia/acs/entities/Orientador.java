package com.agencia.acs.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Orientador extends User{


 @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.MERGE})
 @JoinTable(
         name = "orientador_centros",
         joinColumns = {@JoinColumn(name = "orientador_id")},
         inverseJoinColumns = {@JoinColumn(name = "centro_id")}
 )
 @JsonIgnoreProperties("orientadores")
 private Set<Centro> centros;

 @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.MERGE})
 @JoinTable(
         name = "orientador_trayectos",
         joinColumns = {@JoinColumn(name = "orientador_id")},
         inverseJoinColumns = {@JoinColumn(name = "trayecto_id")}
 )
 @JsonIgnoreProperties("orientadores")
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
}


