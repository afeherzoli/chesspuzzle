module chesspuzzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.tinylog.api;
    requires lombok;
    requires jakarta.xml.bind;

    exports chesspuzzle;
    exports chesspuzzle.model;
    exports chesspuzzle.results;
    opens chesspuzzle.results;
    opens chesspuzzle;
    opens chesspuzzle.model;
    exports chesspuzzle.controller;
    opens chesspuzzle.controller;
}