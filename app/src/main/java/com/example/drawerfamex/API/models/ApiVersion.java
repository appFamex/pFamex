package com.example.drawerfamex.API.models;

import java.util.List;

public class ApiVersion {

    private List<ApiFichaChalet> fichasChalets;
    private List<ApiFichaPabellon> fichasPabellones;
    private List<ApiFichaConferencia> fichasConferencias;
    private List<ApiFichaExpo> fichasExpo;
    private List<ApiFichaInicio> fichasInicio;
    private List<ApiFichaAnuncio> fichasAnuncios;
    private List<ApiFichaHoteles> fichasHoteles;
    private ApiFichaFrancia fichaFrancia;
    private ApiFichaPDFS fichaPDFs;

    public List<ApiFichaChalet> getFichasChalets() {
        return fichasChalets;
    }

    public void setFichasChalets(List<ApiFichaChalet> fichasChalets) {
        this.fichasChalets = fichasChalets;
    }

    public List<ApiFichaPabellon> getFichasPabellones() {
        return fichasPabellones;
    }

    public void setFichasPabellones(List<ApiFichaPabellon> fichasPabellones) {
        this.fichasPabellones = fichasPabellones;
    }

    public List<ApiFichaConferencia> getFichasConferencias() {
        return fichasConferencias;
    }

    public void setFichasConferencias(List<ApiFichaConferencia> fichasConferencias) {
        this.fichasConferencias = fichasConferencias;
    }

    public List<ApiFichaExpo> getFichasExpo() {
        return fichasExpo;
    }

    public void setFichasExpo(List<ApiFichaExpo> fichasExpo) {
        this.fichasExpo = fichasExpo;
    }

    public List<ApiFichaInicio> getFichasInicio() {
        return fichasInicio;
    }

    public void setFichasInicio(List<ApiFichaInicio> fichasInicio) {
        this.fichasInicio = fichasInicio;
    }

    public List<ApiFichaAnuncio> getFichasAnuncios() {
        return fichasAnuncios;
    }

    public void setFichasAnuncios(List<ApiFichaAnuncio> fichasAnuncios) {
        this.fichasAnuncios = fichasAnuncios;
    }

    public List<ApiFichaHoteles> getFichasHoteles() {
        return fichasHoteles;
    }

    public void setFichasHoteles(List<ApiFichaHoteles> fichasHoteles) {
        this.fichasHoteles = fichasHoteles;
    }

    public ApiFichaFrancia getFichaFrancia() {
        return fichaFrancia;
    }

    public ApiFichaPDFS getFichaPDFs() {
        return fichaPDFs;
    }

}
