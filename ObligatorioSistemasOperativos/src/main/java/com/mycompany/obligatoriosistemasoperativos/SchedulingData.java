package com.mycompany.obligatoriosistemasoperativos;

public class SchedulingData {
    long RemainingTimeTillTermination;
    long RemainingTimeTillNextIO;
    long LastExecutionTime;
    int assignedCore;
    final double IOInterval;

    SchedulingData(long remainingTimeTillTermination, long remainingTimeTillNextIO) {
        this.RemainingTimeTillTermination = remainingTimeTillTermination;
        this.RemainingTimeTillNextIO = remainingTimeTillNextIO;
        this.IOInterval = remainingTimeTillNextIO;
        this.assignedCore = 0;
    }

}
