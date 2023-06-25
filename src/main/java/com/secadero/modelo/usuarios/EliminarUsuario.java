package com.secadero.modelo.usuarios;

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

public class EliminarUsuario {
    public EliminarUsuario(){}

    //------------------------------------------- Eliminar Usuario -------------------------------------------------
    public void eliminarUsuario(Label labIDEliminar){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int datoIDEmpleadoFK;

        try {
            String consulta = "SELECT idEmpleadoFK FROM usuarios INNER JOIN empleados ON usuarios.idEmpleadoFK = empleados.idempleados WHERE usuarios.idusuarios = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1, Integer.parseInt(labIDEliminar.getText()));
            rs = pstm.executeQuery();

            while (rs.next()) {
                datoIDEmpleadoFK = rs.getInt("idEmpleadoFK");

                String consult2 = "UPDATE empleados SET estadoSistema = ? WHERE idempleados = ?";
                pstm = con.prepareStatement(consult2);
                pstm.setString(1, " ");
                pstm.setInt(2, datoIDEmpleadoFK);
                pstm.executeUpdate();
            }


            String consulta3 = "DELETE FROM usuarios WHERE idusuarios = ?";
            pstm = con.prepareStatement(consulta3);
            pstm.setInt(1, Integer.parseInt(labIDEliminar.getText()));
            pstm.executeUpdate();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Eliminar usuario");
            alerta.setContentText("Se ha eliminado el usuario correctamente.");
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
            } catch (Exception ex){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error - Eliminar usuario");
                alerta.setContentText("Error en la base de datos.");
                alerta.showAndWait();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }
}
