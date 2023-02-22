package com.example.drawerfamex.item;


public class Item1 implements Item {

    //SE UTILIZA EN PANTALLAS QUE INTREGAN UN PDF
    private String  nombre;
    private int img,tipo;
    private String  pdf;

    public Item1(String nombre, int tipo, int img, String pdf) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.img = img;
        this.pdf = pdf;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    public  String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public int getViewType() {
        return 1;
    }

}