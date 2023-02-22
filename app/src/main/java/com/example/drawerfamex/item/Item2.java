package com.example.drawerfamex.item;

public class Item2 implements Item {

    //SE UTILIZA EN LA PANTALLA DE CONFERECNIAS (RECYCLER VIEW)

    private String nombre, horario, expositor, descripcion;

    public Item2(String nombre, String horario, String expositor, String descripcion) {
        this.nombre = nombre;
        this.horario = horario;
        this.expositor = expositor;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public String getExpositor() {
        return expositor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setExpositor(String expositor) {
        this.expositor = expositor;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getViewType() {
        return 2;
    }
}