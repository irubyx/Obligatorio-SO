package com.mycompany.proyectososegundaentrega;

import java.util.LinkedList;

public class VirtualMemory {
    static final int DEFAULT_FRAME_SIZE = 4096;

    public final LinkedList<Integer> FreeFrames;
    
    int FrameSize;
    int FrameCount;
    int PageSize;
    long PageCount;
    long VMSize;
    long RAMSize;

    public VirtualMemory(long ramSize) {
        this.RAMSize = ramSize;
        this.VMSize = DEFAULT_FRAME_SIZE * 1024 * 1024;
        this.FrameSize = DEFAULT_FRAME_SIZE;
        this.FrameCount = (int)(ramSize / this.FrameSize);
        this.PageSize = this.FrameSize;
        this.PageCount = this.VMSize / this.PageSize;
        this.FreeFrames = new LinkedList<>();
        for (int i = 0; i < this.FrameCount; i++) {
            FreeFrames.add(i);
        }
    }

    public int GetFrameSize() {
        return this.FrameSize;
    }
    
    public long GetFrameCount() {
        return this.FrameCount;
    }

    public long GetPageSize() {
        return this.PageSize;
    }

    public long GetPageCount() {
        return this.PageCount;
    }

    public long GetVMSize() {
        return this.VMSize;
    }

    public long GetRAMSize() {
        return this.RAMSize;
    }
}
