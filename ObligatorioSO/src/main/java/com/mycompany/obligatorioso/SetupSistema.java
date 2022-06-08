/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatorioso;

/**
 *
 * @author nmais
 */
public class SetupSistema {
    private static SetupSistema instance;
    private int cantidadNucleos = 0;
    private int tiempoCPU = 0;
    private int cantidadRAM = 0;
    
    private SetupSistema()
    {}
    
    public static SetupSistema getInstance()
    {
        if(instance == null)
        {
            instance = new SetupSistema();
        }
        return instance;
    }
    
    public void setValores(int pCantidadNucleos, int pTiempoCPU, int pCantidadRAM)
    {
        this.cantidadNucleos = pCantidadNucleos;
        this.tiempoCPU =pTiempoCPU;
        this.cantidadRAM = pCantidadRAM;
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
