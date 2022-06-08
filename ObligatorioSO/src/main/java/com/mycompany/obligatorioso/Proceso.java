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
    private String estado;
    private double ram;

    public Proceso(String nombre,int tiempo, double ram) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.finalizado = false;
        this.eliminado = false;
        this.tiempoRestante = tiempo;
        this.id = this.ids++;
        this.estado = "en espera";
        this.ram = ram;
    }
}
