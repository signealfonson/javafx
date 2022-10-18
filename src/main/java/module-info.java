module se.iths.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.iths.javafx to javafx.fxml;
    exports se.iths.javafx;
}