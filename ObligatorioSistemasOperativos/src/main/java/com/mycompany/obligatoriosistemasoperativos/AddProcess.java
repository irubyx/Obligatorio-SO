/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriosistemasoperativos;

/**
 *
 * @author nmais
 */
public class AddProcess {
    private static AddProcess instance;
    private String nombre = "";
    private int tiempoCPU = 0;
    private boolean estaCargado = false;
    
    private AddProcess()
    {}
    
    public static AddProcess getInstance()
    {
        if(instance == null)
        {
            instance = new AddProcess();
        }
        return instance;
    }
    
    public void setValores(String pNombre, String pTiempoCPU)
    {
        this.nombre = pNombre;
        this.tiempoCPU =Integer.valueOf(pTiempoCPU);
        this.estaCargado = true;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
    
    public int getTiempoCPU()
    {
        return this.tiempoCPU;
    }
    
    public boolean getEstaCargado()
    {
        return this.estaCargado;
    }
    
    public void setEstaCargado()
    {
        this.estaCargado= true;
    }

}
