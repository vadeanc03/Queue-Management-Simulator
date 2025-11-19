package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger waitingPeriod;
    private final int serverID;

    public Server(int serverID) {
        this.serverID = serverID;
        this.tasks = new PriorityBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task task) {
        tasks.add(task);
        waitingPeriod.addAndGet(task.getServiceTime());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Task task = tasks.take();
                int time = task.getServiceTime();
                while (time > 0) {
                    Thread.sleep(1000);
                    time--;
                }
                waitingPeriod.addAndGet(-task.getServiceTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getId() {
        return serverID;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getNrOfTasks() {
        return tasks.size();
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }
}

