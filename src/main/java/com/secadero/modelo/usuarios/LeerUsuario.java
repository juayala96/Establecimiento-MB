package com.secadero.modelo.usuarios;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeerUsuario {
    private int idusuarios;
    private String nombre;
    private String apellido;
    private int legajo;
    private String email;
    private String telefono;
    private String contrasenia;
    private String rol;

    public LeerUsuario(){}

    public LeerUsuario(String nombre, String apellido, int legajo, String email, String telefono, String contrasenia, String rol, int idusuarios) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.idusuarios = idusuarios;
    }

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    //------------------------------------------- Leer Usuarios --------------------------------------------------
    public static ObservableList<LeerUsuario> listaUsuarios(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerUsuario> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM usuarios WHERE estadoUsuario = ?");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerUsuario(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("email"), rs.getString("telefono"), rs.getString("contrasenia"), rs.getString("rol"), rs.getInt("idusuarios")));
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

    //------------------------------------------- Buscar Usuario ------------------------------------------------
    public static ObservableList<LeerUsuario> buscarUsuario(TextField textBuscarUsuario){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerUsuario> listaBuscar = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM usuarios WHERE estadoUsuario = ? AND nombre LIKE ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, textBuscarUsuario.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaBuscar.add(new LeerUsuario(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("email"), rs.getString("telefono"), rs.getString("contrasenia"), rs.getString("rol"), rs.getInt("idusuarios")));
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
        return listaBuscar;
    }

    //------------------------------------------- Filtro Usuario -----------------------------------------------
    public static ObservableList<LeerUsuario> filtroUsuario(ComboBox<String> cbTiposFiltrosUsuarios){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerUsuario> listaFiltro = FXCollections.observableArrayList();
        String dato = cbTiposFiltrosUsuarios.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            pstm = con.prepareStatement("SELECT * FROM usuarios WHERE estadoUsuario = ? ORDER BY " + dato + " ASC");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaFiltro.add(new LeerUsuario(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("email"), rs.getString("telefono"), rs.getString("contrasenia"), rs.getString("rol"), rs.getInt("idusuarios")));
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
        return listaFiltro;
    }
}
