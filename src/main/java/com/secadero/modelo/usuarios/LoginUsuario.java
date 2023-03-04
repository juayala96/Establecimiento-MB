package com.secadero.modelo.usuarios;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUsuario {
    public LoginUsuario(){}

    //------------------------------------------- Login Usuario -------------------------------------------------
    public void loginUsuario(TextField campoNombre, PasswordField campoPassword, Label labID){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT idusuarios FROM usuarios WHERE nombreUsuario = ? AND contrasenia = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, campoNombre.getText());
            pstm.setString(2, campoPassword.getText());
            rs = pstm.executeQuery();
            while (rs.next()){
                labID.setText(String.valueOf(rs.getInt("idusuarios")));
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
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
