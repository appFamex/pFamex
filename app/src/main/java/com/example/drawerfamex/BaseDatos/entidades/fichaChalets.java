package com.example.drawerfamex.BaseDatos.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class fichaChalets {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "imagen")
    public String imagen;

    @ColumnInfo(name = "nBlock")
    public int nBlok;

    @ColumnInfo(name = "francia")
    public boolean francia;

    @ColumnInfo(name = "descES")
    public String descEs;

    @ColumnInfo(name = "descEn")
    public String descEn;

    @ColumnInfo(name = "descFr")
    public String descFr;


    public fichaChalets(){
    }

    public fichaChalets(int id, String nombre, String imagen, int nBlok, boolean francia, String descEs, String descEn, String descFr) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.nBlok = nBlok;
        this.francia = francia;
        this.descEs = descEs;
        this.descEn = descEn;
        this.descFr = descFr;

    }
}
