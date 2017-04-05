package com.example.daini.practica8abril.Models;

/**
 * Created by Daini on 03/04/2017.
 */

public class Recados implements Comparable<Recados> {

    private String  nombre, fecha, hora, direcion,getDirecion2, descripcionRecado;

    public Recados(String nombre, String fecha, String hora, String direcion, String getDirecion2, String descripcionRecado) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.direcion = direcion;
        this.getDirecion2 = getDirecion2;
        this.descripcionRecado = descripcionRecado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getGetDirecion2() {
        return getDirecion2;
    }

    public void setGetDirecion2(String getDirecion2) {
        this.getDirecion2 = getDirecion2;
    }

    public String getDescripcionRecado() {
        return descripcionRecado;
    }

    public void setDescripcionRecado(String descripcionRecado) {
        this.descripcionRecado = descripcionRecado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public int compareTo(Recados o) {
        String hora1 = this.getHora();
        String hora2 =  o.getHora();
        return hora2.compareTo(hora1);
    }
}
