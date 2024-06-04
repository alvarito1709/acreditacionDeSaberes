package com.agencia.acs.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
 @PrimaryKeyJoinColumn(name = "orientadorId")
public class Orientador extends User{
}
