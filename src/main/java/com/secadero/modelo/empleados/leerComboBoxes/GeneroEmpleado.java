package com.secadero.modelo.empleados.leerComboBoxes;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneroEmpleado {
    private int idgenero;
    private String descripcion;

    public GeneroEmpleado(){}

    public GeneroEmpleado(String descripcion, int idgenero) {
        this.descripcion = descripcion;
        this.idgenero = idgenero;
    }

    public int getIdgenero() {
        return idgenero;
    }

    public void setIdgenero(int idgenero) {
        this.idgenero = idgenero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // ------------------------------------------- Combo Box Estado Civil -----------------------------------------------
    public static ObservableList<GeneroEmpleado> getGenero_Empleado(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<GeneroEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM genero");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new GeneroEmpleado(rs.getString("descripcion"), rs.getInt("idgenero")));
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
