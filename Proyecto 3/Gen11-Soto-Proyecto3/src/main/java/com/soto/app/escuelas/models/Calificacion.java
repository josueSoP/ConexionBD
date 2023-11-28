package com.soto.app.escuelas.models;

public class Calificacion {
    // Atributos
    private Long id;
    private Long estudianteId;
    private Long claseId;
    private Double calificacion;
    private String nota;



    //getters and setters}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getClaseId() {
        return claseId;
    }

    public void setClaseId(Long claseId) {
        this.claseId = claseId;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
