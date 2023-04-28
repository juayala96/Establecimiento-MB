package com.secadero.modelo.usuarios;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrearUsuario {
    public CrearUsuario(){}

    //--------------------------------------------- Crear Usuario ---------------------------------------------------
    public void agregarUsuario(TextField textNombreUsuarioCrear, TextField textContraseniaCrear, Label labIDEmpleadoCrear, Label labLimpiarCamposCrear){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        PreparedStatement pstm2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String respuesta = "YES";

        try {
            String consulta = "SELECT * FROM usuarios WHERE nombreUsuario = ?";
            pstm2 = con.prepareStatement(consulta);
            pstm2.setString(1, textNombreUsuarioCrear.getText());
            rs2 = pstm2.executeQuery();
            if(rs2.next()){
                respuesta = "NO";
            }

            if(respuesta.equals("YES")){
                String consulta2 = "INSERT INTO usuarios(nombreUsuario, contrasenia, idEmpleadoFK) VALUES (?, ?, ?)";
                pstm = con.prepareStatement(consulta2);
                pstm.setString(1, textNombreUsuarioCrear.getText());
                pstm.setString(2, textContraseniaCrear.getText());
                pstm.setInt(3, Integer.parseInt(labIDEmpleadoCrear.getText()));
                pstm.executeUpdate();

                String consulta3 = "UPDATE empleados SET estadoSistema = ? WHERE idempleados = ?";
                pstm = con.prepareStatement(consulta3);
                pstm.setString(1, "Permitido");
                pstm.setInt(2, Integer.parseInt(labIDEmpleadoCrear.getText()));
                pstm.executeUpdate();

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Datos Guardados");
                alerta.setContentText("Se a guardado los datos correctamente");
                alerta.showAndWait();
                labLimpiarCamposCrear.setText("OK");
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR de Duplicación");
                alerta.setContentText("No está permitido duplicar la NOMBRE DE USUARIO de otro Usuario");
                alerta.showAndWait();
            }

        } catch (SQLException e) {
            labLimpiarCamposCrear.setText("");
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
