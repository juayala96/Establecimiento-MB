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
    private Button btnOpcionLegajo;
    @FXML
    private Button btnOpcionTarjeta;
    @FXML
    private Label labErrorLegajo;
    @FXML
    private Label labErrorDNI;
    @FXML
    private Label labInformacion;
    @FXML
    private Tab tabRegistroSalidaLegajo;
    @FXML
    private Tab tabRegistroSalidaTarjeta;
    @FXML
    private TabPane panelRegistroSalida;
    @FXML
    private TextField textLegajo;
    @FXML
    private TextField textDNI;

    @FXML
    void aceptar() throws IOException, ParseException {
        camposObligatorios();
        if(Objects.equals(labErrorLegajo.getText(), "") && Objects.equals(labErrorDNI.getText(), "")) {
            validarCampos();
            if (Objects.equals(labErrorLegajo.getText(), "") && Objects.equals(labErrorDNI.getText(), "")) {
                validacionCamposLongitud();
                if (Objects.equals(labErrorLegajo.getText(), "") && Objects.equals(labErrorDNI.getText(), "")) {
                    labErrorLegajo.setText("");
                    Salida codigoSalida = new Salida();
                    codigoSalida.salidaEmpleado(labInformacion, textLegajo, textDNI);

                    if (Objects.equals(labInformacion.getText(), "OK")) {
                        textLegajo.setText("");
                        textDNI.setText("");
                        labInformacion.setText("");
                        closeWindowsPrincipal();
                    } else if(Objects.equals(labInformacion.getText(), "Existe")){
                        textLegajo.setText("");
                        textDNI.setText("");
                        labInformacion.setText("");
                    }
                }
            }
        }
    }

    @FXML
    private void opcionLegajo(){
        SingleSelectionModel<Tab> modeloSeleccion = panelRegistroSalida.getSelectionModel();
        modeloSeleccion.select(tabRegistroSalidaLegajo);
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
        if(textLegajo.getText().trim().isEmpty() || textLegajo.getText() == null){
            labErrorLegajo.setText("Campo Obligatorio");
        } else {
            labErrorLegajo.setText("");
        }
        if(textDNI.getText().trim().isEmpty() || textDNI.getText() == null){
            labErrorDNI.setText("Campo Obligatorio");
        } else {
            labErrorDNI.setText("");
        }
    }

    private void validarCampos(){
        if(validarNumeros(textLegajo.getText())){
            labErrorLegajo.setText("");
        } else {
            labErrorLegajo.setText("Solo se admiten Números");
        }
        if(validarNumeros(textDNI.getText())){
            labErrorDNI.setText("");
        } else {
            labErrorDNI.setText("Solo se admiten Números");
        }
    }

    private void validacionCamposLongitud() {
        String codigo = textLegajo.getText().trim();
        String dni = textDNI.getText().trim();
        codigo = codigo.replaceAll("\\s+", "");
        dni = dni.replaceAll("\\s+", "");

        if (codigo.length() > 6) {
            labErrorLegajo.setText("Está permitido hasta 6 números");
        } else {
            labErrorLegajo.setText("");
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