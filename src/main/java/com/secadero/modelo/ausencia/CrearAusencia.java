package com.secadero.modelo.ausencia;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CrearAusencia {
    public CrearAusencia(){}

    //--------------------------------------------- Crear Ausencia ---------------------------------------------------
    public void agregarAusencia(Label labIDEmpleadoCrear, DatePicker dpFechaCrear, TextArea textMotivoCrear, Label labJustificacionCrear, Label labCertificadoCrear, Label labLimpiarCamposCrear){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String dato;
        String resultado = "YES";

        String fecha = dpFechaCrear.getEditor().getText();
        String fechaAnio = fecha.substring(6, 10);
        String fechaMes = fecha.substring(3, 5);
        String fechaDia = fecha.substring(0, 2);
        String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        try {
            String consulta1 = "SELECT idEmpleadoFK, fecha FROM ausencias WHERE idEmpleadoFK = ? AND fecha = ?";
            pstm = con.prepareStatement(consulta1);
            pstm.setString(1, labIDEmpleadoCrear.getText());
            pstm.setString(2, fechaModificada);
            rs = pstm.executeQuery();
            while (rs.next()){
                dato = String.valueOf(rs.getDate("fecha"));
                if(Objects.equals(fechaModificada, dato)){
                    resultado = "NO";
                }
            }
        } catch (Exception e1){
            System.err.println("Error: " + e1.getMessage());
        }

        if(resultado.equals("YES")){
            try {
                String consulta = "INSERT INTO ausencias(fecha, motivo, justificado, certificado, idEmpleadoFK) VALUES (?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(consulta);
                pstm.setString(1, fechaModificada);
                pstm.setString(2, textMotivoCrear.getText());
                pstm.setString(3, labJustificacionCrear.getText());
                pstm.setString(4, labCertificadoCrear.getText());
                pstm.setInt(5, Integer.parseInt(labIDEmpleadoCrear.getText()));
                pstm.executeUpdate();

                labLimpiarCamposCrear.setText("OK");
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Crear ausencia");
                alerta.setContentText("Se han guardado los datos correctamente.");
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                alerta.showAndWait();

            } catch (SQLException ex) {
                labLimpiarCamposCrear.setText("");
                System.err.println("Error: " + ex.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstm != null) pstm.close();
                    if (con != null) con.close();
                } catch (Exception ex) {
                    labLimpiarCamposCrear.setText("");
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error - Crear ausencia");
                    alerta.setContentText("Error en la base de datos.");
                    alerta.showAndWait();
                    System.err.println("Error: " + ex.getMessage());
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error - Fecha");
            alerta.setContentText("No es posible crear más de una ausencia del mismo empleado en la misma fecha.");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        }
    }
}
