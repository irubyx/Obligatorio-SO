package com.mycompany.proyectososegundaentrega;

public class ProcessDetail {
    public final int PID;
    public final String Name;
    public final ProcessState State;
    public final int Priority;

    public ProcessDetail(int pid, String name, ProcessState state, int priority) {
        this.PID = pid;
        this.Name = name;
        this.State = state;
        this.Priority = priority;
    }
}
