package com.secadero.modelo.cronograma;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class EliminarCronograma {
    public EliminarCronograma(){}

    //--------------------------------------------- Eliminar Ausencia ---------------------------------------------------
    public void eliminarCronograma(Label labIDCronogramaEliminar, Label labIDEmpleadoEliminar) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String consulta = "DELETE FROM empleado_cronograma WHERE idEmpleadoFK = ? AND idCronogramaFK = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1, Integer.parseInt(labIDEmpleadoEliminar.getText()));
            pstm.setInt(2, Integer.parseInt(labIDCronogramaEliminar.getText()));
            pstm.executeUpdate();

            String consulta2 = "DELETE FROM cronograma WHERE idCronograma = ?";
            pstm = con.prepareStatement(consulta2);
            pstm.setInt(1, Integer.parseInt(labIDCronogramaEliminar.getText()));
            pstm.executeUpdate();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("");
            alerta.setContentText("Se a Eliminado Correctamente.");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
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
