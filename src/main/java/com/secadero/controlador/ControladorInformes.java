package com.secadero.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ControladorInformes {
    public ControladorInformes(){}

    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnGenerarFolrmulario;
    @FXML
    private Button btnVolver;
    @FXML
    private DatePicker dpFechaDesde;
    @FXML
    private DatePicker dpFechaHasta;
    @FXML
    private TreeView<String> tabViewEmpleados;

    // --------------------------------------------- Búsqueda Fundamental -----------------------------------------
    @FXML
    private void busqueda() {

    }

    //---------------------------------------------- Evento Importante ----------------------------------------
    @FXML
    private void generar() {

    }

    //------------------------------------ Acción Simple del Botón --------------------------------------

    @FXML
    private void volverPrincipal() throws IOException {
        closeWindowsPrincipalInforme();
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipalInforme() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/principal.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero");
        escenario.setScene(escena);
        escenario.show();

        Stage myEscena = (Stage) this.btnVolver.getScene().getWindow();
        myEscena.close();
    }
}
