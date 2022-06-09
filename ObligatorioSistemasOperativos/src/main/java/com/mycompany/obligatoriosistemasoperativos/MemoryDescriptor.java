package com.mycompany.obligatoriosistemasoperativos;

import java.util.LinkedList;

public class MemoryDescriptor {
    public LinkedList<MemoryArea> Areas;
    public final PageTableEntry[][] PageTable;

    public MemoryDescriptor() {
        PageTable = new PageTableEntry[1024][1024];
        Areas = new LinkedList<MemoryArea>();
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                int address = ((i * 1024) + j) * 4096;
                PageTable[i][j] = new PageTableEntry(address);
            }
        }
    }
}
