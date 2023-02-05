package com.secadero.modelo.licencias.leerComboBoxes;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoLicencia {
    private int idTipoLicencia;
    private String descripcion;

    public TipoLicencia(){}

    public TipoLicencia(String descripcion, int idTipoLicencia) {
        this.descripcion = descripcion;
        this.idTipoLicencia = idTipoLicencia;
    }

    public int getIdTipoLicencia() {
        return idTipoLicencia;
    }

    public void setIdTipoLicencia(int idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // ----------------------------------------- Combo Box Tipo de Licencia -------------------------------------------
    public static ObservableList<TipoLicencia> getTipo_Licencia(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<TipoLicencia> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM tipo_licencias");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new TipoLicencia(rs.getString("descripcion"), rs.getInt("idTipoLicencia")));
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
