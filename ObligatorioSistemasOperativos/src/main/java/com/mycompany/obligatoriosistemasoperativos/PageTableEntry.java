package com.mycompany.obligatoriosistemasoperativos;

public class PageTableEntry {
    public final int StartAddress;

    public boolean Valid;
    public int FrameNumber;

    private int accesses;

    public PageTableEntry(int startAddress) {
        this.StartAddress = startAddress;
        Valid = false;
        FrameNumber = 0;
        accesses = 0;
    }

    public void Access() {
        if (accesses < 2)
            accesses++;
    }

    public void ResetAccess() {
        accesses = 0;
    }

    public int GetAccesses() {
        return accesses;
    }	
    
}
