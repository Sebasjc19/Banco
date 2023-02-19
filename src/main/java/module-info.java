module com.ingesis.edu.bancobonito {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ingesis.edu.Unibanco to javafx.fxml;
    opens com.ingesis.edu.Unibanco.model to javafx.base;
    exports com.ingesis.edu.Unibanco;
    exports com.ingesis.edu.Unibanco.controllers;
    opens com.ingesis.edu.Unibanco.controllers to javafx.fxml;
}