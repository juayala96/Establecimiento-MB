package com.secadero.modelo.ausencia;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ModificarAusencia {
    public ModificarAusencia(){}

    //--------------------------------------------- Modificar Ausencia ---------------------------------------------------
    public void modificarAusencia(Label labIDEmpleadoModificar, Label labIDAusenciaModificar, DatePicker dpFechaModificar, DatePicker dpFechaModificarDuplicada, TextArea textMotivoModificar, Label labJustificacionModificar, Label labCertificadoModificar, Label labLimpiarCamposModificar) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String dato;
        String resultado = "YES";

        try {
            String consulta1 = "SELECT idEmpleadoFK, fecha FROM ausencias WHERE idEmpleadoFK = ? AND fecha = ?";
            pstm = con.prepareStatement(consulta1);
            pstm.setString(1, labIDEmpleadoModificar.getText());
            pstm.setString(2, dpFechaModificar.getEditor().getText());
            rs = pstm.executeQuery();
            while (rs.next()) {
                dato = String.valueOf(rs.getDate("fecha"));
                if (Objects.equals(dpFechaModificarDuplicada.getEditor().getText(), dato)) {
                    resultado = "YES";
                } else {
                    resultado = "NO";
                }
            }
        } catch (Exception e1) {
            System.err.println("Error: " + e1.getMessage());
        }

        if (resultado.equals("YES")) {
            try {
                String consulta = "UPDATE ausencias SET fecha = ?, motivo = ?, justificado = ?, certificado = ? WHERE idausencias = ?";
                pstm = con.prepareStatement(consulta);
                pstm.setString(1, dpFechaModificar.getEditor().getText());
                pstm.setString(2, textMotivoModificar.getText());
                pstm.setString(3, labJustificacionModificar.getText());
                pstm.setString(4, labCertificadoModificar.getText());
                pstm.setInt(5, Integer.parseInt(labIDAusenciaModificar.getText()));
                pstm.executeUpdate();

                labLimpiarCamposModificar.setText("OK");
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Datos Modificados");
                alerta.setContentText("Se a Guardado los Datos Correctamente.");
                alerta.showAndWait();

            } catch (SQLException ex) {
                labLimpiarCamposModificar.setText("");
                System.err.println("Error: " + ex.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstm != null) pstm.close();
                    if (con != null) con.close();
                } catch (Exception ex) {
                    labLimpiarCamposModificar.setText("");
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
