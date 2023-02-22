package com.example.drawerfamex.API.models;

public class ApiFichaConferencia {

    private int id;
    private String nombre;
    private String hInicio;
    private String hFin;
    private String expositor;
    private int nBlok;
    private boolean francia;
    private int nDia;
    private String descEs;
    private String descEn;
    private String descFr;

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

    public String gethInicio() {
        return hInicio;
    }

    public void sethInicio(String hInicio) {
        this.hInicio = hInicio;
    }

    public String gethFin() {
        return hFin;
    }

    public void sethFin(String hFin) {
        this.hFin = hFin;
    }

    public String getExpositor() {
        return expositor;
    }

    public void setExpositor(String expositor) {
        this.expositor = expositor;
    }

    public int getnBlok() {
        return nBlok;
    }

    public void setnBlok(int nBlok) {
        this.nBlok = nBlok;
    }

    public boolean isFrancia() {
        return francia;
    }

    public void setFrancia(boolean francia) {
        this.francia = francia;
    }

    public int getnDia() {
        return nDia;
    }

    public void setnDia(int nDia) {
        this.nDia = nDia;
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
}
