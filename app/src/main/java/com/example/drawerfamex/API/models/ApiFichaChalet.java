package com.example.drawerfamex.API.models;

public class ApiFichaChalet {
    private int id;
    private int nBloque;
    private boolean francia;
    private String imagen;
    private String nombre;
    private String descripcionES;
    private String descripcionEN;
    private String descripcionFR;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnBloque() {
        return nBloque;
    }

    public void setnBloque(int nBloque) {
        this.nBloque = nBloque;
    }

    public boolean isFrancia() {
        return francia;
    }

    public void setFrancia(boolean francia) {
        this.francia = francia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionES() { return descripcionES; }

    public void setDescripcionES(String descripcionES) {
        this.descripcionES = descripcionES;
    }

    public String getDescripcionEN() {
        return descripcionEN;
    }

    public void setDescripcionEN(String descripcionEN) {
        this.descripcionEN = descripcionEN;
    }

    public String getDescripcionFR() {
        return descripcionFR;
    }

    public void setDescripcionFR(String descripcionFR) {
        this.descripcionFR = descripcionFR;
    }
}
