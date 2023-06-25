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

public class Porcentaje {
    private int antiguedad;
    private int rectroactivo;
    private int jubilacion;
    private int ley;
    private int obraSocial;
    private int seguroSepelio;
    private int cuotaAgraria;

    public Porcentaje (){}

    public Porcentaje(int antiguedad, int rectroactivo, int jubilacion, int ley, int obraSocial, int seguroSepelio, int cuotaAgraria) {
        this.antiguedad = antiguedad;
        this.rectroactivo = rectroactivo;
        this.jubilacion = jubilacion;
        this.ley = ley;
        this.obraSocial = obraSocial;
        this.seguroSepelio = seguroSepelio;
        this.cuotaAgraria = cuotaAgraria;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public int getRectroactivo() {
        return rectroactivo;
    }

    public void setRectroactivo(int rectroactivo) {
        this.rectroactivo = rectroactivo;
    }

    public int getJubilacion() {
        return jubilacion;
    }

    public void setJubilacion(int jubilacion) {
        this.jubilacion = jubilacion;
    }

    public int getLey() {
        return ley;
    }

    public void setLey(int ley) {
        this.ley = ley;
    }

    public int getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(int obraSocial) {
        this.obraSocial = obraSocial;
    }

    public int getSeguroSepelio() {
        return seguroSepelio;
    }

    public void setSeguroSepelio(int seguroSepelio) {
        this.seguroSepelio = seguroSepelio;
    }

    public int getCuotaAgraria() {
        return cuotaAgraria;
    }

    public void setCuotaAgraria(int cuotaAgraria) {
        this.cuotaAgraria = cuotaAgraria;
    }

    // ------------------------------------------------- Leer Porcentajes ---------------------------------------------
    public static ObservableList<Porcentaje> leerPorcentajes(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<Porcentaje> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT antiguedad, rectroactivo, jubilacion, ley, obraSocial, seguroSepelio, cuotaAgraria FROM porcentajes");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new Porcentaje(rs.getInt("antiguedad"), rs.getInt("rectroactivo"), rs.getInt("jubilacion"), rs.getInt("ley"), rs.getInt("obraSocial"), rs.getInt("seguroSepelio"), rs.getInt("cuotaAgraria")));
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

    // --------------------------------------------- Modificar Porcentajes -------------------------------------------
    public void modificarPorcentajes(TextField textAntiguedad, TextField textRetroactive, TextField textJubilacion, TextField textLey, TextField textObraSocial, TextField textSeguroSepelio, TextField textCuotaAgraria, Label labConfirmacionPorcentaje){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String consulta = "UPDATE porcentajes SET antiguedad = ?, rectroactivo = ?, jubilacion = ?, ley = ?, obraSocial = ?, seguroSepelio = ?, cuotaAgraria = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1, Integer.parseInt(textAntiguedad.getText()));
            pstm.setInt(2, Integer.parseInt(textRetroactive.getText()));
            pstm.setInt(3, Integer.parseInt(textJubilacion.getText()));
            pstm.setInt(4, Integer.parseInt(textLey.getText()));
            pstm.setInt(5, Integer.parseInt(textObraSocial.getText()));
            pstm.setInt(6, Integer.parseInt(textSeguroSepelio.getText()));
            pstm.setInt(7, Integer.parseInt(textCuotaAgraria.getText()));
            pstm.executeUpdate();

            labConfirmacionPorcentaje.setText("OK");
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Modificar porcentajes");
            alerta.setContentText("Se han guardado los datos de los porcentajes correctamente.");
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
