package com.agencia.acs.entities;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "referenteId")
public class Referente extends User{
}
