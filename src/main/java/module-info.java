module com.example.queuemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.queuemanagement to javafx.fxml;
    exports com.example.queuemanagement;
}