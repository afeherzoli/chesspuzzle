module chesspuzzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.tinylog.api;

    opens chesspuzzle to javafx.fxml;
    exports chesspuzzle;
}