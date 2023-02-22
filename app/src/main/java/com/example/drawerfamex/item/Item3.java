package com.example.drawerfamex.item;

public class Item3 implements Item {

    //SE UTILIZA EN CHALETS (RECYCLER VIEW)

    private String nombre, descripcion;
    private int img;

    public Item3(String nombre, String descripcion, int img) {
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
        return 3;
    }
}
