package com.mycompany.obligatoriosistemasoperativos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PCB implements Serializable {
    public final int PID;
    public final PCB Parent;
    public transient final ProcessContext Context;
    public transient final LinkedList<PCB> Children;
    public final Program Program;
    public transient final MemoryDescriptor Memory;
    public transient final SchedulingData SchedulingData;
    private transient Timer ioResponseTimer;
    
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

    PCB deepCopy() {
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

    public ProcessState GetState() {
        return this.State;
    }

    public int GetPriority() {
        return this.Priority;
    }
    
    public void AwaitIO(int ioTimeMS) {
        this.ioResponseTimer = new Timer(ioTimeMS, new InterruptIOResponseActionListener(this.PID));
        this.ioResponseTimer.setRepeats(false);
        this.ioResponseTimer.start();
    }

    public void StopAwaitingIO() {
        if (this.ioResponseTimer != null) {
            this.ioResponseTimer.stop();
            this.ioResponseTimer = null;
        }
    }


    private class InterruptIOResponseActionListener implements ActionListener {
        private final int PID;
        private final Scheduler scheduler;

        public InterruptIOResponseActionListener(int PID) {
            this.PID = PID;
            this.scheduler = Scheduler.GetInstance();
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            this.scheduler.InterruptIOResponse(this.PID);
        }
    }
}
