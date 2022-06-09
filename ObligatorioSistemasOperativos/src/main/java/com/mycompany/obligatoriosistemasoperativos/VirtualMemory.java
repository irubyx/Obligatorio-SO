package com.mycompany.obligatoriosistemasoperativos;

import java.util.LinkedList;

public class VirtualMemory {
    static final int DEFAULT_FRAME_SIZE = 4096;

    public final LinkedList<Integer> FreeFrames;
    public int FrameSize;
    public int FrameCount;
    public int PageSize;
    public long PageCount;
    public long VMSize;
    public long RAMSize;

    public VirtualMemory(int ramSize) {
        this.RAMSize = ramSize;
        this.VMSize = DEFAULT_FRAME_SIZE * 1024 * 1024;
        this.FrameSize = DEFAULT_FRAME_SIZE;
        this.FrameCount = ramSize / this.FrameSize;
        this.PageSize = this.FrameSize;
        this.PageCount = this.VMSize / this.PageSize;
        this.FreeFrames = new LinkedList<>();
        for (int i = 0; i < this.FrameCount; i++) {
            FreeFrames.add(i);
        }
    }
}
