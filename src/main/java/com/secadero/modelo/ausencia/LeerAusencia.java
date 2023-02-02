package com.secadero.modelo.ausencia;

import com.secadero.conexion.Conexion;
import com.secadero.modelo.empleados.LeerEmpleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LeerAusencia {
    private String nombre;
    private String apellido;
    private int legajo;
    private Date fecha;
    private String motivo;
    private String justificado;
    private String certificado;
    private int idausencias;
    private int idEmpleadoFK;

    public LeerAusencia(){}

    public LeerAusencia(String nombre, String apellido, int legajo, Date fecha, String motivo, String justificado, String certificado, int idausencias, int idEmpleadoFK) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.fecha = fecha;
        this.motivo = motivo;
        this.justificado = justificado;
        this.certificado = certificado;
        this.idausencias = idausencias;
        this.idEmpleadoFK = idEmpleadoFK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
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

    public int getIdEmpleadoFK() {
        return idEmpleadoFK;
    }

    public void setIdEmpleadoFK(int idEmpleadoFK) {
        this.idEmpleadoFK = idEmpleadoFK;
    }

    //------------------------------------------- Leer Ausencias --------------------------------------------------
    public static ObservableList<LeerAusencia> listaAusencia(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerAusencia> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, motivo, justificado, certificado, idausencias, idEmpleadoFK FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ?");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerAusencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getDate("fecha"), rs.getString("motivo"), rs.getString("justificado"), rs.getString("certificado"), rs.getInt("idausencias"), rs.getInt("idEmpleadoFK")));
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

    //------------------------------------------- Buscar Ausencia --------------------------------------------------
    public static ObservableList<LeerAusencia> buscarAusenciaAmbos(TextField textBuscarAusenciaLegajoE, DatePicker dpBuscarFecha){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerAusencia> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, motivo, justificado, certificado, idausencias, idEmpleadoFK FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND (fecha = ? AND empleados.legajo = ?)");
            pstm.setString(1, "Vigente");
            pstm.setDate(2, java.sql.Date.valueOf(dpBuscarFecha.getEditor().getText()));
            pstm.setString(3, textBuscarAusenciaLegajoE.getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerAusencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getDate("fecha"), rs.getString("motivo"), rs.getString("justificado"), rs.getString("certificado"), rs.getInt("idausencias"), rs.getInt("idEmpleadoFK")));
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
    public static ObservableList<LeerAusencia> filtroAusencia(ComboBox<String> cbTiposFiltrosAusencias){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerAusencia> lista = FXCollections.observableArrayList();
        String dato = cbTiposFiltrosAusencias.getSelectionModel().getSelectedItem().toLowerCase();
        if(!dato.equals("fecha")){
            try {
                pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, motivo, justificado, certificado, idausencias, idEmpleadoFK FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? ORDER BY " + dato +" ASC");
                pstm.setString(1, "Vigente");

                rs = pstm.executeQuery();

                while (rs.next()){
                    lista.add(new LeerAusencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getDate("fecha"), rs.getString("motivo"), rs.getString("justificado"), rs.getString("certificado"), rs.getInt("idausencias"), rs.getInt("idEmpleadoFK")));
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
        } else {
            try {
                pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, motivo, justificado, certificado, idausencias, idEmpleadoFK FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? ORDER BY " + dato +" DESC");
                pstm.setString(1, "Vigente");

                rs = pstm.executeQuery();

                while (rs.next()){
                    lista.add(new LeerAusencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getDate("fecha"), rs.getString("motivo"), rs.getString("justificado"), rs.getString("certificado"), rs.getInt("idausencias"), rs.getInt("idEmpleadoFK")));
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
}
