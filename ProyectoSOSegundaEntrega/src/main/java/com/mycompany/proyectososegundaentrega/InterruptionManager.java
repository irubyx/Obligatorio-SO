package com.mycompany.proyectososegundaentrega;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class InterruptionManager {
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
    //private static final Scheduler scheduler = Scheduler.GetInstance();

    static ScheduledFuture<?> SetInterrupt(Runnable interruptionRoutine, long delayMS) {
        return executor.schedule(interruptionRoutine, delayMS, TimeUnit.MILLISECONDS);
    }

    static void CancelInterrupt(ScheduledFuture<?> interrupt) {
        interrupt.cancel(false);
    }
}
