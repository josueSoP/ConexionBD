package com.soto.app.escuelas.models;

import com.soto.app.escuelas.models.enums.Semestres;

import java.time.LocalDate;

public class Estudiante {
    // Atributos
    private Long id;
    private Long escuelaId;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String direccionAlum;
    private Semestres semestre;
    private String telefono;
    private String correo;
    private LocalDate fechaNacimiento;
    private Boolean activo;
    private Boolean egreso;



    //getters and setters}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getEscuelaId() {
        return escuelaId;
    }
    public void setEscuelaId(Long escuelaId) {
        this.escuelaId = escuelaId;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getDireccionAlum() {
        return direccionAlum;
    }
    public void setDireccionAlum(String direccionAlum) {
        this.direccionAlum = direccionAlum;
    }

    public Semestres getSemestre() {
        return semestre;
    }
    public void setSemestre(Semestres semestre) {
        this.semestre = semestre;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getEgreso() {
        return egreso;
    }

    public void setEgreso(Boolean egreso) {
        this.egreso = egreso;
    }
}
