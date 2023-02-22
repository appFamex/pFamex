package com.example.drawerfamex.API.models;

public class ApiFichaExpo {
    private int id;
    private String nombre;
    private boolean francia;
    private String descEs;
    private String descEn;
    private String descFr;
    private String imagen;
    private int nBlok;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isFrancia() {
        return francia;
    }

    public void setFrancia(boolean francia) {
        this.francia = francia;
    }

    public String getDescEs() {
        return descEs;
    }

    public void setDescEs(String descEs) {
        this.descEs = descEs;
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getDescFr() {
        return descFr;
    }

    public void setDescFr(String descFr) {
        this.descFr = descFr;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getnBlok() {
        return nBlok;
    }

    public void setnBlok(int nBlok) {
        this.nBlok = nBlok;
    }
}
