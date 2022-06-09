/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriosistemasoperativos;

/**
 *
 * @author nmais
 */
public class SystemSetup {
     private static SystemSetup instance;
    private int cantidadNucleos = 0;
    private int tiempoCPU = 0;
    private int cantidadRAM = 0;
    
    private SystemSetup()
    {}
    
    public static SystemSetup getInstance()
    {
        if(instance == null)
        {
            instance = new SystemSetup();
        }
        return instance;
    }
    
    public void setValores(String pCantidadNucleos, String pTiempoCPU, String pCantidadRAM)
    {
        this.cantidadNucleos = Integer.valueOf(pCantidadNucleos);
        this.tiempoCPU =Integer.valueOf(pTiempoCPU);
        this.cantidadRAM = Integer.valueOf(pCantidadRAM);
    }
    
    public int getCantidadNucleos()
    {
        return this.cantidadNucleos;
    }
    
    
    public int getTiempoCPU()
    {
        return this.tiempoCPU;
    }
    
    public int getCantidadRAM()
    {
        return this.cantidadRAM;
    }
}
