/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatorioso;

/**
 *
 * @author SebaFripp
 */
public class Nucleo {
    private static int genIds;
    private int id;
    private int capComputo;
    
    public Nucleo(int capComputo){
        this.capComputo = capComputo;
        this.setId(this.genIds++);
    }

    private void setId(int id){
        this.id = id;
    }
}