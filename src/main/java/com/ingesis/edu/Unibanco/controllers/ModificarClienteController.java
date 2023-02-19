package com.ingesis.edu.Unibanco.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.ingesis.edu.Unibanco.controllers.AppController.INSTANCE;
import static com.ingesis.edu.Unibanco.utils.VentanaUtil.mostrarInformacion;
import static com.ingesis.edu.Unibanco.utils.VentanaUtil.mostrarVentana;

public class ModificarClienteController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnModificar;

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfDireccion;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNombre;

    @FXML
    void btnCancelarClick(ActionEvent event) throws IOException {
        mostrarVentana("banco-view.fxml","Unibanco", 637, 330, btnCancelar);
    }

    @FXML
    void btnModificarClick(ActionEvent event) throws IOException {
        INSTANCE.getBanco().modificarCliente(tfNombre.getText(),tfApellido.getText(),tfDireccion.getText(),tfEmail.getText());
        mostrarInformacion("Cliente modificado con exito");
        mostrarVentana("banco-view.fxml", "Unibanco", 637, 330, btnModificar);
    }

}
