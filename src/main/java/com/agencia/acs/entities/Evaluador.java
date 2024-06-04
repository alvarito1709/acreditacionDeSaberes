package com.agencia.acs.entities;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "evaluadorId")
public class Evaluador extends User{
}
