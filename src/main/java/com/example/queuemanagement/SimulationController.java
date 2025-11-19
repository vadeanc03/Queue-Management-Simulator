package com.example.queuemanagement;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import logic.SimulationManager;
import logic.SelectionPolicy;

public class SimulationController extends SimulationView {

    @FXML private TextField textNumberOfClients;
    @FXML private TextField textNumberOfQueues;
    @FXML private TextField textSimulationInterval;
    @FXML private TextField textMinArrivalTime;
    @FXML private TextField textMaxArrivalTime;
    @FXML private TextField textMinServiceTime;
    @FXML private TextField textMaxServiceTime;
    @FXML private RadioButton buttonTime;
    @FXML private RadioButton buttonQueue;
    @FXML private HBox buttonBox;

    private ToggleGroup policyGroup;

    public void initialize() {
        policyGroup = new ToggleGroup();
        buttonTime.setToggleGroup(policyGroup);
        buttonQueue.setToggleGroup(policyGroup);
        buttonTime.setSelected(true);
    }

    @FXML
    private void onSimulate() {
        int numberOfClients = Integer.parseInt(textNumberOfClients.getText());
        int numberOfQueues = Integer.parseInt(textNumberOfQueues.getText());
        int simulationInterval = Integer.parseInt(textSimulationInterval.getText());
        int minArrivalTime = Integer.parseInt(textMinArrivalTime.getText());
        int maxArrivalTime = Integer.parseInt(textMaxArrivalTime.getText());
        int minServiceTime = Integer.parseInt(textMinServiceTime.getText());
        int maxServiceTime = Integer.parseInt(textMaxServiceTime.getText());
        SelectionPolicy policy = buttonTime.isSelected() ? SelectionPolicy.SHORTEST_TIME : SelectionPolicy.SHORTEST_QUEUE;

        SimulationManager simulationManager = new SimulationManager(
                numberOfQueues, maxServiceTime, numberOfClients, simulationInterval, this);
        simulationManager.startSimulation();
    }
}
