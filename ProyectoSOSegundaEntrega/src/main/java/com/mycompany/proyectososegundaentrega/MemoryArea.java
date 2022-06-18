package com.mycompany.proyectososegundaentrega;

import java.io.Serializable;

public class MemoryArea implements Serializable{
    final transient MemoryDescriptor Owner;
    final int Size;
    final int StartAddress;
    final int EndAddress;

    MemoryArea(int startAddress, int endAddress, MemoryDescriptor owner) {
        this.StartAddress = startAddress;
        this.EndAddress = endAddress;
        this.Owner = owner;
        this.Size = endAddress - startAddress;
    }
}
