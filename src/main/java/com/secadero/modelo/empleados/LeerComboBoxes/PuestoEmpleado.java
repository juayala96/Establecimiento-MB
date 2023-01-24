package com.secadero.modelo.empleados.LeerComboBoxes;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PuestoEmpleado {
    private int idpuesto;
    private String descripcion;

    public PuestoEmpleado(){}

    public PuestoEmpleado(String descripcion, int idpuesto) {
        this.descripcion = descripcion;
        this.idpuesto = idpuesto;
    }

    public int getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(int idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // ------------------------------------------- Combo Box Estado Civil -----------------------------------------------
    public static ObservableList<PuestoEmpleado> getPuesto_Empleado(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<PuestoEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM puesto");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new PuestoEmpleado(rs.getString("descripcion"), rs.getInt("idpuesto")));
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
