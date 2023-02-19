package com.ingesis.edu.Unibanco.utils;

import com.ingesis.edu.Unibanco.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaUtil {

    public static void mostrarVentana(String fxml, String titulo, int ancho, int largo, Button button) throws IOException {
        Parent parent = FXMLLoader.load(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(parent, ancho, largo);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.initOwner(button.getScene().getWindow());
        button.getScene().getWindow().hide();
        stage.show();
    }

    public static void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
