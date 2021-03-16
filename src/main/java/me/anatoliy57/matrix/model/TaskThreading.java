package me.anatoliy57.matrix.model;

import java.util.*;

/**
 * Class that distributes the transferred tasks to several threads for parallel calculation
 *
 * @author Udartsev Anatoliy
 */
public class TaskThreading {

    /** Tasks provided */
    private final LinkedList<Runnable> tasks;
    /** Created threads to execute tasks */
    private final List<Thread> threads;

    /** Object for synchronous distribution of tasks */
    private final Object lock = new Object();
    /** Object for wait for all passed tasks to complete */
    private final Object join = new Object();
    /** Completed tasks */
    private int completedTasks = 0;
    /** Total number of all tasks */
    private int countTask = 0;

    /**
     * The constructor immediately creates threads that, in the absence of a task, go into waiting
     *
     * @param maxThreads maximum number of threads
     */
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
            completedTasks++;
            if (countTask == completedTasks) {
                join.notifyAll();
            }
        }
    }
}
