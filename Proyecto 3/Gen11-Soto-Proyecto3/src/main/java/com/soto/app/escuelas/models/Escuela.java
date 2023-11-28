package com.soto.app.escuelas.models;

public class Escuela {
    // Atributos
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String directorNombre;
    private String directorApp;
    private String directorApm;
    private String paginaWeb;



    //getters and setters}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDirectorNombre() {
        return directorNombre;
    }

    public void setDirectorNombre(String directorNombre) {
        this.directorNombre = directorNombre;
    }

    public String getDirectorApp() {
        return directorApp;
    }

    public void setDirectorApp(String directorApp) {
        this.directorApp = directorApp;
    }

    public String getDirectorApm() {
        return directorApm;
    }

    public void setDirectorApm(String directorApm) {
        this.directorApm = directorApm;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }
}
