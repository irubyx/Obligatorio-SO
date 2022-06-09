package com.mycompany.obligatoriosistemasoperativos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {
    static final int MAX_PID = 1000;
    static final double DEFAULT_QUANTUM_MS = 100;
    static final double NRU_MS_INTERVAL = 100;

    private final LinkedList<Process> readyQueue;
    private final LinkedList<Process> blockedProcesses;
    private final Integer cores;
    private Hardware hardware;
    private final VirtualMemory virtualMemory;
    private final LinkedList<MemoryDescriptor> memoryDescriptors;
    private final LinkedList<Process> processTable;
    private final Queue<Integer> freePIDs;
    private final ExecutorService executorService;
    private long cycles;

    private boolean running;

    public Scheduler(Hardware hardware) {
        executorService = Executors.newSingleThreadExecutor();
        this.hardware = hardware;
        this.virtualMemory = new VirtualMemory(hardware.GetRAMSize());
        this.memoryDescriptors = new LinkedList<>();
        this.processTable = new LinkedList<>();
        this.freePIDs = new LinkedList<>();
        this.readyQueue = new LinkedList<>();
        this.blockedProcesses = new LinkedList<>();
        this.cores = hardware.GetCPUCoreCount();
        this.cycles = 0;
        this.running = false;
        for (int i = 0; i < MAX_PID; i++) {
            freePIDs.add(i);
        }
    }

    public void Start() {
        if (running) {
            throw new IllegalStateException("Already running");
        }

        this.cycles = 0;
        running = true;

        Program initialProgram = new Program("init", 0, 0, 0, 1024);
        Process init = this.CreateProcess(initialProgram, 1, null);


        
    }

    public void Stop() {
        if (!running) {
            throw new IllegalStateException("Not running");
        }

        running = false;
        executorService.shutdownNow();
    }

    public Process CreateProcess(Program program, int priority, Process parent) {
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
        Process process = new Process(pid, parent, program);
        process.Priority = priority;
        process.State = ProcessState.New;
        MemoryManager.NewProcessArea(process, program.RequiredMemoryB);
        processTable.add(process);
        return process;
    }

    public void TerminateProcess(Process process) {
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

    private void Schedule() {
        while (running) {
        }
    }
}
