package com.secadero.modelo.usuarios;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ModificarUsuario {
    public ModificarUsuario(){}

    //--------------------------------------------- Modificar Usuario ---------------------------------------------------
    public void modificarUsuario(TextField textNombreUsuarioModificar, TextField textContraseniaModificar, Label labIDModificar, Label labInformacionModificarNombreUsuario, Label labLimpiarCamposModificar){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String respuesta = "YES";

        try {
            String consulta = "SELECT * FROM usuarios WHERE nombreUsuario = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, textNombreUsuarioModificar.getText());
            rs = pstm.executeQuery();
            if(rs.next()){
                String nombreUsuario = rs.getString("nombreUsuario");
                if(Objects.equals(labInformacionModificarNombreUsuario.getText(), nombreUsuario)){
                    respuesta = "YES";
                } else {
                    respuesta = "NO";
                }
            }

            if(respuesta.equals("YES")){
                String nombreUsuario = textNombreUsuarioModificar.getText().trim();
                String contrasenia = textContraseniaModificar.getText().trim();
                nombreUsuario = nombreUsuario.replaceAll("\\s+", "");
                contrasenia = contrasenia.replaceAll("\\s+", "");

                String consulta2 = "UPDATE usuarios SET nombreUsuario = ?,  contrasenia = ? WHERE idusuarios = ?";
                pstm = con.prepareStatement(consulta2);
                pstm.setString(1, nombreUsuario);
                pstm.setString(2, contrasenia);
                pstm.setInt(3, Integer.parseInt(labIDModificar.getText()));
                pstm.executeUpdate();

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Datos Modificados");
                alerta.setContentText("Se a guardado los datos correctamente");
                alerta.showAndWait();
                labLimpiarCamposModificar.setText("OK");

            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR de Duplicación");
                alerta.setContentText("No está permitido duplicar el NOMBRE DE USUARIO de otro Usuario");
                alerta.showAndWait();
            }

        } catch (SQLException e) {
            labLimpiarCamposModificar.setText("");
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
