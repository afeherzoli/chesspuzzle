module chesspuzzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.tinylog.api;
    requires lombok;

    exports chesspuzzle;
    exports chesspuzzle.model;
    opens chesspuzzle;
    opens chesspuzzle.model;
}