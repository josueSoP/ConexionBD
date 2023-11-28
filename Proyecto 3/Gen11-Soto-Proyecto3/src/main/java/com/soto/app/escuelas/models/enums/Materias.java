package com.soto.app.escuelas.models.enums;

public enum Materias {
    ESPANOL("Español"),
    MATEMATICAS ("Matematicas"),
    CIENCIAS ("Ciencias"),
    INGLES ("Ingles"),
    EDUCACION_FISICA ("Educacion Fisica"),
    PROGRAMACION ("Programacion"),
    PROGRAMACION_AVANZADA ("Programacion Avanzada"),
    HISTORIA ("Historia"),
    GEOGRAFIA ("Geografia");



    //atributos
    private String Descripcion;


    //constructor
    Materias(String descripcion){
        Descripcion = descripcion;
    }


    //gets and sets
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
