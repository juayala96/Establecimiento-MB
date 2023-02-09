package com.secadero.controlador.controladorAsistencia;

import com.secadero.controlador.ControladorCronograma;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorMenu {

    public ControladorMenu(){}

    @FXML
    private Button btnEntrada;
    @FXML
    private Button btnSalida;

    // --------------------------------------------- ENTRADA ---------------------------------------------------
    @FXML
    void getEntrada() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/entrada.fxml"));
        AnchorPane root = loader.load();
        ControladorEntrada entrada = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Registro de Entrada");
        escenario.setScene(escena);
        escenario.show();

        escenario.setOnCloseRequest(e -> {
            try {
                entrada.closeWindowsPrincipal();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myEscena = (Stage) this.btnEntrada.getScene().getWindow();
        myEscena.close();
    }

    // --------------------------------------------- SALIDA ---------------------------------------------------
    @FXML
    void getSalida() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/salida.fxml"));
        AnchorPane root = loader.load();
        ControladorSalida salida = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Registro de Entrada");
        escenario.setScene(escena);
        escenario.show();

        escenario.setOnCloseRequest(e -> {
            try {
                salida.closeWindowsPrincipal();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myEscena = (Stage) this.btnSalida.getScene().getWindow();
        myEscena.close();
    }
}
