package com.mycompany.obligatoriosistemasoperativos;

public class MemoryArea {
    public final MemoryDescriptor Owner;
    public final int Size;
    public final int StartAddress;
    public final int EndAddress;

    public MemoryArea(int startAddress, int endAddress, MemoryDescriptor owner) {
        this.StartAddress = startAddress;
        this.EndAddress = endAddress;
        this.Owner = owner;
        this.Size = endAddress - startAddress;
    }
}
