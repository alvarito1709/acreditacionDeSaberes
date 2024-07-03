package com.agencia.acs.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Set;

@Entity
 @PrimaryKeyJoinColumn(name = "orientadorId")
public class Orientador extends User{


 @OneToMany
 private Set<Centro> centros;

 @OneToMany
 private Set<Sector> sector;

 public Set<Centro> getCentros() {
  return centros;
 }

 public void setCentros(Set<Centro> centros) {
  this.centros = centros;
 }

 public Set<Sector> getSector() {
  return sector;
 }

 public void setSector(Set<Sector> sector) {
  this.sector = sector;
 }
}
