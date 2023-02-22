package com.example.drawerfamex.BaseDatos.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class fichaConferencia {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "horaInicio")
    public String hInicio;

    @ColumnInfo(name = "horaFin")
    public String hFin;

    @ColumnInfo(name = "expositor")
    public String expositor;

    @ColumnInfo(name = "nBlock")
    public int nBlok;

    @ColumnInfo(name = "francia")
    public boolean francia;

    @ColumnInfo(name = "nDia")
    public int nDia;

    @ColumnInfo(name = "descES")
    public String descEs;

    @ColumnInfo(name = "descEn")
    public String descEn;

    @ColumnInfo(name = "descFr")
    public String descFr;

    @ColumnInfo(name = "it")
    public boolean it;

    public fichaConferencia(int id, String nombre, String hInicio, String hFin, String expositor, int nBlok, boolean francia, int nDia, String descEs, String descEn, String descFr, boolean it) {
        this.id = id;
        this.nombre = nombre;
        this.hInicio = hInicio;
        this.hFin = hFin;
        this.expositor = expositor;
        this.nBlok = nBlok;
        this.francia = francia;
        this.nDia = nDia;
        this.descEs = descEs;
        this.descEn = descEn;
        this.descFr = descFr;
        this.it = it;
    }
}
