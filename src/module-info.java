module bouquinsFX {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires jade;

    opens sma.containers;
    opens sma.agents;
    opens sma.behaviours;
}