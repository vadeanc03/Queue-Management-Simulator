package logic;

import model.Server;
import model.Task;
import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        Server leastBusy = servers.get(0);
        for (Server server : servers) {
            if (server.getNrOfTasks() < leastBusy.getNrOfTasks()) {
                leastBusy = server;
            }
        }
        leastBusy.addTask(task);
    }
}
