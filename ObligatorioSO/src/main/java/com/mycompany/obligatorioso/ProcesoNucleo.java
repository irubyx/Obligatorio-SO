/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatorioso;

/**
 *
 * @author SebaFripp
 */
public class ProcesoNucleo {
    private int id;
    private Proceso proc;
    private Nucleo nuc;
    private boolean eliminado;
    
    public ProcesoNucleo(int id, Proceso proc, Nucleo nuc){
        this.id = id;
        this.proc = proc;
        this.nuc = nuc;
        this.eliminado = false;
    }
}
