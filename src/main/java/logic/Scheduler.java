package logic;

import model.Server;
import logic.Strategy;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private Strategy strategy;

    public Scheduler(int maxNoServers, Strategy strategy) {
        this.servers = new ArrayList<>();
        for (int i = 0; i < maxNoServers; i++) {
            servers.add(new Server(i));
        }
        this.strategy = strategy;
    }

    public void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void dispatchTask(Task task) {
        strategy.addTask(servers, task);
    }

    public List<Server> getServers() {
        return servers;
    }
}

