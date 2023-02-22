package com.example.drawerfamex.item;

import java.io.Serializable;
import java.util.ArrayList;

public class Item5 implements Item, Serializable {

    //SE UTILIZA EN LOS POP UPS DE EXPOSICION ESTATICA NORMAL Y FRANCIA

    private String nombre, descripcion;
    private int img;
    private ArrayList<Item5> data = new ArrayList<>();

    public Item5(String nombre, String descripcion,int img) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.img=img;
    }
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getViewType() {
        return 5;
    }
}
