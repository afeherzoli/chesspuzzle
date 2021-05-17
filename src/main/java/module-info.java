module chesspuzzle {
    requires javafx.controls;
    requires javafx.fxml;

    opens chesspuzzle to javafx.fxml;
    exports chesspuzzle;
}