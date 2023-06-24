package com.secadero.modelo.preRecibo.modificaciones;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class Sueldo {
    private int idPreRecibo;
    private int sueldo;
    private int idAreaFK;
    private int idPuestoFK;

    public Sueldo() {}

    public Sueldo(int idPreRecibo, int sueldo, int idAreaFK, int idPuestoFK) {
        this.idPreRecibo = idPreRecibo;
        this.sueldo = sueldo;
        this.idAreaFK = idAreaFK;
        this.idPuestoFK = idPuestoFK;
    }

    public int getIdPreRecibo() {
        return idPreRecibo;
    }

    public void setIdPreRecibo(int idPreRecibo) {
        this.idPreRecibo = idPreRecibo;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getIdAreaFK() {
        return idAreaFK;
    }

    public void setIdAreaFK(int idAreaFK) {
        this.idAreaFK = idAreaFK;
    }

    public int getIdPuestoFK() {
        return idPuestoFK;
    }

    public void setIdPuestoFK(int idPuestoFK) {
        this.idPuestoFK = idPuestoFK;
    }

    // ------------------------------------------------- Leer Salario ---------------------------------------------
    public static ObservableList<Sueldo> leerSueldo(String valorActual, String valorActual2, TextField textSueldo, Label labIDRecibo){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<Sueldo> lista = FXCollections.observableArrayList();

        try {
            String consulta = "SELECT idPreRecibo, sueldo FROM pre_recibo INNER JOIN area ON pre_recibo.idAreaFK = area.idarea INNER JOIN puesto ON pre_recibo.idPuestoFK = puesto.idpuesto WHERE area.descripcion = ? AND puesto.descripcion = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, valorActual);
            pstm.setString(2, valorActual2);
            rs = pstm.executeQuery();
            while (rs.next()){
                labIDRecibo.setText(String.valueOf(rs.getInt("idPreRecibo")));
                textSueldo.setText(String.valueOf(rs.getInt("sueldo")));
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex){
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    // --------------------------------------------- Modificar Salario -------------------------------------------
    public void modificarSueldo(TextField textSueldo, Label labIDRecibo, Label labConfirmacionSalario){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String consulta = "UPDATE pre_recibo SET sueldo = ? WHERE idPreRecibo = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1,  Integer.parseInt(textSueldo.getText()));
            pstm.setInt(2, Integer.parseInt(labIDRecibo.getText()));
            pstm.executeUpdate();

            labConfirmacionSalario.setText("OK");
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("");
            alerta.setContentText("Se a Guardado los Datos del Salario.");
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
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }
}
