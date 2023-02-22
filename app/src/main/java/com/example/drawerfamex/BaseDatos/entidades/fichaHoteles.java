package com.example.drawerfamex.BaseDatos.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class fichaHoteles {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "imagenEsp")
    public String imagenEsp;

    @ColumnInfo(name = "imagenEn")
    public String ImagenEn;

    @ColumnInfo(name = "imagenFr")
    public String ImagenFr;

    @ColumnInfo(name = "linkES")
    public String linkES;

    @ColumnInfo(name = "linkEN")
    public String linkEN;

    @ColumnInfo(name = "linkFR")
    public String linkFR;

    public fichaHoteles(){

    }

    public fichaHoteles(int id, String imagenEsp, String imagenEn, String imagenFr, String linkES, String linkEN, String linkFR) {
        this.id = id;
        this.imagenEsp = imagenEsp;
        ImagenEn = imagenEn;
        ImagenFr = imagenFr;
        this.linkES = linkES;
        this.linkEN = linkEN;
        this.linkFR = linkFR;
    }
}
