/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatorioso;

/**
 *
 * @author nmais
 */
public class AgregarProcesos {
    private static AgregarProcesos instance;
    private String nombre = "";
    private int tiempoCPU = 0;
    private boolean estaCargado = false;
    
    private AgregarProcesos()
    {}
    
    public static AgregarProcesos getInstance()
    {
        if(instance == null)
        {
            instance = new AgregarProcesos();
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
