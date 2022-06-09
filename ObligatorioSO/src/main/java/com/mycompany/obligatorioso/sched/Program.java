package com.mycompany.obligatorioso.sched;

public class Program {
    public final int ExecutionTimeMS;
    public final int IOIntervalMS;
    public final int IOWaitTimeMS;
    public final String Name;
    public final int RequiredMemoryB;

    public Program(String name, int executionTimeMS, int iOIntervalMS, int iOWaitTimeMS, int requiredMemoryB) {
        this.Name = name;
        this.ExecutionTimeMS = executionTimeMS;
        this.IOIntervalMS = iOIntervalMS;
        this.IOWaitTimeMS = iOWaitTimeMS;
        this.RequiredMemoryB = requiredMemoryB;
    }
}
