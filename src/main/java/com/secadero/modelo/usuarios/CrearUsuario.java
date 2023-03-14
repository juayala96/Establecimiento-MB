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
    public void agregarUsuario(TextField textNombreCrear, TextField textApellidoCrear, TextField textLegajoCrear, TextField textDNICrear, TextField textEmailCrear, TextField textTelefonoCrear, TextField textNombreUsuarioCrear, TextField textContraseniaCrear, ComboBox<String> cbRolCrear, Label labLimpiarCamposCrear){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        PreparedStatement pstm2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String respuesta = "YES";
        String respuesta2 = "YES";
        String respuesta3 = "YES";

        try {
            String consulta = "SELECT * FROM usuarios WHERE legajo = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, textLegajoCrear.getText());
            rs = pstm.executeQuery();
            if(rs.next()){
                respuesta = "NO";
            }

            String consulta2 = "SELECT * FROM usuarios WHERE nombreUsuario = ?";
            pstm2 = con.prepareStatement(consulta2);
            pstm2.setString(1, textNombreUsuarioCrear.getText());
            rs2 = pstm2.executeQuery();
            if(rs2.next()){
                respuesta2 = "NO";
            }

            String consulta3 = "SELECT * FROM usuarios WHERE dni = ?";
            pstm2 = con.prepareStatement(consulta3);
            pstm2.setString(1, textDNICrear.getText());
            rs2 = pstm2.executeQuery();
            if(rs2.next()){
                respuesta3 = "NO";
            }

            if(respuesta.equals("YES")){
                if(respuesta2.equals("YES")){
                    if(respuesta3.equals("YES")){
                        String consulta4 = "INSERT INTO usuarios(nombre, apellido, legajo, dni, email, telefono, nombreUsuario, contrasenia, rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        pstm = con.prepareStatement(consulta4);
                        pstm.setString(1, textNombreCrear.getText());
                        pstm.setString(2, textApellidoCrear.getText());
                        pstm.setInt(3, Integer.parseInt(textLegajoCrear.getText()));
                        pstm.setInt(4, Integer.parseInt(textDNICrear.getText()));
                        pstm.setString(5, textEmailCrear.getText());
                        pstm.setString(6, textTelefonoCrear.getText());
                        pstm.setString(7, textNombreUsuarioCrear.getText());
                        pstm.setString(8, textContraseniaCrear.getText());
                        pstm.setString(9, cbRolCrear.getSelectionModel().getSelectedItem());
                        pstm.executeUpdate();

                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Datos Guardados");
                        alerta.setContentText("Se a guardado los datos correctamente");
                        alerta.showAndWait();
                        labLimpiarCamposCrear.setText("OK");
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("ERROR de Duplicación");
                        alerta.setContentText("No está permitido duplicar el DNI de otro Usuario");
                        alerta.showAndWait();
                    }

                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR de Duplicación");
                    alerta.setContentText("No está permitido duplicar la NOMBRE DE USUARIO de otro Usuario");
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
