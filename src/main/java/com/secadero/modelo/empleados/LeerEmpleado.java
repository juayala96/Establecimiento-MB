package com.secadero.modelo.empleados;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LeerEmpleado {
    private int idempleados;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String telefono;
    private int legajo;
    private Date fechaIngreso;
    private String fotografia;
    private String estado;

    public LeerEmpleado(){}

    public LeerEmpleado(String nombre, String apellido, Date fechaNacimiento, String telefono, int legajo, Date fechaIngreso, String fotografia, String estado, int idempleados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.legajo = legajo;
        this.fechaIngreso = fechaIngreso;
        this.fotografia = fotografia;
        this.estado = estado;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //------------------------------------------- Leer Empleados --------------------------------------------------
    public static ObservableList<LeerEmpleado> listaEmpleados(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM empleados WHERE estadoEmpleado = ?");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getDate("fechaNacimiento"), rs.getString("telefono"), rs.getInt("legajo"), rs.getDate("fechaIngreso"), rs.getString("fotografia"), rs.getString("estado"), rs.getInt("idempleados")));
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