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
    private String nombre;
    private String apellido;
    private int legajo;
    private int dni;
    private String telefono;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;
    private String email;
    private int idusuarios;

    public LeerUsuario(){}

    public LeerUsuario(String nombre, String apellido, int legajo, int dni, String telefono, String nombreUsuario, String contrasenia, String rol, String email, int idusuarios) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.dni = dni;
        this.telefono = telefono;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.email = email;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }

    //------------------------------------------- Leer Usuarios --------------------------------------------------
    public static ObservableList<LeerUsuario> listaUsuarios(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerUsuario> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM usuarios");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerUsuario(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), rs.getString("nombreUsuario"), rs.getString("contrasenia"), rs.getString("rol"), rs.getString("email"), rs.getInt("idusuarios")));
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
            pstm = con.prepareStatement("SELECT * FROM usuarios WHERE legajo LIKE ?");
            pstm.setString(1, textBuscarUsuario.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaBuscar.add(new LeerUsuario(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), rs.getString("nombreUsuario"), rs.getString("contrasenia"), rs.getString("rol"), rs.getString("email"), rs.getInt("idusuarios")));
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
            pstm = con.prepareStatement("SELECT * FROM usuarios ORDER BY " + dato + " ASC");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaFiltro.add(new LeerUsuario(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), rs.getString("nombreUsuario"), rs.getString("contrasenia"), rs.getString("rol"), rs.getString("email"), rs.getInt("idusuarios")));
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
