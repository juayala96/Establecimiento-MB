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

public class CrearUsuario {
    public CrearUsuario(){}

    //--------------------------------------------- Crear Usuario ---------------------------------------------------
    public void agregarUsuario(TextField textNombreCrear, TextField textApellidoCrear, TextField textLegajoCrear, TextField textEmailCrear, TextField textTelefonoCrear, TextField textNombreUsuarioCrear, TextField textContraseniaCrear, ComboBox<String> cbRolCrear, Label labLimpiarCamposCrear){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String respuesta = "YES";
        String respuesta2 = "YES";

        try {
            String consulta = "SELECT * FROM usuarios WHERE legajo = ? AND estadoUsuario = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, textLegajoCrear.getText());
            pstm.setString(2, "Vigente");
            rs = pstm.executeQuery();
            if(rs.next()){
                respuesta = "NO";
            }

            String consulta2 = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND estadoUsuario = ?";
            pstm = con.prepareStatement(consulta2);
            pstm.setString(1, textContraseniaCrear.getText());
            pstm.setString(2, "Vigente");
            rs = pstm.executeQuery();
            if(rs.next()){
                respuesta2 = "NO";
            }

            if(respuesta.equals("YES")){
                if(respuesta2.equals("YES")){
                    String consulta3 = "INSERT INTO usuarios(nombre, apellido, legajo, email, telefono, nombreUsuario, contrasenia, rol, estadoUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    pstm = con.prepareStatement(consulta3);
                    pstm.setString(1, textNombreCrear.getText());
                    pstm.setString(2, textApellidoCrear.getText());
                    pstm.setInt(3, Integer.parseInt(textLegajoCrear.getText()));
                    pstm.setString(4, textEmailCrear.getText());
                    pstm.setString(5, textTelefonoCrear.getText());
                    pstm.setString(6, textNombreUsuarioCrear.getText());
                    pstm.setString(7, textContraseniaCrear.getText());
                    pstm.setString(8, cbRolCrear.getSelectionModel().getSelectedItem());
                    pstm.setString(9, "Vigente");
                    pstm.executeUpdate();

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Datos Guardados");
                    alerta.setContentText("Se a guardado los datos correctamente");
                    alerta.showAndWait();
                    labLimpiarCamposCrear.setText("OK");

                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR de Duplicación");
                    alerta.setContentText("No está permitido duplicar la CONTRASEÑA de otro Usuario");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR de Duplicación");
                alerta.setContentText("No está permitido duplicar el LEGAJO de otro Usuario");
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
