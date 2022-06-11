package com.mycompany.obligatoriosistemasoperativos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Core {
    final int CoreID;
    private final ProcessContext Context;
    private PCB RunningProcess;
    Timer pitTimer;
    Timer ioTimer;
    Timer executionTimer;
    private boolean timerFlag;

    Core(int coreId) {
        this.CoreID = coreId;
        this.timerFlag = false;
        this.Context = new ProcessContext();
    }

    void DispatchProcess(PCB process, double timeSliceMS) {
        if (RunningProcess != null) 
            throw new IllegalStateException("Core " + CoreID + " is already running a process");

        RunningProcess = process;
        process.State = ProcessState.Running;
        Context.SaveContext(process.Context);
        pitTimer = new Timer((int) timeSliceMS, new CoreTerminationActionListener(this, timeSliceMS, ProcessState.Ready));
        pitTimer.setRepeats(false);
        ioTimer = new Timer((int) process.SchedulingData.RemainingTimeTillNextIO, new CoreTerminationActionListener(this, process.SchedulingData.RemainingTimeTillNextIO, ProcessState.Ready));
        ioTimer.setRepeats(false);
        executionTimer = new Timer((int) process.SchedulingData.RemainingTimeTillTermination, new CoreTerminationActionListener(this, process.SchedulingData.RemainingTimeTillTermination, ProcessState.Finished));
        executionTimer.setRepeats(false);

        pitTimer.start();
        ioTimer.start();
        executionTimer.start();
    }

    PCB GetRunningProcess() {
        return RunningProcess;
    }

    PCB AppropriateCPU() {
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
        private final Scheduler scheduler;
        private final ProcessState endState;

        private CoreTerminationActionListener(Core core, double timeBurst, ProcessState endState) {
            this.core = core;
            this.timeBurst = timeBurst;
            this.scheduler = Scheduler.GetInstance();
            this.endState = endState;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (!core.timerFlag) {
                core.timerFlag = true;
                core.pitTimer.stop();
                core.ioTimer.stop();
                core.executionTimer.stop();
                core.RunningProcess.SchedulingData.RemainingTimeTillTermination -= timeBurst;
                if (core.RunningProcess.SchedulingData.RemainingTimeTillTermination <= 0) {
                    core.RunningProcess.SchedulingData.RemainingTimeTillTermination = 0;
                }
                core.RunningProcess.SchedulingData.RemainingTimeTillNextIO -= timeBurst;
                if (core.RunningProcess.SchedulingData.RemainingTimeTillNextIO <= 0) {
                    core.RunningProcess.SchedulingData.RemainingTimeTillNextIO = 0;
                }
                scheduler.AppropriateProcess(core.CoreID, endState);
            }
        }
    }
}
