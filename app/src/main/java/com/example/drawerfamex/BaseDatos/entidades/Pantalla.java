package com.example.drawerfamex.BaseDatos.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.drawerfamex.BaseDatos.clases.Textos;

import java.util.ArrayList;

@Entity
public class Pantalla {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "textosEsp")
    public ArrayList<String> textosEsp;

    @ColumnInfo(name = "textosEn")
    public ArrayList<String> textosEn;

    @ColumnInfo(name = "textosFr")
    public ArrayList<String> textosFr;

    @ColumnInfo(name = "nombre")
    public String name;

    public Pantalla(){

    }
    public Pantalla(int id, String name){
        this.id = id;
        this.name = name;
        textosEn = new ArrayList<>();
        textosEsp = new ArrayList<>();
        textosFr = new ArrayList<>();
    }

    public Pantalla(int id, String name, ArrayList<String> txtEsp, ArrayList<String> txtEn, ArrayList<String> txtFr){
        this.id = id;
        this.name = name;
        textosEn = txtEn;
        textosEsp = txtEsp;
        textosFr = txtFr;
    }

}
