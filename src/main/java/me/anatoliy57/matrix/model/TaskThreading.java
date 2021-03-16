package me.anatoliy57.matrix.model;

import java.util.*;

public class TaskThreading {

    private final LinkedList<Runnable> tasks;
    private final List<Thread> threads;

    private final Object lock = new Object();
    private final Object join = new Object();
    private int finishedTasks = 0;
    private int countTask = 0;

    public TaskThreading(int maxThreads) {
        tasks = new LinkedList<>();
        threads = new ArrayList<>();

        for (int i = 0; i < maxThreads; i++) {
            Thread t = new Thread(() -> {
                while(true) {
                    Runnable task;

                    synchronized (lock) {
                        if (tasks.isEmpty()) {
                            try {
                                lock.wait();

                            } catch (InterruptedException e) {
                                return;
                            }
                        }

                        task = tasks.poll();
                    }

                    assert task != null;
                    task.run();
                    finishTask();
                }
            });
            t.start();
            threads.add(t);
        }
    }

    public void addTask(Runnable task) {
        synchronized (lock) {
            tasks.add(task);
            countTask++;
            lock.notifyAll();
        }
    }

    public void join() throws InterruptedException {
        if (threads.isEmpty()) {
            return;
        }

        synchronized (join) {
            join.wait();
        }
    }

    public void stop() {
        threads.forEach(Thread::interrupt);
        threads.clear();
    }

    private void finishTask() {
        synchronized (join) {
            finishedTasks++;
            if (countTask == finishedTasks) {
                join.notifyAll();
            }
        }
    }
}
