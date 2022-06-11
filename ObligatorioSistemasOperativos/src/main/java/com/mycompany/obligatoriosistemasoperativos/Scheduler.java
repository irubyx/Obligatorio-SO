package com.mycompany.obligatoriosistemasoperativos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {
    private static Scheduler instance;

    static final int MAX_PID = 1000;
    static final double DEFAULT_QUANTUM_MS = 100;
    static final double NRU_MS_INTERVAL = 100;

    private final LinkedList<PCB> readyQueue;
    private final LinkedList<PCB> blockedProcesses;
    private final Core[] cores;
    private Hardware hardware;
    private final VirtualMemory virtualMemory;
    private final LinkedList<MemoryDescriptor> memoryDescriptors;
    private final LinkedList<PCB> processTable;
    private final Queue<Integer> freePIDs;
    private final ExecutorService executorService;
    private double timeSliceMS;
    private long cycles;

    private boolean running;

    private Scheduler(Hardware hardware) {
        executorService = Executors.newSingleThreadExecutor();
        this.hardware = hardware;
        this.virtualMemory = new VirtualMemory(hardware.GetRAMSize());
        this.memoryDescriptors = new LinkedList<>();
        this.processTable = new LinkedList<>();
        this.freePIDs = new LinkedList<>();
        this.readyQueue = new LinkedList<>();
        this.blockedProcesses = new LinkedList<>();
        this.cores = new Core[hardware.GetCPUCoreCount()];
        for (int i = 0; i < cores.length; i++) {
            cores[i] = new Core(i);
        }
        this.cycles = 0;
        this.running = false;
        for (int i = 0; i < MAX_PID; i++) {
            freePIDs.add(i);
        }
        this.timeSliceMS = DEFAULT_QUANTUM_MS;
    }

    public static Scheduler GetInstance(Hardware hardware) {
        if (instance == null) {
            instance = new Scheduler(hardware);
        }
        return instance;
    }

    public void Start() {
        if (running) {
            throw new IllegalStateException("Already running");
        }

        this.cycles = 0;
        running = true;

        Program initialProgram = new Program("init", 0, 0, 0, 1024);
        PCB process = this.CreateProcess(initialProgram, 10, null);
        this.AddProcessToReadyQueue(process);
        
        executorService.submit(this::Schedule);
    }

    public void Stop() {
        if (!running) {
            throw new IllegalStateException("Not running");
        }

        running = false;
        executorService.shutdownNow();
    }

    private PCB CreateProcess(Program program, int priority, PCB parent) {
        if (!running)
            throw new IllegalStateException("Not running");
        if (program == null)
            throw new IllegalArgumentException("Program cannot be null");
        if (priority < 1 || priority > 99)
            throw new IllegalArgumentException("Priority must be between 1 and 99");
        if (parent != null && processTable.size() == 0 && !processTable.contains(parent))
            throw new IllegalArgumentException("Parent process not found");
        if (parent == null && processTable.size() > 0)
            throw new IllegalArgumentException("Parent process can only be null if there are no processes");

        int pid = freePIDs.remove();
        PCB process = new PCB(pid, parent, program);
        process.Priority = priority;
        process.State = ProcessState.New;
        MemoryManager.NewProcessArea(process, program.RequiredMemoryB);
        processTable.add(process);
        return process;
    }

    private void TerminateProcess(PCB process) {
        if (!running)
            throw new IllegalStateException("Not running");
        if (process == null)
            throw new IllegalArgumentException("Process cannot be null");
        if (!processTable.contains(process))
            throw new IllegalArgumentException("Process not found");

        if (process.State == ProcessState.Ready) {
            this.readyQueue.remove(process);
        } else if (process.State == ProcessState.Blocked) {
            this.blockedProcesses.remove(process);
        }

        process.State = ProcessState.Finished;
        MemoryManager.FreeProcessMemory(this.virtualMemory, process);
        processTable.remove(process);
        freePIDs.add(process.PID);
    }

    public PCB[] GetProcesses() {
        if (!running)
            throw new IllegalStateException("Not running");

        PCB[] runningProcesses = new PCB[this.hardware.GetCPUCoreCount()];
        for (int i = 0; i < this.hardware.GetCPUCoreCount(); i++) {
            runningProcesses[i] = this.cores[i].RunningProcess.deepCopy();
        }
        return runningProcesses;
    }

    public LinkedList<PCB> GetReadyQueue() {
        if (!running)
            throw new IllegalStateException("Not running");

        LinkedList<PCB> readyQueueCopy = new LinkedList<>();
        for (PCB process : this.readyQueue) {
            readyQueueCopy.add(process.deepCopy());
        }

        return readyQueueCopy;
    }

    public LinkedList<PCB> GetBlockedProcesses() {
        if (!running)
            throw new IllegalStateException("Not running");

        LinkedList<PCB> blockedProcessesCopy = new LinkedList<>();
        for (PCB process : this.blockedProcesses) {
            blockedProcessesCopy.add(process.deepCopy());
        }

        return blockedProcessesCopy;
    }

    public int GetMemoryUsage() {
        if (!running)
            throw new IllegalStateException("Not running");

        return MemoryManager.GetMemoryUsage(this.memoryDescriptors);
    }

    public void RunProgram(Program program, int priority, int parentPID) {
        if (!running)
            throw new IllegalStateException("Not running");
        if (program == null)
            throw new IllegalArgumentException("Program cannot be null");
        if (priority < 1 || priority > 99)
            throw new IllegalArgumentException("Priority must be between 1 and 99");

        PCB parent = this.GetProcessByPID(parentPID);

        if (parent != null && processTable.size() == 0 && !processTable.contains(parent))
            throw new IllegalArgumentException("Parent process not found");
        if (parent == null && processTable.size() > 0)
            throw new IllegalArgumentException("Parent process can only be null if there are no processes");

        PCB process = this.CreateProcess(program, priority, parent);
        this.AddProcessToReadyQueue(process);
    }

    public void BlockProcess(int pid) {
        if (!running)
            throw new IllegalStateException("Not running");

        
        PCB process = this.GetProcessByPID(pid);

        if (process == null)
            throw new IllegalArgumentException("Process cannot be null");
        if (!processTable.contains(process))
            throw new IllegalArgumentException("Process not found");
        if (process.State == ProcessState.Finished)
            throw new IllegalArgumentException("Process is already finished");
        if (process.State == ProcessState.Blocked)
            throw new IllegalArgumentException("Process is already blocked");

        if (process.State == ProcessState.Ready) {
            this.readyQueue.remove(process);
        }

        process.State = ProcessState.Blocked;
        this.blockedProcesses.add(process);
    }

    public void TerminateProcess(int pid) {
        if (!running)
            throw new IllegalStateException("Not running");

        PCB process = this.GetProcessByPID(pid);
        this.TerminateProcess(process);
    }

    public void UnblockProcess(int pid) {
        if (!running)
            throw new IllegalStateException("Not running");

        PCB process = this.GetProcessByPID(pid);

        if (process == null)
            throw new IllegalArgumentException("Process cannot be null");
        if (!processTable.contains(process))
            throw new IllegalArgumentException("Process not found");
        if (!this.blockedProcesses.contains(process))
            throw new IllegalArgumentException("Process is not blocked");

        this.blockedProcesses.remove(process);
        this.AddProcessToReadyQueue(process);
    }

    private void Schedule() {
        while (running) {

        }
    }

    private PCB GetProcessByPID(int pid) {
        for (PCB process : this.processTable) {
            if (process.PID == pid) {
                return process;
            }
        }

        return null;
    }

    private void AddProcessToReadyQueue(PCB process) {
        if (process == null)
            throw new IllegalArgumentException("Process cannot be null");

        process.State = ProcessState.Ready;

        if (this.readyQueue.size() == 0) {
            this.readyQueue.add(process);
        } else {
            for (int i = 0; i < this.readyQueue.size(); i++) {
                if (process.Priority > this.readyQueue.get(i).Priority) {
                    this.readyQueue.add(i, process);
                    return;
                }
            }

            this.readyQueue.add(process);
        }
    }

    public void SetTimeSlice(double milliseconds) {
        if (running)
            throw new IllegalStateException("Running");
        if (milliseconds < 1)
            throw new IllegalArgumentException("Time slice must be greater than 0");

        this.timeSliceMS = milliseconds;
    }

    void DispatchNewProcess(int cpuCore) {
        PCB appropriatedProcess = this.cores[cpuCore].AppropriateCPU();



        if (this.readyQueue.size() == 0)
            return;

        PCB process = this.readyQueue.removeFirst();
        process.State = ProcessState.Running;
        this.cores[0].RunningProcess = process;
    }
}
