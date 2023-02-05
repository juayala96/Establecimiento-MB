package com.secadero.modelo.licencias;

import com.secadero.conexion.Conexion;
import com.secadero.modelo.ausencia.LeerAusencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LeerLicencia {

    private String nombre;
    private String apellido;
    private int legajo;
    private String telefono;
    private Date fecha_Inicio;
    private Date fecha_Fin;
    private String descripcion;
    private int idLicencias;
    private int idempleados;

    public LeerLicencia(){}

    public LeerLicencia(String nombre, String apellido, int legajo, String telefono, Date fecha_Inicio, Date fecha_Fin, String descripcion, int idLicencias, int idempleados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.telefono = telefono;
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_Fin = fecha_Fin;
        this.descripcion = descripcion;
        this.idLicencias = idLicencias;
        this.idempleados = idempleados;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_Inicio() {
        return fecha_Inicio;
    }

    public void setFecha_Inicio(Date fecha_Inicio) {
        this.fecha_Inicio = fecha_Inicio;
    }

    public Date getFecha_Fin() {
        return fecha_Fin;
    }

    public void setFecha_Fin(Date fecha_Fin) {
        this.fecha_Fin = fecha_Fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdLicencias() {
        return idLicencias;
    }

    public void setIdLicencias(int idLicencias) {
        this.idLicencias = idLicencias;
    }

    public int getIdempleados() {
        return idempleados;
    }

    public void setIdempleados(int idempleados) {
        this.idempleados = idempleados;
    }

    //------------------------------------------- Leer Licencias --------------------------------------------------
    public static ObservableList<LeerLicencia> listaLiencia() {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerLicencia> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, empleados.telefono, fecha_Inicio, fecha_Fin, tipo_licencias.descripcion, idLicencias, empleados.idempleados FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND licencias.estadoLicencia = ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()) {
                lista.add(new LeerLicencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"),  rs.getString("telefono"), rs.getDate("fecha_Inicio"), rs.getDate("fecha_Fin"), rs.getString("descripcion"), rs.getInt("idLicencias"), rs.getInt("idempleados")));
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

}
