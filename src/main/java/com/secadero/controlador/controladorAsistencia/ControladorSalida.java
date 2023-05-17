package com.secadero.controlador.controladorAsistencia;

import com.secadero.modelo.asistencia.Salida;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class ControladorSalida {
    public ControladorSalida(){}

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
    private Label labErrorDNI;
    @FXML
    private Label labInformacion;
    @FXML
    private Tab tabRegistroSalidaCodigo;
    @FXML
    private Tab tabRegistroSalidaTarjeta;
    @FXML
    private TabPane panelRegistroSalida;
    @FXML
    private TextField textCodigo;
    @FXML
    private TextField textDNI;

    @FXML
    void aceptar() throws IOException, ParseException {
        camposObligatorios();
        if(Objects.equals(labErrorCodigo.getText(), "") && Objects.equals(labErrorDNI.getText(), "")) {
            validarCampos();
            if (Objects.equals(labErrorCodigo.getText(), "") && Objects.equals(labErrorDNI.getText(), "")) {
                validacionCamposLongitud();
                if (Objects.equals(labErrorCodigo.getText(), "") && Objects.equals(labErrorDNI.getText(), "")) {
                    labErrorCodigo.setText("");
                    Salida codigoSalida = new Salida();
                    codigoSalida.salidaEmpleado(labInformacion, textCodigo, textDNI);

                    if (Objects.equals(labInformacion.getText(), "OK")) {
                        textCodigo.setText("");
                        textDNI.setText("");
                        labInformacion.setText("");
                        closeWindowsPrincipal();
                    }
                }
            }
        }
    }

    @FXML
    private void opcionCodigo(){
        SingleSelectionModel<Tab> modeloSeleccion = panelRegistroSalida.getSelectionModel();
        modeloSeleccion.select(tabRegistroSalidaCodigo);
    }

    @FXML
    private void opcionTarjeta(){
        SingleSelectionModel<Tab> modeloSeleccion = panelRegistroSalida.getSelectionModel();
        modeloSeleccion.select(tabRegistroSalidaTarjeta);
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

    //---------------------------------------- Comprobación de Campos -------------------------------------------
    private void camposObligatorios(){
        if(textCodigo.getText().trim().isEmpty() || textCodigo.getText() == null){
            labErrorCodigo.setText("Campo Obligatorio");
        } else {
            labErrorCodigo.setText("");
        }
        if(textDNI.getText().trim().isEmpty() || textDNI.getText() == null){
            labErrorDNI.setText("Campo Obligatorio");
        } else {
            labErrorDNI.setText("");
        }
    }

    private void validarCampos(){
        if(validarNumeros(textCodigo.getText())){
            labErrorCodigo.setText("");
        } else {
            labErrorCodigo.setText("Solo se admiten Números");
        }
        if(validarNumeros(textDNI.getText())){
            labErrorDNI.setText("");
        } else {
            labErrorDNI.setText("Solo se admiten Números");
        }
    }

    private void validacionCamposLongitud() {
        String codigo = textCodigo.getText().trim();
        String dni = textDNI.getText().trim();
        codigo = codigo.replaceAll("\\s+", "");
        dni = dni.replaceAll("\\s+", "");

        if (codigo.length() > 6) {
            labErrorCodigo.setText("Está permitido hasta 6 números");
        } else {
            labErrorCodigo.setText("");
        }
        if (dni.length() >= 9) {
            labErrorDNI.setText("Está permitido hasta 8 números");
        } else {
            labErrorDNI.setText("");
        }
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