package com.secadero.modelo.cronograma;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EliminarCronograma {
    public EliminarCronograma(){}

    //--------------------------------------------- Eliminar Ausencia ---------------------------------------------------
    public void eliminarCronograma(Label labIDCronogramaEliminar, Label labIDEmpleadoEliminar) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String consulta = "UPDATE cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados SET estadoCronograma = ? WHERE cronograma.idCronograma = ? AND empleado_cronograma.idEmpleadoFK = ? AND empleados.estadoEmpleado = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, "Eliminado");
            pstm.setInt(2, Integer.parseInt(labIDCronogramaEliminar.getText()));
            pstm.setInt(3, Integer.parseInt(labIDEmpleadoEliminar.getText()));
            pstm.setString(4, "Vigente");
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
