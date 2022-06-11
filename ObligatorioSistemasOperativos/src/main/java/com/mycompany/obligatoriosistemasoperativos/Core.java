package com.mycompany.obligatoriosistemasoperativos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Core {
    final int CoreID;
    private ProcessContext Context;
    private PCB RunningProcess;
    Timer pitTimer;

    Core (int coreId) {
        CoreID = coreId;
    }

    void DispatchProcess(PCB process, double timeSliceMS) {
        if (RunningProcess != null) 
            throw new IllegalStateException("Core " + CoreID + " is already running a process");

        RunningProcess = process;
        Context = process.Context;
        double burstTime = process.SchedulingData.RemainingTimeTillTermination;
        ProcessState endState = ProcessState.Finished;
        if (timeSliceMS < burstTime) {
            endState = ProcessState.Ready;
            burstTime = timeSliceMS;
        }
        if (process.SchedulingData.RemainingTimeTillNextIO < burstTime) {
            endState = ProcessState.Blocked;
            burstTime = process.SchedulingData.RemainingTimeTillNextIO;
        }
        pitTimer = new Timer( (int)timeSliceMS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                this.
            }
        });
    }

    PCB AppropriateCPU() {
        pitTimer.stop();
        RunningProcess.Context.SaveContext(Context);
        PCB process = RunningProcess;
        RunningProcess = null;
        return process;
    }

    boolean IsIdle() {
        return RunningProcess == null;
    }
    

    private class CoreTerminationActionListener implements ActionListener {
        private final Core core;
        private final double timeBurst;

        private CoreTerminationActionListener(Core core, double timeBurst) {
            this.core = core;
            this.timeBurst = timeBurst;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            this.
        }
    }
}
