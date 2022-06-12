package com.mycompany.obligatoriosistemasoperativos;

import java.io.Serializable;

public class PageTableEntry implements Serializable{
    public final int StartAddress;

    boolean Valid;
    int FrameNumber;

    private int accesses;

    PageTableEntry(int startAddress) {
        this.StartAddress = startAddress;
        Valid = false;
        FrameNumber = 0;
        accesses = 0;
    }

    void Access() {
        if (accesses < 2)
            accesses++;
    }

    void ResetAccess() {
        accesses = 0;
    }

    public int GetAccesses() {
        return accesses;
    }

    public boolean IsValid() {
        return Valid;
    }

    public int GetFrameNumber() {
        return FrameNumber;
    }
}
