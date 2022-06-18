package com.mycompany.proyectososegundaentrega;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Scheduler {

    private static Scheduler instance;

    static final int MAX_PID = 1000;
    static final long DEFAULT_QUANTUM_MS = 100;
    static final long NRU_MS_INTERVAL = 100;

    private final LinkedList<PCB> readyQueue;
    private final LinkedList<PCB> blockedProcesses;
    private final LinkedList<PCB> processTable;
    private Core[] cores;
    private Hardware hardware;
    private VirtualMemory virtualMemory;
    private final LinkedList<MemoryDescriptor> memoryDescriptors;
    private final Queue<Integer> freePIDs;
    private long quantumMS;
    private boolean running;
    private Semaphore mutex;

    private Scheduler() {
        this.memoryDescriptors = new LinkedList<>();
        this.processTable = new LinkedList<>();
        this.freePIDs = new LinkedList<>();
        this.readyQueue = new LinkedList<>();
        this.blockedProcesses = new LinkedList<>();
        this.mutex = new Semaphore(1);
        this.running = false;
        for (int i = 0; i < MAX_PID; i++) {
            freePIDs.add(i);
        }
        this.quantumMS = DEFAULT_QUANTUM_MS;
    }

    public static Scheduler GetInstance() {
        if (instance == null) {
            instance = new Scheduler();
        }
        return instance;
    }

    /*
     * Scheduler Control.
     */
    public void Start() {
        if (this.running) {
            throw new IllegalStateException("Scheduler is already running");
        }
        if (this.hardware == null) {
            throw new IllegalStateException("Scheduler must be bound to a hardware");
        }

        this.running = true;
        this.StartInit();
        this.Schedule();
    }

    public void Stop() {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }

        for (int i = 0; i < this.cores.length; i++) {
            this.ReleaseCore(i);;
        }
        this.ClearProcesses();
        this.running = false;
    }

    /*
     * Settings
     */
    public void BindHardware(Hardware hardware) {
        if (this.running) {
            throw new IllegalStateException("Can't bind hardware while scheduler is running");
        }

        this.hardware = hardware;
        this.virtualMemory = new VirtualMemory(hardware.GetRAMSize());
        this.cores = new Core[hardware.GetCPUCoreCount()];
        for (int i = 0; i < cores.length; i++) {
            cores[i] = new Core(i);
        }
    }

    public void SetTimeSlice(long quantumMS) {
        if (this.running) {
            throw new IllegalStateException("Can't change quantum while scheduler is running");
        }

        this.quantumMS = quantumMS;
    }

    /*
     * Process Management
     */
    public void RunProgram(Program program, int priority, int parentId) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }
        if (program == null) {
            throw new IllegalArgumentException("Program can't be null");
        }
        if (priority < 1 || priority > 99) {
            throw new IllegalArgumentException("Priority must be between 1 and 99");
        }

        PCB parent = this.GetProcess(parentId);
        if (parent == null) {
            parent = this.GetProcess(0);
        }
        PCB process = this.InitializePCB(program, priority, parent);            //Tengo el nuevo proceso
        this.RunProcess(process);
    }

    public void TerminateProcess(int pid) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }

        PCB process = this.GetProcess(pid);
        this.KillProcess(process);
    }

    public void BlockProcess(int pid) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }

        PCB process = this.GetProcess(pid);
        this.BlockProcess(process, 0);
    }

    public void UnblockProcess(int pid) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }

        PCB process = this.GetProcess(pid);
        this.UnblockProcess(process);
    }

    /*
     * Querying
     */
    public LinkedList<ProcessDetail> GetRunningProcesses() {
        if (!running) {
            throw new IllegalStateException("Not running");
        }

        LinkedList<ProcessDetail> runningProcesses = new LinkedList<>();
        try {
            this.mutex.acquire();
            try {
                for (int i = 0; i < this.hardware.GetCPUCoreCount(); i++) {
                    if (!this.cores[i].IsIdle()) {
                        PCB process = this.cores[i].RunningProcess;
                        ProcessDetail processDetail = new ProcessDetail(process.PID, process.Program.Name, process.State, process.Priority);
                        runningProcesses.add(processDetail);
                    }
                }
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }

        return runningProcesses;
    }

    public LinkedList<ProcessDetail> GetAllProcesses() {
        if (!running) {
            throw new IllegalStateException("Not running");
        }

        LinkedList<ProcessDetail> processes = new LinkedList<>();
        try {
            this.mutex.acquire();
            try {
                for (PCB process : this.processTable) {
                    ProcessDetail processDetail = new ProcessDetail(process.PID, process.Program.Name, process.State, process.Priority);
                    processes.add(processDetail);
                }
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }

        return processes;
    }

    public LinkedList<ProcessDetail> GetReadyQueue() {
        if (!running) {
            throw new IllegalStateException("Not running");
        }

        LinkedList<ProcessDetail> readyQueueCopy = new LinkedList<>();
        try {
            this.mutex.acquire();
            try {
                for (PCB process : this.readyQueue) {
                    ProcessDetail processDetail = new ProcessDetail(process.PID, process.Program.Name, process.State, process.Priority);
                    readyQueueCopy.add(processDetail);
                }
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }

        return readyQueueCopy;
    }

    public LinkedList<ProcessDetail> GetBlockedProcesses() {
        if (!running) {
            throw new IllegalStateException("Not running");
        }

        LinkedList<ProcessDetail> blockedProcessesCopy = new LinkedList<>();
        try {
            this.mutex.acquire();
            try {
                for (PCB process : this.blockedProcesses) {
                    ProcessDetail processDetail = new ProcessDetail(process.PID, process.Program.Name, process.State, process.Priority);
                    blockedProcessesCopy.add(processDetail);
                }
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }

        return blockedProcessesCopy;
    }

    public int GetMemoryUsage() {
        if (!running) {
            throw new IllegalStateException("Not running");
        }

        return MemoryManager.GetMemoryUsage(this.memoryDescriptors);
    }
    
    public VirtualMemory getVirtualMemory()
    {
        return this.virtualMemory;
    }

    public Core[] getCores() {
        return this.cores;
    }

    public void ProcessPriorityUpdate(int pPID, int pPriority) {
        PCB process = this.GetProcess(pPID);
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }
        if (process == null) {
            throw new IllegalArgumentException("Process cannot be null");
        }
        if (pPriority < 1 || pPriority > 99) {
            throw new IllegalArgumentException("Priority must be between 1 and 99");
        }
        if(!this.processTable.contains(process))
        {
            throw new IllegalArgumentException("The process is not in the sistem");
        }
        process.Priority = pPriority;
    }
    
    public Hardware getHardware()
    {
        return this.hardware;
    }

    /*
     * Internal methods
     */
    private void StartInit() {
        Program initialProgram = new Program("init", 0, 0, 0, 1024);
        PCB process = this.InitializePCB(initialProgram, 10, null);
        this.RunProcess(process);
    }

    private void Schedule() {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }

        try{
            this.mutex.acquire();
            try {
                for (int i = 0; i < cores.length; i++) {
                    if (this.cores[i].IsIdle()) {
                        this.Schedule(i);
                    }
                }
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }
    }

    private void Schedule(int coreId) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }
        if (coreId < 0 || coreId >= this.cores.length) {
            throw new IllegalArgumentException("Invalid core id");
        }

        PCB process = this.readyQueue.poll();
        if (process != null) {
            MemoryManager.AllocateMemory(process, memoryDescriptors, virtualMemory);
            this.cores[coreId].Dispatch(process, quantumMS);
        }
    }

    private void ReleaseCore(int coreId) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }

        try {
            this.mutex.acquire();
            try {
                if (!this.cores[coreId].IsIdle()) {
                    this.cores[coreId].Appropriate();
                }
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }
    }

    private void ClearProcesses() {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }

        try {
            this.mutex.acquire();
            try {
                this.readyQueue.clear();
                this.blockedProcesses.clear();
                this.processTable.clear();
                this.freePIDs.clear();
                this.memoryDescriptors.clear();
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }
    }

    private PCB InitializePCB(Program program, int priority, PCB parent) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }
        if (program == null) {
            throw new IllegalArgumentException("Program cannot be null");
        }
        if (priority < 1 || priority > 99) {
            throw new IllegalArgumentException("Priority must be between 1 and 99");
        }
        if (this.processTable.size() == 0 && parent != null) {
            throw new IllegalArgumentException("Parent process for first process must be null");
        }
        if (this.processTable.size() > 0 && parent == null) {
            throw new IllegalArgumentException("All processes aside first process must have a parent");
        }
        if (this.processTable.size() > 0 && parent != null && !this.processTable.contains(parent)) {
            throw new IllegalArgumentException("Parent process must be in process table");
        }

        int pid = this.freePIDs.poll();
        if (pid == -1) {
            throw new IllegalStateException("No more free PIDs");
        }

        PCB process = new PCB(pid, parent, program);
        if (parent != null) {
            parent.AddChild(process);
        }
        process.Priority = priority;
        process.State = ProcessState.New;
        MemoryManager.NewProcessArea(process, program.RequiredMemoryB);
        return process;
    }

    private void RunProcess(PCB process) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }
        if (process == null) {
            throw new IllegalArgumentException("Process cannot be null");
        }
        if (process.State != ProcessState.New) {
            throw new IllegalArgumentException("Process must be in New state");
        }

        try {
            this.mutex.acquire();
            try {
                process.State = ProcessState.Ready;
                this.memoryDescriptors.add(process.Memory);
                this.processTable.add(process);
                this.EnqueueProcess(process);
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }

        this.Schedule();
    }

    private void EnqueueProcess(PCB process) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }
        if (process == null) {
            throw new IllegalArgumentException("Process cannot be null");
        }
        if (process.State != ProcessState.Ready) {
            throw new IllegalArgumentException("Process must be in Ready state");
        }

        for (int i = 0; i < this.readyQueue.size(); i++) {
            if (this.readyQueue.get(i).Priority > process.Priority) {
                this.readyQueue.add(i, process);
                return;
            }
        }
        this.readyQueue.add(process);
    }

    private void KillProcess(PCB process) {
        this.KillProcessAux(process);
        process.Parent.RemoveChild(process);
        this.Schedule();
    }

    private void KillProcessAux(PCB process) {
        if (!this.running) {
            throw new IllegalStateException("Not running");
        }
        if (process == null) {
            throw new IllegalArgumentException("Process cannot be null");
        }
        if (!this.processTable.contains(process)) {
            throw new IllegalArgumentException("Process not found");
        }

        for (PCB hijo : process.Children) {
            this.KillProcessAux(hijo);
        }

        try {
            this.mutex.acquire();
            try {
                if (this.readyQueue.contains(process)) {
                    this.readyQueue.remove(process);
                }
                if (this.blockedProcesses.contains(process)) {
                    this.blockedProcesses.remove(process);
                }
                if (process.SchedulingData.assignedCore >= 0) {
                    this.cores[process.SchedulingData.assignedCore].Appropriate();
                }
                this.processTable.remove(process);
                this.freePIDs.add(process.PID);
                process.State = ProcessState.Finished;
                MemoryManager.FreeProcessMemory(this.virtualMemory, process);
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }
    }

    private void BlockProcess(PCB process, long time) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }
        if (process == null) {
            throw new IllegalArgumentException("Process cannot be null");
        }
        if (!this.processTable.contains(process)) {
            throw new IllegalArgumentException("Process not found");
        }
        if (process.State != ProcessState.Ready && process.State != ProcessState.Running) {
            throw new IllegalArgumentException("Process must be in Ready or Running state");
        }

        try {
            this.mutex.acquire();
            try {
                if (this.readyQueue.contains(process)) {
                    this.readyQueue.remove(process);
                }
                if (process.State == ProcessState.Running) {
                    this.cores[process.SchedulingData.assignedCore].Appropriate();
                }
                process.State = ProcessState.Blocked;
                if (time > 0) {
                    this.AwaitIO(process, time);
                }
                this.blockedProcesses.add(process);

            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }

        this.Schedule();
    }

    private void UnblockProcess(PCB Process) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }
        if (Process == null) {
            throw new IllegalArgumentException("Process cannot be null");
        }
        if (!this.processTable.contains(Process)) {
            throw new IllegalArgumentException("Process not found");
        }
        if (Process.State != ProcessState.Blocked) {
            throw new IllegalArgumentException("Process must be in Blocked state");
        }
        if (!this.blockedProcesses.contains(Process)) {
            throw new IllegalArgumentException("Process not found in blocked queue");
        }
        try {
            this.mutex.acquire();
            try {
                this.blockedProcesses.remove(Process);
                if (Process.ioResponseFuture != null) {
                    Process.ioResponseFuture.cancel(true);
                }
                Process.State = ProcessState.Ready;
                this.EnqueueProcess(Process);
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }
        this.Schedule();
    }

    PCB GetProcess(int pid) {
        if (!this.running) {
            throw new IllegalStateException("Scheduler is not running");
        }
        if (pid < 0 || pid >= MAX_PID) {
            throw new IllegalArgumentException("Invalid PID");
        }

        for (PCB process : this.processTable) {
            if (process.PID == pid) {
                return process;
            }
        }

        return null;
    }

    void AwaitIO(PCB process, long delay) {
        process.ioResponseFuture = InterruptionManager.SetInterrupt(new ResponseIORunnable(process.PID), delay);
    }

    void StopAwaitingIO(PCB process) {
        if (process.ioResponseFuture != null) {
            InterruptionManager.CancelInterrupt(process.ioResponseFuture);
            process.ioResponseFuture = null;
        }
    }

    void InterruptIO(Core core) {
        this.BlockProcess(core.RunningProcess, core.RunningProcess.SchedulingData.IOInterval);
    }

    void InterruptTimer(Core core) {
        try {
            this.mutex.acquire();
            try {
                PCB process = core.Appropriate();
                process.State = ProcessState.Ready;
                this.EnqueueProcess(process);
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }

        this.Schedule();
    }

    void InterruptCompletion(Core core) {
        this.KillProcess(core.RunningProcess);
    }

    void InterruptIOResponse(int pid) {
        PCB process = this.GetProcess(pid);
        if (process == null) {
            throw new IllegalArgumentException("Process not found");
        }
        if (process.State != ProcessState.Blocked) {
            throw new IllegalArgumentException("Process must be in Blocked state");
        }

        try {
            this.mutex.acquire();
            try {
                this.blockedProcesses.remove(process);
                process.State = ProcessState.Ready;
                this.EnqueueProcess(process);
            } finally {
                this.mutex.release();
            }
        } catch (InterruptedException e) {
        }

        this.Schedule();
    }

    private class ResponseIORunnable implements Runnable {

        private final int pid;
        private final Scheduler scheduler;

        private ResponseIORunnable(int pid) {
            this.pid = pid;
            this.scheduler = Scheduler.GetInstance();
        }

        @Override
        public void run() {
            this.scheduler.InterruptIOResponse(this.pid);
        }
    }
}
