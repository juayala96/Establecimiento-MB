package com.secadero.modelo.ausencia;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EliminarAusencia {
    public EliminarAusencia(){}

    //--------------------------------------------- Eliminar Ausencia ---------------------------------------------------
    public void eliminarAusencia(Label labIDAusenciaEliminar) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String consulta = "DELETE FROM ausencias WHERE idausencias = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1, Integer.parseInt(labIDAusenciaEliminar.getText()));
            pstm.executeUpdate();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Datos Eliminados");
            alerta.setContentText("Se a Eliminado Correctamente.");
            alerta.showAndWait();

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error!");
                alerta.setContentText("Error en la Base de Datos");
                alerta.showAndWait();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }
}