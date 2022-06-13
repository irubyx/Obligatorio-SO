/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriosistemasoperativos;

import GUI.Dashboard;
import static GUI.Dashboard.scheduler;
import java.util.LinkedList;

/**
 *
 * @author nmais
 */
public class DashboardUpdate extends Thread {

    public Dashboard dashboard;

    public DashboardUpdate(Dashboard pDashboard) {
        dashboard = pDashboard;
    }

    public void run() {
        while (true) {
            this.dashboard.cargarTablas();
        }
    }
}
