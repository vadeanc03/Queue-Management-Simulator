package com.example.queuemanagement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import model.Server;

import java.util.List;

public class SimulationView extends Application {
    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simulation View");
        textArea = new TextArea();
        Scene scene = new Scene(textArea, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void update(int currentTime, List<Server> servers) {
        StringBuilder sb = new StringBuilder("Time: " + currentTime + "\n");
        for (Server server : servers) {
            sb.append("Server ").append(server.getId()).append(": ");
            sb.append("Tasks in queue: ").append(server.getNrOfTasks()).append("\n");
        }
        textArea.setText(sb.toString());
    }

    public void finalizeSimulation() {
        textArea.appendText("Simulation complete.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
