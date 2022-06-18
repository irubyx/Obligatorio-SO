package com.mycompany.proyectososegundaentrega;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

class MemoryManager {
    static void NewProcessArea(PCB process, int size) {
        MemoryDescriptor memory = process.Memory;

        int startAddress = 0x0;
        Iterator<MemoryArea> iterator = memory.Areas.iterator();
        while (iterator.hasNext()) {
            MemoryArea area = iterator.next();
            if (area.StartAddress - startAddress >= size) {
                memory.Areas.add(new MemoryArea(startAddress, startAddress + size, memory));
                break;
            }
            startAddress = area.EndAddress;
        }

        if (startAddress + size > Integer.MAX_VALUE)
            throw new IllegalArgumentException("Not enough memory");

        memory.Areas.add(new MemoryArea(startAddress, startAddress + size, memory));
    }

    static void FreeProcessMemory(VirtualMemory vm, PCB process) {
        MemoryDescriptor memory = process.Memory;
        LinkedList<PageTableEntry> pages = new LinkedList<PageTableEntry>();
        for (MemoryArea memoryArea : memory.Areas) {
            pages.addAll(MemoryManager.GetAreaRequiredPages(process, memoryArea));
        }
        for (PageTableEntry page : pages) {
            if (page.Valid) {
                MemoryManager.ReleaseFrame(vm, page.FrameNumber);
                page.Valid = false;
                page.FrameNumber = 0;
            }
        }
    }

    static PageTableEntry GetPage(PCB process, int address) {
        int directory = (address & 0xFFC00000) >> 22;
        int table = (address & 0x3FF000) >> 12;
        return process.Memory.PageTable[directory][table];
    }

    static LinkedList<PageTableEntry> GetAreaRequiredPages(PCB process, MemoryArea area) {
        LinkedList<PageTableEntry> pages = new LinkedList<PageTableEntry>();
        for (int i = area.StartAddress; i < area.EndAddress; i += 0x1000) {
            pages.add(MemoryManager.GetPage(process, i));
        }
        return pages;
    }

    static void AccessPages(PCB process) {
        LinkedList<PageTableEntry> pages = new LinkedList<PageTableEntry>();
        for (MemoryArea memoryArea : process.Memory.Areas) {
            pages.addAll(MemoryManager.GetAreaRequiredPages(process, memoryArea));
        }
        for (PageTableEntry page : pages) {
            page.Access();
        }
    }
    
    static void ClearAccessesCounters(PCB process) {
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                process.Memory.PageTable[i][j].ResetAccess();;
            }
        }
    }

    static int RequestFrame(VirtualMemory memory) {
        if (memory.FreeFrames.isEmpty()) {
            throw new IllegalStateException("No free frames");
        }
        int frame = memory.FreeFrames.removeFirst();
        return frame;
    }

    static void ReleaseFrame(VirtualMemory memory, int frame) {
        memory.FreeFrames.add(frame);
    }

    static int GetMemoryUsage(LinkedList<MemoryDescriptor> descriptors) {
        int memoryUsage = 0;
        for (MemoryDescriptor descriptor : descriptors) {
            for (int i = 0; i < 1024; i++) {
                for (int j = 0; j < 1024; j++) {
                    if (descriptor.PageTable[i][j].Valid) {
                        memoryUsage += 0x1000;
                    }
                }
            }
        }

        return memoryUsage;
    }

    static void AllocateMemory(PCB process, LinkedList<MemoryDescriptor> descriptors, VirtualMemory vm) {
        LinkedList<PageTableEntry> pages = new LinkedList<PageTableEntry>();
        for (MemoryArea area : process.Memory.Areas) {
            pages.addAll(GetAreaRequiredPages(process, area));
        }

        for (PageTableEntry page : pages) {
            if (!page.Valid) {
                try {
                    int frame = RequestFrame(vm);
                    page.FrameNumber = frame;
                    page.Valid = true;
                } catch (IllegalStateException e) {
                    int frame = SwapPage(descriptors);
                    page.FrameNumber = frame;
                    page.Valid = true;
                }
            }
        }
    }

    static int SwapPage(LinkedList<MemoryDescriptor> descriptors) {
        LinkedList<PageTableEntry> candidates = new LinkedList<PageTableEntry>();
        
        for (MemoryDescriptor descriptor : descriptors) {
            for (int i = 0; i < 1024; i++) {
                for (int j = 0; j < 1024; j++) {
                    if (descriptor.PageTable[i][j].Valid) {
                        if (descriptor.PageTable[i][j].GetAccesses() == 0) {
                            candidates.add(descriptor.PageTable[i][j]);
                            candidates.add(descriptor.PageTable[i][j]);
                        }
                        else if (descriptor.PageTable[i][j].GetAccesses() == 1) {
                            candidates.add(descriptor.PageTable[i][j]);
                        }
                    }
                }
            }
        }

        if (candidates.isEmpty()) {
            throw new IllegalStateException("No free frames");
        }
        else {
            Random random = new Random();
            PageTableEntry page = candidates.get(random.nextInt(candidates.size()));
            int frame = page.FrameNumber;
            page.Valid = false;
            page.FrameNumber = 0;
            return frame;
        }
    }
}
