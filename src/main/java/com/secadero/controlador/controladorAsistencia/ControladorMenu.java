package com.secadero.controlador.controladorAsistencia;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class ControladorMenu {

    public ControladorMenu(){}

    @FXML
    private Button btnEntrada;
    @FXML
    private Button btnSalida;
    @FXML
    private Label labFechaActual;
    @FXML
    private Label labHoraActual;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() throws ParseException {
        // Iniciar el AnimationTimer para actualizar la fecha y hora cada segundo
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Obtener la fecha y hora actual
                LocalDate fechaActual = LocalDate.now();
                LocalTime horaActual = LocalTime.now();

                // Formatear la fecha y hora en el formato deseado
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fechaFormateada = fechaActual.format(formato);
                int hora = horaActual.getHour();
                int minuto = horaActual.getMinute();

                // Actualizar el texto del Label con la fecha y hora actualizada
                labFechaActual.setText(fechaFormateada);
                labHoraActual.setText(hora + ":" + minuto);
            }
        };
        timer.start();
    }

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
        escenario.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/imagenes/iconoAsistencia.png")).toString()));
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
        escenario.setTitle("Registro de Salida");
        escenario.setScene(escena);
        escenario.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/imagenes/iconoAsistencia.png")).toString()));
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
