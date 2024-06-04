package com.agencia.acs.entities;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "entrevistadorId")
public class Entrevistador extends User{
}
