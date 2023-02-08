package com.secadero.modelo.ausencia;

import com.secadero.conexion.Conexion;
import com.secadero.modelo.empleados.LeerEmpleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LeerAusencia {
    private Date fecha;
    private String motivo;
    private String justificado;
    private String certificado;
    private int idausencias;

    public LeerAusencia(){}

    public LeerAusencia(Date fecha, String motivo, String justificado, String certificado, int idausencias) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.justificado = justificado;
        this.certificado = certificado;
        this.idausencias = idausencias;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getJustificado() {
        return justificado;
    }

    public void setJustificado(String justificado) {
        this.justificado = justificado;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public int getIdausencias() {
        return idausencias;
    }

    public void setIdausencias(int idausencias) {
        this.idausencias = idausencias;
    }

    //------------------------------------------- Leer Ausencias --------------------------------------------------
    public static ObservableList<LeerAusencia> listaAusencia(Label labIDEmpleadoLista){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerAusencia> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT fecha, motivo, justificado, certificado, idausencias FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND ausencias.idEmpleadoFK = ?");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(labIDEmpleadoLista.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerAusencia(rs.getDate("fecha"), rs.getString("motivo"), rs.getString("justificado"), rs.getString("certificado"), rs.getInt("idausencias")));
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

    //-------------------------------------- Leer Ausencias Comprobación ----------------------------------------------
    public void listaAusenciaComprobacion(Label labIDEmpleadoLista, Label labIDAusenciaLista, Label labResultadoID){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int cont = 0;

        try {
            pstm = con.prepareStatement("SELECT idausencias FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND ausencias.idEmpleadoFK = ? AND ausencias.idausencias = ?");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(labIDEmpleadoLista.getText()));
            pstm.setInt(3, Integer.parseInt(labIDAusenciaLista.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                labResultadoID.setText("YES");
                cont +=1;
            }

            if(cont == 0){
                labResultadoID.setText("NO");
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
    }


    //---------------------------------------- Buscar Ausencia Fecha --------------------------------------------------
    public static ObservableList<LeerAusencia> buscarAusenciaFecha(Label labIDEmpleadoLista, DatePicker dpBuscarFecha){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerAusencia> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT fecha, motivo, justificado, certificado, idausencias FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND ausencias.idEmpleadoFK = ? AND ausencias.fecha = ?");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(labIDEmpleadoLista.getText()));
            pstm.setDate(3, java.sql.Date.valueOf(dpBuscarFecha.getEditor().getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerAusencia(rs.getDate("fecha"), rs.getString("motivo"), rs.getString("justificado"), rs.getString("certificado"), rs.getInt("idausencias")));
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

    //------------------------------------------- Filtro Ausencia --------------------------------------------------
    public static ObservableList<LeerAusencia> filtroAusencia(ComboBox<String> cbTiposFiltrosAusencias, Label labIDEmpleadoLista){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerAusencia> lista = FXCollections.observableArrayList();
        String dato = cbTiposFiltrosAusencias.getSelectionModel().getSelectedItem().toLowerCase();

        if(labIDEmpleadoLista.getText().equals("")){
            labIDEmpleadoLista.setText("0");
        }

        try {
            pstm = con.prepareStatement("SELECT fecha, motivo, justificado, certificado, idausencias FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.idempleados = ? ORDER BY " + dato +" DESC");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(labIDEmpleadoLista.getText()));

            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerAusencia(rs.getDate("fecha"), rs.getString("motivo"), rs.getString("justificado"), rs.getString("certificado"), rs.getInt("idausencias")));
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

