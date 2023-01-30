package com.secadero.modelo.empleados;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class LeerEmpleado {
    private int idempleados;
    private String nombre;
    private String apellido;
    private int legajo;
    private String telefono;
    private Date fechaIngreso;
    private String idAreaFK;
    private String idPuestoFK;

    public LeerEmpleado(){}

    public LeerEmpleado(String nombre, String apellido, int legajo, String telefono, Date fechaIngreso, String idAreaFK, String idPuestoFK, int idempleados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.telefono = telefono;
        this.fechaIngreso = fechaIngreso;
        this.idAreaFK = idAreaFK;
        this.idPuestoFK = idPuestoFK;
        this.idempleados = idempleados;
    }

    public int getIdempleados() {
        return idempleados;
    }

    public void setIdempleados(int idempleados) {
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getIdAreaFK() {
        return idAreaFK;
    }

    public void setIdAreaFK(String idAreaFK) {
        this.idAreaFK = idAreaFK;
    }

    public String getIdPuestoFK() {
        return idPuestoFK;
    }

    public void setIdPuestoFK(String idPuestoFK) {
        this.idPuestoFK = idPuestoFK;
    }

    //------------------------------------------- Leer Empleados --------------------------------------------------
    public static ObservableList<LeerEmpleado> listaEmpleados(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE estadoEmpleado = ?");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fechaIngreso"), rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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
