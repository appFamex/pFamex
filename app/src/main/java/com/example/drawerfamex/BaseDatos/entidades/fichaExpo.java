package com.example.drawerfamex.BaseDatos.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class fichaExpo {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "francia")
    public boolean francia;

    @ColumnInfo(name = "descES")
    public String descEs;

    @ColumnInfo(name = "descEn")
    public String descEn;

    @ColumnInfo(name = "descFr")
    public String descFr;

    @ColumnInfo(name = "imagen")
    public String imagen;

    @ColumnInfo(name = "nBlock")
    public int nBlok;

    public fichaExpo(){
    }

    public fichaExpo(int id, String nombre, boolean francia, String descEs, String descEn, String descFr, String imagen, int nBlok) {
        this.id = id;
        this.nombre = nombre;
        this.francia = francia;
        this.descEs = descEs;
        this.descEn = descEn;
        this.descFr = descFr;
        this.imagen = imagen;
        this.nBlok = nBlok;
    }
}
