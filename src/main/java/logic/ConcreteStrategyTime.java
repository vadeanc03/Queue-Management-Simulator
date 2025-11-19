package logic;

import model.Server;
import model.Task;
import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        if (servers.isEmpty()) {
            return;
        }

        Server leastBusy = servers.get(0);

        int minTime = leastBusy.getWaitingPeriod().get();


        for (Server server : servers) {
            if (server.getWaitingPeriod().get() < minTime) {
                leastBusy = server;
                minTime = server.getWaitingPeriod().get(); // Update the minimum time found
            }
        }

      
        leastBusy.addTask(task);
    }
}
