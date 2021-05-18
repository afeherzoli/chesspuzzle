module chesspuzzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.tinylog.api;
    requires javax.inject;

    opens chesspuzzle to javafx.fxml;
    exports chesspuzzle;
}