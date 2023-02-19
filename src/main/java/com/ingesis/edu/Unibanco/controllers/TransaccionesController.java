package com.ingesis.edu.Unibanco.controllers;

import com.ingesis.edu.Unibanco.model.Estado;
import com.ingesis.edu.Unibanco.model.TipoTransaccion;
import com.ingesis.edu.Unibanco.model.Transaccion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static com.ingesis.edu.Unibanco.controllers.AppController.INSTANCE;
import static com.ingesis.edu.Unibanco.utils.VentanaUtil.mostrarVentana;

public class TransaccionesController {

    @FXML
    private TableView<Transaccion> tblTransacciones;
    @FXML
    private Button btnRegresar;
    @FXML
    private TableColumn<Transaccion, Estado> colEstado;
    @FXML
    private TableColumn<Transaccion, String> colFecha;
    @FXML
    private TableColumn<Transaccion, String> colHora;
    @FXML
    private TableColumn<Transaccion, String> colNumeroCuenta;
    @FXML
    private TableColumn<Transaccion, TipoTransaccion> colTipo;
    @FXML
    private TableColumn<Transaccion, Double> colValor;

    @FXML
    public void initialize() {
        llenarTabla(INSTANCE.getBanco().buscarTransacciones(null, null, null,null,null,null));
        colNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoTransaccion"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));


    }

    private void llenarTabla(List<Transaccion> transacciones) {
        tblTransacciones.setItems(FXCollections.observableArrayList(transacciones));
        tblTransacciones.refresh();
    }

    @FXML
    void onRegresarClick(ActionEvent event) throws IOException {
        mostrarVentana("banco-view.fxml","Unibanco", 637, 330, btnRegresar);
    }

}
