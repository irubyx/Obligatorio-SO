package com.mycompany.obligatoriosistemasoperativos;

import java.io.Serializable;

public class SchedulingData implements Serializable{
    long RemainingTimeTillTermination;
    long RemainingTimeTillNextIO;
    long LastExecutionTime;
    int assignedCore;
    final long IOInterval;
    final boolean Loops;
    
    SchedulingData(long remainingTimeTillTermination, long remainingTimeTillNextIO) {
        this.RemainingTimeTillTermination = remainingTimeTillTermination;
        this.RemainingTimeTillNextIO = remainingTimeTillNextIO;
        this.IOInterval = remainingTimeTillNextIO;
        this.assignedCore = 0;
        this.Loops = (remainingTimeTillTermination == 0);
    }

}
