package com.mycompany.obligatorioso.sched;

import java.util.Iterator;
import java.util.LinkedList;

public class MemoryManager {
    public static void NewProcessArea(Process process, int size) {
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

        if (startAddress + size > 0xFFFFFFFF)
            throw new IllegalArgumentException("Not enough memory");

        memory.Areas.add(new MemoryArea(startAddress, startAddress + size, memory));
    }

    public static void FreeProcessMemory(VirtualMemory vm, Process process) {
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

    public static PageTableEntry GetPage(Process process, int address) {
        int directory = (address & 0xFFC00000) >> 22;
        int table = (address & 0x3FF000) >> 12;
        return process.Memory.PageTable[directory][table];
    }

    public static LinkedList<PageTableEntry> GetAreaRequiredPages(Process process, MemoryArea area) {
        LinkedList<PageTableEntry> pages = new LinkedList<PageTableEntry>();
        for (int i = area.StartAddress; i < area.EndAddress; i += 0x1000) {
            pages.add(MemoryManager.GetPage(process, i));
        }
        return pages;
    }

    public static void AccessPages(Process process) {
        LinkedList<PageTableEntry> pages = new LinkedList<PageTableEntry>();
        for (MemoryArea memoryArea : process.Memory.Areas) {
            pages.addAll(MemoryManager.GetAreaRequiredPages(process, memoryArea));
        }
        for (PageTableEntry page : pages) {
            page.Access();
        }
    }
    
    public static void ClearAccessesCounters(Process process) {
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                process.Memory.PageTable[i][j].ResetAccess();;
            }
        }
    }

    public static int RequestFrame(VirtualMemory memory) {
        if (memory.FreeFrames.isEmpty()) {
            throw new IllegalStateException("No free frames");
        }
        int frame = memory.FreeFrames.removeFirst();
        return frame;
    }

    public static void ReleaseFrame(VirtualMemory memory, int frame) {
        memory.FreeFrames.add(frame);
    }

    public static int GetMemoryUsage(LinkedList<MemoryDescriptor> descriptors) {
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
}
