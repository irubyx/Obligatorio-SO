/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriosistemasoperativos;

import GUI.Estadisticas;

/**
 *
 * @author nmais
 */
public class EstadisticasUpdate implements Runnable {
    public Estadisticas estadisticas;

    public EstadisticasUpdate(Estadisticas pEstadistica) {
        estadisticas = pEstadistica;
    }
    
    @Override
    public void run() {
        this.estadisticas.cargarTablas();
        this.estadisticas.cargarMemoria();
    }
}
