package logic;

import logic.Scheduler;
import model.Server;
import model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.example.queuemanagement.SimulationView;

public class SimulationManager {
    private Scheduler scheduler;
    private List<Task> tasks;
    private int timeLimit;
    private volatile boolean isRunning;

    public SimulationManager(int numberOfServers, int maxTasksPerServer, int numberOfTasks, int timeLimit, SimulationView view) {
        Strategy initialStrategy = new ConcreteStrategyQueue(); // Default strategy
        this.scheduler = new Scheduler(numberOfServers, initialStrategy);
        this.tasks = generateTasks(numberOfTasks);
        this.timeLimit = timeLimit;
        this.isRunning = false;
    }

    private List<Task> generateTasks(int numberOfTasks) {
        List<Task> generatedTasks = new ArrayList<>();
        Random random = new Random();
        int maxArrivalTime = timeLimit - 1;
        int maxServiceTime = 10;
        int minServiceTime = 1;

        for (int i = 0; i < numberOfTasks; i++) {
            int arrivalTime = random.nextInt(maxArrivalTime);
            int serviceTime = random.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;
            generatedTasks.add(new Task(i, arrivalTime, serviceTime));
        }
        return generatedTasks;
    }

    public void startSimulation() {
        if (!isRunning) {
            isRunning = true;
            new Thread(this::runSimulation).start();
        }
    }

    public void stopSimulation() {
        isRunning = false;
    }

    private void runSimulation() {
        int currentTime = 0;
        while (isRunning && currentTime < timeLimit) {
            dispatchTasks(currentTime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            currentTime++;
        }
        isRunning = false;
    }

    private void dispatchTasks(int currentTime) {
        List<Task> tasksToDispatch = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getArrivalTime() == currentTime) {
                tasksToDispatch.add(task);
            }
        }
        tasks.removeAll(tasksToDispatch);
        for (Task task : tasksToDispatch) {
            scheduler.dispatchTask(task);
        }
    }

    public List<Server> getServers() {
        return scheduler.getServers();
    }
}
