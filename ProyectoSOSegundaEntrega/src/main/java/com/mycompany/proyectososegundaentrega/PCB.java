package com.mycompany.proyectososegundaentrega;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ScheduledFuture;

public class PCB implements Serializable {
    public final int PID;
    public final PCB Parent;
    public final ProcessContext Context;
    public final LinkedList<PCB> Children;
    public final Program Program;
    public final MemoryDescriptor Memory;
    public final SchedulingData SchedulingData;
    
    ScheduledFuture<?> ioResponseFuture;
    ProcessState State;
    int Priority;

    PCB(int pid, PCB parent, Program program) {
        this.PID = pid;
        this.Parent = parent;
        this.Context = new ProcessContext();
        this.Children = new LinkedList<>();
        this.Program = program;
        this.Memory = new MemoryDescriptor();
        this.State = ProcessState.New;
        this.Priority = 1;
        this.SchedulingData = new SchedulingData(program.ExecutionTimeMS, program.IOIntervalMS);
    }

    void AddChild(PCB child) {
        if (this.Children.contains(child))
            throw new IllegalStateException("Child already added");

        this.Children.add(child);
    }

    void RemoveChild(PCB child) {
        if (!this.Children.contains(child))
            throw new IllegalStateException("Child not found");

        this.Children.remove(child);
    }

    public ProcessState GetState() {
        return this.State;
    }

    public int GetPriority() {
        return this.Priority;
    }
}
