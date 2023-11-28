package com.soto.app.escuelas.models.enums;

public enum Semestres {
    PRIMERO("1°"),
    SEGUNDO("2°"),
    TERCERO("3°"),
    CUARTO("4°"),
    QUINTO("5°"),
    SEXTO("6°");



    //atributos
    private String Descripcion;

    //constructor
    Semestres(String descripcion){
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
