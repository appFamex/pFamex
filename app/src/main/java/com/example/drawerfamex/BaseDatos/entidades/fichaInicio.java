package com.example.drawerfamex.BaseDatos.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class fichaInicio {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "imagenEsp")
    public String imagen1;

    @ColumnInfo(name = "imagenEn")
    public String Imagen2;

    @ColumnInfo(name = "imagenFr")
    public String Imagen3;

    public fichaInicio(){

    }

    public fichaInicio(int id, String imagen1, String imagen2, String imagenFr) {
        this.id = id;
        this.imagen1 = imagen1;
        Imagen2 = imagen2;
        Imagen3 = imagenFr;
    }
}
