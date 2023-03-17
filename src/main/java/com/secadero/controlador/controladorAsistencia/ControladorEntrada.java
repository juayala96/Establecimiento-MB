package com.secadero.controlador.controladorAsistencia;

import com.secadero.modelo.asistencia.Entrada;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class ControladorEntrada {
    public ControladorEntrada(){}

    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnSalir2;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnOpcionCodigo;
    @FXML
    private Button btnOpcionTarjeta;
    @FXML
    private Label labErrorCodigo;
    @FXML
    private Label labInformacion;
    @FXML
    private Tab tabRegistroEntradaCodigo;
    @FXML
    private Tab tabRegistroEntradaTarjeta;
    @FXML
    private TabPane panelRegistroEntrada;
    @FXML
    private TextField textCodigo;

    @FXML
    void aceptar() throws IOException, ParseException {
        if(!textCodigo.getText().equals("")){
            if(validarNumeros(textCodigo.getText())){
                labErrorCodigo.setText("");
                Entrada codigoEntrada = new Entrada();
                codigoEntrada.entradaEmpleado(labInformacion, textCodigo);

                if(Objects.equals(labInformacion.getText(), "OK")){
                    textCodigo.setText("");
                    labInformacion.setText("");
                    closeWindowsPrincipal();
                }

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
    private void opcionCodigo(){
        SingleSelectionModel<Tab> modeloSeleccion = panelRegistroEntrada.getSelectionModel();
        modeloSeleccion.select(tabRegistroEntradaCodigo);
    }

    @FXML
    private void opcionTarjeta(){
        SingleSelectionModel<Tab> modeloSeleccion = panelRegistroEntrada.getSelectionModel();
        modeloSeleccion.select(tabRegistroEntradaTarjeta);
    }

    @FXML
    private void salir2() throws IOException {
        closeWindowsPrincipal();
    }

    @FXML
    private void salir() throws IOException {
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
        escenario.setTitle("Registro de Asistencias (Empleados)");
        escenario.setScene(escena);
        escenario.show();

        Stage myEscena = (Stage) this.btnSalir.getScene().getWindow();
        myEscena.close();
    }
}
