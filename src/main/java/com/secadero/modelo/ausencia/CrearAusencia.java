package com.secadero.modelo.ausencia;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CrearAusencia {
    public CrearAusencia(){}

    //--------------------------------------------- Crear Ausencia ---------------------------------------------------
    public void agregarAusencia(Label labIDEmpleadoCrear, DatePicker dpFechaCrear, TextArea textMotivoCrear, Label labJustificacionCrear, TextField textCertificadoCrear, Label labLimpiarCamposCrear){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String dato;
        String resultado = "YES";

        try {
            String consulta1 = "SELECT idEmpleadoFK, fecha FROM ausencias WHERE idEmpleadoFK = ? AND fecha = ?";
            pstm = con.prepareStatement(consulta1);
            pstm.setString(1, labIDEmpleadoCrear.getText());
            pstm.setString(2, dpFechaCrear.getEditor().getText());
            rs = pstm.executeQuery();
            while (rs.next()){
                dato = String.valueOf(rs.getDate("fecha"));
                if(Objects.equals(dpFechaCrear.getEditor().getText(), dato)){
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
                pstm.setString(1, dpFechaCrear.getEditor().getText());
                pstm.setString(2, textMotivoCrear.getText());
                pstm.setString(3, labJustificacionCrear.getText());
                pstm.setString(4, textCertificadoCrear.getText());
                pstm.setInt(5, Integer.parseInt(labIDEmpleadoCrear.getText()));
                pstm.executeUpdate();

                labLimpiarCamposCrear.setText("OK");
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Datos Guardados");
                alerta.setContentText("Se a Guardado los Datos.");
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
                    alerta.setTitle("Error!");
                    alerta.setContentText("Error en la Base de Datos");
                    alerta.showAndWait();
                    System.err.println("Error: " + ex.getMessage());
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Fecha!");
            alerta.setContentText("No esta permitido crear 2 ausencias del mismo empleado de la misma fecha");
            alerta.showAndWait();
        }
    }
}
