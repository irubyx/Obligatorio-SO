/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatorioso;

/**
 *
 * @author SebaFripp
 */
public class Proceso {

    private static int ids;
    private String nombre;
    private boolean finalizado;
    private int tiempo;
    private int tiempoRestante;
    private boolean eliminado;
    private int id;
    private String estado;

    public Proceso(String nombre,int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.finalizado = false;
        this.eliminado = false;
        this.tiempoRestante = tiempo;
        this.id = this.ids++;
        this.estado = "en espera";
    }
}
