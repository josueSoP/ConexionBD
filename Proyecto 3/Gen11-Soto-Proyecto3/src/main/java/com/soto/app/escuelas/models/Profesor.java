package com.soto.app.escuelas.models;

import java.time.LocalDate;

public class Profesor {
    // Atributos
    private Long id;
    private Long escuelaId;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String direccionProf;
    private String correo;
    private String telefono;
    private Double salario;
    private LocalDate fechaContrato;



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

    public String getDireccionProf() {
        return direccionProf;
    }

    public void setDireccionProf(String direccionProf) {
        this.direccionProf = direccionProf;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(LocalDate fechaContrato) {
        this.fechaContrato = fechaContrato;
    }
}
