package com.ingesis.edu.Unibanco.controllers;


import com.ingesis.edu.Unibanco.exceptions.ValorRequeridoException;
import com.ingesis.edu.Unibanco.model.Cliente;
import com.ingesis.edu.Unibanco.model.Cuenta;
import com.ingesis.edu.Unibanco.model.TipoCuenta;
import com.ingesis.edu.Unibanco.model.TipoTransaccion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.ingesis.edu.Unibanco.controllers.AppController.INSTANCE;
import static com.ingesis.edu.Unibanco.utils.VentanaUtil.mostrarVentana;

public class BancoController {

    @FXML
    private TextField tfCantidad;
    @FXML
    private Button btnCrearCliente;
    @FXML
    private Button btnModificarCliente;
    @FXML
    private Button btnEliminarCliente;
    @FXML
    private Button btnInformacionCliente;
    @FXML
    private Button btnHistorialTransacciones;
    @FXML
    private Button btnDepositar;
    @FXML
    private Button btnRetirar;
    @FXML
    private Button btnSolicitar;
    @FXML
    private TableView<Cuenta> tblBanco;
    @FXML
    private TableColumn<Cuenta, String> colNumeroCuenta = new TableColumn<>("Numero de cuenta");
    @FXML
    private TableColumn<Cuenta, Double> colSaldo = new TableColumn<>("Saldo");
    @FXML
    private TableColumn<Cuenta, TipoCuenta> colTipoCuenta = new TableColumn<>("Tipo de cuenta");



    @FXML
    public void initialize() {
        llenarTabla(INSTANCE.getBanco().buscar(null, null, null));
        colNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        colTipoCuenta.setCellValueFactory(new PropertyValueFactory<>("tipoCuenta"));

        btnSolicitar.setDisable(true);
        btnDepositar.setDisable(true);
        btnRetirar.setDisable(true);
        btnInformacionCliente.setDisable(true);
        btnEliminarCliente.setDisable(true);
        btnModificarCliente.setDisable(true);
        tblBanco.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            btnSolicitar.setDisable(false);
            btnDepositar.setDisable(false);
            btnRetirar.setDisable(false);
            btnInformacionCliente.setDisable(false);
            btnEliminarCliente.setDisable(false);
            btnModificarCliente.setDisable(false);
            });
    }

    private void llenarTabla(List<Cuenta> cuentas) {
        tblBanco.setItems(FXCollections.observableArrayList(cuentas));
        tblBanco.refresh();
    }

    @FXML
    void OnEliminarClienteClick(ActionEvent event) {
        TableView.TableViewSelectionModel<Cuenta> selectionModel = tblBanco.getSelectionModel();
        Cuenta cuenta = selectionModel.getSelectedItem();
        INSTANCE.getBanco().removeCuenta(cuenta);
        llenarTabla(INSTANCE.getBanco().buscar(null, null, null));

    }

    @FXML
    void OnInformacionClienteClick(ActionEvent event) {
        TableView.TableViewSelectionModel<Cuenta> selectionModel = tblBanco.getSelectionModel();
        Cuenta cuenta = selectionModel.getSelectedItem();
        Cliente cliente = cuenta.getCliente();
        mostrarInformacion("Numero cuenta: "+cuenta.getNumeroCuenta()+"\nTipo de cuenta: "+cuenta.getTipoCuenta()+
                "\nCantidad prestada: "+cuenta.getCantidadPrestamo()+"\nNumero de identidad: "+cliente.getCedula()+"\nNombre: "+cliente.getNombre()+ " "+cliente.getApellidos()+
                "\nDirección: "+cliente.getDireccion()+"\nemail: "+cliente.getEmail());
    }

    @FXML
    void onCrearClienteClick(ActionEvent event) throws IOException {
        mostrarVentana("cliente-view.fxml", "Creación cliente", 400, 400, btnCrearCliente);
    }

    @FXML
    void onModificarClienteClick(ActionEvent event) throws IOException {
        TableView.TableViewSelectionModel<Cuenta> selectionModel = tblBanco.getSelectionModel();
        Cuenta cuenta = selectionModel.getSelectedItem();
        INSTANCE.getBanco().setCuentaSeleccionada(cuenta);
        mostrarVentana("modificarCliente-view.fxml", "Modificación cliente", 350, 253, btnCrearCliente);
    }
    @FXML
    public void OnHistorialTransaccionesClick(ActionEvent event) throws IOException {
        mostrarVentana("transacciones-view.fxml", "Historial de transacciones", 521, 400, btnCrearCliente);
    }

    public void OnDepositarClick(ActionEvent event) throws ValorRequeridoException{
        TableView.TableViewSelectionModel<Cuenta> selectionModel = tblBanco.getSelectionModel();
        Cuenta cuenta = selectionModel.getSelectedItem();
        Cliente cliente = cuenta.getCliente();
        if(Objects.requireNonNull(tfCantidad.getText(), "Se requiere una cantidad").isEmpty()){
            throw new ValorRequeridoException("cantidad");
        }
        mostrarInformacion("Transacción completada");
        INSTANCE.getBanco().addTransaccion(cuenta,Double.parseDouble(tfCantidad.getText()), TipoTransaccion.DEPOSITAR);
        tfCantidad.setText("");
        llenarTabla(INSTANCE.getBanco().buscar(null, null, null));
    }

    public void OnSolicitarClick(ActionEvent event) throws ValorRequeridoException {
        TableView.TableViewSelectionModel<Cuenta> selectionModel = tblBanco.getSelectionModel();
        Cuenta cuenta = selectionModel.getSelectedItem();
        Cliente cliente = cuenta.getCliente();
        if(Objects.requireNonNull(tfCantidad.getText(), "Se requiere una cantidad").isEmpty()){
            throw new ValorRequeridoException("cantidad");
        }
        mostrarInformacion("Transacción completada");
        INSTANCE.getBanco().addTransaccion(cuenta,Double.parseDouble(tfCantidad.getText()), TipoTransaccion.SOLICITAR);
        tfCantidad.setText("");
        llenarTabla(INSTANCE.getBanco().buscar(null, null, null));
    }

    public void OnRetirarClick(ActionEvent event) throws ValorRequeridoException {
        TableView.TableViewSelectionModel<Cuenta> selectionModel = tblBanco.getSelectionModel();
        Cuenta cuenta = selectionModel.getSelectedItem();
        Cliente cliente = cuenta.getCliente();
        if(Objects.requireNonNull(tfCantidad.getText(), "Se requiere una cantidad").isEmpty()){
            throw new ValorRequeridoException("cantidad");
        }
        mostrarInformacion("Transacción completada");
        INSTANCE.getBanco().addTransaccion(cuenta,Double.parseDouble(tfCantidad.getText()), TipoTransaccion.RETIRAR);
        tfCantidad.setText("");
        llenarTabla(INSTANCE.getBanco().buscar(null, null, null));
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
