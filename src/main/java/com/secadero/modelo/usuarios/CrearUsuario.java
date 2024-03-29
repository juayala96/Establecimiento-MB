package com.secadero.modelo.usuarios;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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
                String nombreUsuario = textNombreUsuarioCrear.getText().trim();
                String contrasenia = textContraseniaCrear.getText().trim();
                nombreUsuario = nombreUsuario.replaceAll("\\s+", "");
                contrasenia = contrasenia.replaceAll("\\s+", "");

                String consulta2 = "INSERT INTO usuarios(nombreUsuario, contrasenia, idEmpleadoFK) VALUES (?, ?, ?)";
                pstm = con.prepareStatement(consulta2);
                pstm.setString(1, nombreUsuario);
                pstm.setString(2, contrasenia);
                pstm.setInt(3, Integer.parseInt(labIDEmpleadoCrear.getText()));
                pstm.executeUpdate();

                String consulta3 = "UPDATE empleados SET estadoSistema = ? WHERE idempleados = ?";
                pstm = con.prepareStatement(consulta3);
                pstm.setString(1, "Permitido");
                pstm.setInt(2, Integer.parseInt(labIDEmpleadoCrear.getText()));
                pstm.executeUpdate();

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Crear usuario");
                alerta.setContentText("Se han guardado los datos correctamente.");
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                alerta.showAndWait();
                labLimpiarCamposCrear.setText("OK");
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error - Nombre de usuario");
                alerta.setContentText("El nombre de usuario ingresado pertenece a otro usuario. \nNo está permitido duplicarlo.");
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
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
                alerta.setTitle("Error - Crear usuario");
                alerta.setContentText("Error en la base de datos");
                alerta.showAndWait();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }
}
