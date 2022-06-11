package com.mycompany.obligatoriosistemasoperativos;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Core2 {
    final int CoreID;
    final ProcessContext Context;
    PCB RunningProcess;
    Timer PITTimer;
    Timer IOTimer;
    Timer ExecutionTimer;
    private boolean timerFlag;

    Core2(int coreId) {
        this.CoreID = coreId;
        this.timerFlag = false;
        this.Context = new ProcessContext();
    }


    void Dispatch(PCB process, double timeSliceMS) {
        if (RunningProcess != null)
            throw new IllegalStateException("Core " + CoreID + " is already running a process");

        this.RunningProcess = process;
        process.State = ProcessState.Running;
        this.RestoreContext();
        this.PITTimer = new Timer((int) timeSliceMS, new InterruptTimerActionListener(this, timeSliceMS));
        this.PITTimer.setRepeats(false);
        this.IOTimer = new Timer((int) process.SchedulingData.RemainingTimeTillNextIO, new InterruptIOActionListener(this, process.SchedulingData.RemainingTimeTillNextIO));
        this.IOTimer.setRepeats(false);
        this.ExecutionTimer = new Timer((int) process.SchedulingData.RemainingTimeTillTermination, new InterruptCompletionActionListener(this, process.SchedulingData.RemainingTimeTillTermination));
        this.ExecutionTimer.setRepeats(false);

        this.PITTimer.start();
        this.IOTimer.start();
        this.ExecutionTimer.start();
    }

    PCB Appropriate() {
        this.timerFlag = true;
        this.PITTimer.stop();
        this.IOTimer.stop();
        this.ExecutionTimer.stop();
        this.SaveContext();
        PCB process = this.RunningProcess;
        this.RunningProcess = null;
        return process;
    }

    boolean IsIdle() {
        return RunningProcess == null;
    }


    private void SaveContext() {
        this.RunningProcess.Context.SaveContext(this.Context);
        long elapsedTime = System.nanoTime() - this.RunningProcess.SchedulingData.LastExecutionTime;
        this.RunningProcess.SchedulingData.RemainingTimeTillTermination -= elapsedTime;
        this.RunningProcess.SchedulingData.RemainingTimeTillNextIO -= elapsedTime;
        if (this.RunningProcess.SchedulingData.RemainingTimeTillTermination < 0) {
            this.RunningProcess.SchedulingData.RemainingTimeTillTermination = 0;
        }
        if (this.RunningProcess.SchedulingData.RemainingTimeTillNextIO < 0) {
            this.RunningProcess.SchedulingData.RemainingTimeTillNextIO = 0;
        }
    }

    private void RestoreContext() {
        this.Context.SaveContext(this.RunningProcess.Context);
        this.RunningProcess.SchedulingData.LastExecutionTime = System.nanoTime();
    }
    
    
    abstract private class CoreTerminationActionListener implements ActionListener {
        protected final Core2 core;
        protected final double timeBurst;
        protected final Scheduler2 scheduler;

        private CoreTerminationActionListener(Core2 core, double timeBurst) {
            this.core = core;
            this.timeBurst = timeBurst;
            this.scheduler = Scheduler2.GetInstance();
        }

        
        protected void preAction() {
            this.core.timerFlag = true;
            this.core.PITTimer.stop();
            this.core.IOTimer.stop();
            this.core.ExecutionTimer.stop();
            this.core.RunningProcess.SchedulingData.RemainingTimeTillTermination -= this.timeBurst;
            if (this.core.RunningProcess.SchedulingData.RemainingTimeTillTermination <= 0) {
                this.core.RunningProcess.SchedulingData.RemainingTimeTillTermination = 0;
            }
            this.core.RunningProcess.SchedulingData.RemainingTimeTillNextIO -= this.timeBurst;
            if (this.core.RunningProcess.SchedulingData.RemainingTimeTillNextIO <= 0) {
                this.core.RunningProcess.SchedulingData.RemainingTimeTillNextIO = 0;
            }
        }
    }

    private class InterruptIOActionListener extends CoreTerminationActionListener {
        private InterruptIOActionListener(Core2 core, double timeBurst) {
            super(core, timeBurst);
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (!this.core.timerFlag) {
                super.preAction();
                this.scheduler.InterruptIO(this.core);
            }
        }
    }

    private class InterruptTimerActionListener extends CoreTerminationActionListener {
        private InterruptTimerActionListener(Core2 core, double timeBurst) {
            super(core, timeBurst);
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (!this.core.timerFlag) {
                super.preAction();
                this.scheduler.InterruptTimer(this.core);
            }
        }
    }

    private class InterruptCompletionActionListener extends CoreTerminationActionListener {
        private InterruptCompletionActionListener(Core2 core, double timeBurst) {
            super(core, timeBurst);
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (!this.core.timerFlag) {
                super.preAction();
                this.scheduler.InterruptCompletion(this.core);
            }
        }
    }
}
