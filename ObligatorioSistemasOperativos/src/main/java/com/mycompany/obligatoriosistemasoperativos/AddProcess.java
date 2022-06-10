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
    private String name = "";
    private int CPU_time = 0;
    private boolean isLoaded = false;
    
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
    
    public void setValues(String pName, String pCPU_time)
    {
        this.name = pName;
        this.CPU_time =Integer.valueOf(pCPU_time);
        this.isLoaded = true;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    
    public int getCPUTime()
    {
        return this.CPU_time;
    }
    
    public boolean getIsLoaded()
    {
        return this.isLoaded;
    }
    
    public void setIsLoaded()
    {
        this.isLoaded = true;
    }

}
