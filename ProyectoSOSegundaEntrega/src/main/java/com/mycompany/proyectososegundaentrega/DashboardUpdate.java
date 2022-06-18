/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectososegundaentrega;

import GUI.Dashboard;
/**
 *
 * @author nmais
 */
public class DashboardUpdate implements Runnable {

    public Dashboard dashboard;

    public DashboardUpdate(Dashboard pDashboard) {
        dashboard = pDashboard;
    }
    
    @Override
    public void run() {
        
        //this.dashboard.cargarTablas();
    }
}
