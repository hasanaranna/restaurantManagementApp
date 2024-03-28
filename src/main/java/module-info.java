module com.example.demotester {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.demotester to javafx.fxml;
    exports com.example.demotester;
    exports server;
    opens server to javafx.fxml;
    exports util;
    opens util to javafx.fxml;
}