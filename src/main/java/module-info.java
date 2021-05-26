module chesspuzzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.tinylog.api;
    requires lombok;

    opens chesspuzzle;
    opens chesspuzzle.model;
    exports chesspuzzle;
}