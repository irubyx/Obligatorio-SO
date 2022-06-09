/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatorioso;

import java.util.LinkedList;

/**
 *
 * @author SebaFripp
 */
public class Proceso {

    private static LinkedList<Proceso> procesos;
    private static int ids;
    private String nombre;
    private boolean finalizado;
    private int tiempo;
    private int tiempoRestante;
    private boolean eliminado;
    private int id;
    private Estados estado;
    private double ram;

    public Proceso(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.finalizado = false;
        this.eliminado = false;
        this.tiempoRestante = tiempo;
        this.id = this.ids++;
        this.estado = Estados.listo;
        this.ram = 30;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getTiempo() {
        return this.tiempo;
    }

    public int getTiempoRestante() {
        return this.tiempo;
    }

    public Estados getEstado() {
        return this.estado;
    }

    public double getRAM() {
        return this.ram;
    }

}
