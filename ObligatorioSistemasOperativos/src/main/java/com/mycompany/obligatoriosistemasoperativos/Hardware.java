package com.mycompany.obligatoriosistemasoperativos;

public class Hardware {

    static int DEFAULT_CPU_CORE_COUNT = 4;
    static int DEFAULT_CPU_CORE_FREQUENCY = 1000 * 1000 * 16 * 1000;
    static int DEFAULT_RAM_SIZE = 1024 * 1024 * 1024;

    private static Hardware instance;
    private int cpuCoreCount;
    private int cpuCoreFrequency;
    private int ramSize;

    private Hardware() {
        cpuCoreCount = DEFAULT_CPU_CORE_COUNT;
        cpuCoreFrequency = DEFAULT_CPU_CORE_FREQUENCY;
        ramSize = DEFAULT_RAM_SIZE;
    }

    public static Hardware getInstance() {
        if (instance == null) {
            instance = new Hardware();
        }
        return instance;
    }

    public int GetCPUCoreCount() {
        return cpuCoreCount;
    }

    public int GetCPUCoreFrequency() {
        return cpuCoreFrequency;
    }

    public int GetRAMSize() {
        return ramSize;
    }
    
    public void setValues(String pCoreCuantity, String pCPU_time, String pRamQuantity)
    {
        this.cpuCoreCount = Integer.valueOf(pCoreCuantity);
        this.cpuCoreFrequency =Integer.valueOf(pCPU_time);
        this.ramSize = Integer.valueOf(pRamQuantity);
    }
}
