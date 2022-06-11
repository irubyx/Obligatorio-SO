package com.mycompany.obligatoriosistemasoperativos;

public class SchedulingData {
    double RemainingTimeTillTermination;
    double RemainingTimeTillNextIO;
    final double IOInterval;

    SchedulingData(double remainingTimeTillTermination, double remainingTimeTillNextIO) {
        this.RemainingTimeTillTermination = remainingTimeTillTermination;
        this.RemainingTimeTillNextIO = remainingTimeTillNextIO;
        this.IOInterval = remainingTimeTillNextIO;
    }

}
