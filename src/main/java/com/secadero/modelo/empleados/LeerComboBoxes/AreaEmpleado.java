package com.secadero.modelo.empleados.LeerComboBoxes;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaEmpleado {
    private int idarea;
    private String descripcion;

    public AreaEmpleado(){}

    public AreaEmpleado(String descripcion, int idarea) {
        this.descripcion = descripcion;
        this.idarea = idarea;
    }

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // ------------------------------------------- Combo Box Estado Civil -----------------------------------------------
    public static ObservableList<AreaEmpleado> getArea_Empleado(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<AreaEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM area");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new AreaEmpleado(rs.getString("descripcion"), rs.getInt("idarea")));
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
