package com.example.drawerfamex.item;

public class Item4 implements Item{

    //SE UTILIZA EN ITINERARIO (RECYCLER VIEW)

    private String nombreevento, nombreexpositor, hora, descripcion,lugar;
    private int img;

    public Item4(String nombreevento, String descripcion,String nombreexpositor,String hora, String lugar,int img) {
        this.nombreevento = nombreevento;
        this.descripcion = descripcion;
        this.img=img;
        this.nombreexpositor = nombreexpositor;
        this.hora = hora;
        this.lugar = lugar;
    }

    public void setNombreevento(String nombreevento) {
        this.nombreevento = nombreevento;
    }

    public String getNombreevento() {
        return nombreevento;
    }

    public void setNombreexpositor(String nombreexpositor) {
        this.nombreexpositor = nombreexpositor;
    }


    public String getNombreexpositor() {
        return nombreexpositor;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getLugar() {
        return lugar;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getViewType() {
        return 4;
    }
}
