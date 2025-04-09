package com.green.attaparunever2.common.delayed;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DelayedTaskScheduler {
    private final DelayQueue<DelayedTask> queue = new DelayQueue<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    private final AtomicInteger taskCount = new AtomicInteger(0);

    public DelayedTaskScheduler() {
        startWorkerThread();
    }

    private void startWorkerThread() {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    DelayedTask task = queue.take();
                    executor.execute(task::execute);
                    taskCount.decrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        worker.setDaemon(true);
        worker.start();
    }

    public void schedule(Runnable task, long delay, TimeUnit unit) {
        long delayMillis = unit.toMillis(delay);
        queue.put(new DelayedTask(task, delayMillis));
        taskCount.incrementAndGet();
    }

    public int getPendingTaskCount() {
        return taskCount.get();
    }

    public void shutdown() {
        executor.shutdown();
    }
}