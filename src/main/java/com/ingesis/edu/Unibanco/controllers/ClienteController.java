package com.ingesis.edu.Unibanco.controllers;

import com.ingesis.edu.Unibanco.model.Cliente;
import com.ingesis.edu.Unibanco.model.Cuenta;
import com.ingesis.edu.Unibanco.model.TipoCuenta;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.ingesis.edu.Unibanco.controllers.AppController.INSTANCE;
import static com.ingesis.edu.Unibanco.utils.VentanaUtil.mostrarInformacion;
import static com.ingesis.edu.Unibanco.utils.VentanaUtil.mostrarVentana;

public class ClienteController {

    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<TipoCuenta> cbTipoCuenta;
    @FXML
    private TextField tfApellido;
    @FXML
    private TextField tfCedula;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNumeroCuenta;

    @FXML
    public void initialize(){
        cbTipoCuenta.setItems(FXCollections.observableArrayList((TipoCuenta.values())));
    }

    @FXML
    void btnCancelarClick(ActionEvent event) throws IOException {
        mostrarVentana("banco-view.fxml","Unibanco", 637, 330, btnCancelar);
    }

    @FXML
    void btnRegistrarClick(ActionEvent event) {
        try {
            Cuenta cuenta = Cuenta.of(tfNumeroCuenta.getText(), cbTipoCuenta.getValue());
            cuenta.setCliente(new Cliente(tfNombre.getText(), tfApellido.getText(), tfCedula.getText(), tfDireccion.getText(), tfEmail.getText()));
            INSTANCE.getBanco().addCuenta(cuenta);
            mostrarInformacion("Cliente añadido con exito");
            mostrarVentana("banco-view.fxml", "Unibanco", 637, 330, btnRegistrar);
        }catch (Exception e){
            mostrarInformacion(e.getMessage());
        }
    }

}

