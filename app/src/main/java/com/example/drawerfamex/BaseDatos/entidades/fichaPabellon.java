package com.example.drawerfamex.BaseDatos.entidades;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class fichaPabellon {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "textoEsp")
    public String textoEsp;

    @ColumnInfo(name = "textoEn")
    public String textoEn;

    @ColumnInfo(name = "textoFr")
    public String textoFr;

    @ColumnInfo(name = "imagen")
    public String imagen;

    @ColumnInfo(name = "nPab")
    public int nPab;

    @ColumnInfo(name = "francia")
    public boolean francia;

    @ColumnInfo(name = "nPos")
    public int nPos;

    public fichaPabellon(){
    }

    public fichaPabellon(int id, String textoEsp, String textoEn, String textoFr, String imagen, int nPab, boolean francia, int posicion) {
        this.id = id;
        this.textoEsp = textoEsp;
        this.textoEn = textoEn;
        this.textoFr = textoFr;
        this.imagen = imagen;
        this.nPab = nPab;
        this.francia = francia;
        this.nPos = posicion;
    }
}
