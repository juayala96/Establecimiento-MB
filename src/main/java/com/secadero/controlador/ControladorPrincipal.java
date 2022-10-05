package com.secadero.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ControladorPrincipal {
    public ControladorPrincipal(){}

    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnCronograma;
    @FXML
    private Button btnEmpleados;
    @FXML
    private Button btnInformes;
    @FXML
    private Button btnPreRecibo;
    @FXML
    private Button btnUsuarios;

    //------------------------------------------ Usuarios ----------------------------------------------------
    @FXML
    private void Usuarios() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/usuarios.fxml"));
        AnchorPane root = loader.load();
        ControladorUsuarios usuario = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero (Usuarios)");
        escenario.setScene(escena);
        escenario.show();

        escenario.setOnCloseRequest(e -> {
            try {
                usuario.closeWindowsPrincipalUsuario();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myEscena = (Stage) this.btnUsuarios.getScene().getWindow();
        myEscena.close();
    }

    //------------------------------------------ Empleados ----------------------------------------------------
    @FXML
    private void Empleados() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/empleados.fxml"));
        AnchorPane root = loader.load();
        ControladorEmpleados empleado = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero (Empleados)");
        escenario.setScene(escena);
        escenario.show();

        escenario.setOnCloseRequest(e -> {
            try {
                empleado.closeWindowsPrincipalEmpleado();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myEscena = (Stage) this.btnEmpleados.getScene().getWindow();
        myEscena.close();
    }

    //------------------------------------------ Cronograma ----------------------------------------------------
    @FXML
    private void Cronograma() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/cronograma.fxml"));
        AnchorPane root = loader.load();
        ControladorCronograma cronograma = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero (Cronograma)");
        escenario.setScene(escena);
        escenario.show();

        escenario.setOnCloseRequest(e -> {
            try {
                cronograma.closeWindowsPrincipalCronograma();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myEscena = (Stage) this.btnCronograma.getScene().getWindow();
        myEscena.close();
    }

    //------------------------------------------ Informes ----------------------------------------------------
    @FXML
    private void Informes() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/informe.fxml"));
        AnchorPane root = loader.load();
        ControladorInformes informe = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero (Informe)");
        escenario.setScene(escena);
        escenario.show();

        escenario.setOnCloseRequest(e -> {
            try {
                informe.closeWindowsPrincipalInforme();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myEscena = (Stage) this.btnInformes.getScene().getWindow();
        myEscena.close();
    }

    //------------------------------------------ Pre-Recibo ----------------------------------------------------
    @FXML
    private void PreRecibo() {

    }

    //------------------------------------------ Cerrar Sesión ----------------------------------------------------
    @FXML
    private void CerrarSesion() throws IOException {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar ¿Cerrar Sesión?");
        alerta.setContentText("¿Desea Cerrar Sesión?");
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK){
            closeWindowsLoginPrincipal();
        }
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsLoginPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/login.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero");
        escenario.setScene(escena);
        escenario.show();

        Stage myEscena = (Stage) this.btnCerrarSesion.getScene().getWindow();
        myEscena.close();
    }
}
