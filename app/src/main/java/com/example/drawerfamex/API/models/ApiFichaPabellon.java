package com.example.drawerfamex.API.models;

public class ApiFichaPabellon {
    private int id;
    private int nPabellon;
    private boolean francia;
    private int posicion;
    private String imagen;
    private String descripcionES;
    private String descripcionEN;
    private String descripcionFR;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnPabellon() { return nPabellon; }

    public boolean isFrancia() { return francia; }

    public void setFrancia(boolean francia) { this.francia = francia; }

    public void setnPabellon(int nPabellon) {
        this.nPabellon = nPabellon;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcionES() {
        return descripcionES;
    }

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

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
