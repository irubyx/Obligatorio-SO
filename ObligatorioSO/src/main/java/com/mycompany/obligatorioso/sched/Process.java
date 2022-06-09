package com.mycompany.obligatorioso.sched;

import java.util.LinkedList;

public class Process {
    public final int PID;
    public final Process Parent;
    public final ProcessContext Context;
    public final LinkedList<Process> Children;
    public final Program Program;
    public final MemoryDescriptor Memory;
    public final SchedulingData SchedulingData;
    
    public ProcessState State;
    public int Priority;

    public Process(int pid, Process parent, Program program) {
        this.PID = pid;
        this.Parent = parent;
        this.Context = new ProcessContext();
        this.Children = new LinkedList<>();
        this.Program = program;
        this.Memory = new MemoryDescriptor();
        this.State = ProcessState.New;
        this.Priority = 1;
        this.SchedulingData = new SchedulingData();
    }

    public void AddChild(Process child) {
        if (this.Children.contains(child))
            throw new IllegalStateException("Child already added");

        this.Children.add(child);
    }

    public void RemoveChild(Process child) {
        if (!this.Children.contains(child))
            throw new IllegalStateException("Child not found");

        this.Children.remove(child);
    }
}
