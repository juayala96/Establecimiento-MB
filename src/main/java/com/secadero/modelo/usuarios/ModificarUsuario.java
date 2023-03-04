package com.secadero.modelo.usuarios;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
    public void modificarUsuario(TextField textNombreModificar, TextField textApellidoModificar, TextField textLegajoModificar, TextField textEmailModificar, TextField textTelefonoModificar, TextField textNombreUsuarioModificar, TextField textContraseniaModificar, ComboBox<String> cbRolModificar,Label labIDModificar, Label labLimpiarCamposModificar, Label labInformacionModificarLegajo, Label labInformacionModificarNombreUsuario){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String respuesta = "YES";
        String respuesta2 = "YES";

        try {
            String consulta = "SELECT * FROM usuarios WHERE legajo = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1, Integer.parseInt(textLegajoModificar.getText()));
            rs = pstm.executeQuery();
            if(rs.next()){
                int legajo = rs.getInt("legajo");
                if(Objects.equals(Integer.parseInt(labInformacionModificarLegajo.getText()), legajo)){
                    respuesta = "YES";
                } else {
                    respuesta = "NO";
                }
            }

            String consulta2 = "SELECT * FROM usuarios WHERE nombreUsuario = ?";
            pstm = con.prepareStatement(consulta2);
            pstm.setString(1, textNombreUsuarioModificar.getText());
            rs = pstm.executeQuery();
            if(rs.next()){
                String nombreUsuario = rs.getString("nombreUsuario");
                if(Objects.equals(labInformacionModificarNombreUsuario.getText(), nombreUsuario)){
                    respuesta2 = "YES";
                } else {
                    respuesta2 = "NO";
                }
            }

            if(respuesta.equals("YES")){
                if(respuesta2.equals("YES")){
                    String consulta3 = "UPDATE usuarios SET nombre = ?, apellido = ?, legajo = ?, email = ?, telefono = ?, nombreUsuario = ?,  contrasenia = ?, rol = ? WHERE idusuarios = ?";
                    pstm = con.prepareStatement(consulta3);
                    pstm.setString(1, textNombreModificar.getText());
                    pstm.setString(2, textApellidoModificar.getText());
                    pstm.setInt(3, Integer.parseInt(textLegajoModificar.getText()));
                    pstm.setString(4, textEmailModificar.getText());
                    pstm.setString(5, textTelefonoModificar.getText());
                    pstm.setString(6, textNombreUsuarioModificar.getText());
                    pstm.setString(7, textContraseniaModificar.getText());
                    pstm.setString(8, cbRolModificar.getSelectionModel().getSelectedItem());
                    pstm.setInt(9, Integer.parseInt(labIDModificar.getText()));
                    pstm.executeUpdate();

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Datos Modificados");
                    alerta.setContentText("Se a guardado los datos correctamente");
                    alerta.showAndWait();
                    labLimpiarCamposModificar.setText("OK");

                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR de Duplicación");
                    alerta.setContentText("No está permitido duplicar el NOMBRE DE USUARIO de otro");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR de Duplicación");
                alerta.setContentText("No está permitido duplicar la LEGAJO de otro Usuario");
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
