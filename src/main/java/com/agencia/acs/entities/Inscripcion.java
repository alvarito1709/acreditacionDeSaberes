package com.agencia.acs.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postulante_id")
    private Postulante postulante;


    @ManyToOne
    @JoinColumn(name = "entrevistador_id")
    private Entrevistador entrevistador;

    @ManyToOne
    @JoinColumn(name = "trayecto_id")
    private Trayecto trayecto;

    @ManyToOne
    @JoinColumn(name = "centro_id")
    private Centro centro;

    private String estado;

    private String nota;

    private String notaMaximaDelExamen;

    private Date fechaEntrevista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public Trayecto getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(Trayecto trayecto) {
        this.trayecto = trayecto;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getNotaMaximaDelExamen() {
        return notaMaximaDelExamen;
    }

    public void setNotaMaximaDelExamen(String notaMaximaDelExamen) {
        this.notaMaximaDelExamen = notaMaximaDelExamen;
    }

    public Date getFechaEntrevista() {
        return fechaEntrevista;
    }

    public void setFechaEntrevista(Date fechaEntrevista) {
        this.fechaEntrevista = fechaEntrevista;
    }

    public Entrevistador getEntrevistador() {
        return entrevistador;
    }

    public void setEntrevistador(Entrevistador entrevistador) {
        this.entrevistador = entrevistador;
    }
}
