package com.secadero.modelo.empleados;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EliminarEmpleado {
    public EliminarEmpleado(){}

    //--------------------------------------------- Eliminar Empleado ---------------------------------------------------
    public void eliminarEmpleado(Label labIDEliminar){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String consulta = "UPDATE empleados SET estadoEmpleado = ? WHERE idempleados = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, "Eliminado");
            pstm.setInt(2, Integer.parseInt(labIDEliminar.getText()));
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error!");
                alerta.setContentText("Error en la Base de Datos");
                alerta.showAndWait();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }
}
