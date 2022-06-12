package com.mycompany.obligatoriosistemasoperativos;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Core {

    final int CoreID;
    final ProcessContext Context;
    PCB RunningProcess;
    ScheduledFuture<?> RunningProcessFuture;

    Core(int coreId) {
        this.CoreID = coreId;
        this.Context = new ProcessContext();
    }

    void Dispatch(PCB process, long timeSliceMS) {
        if (RunningProcess != null) {
            throw new IllegalStateException("Core " + CoreID + " is already running a process");
        }
        this.RunningProcess = process;
        this.RunningProcess.SchedulingData.CalculateNextBurst(timeSliceMS);
        this.RunningProcess.SchedulingData.assignedCore = this.CoreID;
        this.RunningProcess.State = ProcessState.Running;
        this.RestoreContext();

        switch (this.RunningProcess.SchedulingData.NextBurstType) {
            case IO:
                this.RunningProcessFuture = InterruptionManager.SetInterrupt(new InterruptIORunnable(this), this.RunningProcess.SchedulingData.NextBurst);
                break;
            case Termination:
                this.RunningProcessFuture = InterruptionManager.SetInterrupt(new InterruptCompletionRunnable(this), this.RunningProcess.SchedulingData.NextBurst);
                break;
            case TimeSlice:
                this.RunningProcessFuture = InterruptionManager.SetInterrupt(new InterruptTimerRunnable(this), this.RunningProcess.SchedulingData.NextBurst);
                break;
            default:
                break;
        }
    }

    PCB Appropriate() {
        long burstTime;
        if (RunningProcess == null) {
            throw new IllegalStateException("Core " + CoreID + " is already running a process");
        }
        if (this.RunningProcessFuture.getDelay(TimeUnit.MILLISECONDS) <= 0) {
            burstTime = this.RunningProcess.SchedulingData.NextBurst;
        } else {
            burstTime = this.RunningProcess.SchedulingData.NextBurst - this.RunningProcessFuture.getDelay(TimeUnit.MILLISECONDS);
            InterruptionManager.CancelInterrupt(this.RunningProcessFuture);
        }

        this.SaveContext(burstTime);
        PCB process = this.RunningProcess;
        this.RunningProcess = null;
        process.SchedulingData.assignedCore = -1;
        return process;
    }

    boolean IsIdle() {
        return RunningProcess == null;
    }

    private void SaveContext(long burstTime) {
        this.RunningProcess.Context.SaveContext(this.Context);
        this.RunningProcess.SchedulingData.Update(burstTime);
    }

    private void RestoreContext() {
        this.Context.SaveContext(this.RunningProcess.Context);
    }

    /*
     * Interrupt Runnables
     */
    abstract private class InterruptRunnable implements Runnable {

        private final Core core;
        private final Scheduler scheduler;

        private InterruptRunnable(Core core) {
            this.core = core;
            this.scheduler = Scheduler.GetInstance();
        }
    }

    private class InterruptTimerRunnable extends InterruptRunnable {

        private InterruptTimerRunnable(Core core) {
            super(core);
        }

        @Override
        public void run() {
            super.scheduler.InterruptTimer(super.core);
        }
    }

    private class InterruptIORunnable extends InterruptRunnable {

        private InterruptIORunnable(Core core) {
            super(core);
        }

        @Override
        public void run() {
            super.scheduler.InterruptIO(super.core);
        }
    }

    private class InterruptCompletionRunnable extends InterruptRunnable {

        private InterruptCompletionRunnable(Core core) {
            super(core);
        }

        @Override
        public void run() {
            super.scheduler.InterruptCompletion(super.core);
        }
    }
}
