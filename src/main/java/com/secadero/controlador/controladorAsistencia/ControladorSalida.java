package com.secadero.controlador.controladorAsistencia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorSalida {
    public ControladorSalida(){}

    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnSalir;
    @FXML
    private Label labErrorCodigo;
    @FXML
    private Label labInformacion;
    @FXML
    private Tab tabRegistroSalidaCodigo;
    @FXML
    private TextField textCodigo;

    @FXML
    void aceptar() {
        if(!textCodigo.getText().equals("")){
            if(validarNumeros(textCodigo.getText())){
                labErrorCodigo.setText("");

            } else {
                labErrorCodigo.setText("Solo se permite Números");
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de completar el campo");
            alerta.showAndWait();
        }
    }

    @FXML
    void salir() throws IOException {
        closeWindowsPrincipal();
    }

    //---------------------------------------- Validación de Caracteres ------------------------------------------
    public static boolean validarNumeros(String datos){
        return datos.matches("[0-9]*");
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/registro_Asistencia.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero");
        escenario.setScene(escena);
        escenario.show();

        Stage myEscena = (Stage) this.btnSalir.getScene().getWindow();
        myEscena.close();
    }
}