package com.secadero.modelo.empleados.LeerComboBoxes;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadoCivilEmpleado {
    private int idestadoCivil;
    private String descripcion;

    public EstadoCivilEmpleado(){}

    public EstadoCivilEmpleado(String descripcion, int idestadoCivil) {
        this.descripcion = descripcion;
        this.idestadoCivil = idestadoCivil;
    }

    public int getIdestadoCivil() {
        return idestadoCivil;
    }

    public void setIdestadoCivil(int idestadoCivil) {
        this.idestadoCivil = idestadoCivil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // ------------------------------------------- Combo Box Estado Civil -----------------------------------------------
    public static ObservableList<EstadoCivilEmpleado> getEstadoCivil_Empleado(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<EstadoCivilEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM estado_civil");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new EstadoCivilEmpleado(rs.getString("descripcion"), rs.getInt("idestadoCivil")));
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
}
