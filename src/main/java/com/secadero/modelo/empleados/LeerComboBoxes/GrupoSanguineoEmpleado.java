package com.secadero.modelo.empleados.LeerComboBoxes;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GrupoSanguineoEmpleado {
    private int idgrupoSanguineo;
    private String descripcion;

    public GrupoSanguineoEmpleado(){}

    public GrupoSanguineoEmpleado(String descripcion, int idgrupoSanguineo) {
        this.descripcion = descripcion;
        this.idgrupoSanguineo = idgrupoSanguineo;
    }

    public int getIdgrupoSanguineol() {
        return idgrupoSanguineo;
    }

    public void setIdgrupoSanguineo(int idgrupoSanguineo) {
        this.idgrupoSanguineo = idgrupoSanguineo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // ------------------------------------------- Combo Box Grupo Sanguineo -----------------------------------------------
    public static ObservableList<GrupoSanguineoEmpleado> getGrupoSanguineo_Empleado(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<GrupoSanguineoEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM grupo_sanguineo");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new GrupoSanguineoEmpleado(rs.getString("descripcion"), rs.getInt("idgrupoSanguineo")));
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
