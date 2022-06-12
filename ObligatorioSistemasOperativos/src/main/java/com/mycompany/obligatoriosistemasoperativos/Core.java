package com.mycompany.obligatoriosistemasoperativos;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Core {

    final int CoreID;
    final ProcessContext Context;
    PCB RunningProcess;
    Timer PITTimer;
    Timer IOTimer;
    Timer ExecutionTimer;
    private boolean timerFlag;

    Core(int coreId) {
        this.CoreID = coreId;
        this.timerFlag = false;
        this.Context = new ProcessContext();
    }

    void Dispatch(PCB process, double timeSliceMS) {
        if (RunningProcess != null) {
            throw new IllegalStateException("Core " + CoreID + " is already running a process");
        }
        this.timerFlag = false;
        this.RunningProcess = process;
        process.State = ProcessState.Running;
        this.RestoreContext();
        this.PITTimer = new Timer((int) timeSliceMS, new InterruptTimerActionListener(this, timeSliceMS));
        this.PITTimer.setRepeats(false);
        if (process.SchedulingData.IOInterval > 0) {
            this.IOTimer = new Timer((int) process.SchedulingData.RemainingTimeTillNextIO, new InterruptIOActionListener(this, process.SchedulingData.RemainingTimeTillNextIO));
            this.IOTimer.setRepeats(false);
            this.IOTimer.start();
        }
        if (!process.SchedulingData.Loops) {
            this.ExecutionTimer = new Timer((int) process.SchedulingData.RemainingTimeTillTermination, new InterruptCompletionActionListener(this, process.SchedulingData.RemainingTimeTillTermination));
            this.ExecutionTimer.setRepeats(false);
            this.ExecutionTimer.start();
        }
        this.PITTimer.start();
    }

    PCB Appropriate() {
        this.timerFlag = true;
        if (this.PITTimer != null) {
            this.PITTimer.stop();
        }
        if (this.IOTimer != null) {
            this.IOTimer.stop();
        }
        if (this.ExecutionTimer != null) {
            this.ExecutionTimer.stop();
        }
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

        protected final Core core;
        protected final double timeBurst;
        protected final Scheduler scheduler;

        private CoreTerminationActionListener(Core core, double timeBurst) {
            this.core = core;
            this.timeBurst = timeBurst;
            this.scheduler = Scheduler.GetInstance();
        }

        protected void preAction() {
            this.core.timerFlag = true;
            if (this.core.PITTimer != null) {
                this.core.PITTimer.stop();
            }
            if (this.core.IOTimer != null) {
                this.core.IOTimer.stop();
            }
            if (this.core.ExecutionTimer != null) {
                this.core.ExecutionTimer.stop();
            }
            if (!this.core.RunningProcess.SchedulingData.Loops) {
                this.core.RunningProcess.SchedulingData.RemainingTimeTillTermination -= this.timeBurst;
                if (this.core.RunningProcess.SchedulingData.RemainingTimeTillTermination <= 0) {
                    this.core.RunningProcess.SchedulingData.RemainingTimeTillTermination = 0;
                }
            }
            if (this.core.RunningProcess.SchedulingData.IOInterval != 0) {
                this.core.RunningProcess.SchedulingData.RemainingTimeTillNextIO -= this.timeBurst;
                if (this.core.RunningProcess.SchedulingData.RemainingTimeTillNextIO <= 0) {
                    this.core.RunningProcess.SchedulingData.RemainingTimeTillNextIO = 0;
                }
            }
        }
    }
        private class InterruptIOActionListener extends CoreTerminationActionListener {

            private InterruptIOActionListener(Core core, double timeBurst) {
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

            private InterruptTimerActionListener(Core core, double timeBurst) {
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

            private InterruptCompletionActionListener(Core core, double timeBurst) {
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
