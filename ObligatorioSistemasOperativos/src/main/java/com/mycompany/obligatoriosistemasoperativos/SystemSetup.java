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
    private int coresQuantity = 0;
    private int CPU_time = 0;
    private int ramQuantity = 0;
    
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
    
    public void setValores(String pCoreCuantity, String pCPU_time, String pRamQuantity)
    {
        this.coresQuantity = Integer.valueOf(pCoreCuantity);
        this.CPU_time =Integer.valueOf(pCPU_time);
        this.ramQuantity = Integer.valueOf(pRamQuantity);
    }
    
    public int getCoreQuantity()
    {
        return this.coresQuantity;
    }
    
    
    public int getCpu_Time()
    {
        return this.CPU_time;
    }
    
    public int getRamQuantity()
    {
        return this.ramQuantity;
    }
}
