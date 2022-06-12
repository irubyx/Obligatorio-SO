package com.mycompany.obligatoriosistemasoperativos;

public class SchedulingData {
    long RemainingTimeTillTermination;
    long RemainingTimeTillNextIO;
    long NextBurst;
    AppropriationType NextBurstType;
    int assignedCore;
    final long IOInterval;
    final boolean Loops;
    final boolean HasIO;
    

    SchedulingData(long remainingTimeTillTermination, long remainingTimeTillNextIO) {
        this.RemainingTimeTillTermination = remainingTimeTillTermination;
        this.RemainingTimeTillNextIO = remainingTimeTillNextIO;
        this.IOInterval = remainingTimeTillNextIO;
        this.assignedCore = 0;
        this.Loops = (remainingTimeTillTermination == 0);
        this.HasIO = (IOInterval > 0);
    }


    void CalculateNextBurst(long quantumMS) {
        long burst = Long.MAX_VALUE;
        AppropriationType nextAppropriationType = AppropriationType.TimeSlice;
        if (!this.Loops) {
            burst = this.RemainingTimeTillTermination;
            nextAppropriationType = AppropriationType.Termination;
        }
        if (this.HasIO && burst > this.RemainingTimeTillNextIO) {
            burst = this.RemainingTimeTillNextIO;
            nextAppropriationType = AppropriationType.IO;
        }
        if (burst > quantumMS) {
            burst = quantumMS;
            nextAppropriationType = AppropriationType.TimeSlice;
        }

        this.NextBurst = burst;
        this.NextBurstType = nextAppropriationType;
    }

    void Update(long elapsedInterval) {
        this.RemainingTimeTillTermination -= elapsedInterval;
        this.RemainingTimeTillNextIO -= elapsedInterval;
        if (this.RemainingTimeTillTermination < 0) {
            this.RemainingTimeTillTermination = 0;
        }
        if (this.RemainingTimeTillNextIO < 0) {
            this.RemainingTimeTillNextIO = 0;
        }
        if (this.NextBurstType == AppropriationType.IO && this.RemainingTimeTillNextIO == 0) {
            this.RemainingTimeTillNextIO = this.IOInterval;
        }
        if (!this.Loops && this.HasIO && this.RemainingTimeTillNextIO == this.RemainingTimeTillTermination) {
            this.RemainingTimeTillTermination++;
        }
    }

    public enum AppropriationType {
        IO,
        Termination,
        TimeSlice
    }
}
