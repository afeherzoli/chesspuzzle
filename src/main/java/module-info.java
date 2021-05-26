module chesspuzzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.tinylog.api;
    requires lombok;

    opens chesspuzzle;
    exports chesspuzzle;
}