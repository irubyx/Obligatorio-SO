package com.mycompany.obligatoriosistemasoperativos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class PCB implements Serializable {
    public final int PID;
    public final PCB Parent;
    public final ProcessContext Context;
    public final LinkedList<PCB> Children;
    public final Program Program;
    public final MemoryDescriptor Memory;
    public final SchedulingData SchedulingData;
    
    public ProcessState State;
    public int Priority;

    public PCB(int pid, PCB parent, Program program) {
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

    public void AddChild(PCB child) {
        if (this.Children.contains(child))
            throw new IllegalStateException("Child already added");

        this.Children.add(child);
    }

    public void RemoveChild(PCB child) {
        if (!this.Children.contains(child))
            throw new IllegalStateException("Child not found");

        this.Children.remove(child);
    }

    public PCB deepCopy() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            PCB copy = (PCB) ois.readObject();
            ois.close();

            return copy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
