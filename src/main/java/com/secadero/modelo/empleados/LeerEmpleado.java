package com.secadero.modelo.empleados;

import com.secadero.conexion.Conexion;
import com.secadero.modelo.usuarios.LeerUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private String email;

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

    public LeerEmpleado(String nombre, String apellido, int legajo, String telefono, String email, int idempleados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.telefono = telefono;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    //---------------------------------- Leer Empleados al seleccionar Modificar ----------------------------------------
    public void listaEmpleadoSeleccionadoM(TextField textNombreModificar, TextField textApellidoModificar, TextField textLegajoModificar, TextField textTelefonoModificar, TextField textDireccionModificar, TextField textEmailModificar, ComboBox<String> cbGeneroModificar, ComboBox<String> cbEstadoCivilModificar, DatePicker dpFechaNaciminetoModificar, ComboBox<String> cbGrupoSanguineoModificar, DatePicker dpFechaIngresoModificar, ComboBox<String> cbAreaModificar, ComboBox<String> cbPuestoModificar, Label labIDModificar, Label labInformacionModificarLegajo){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, telefono, direccion, email, genero.descripcion, estado_civil.descripcion, fechaNacimiento, grupo_sanguineo.descripcion, fechaIngreso, area.descripcion, puesto.descripcion FROM empleados INNER JOIN genero ON empleados.idGeneroFK = genero.idgenero INNER JOIN estado_civil ON empleados.idEstadoCivilFK = estado_civil.idestadoCivil INNER JOIN grupo_sanguineo ON empleados.idGrupoSanguineoFK = grupo_sanguineo.idgrupoSanguineo INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE idempleados = ?");
            pstm.setString(1, labIDModificar.getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                textNombreModificar.setText(rs.getString("nombre"));
                textApellidoModificar.setText(rs.getString("apellido"));
                textLegajoModificar.setText(String.valueOf(rs.getInt("legajo")));
                labInformacionModificarLegajo.setText(String.valueOf(rs.getInt("legajo")));
                textTelefonoModificar.setText(rs.getString("telefono"));
                textDireccionModificar.setText(rs.getString("direccion"));
                textEmailModificar.setText(rs.getString("email"));
                cbGeneroModificar.getSelectionModel().select(rs.getString("genero.descripcion"));
                cbEstadoCivilModificar.getSelectionModel().select(rs.getString("estado_civil.descripcion"));
                dpFechaNaciminetoModificar.getEditor().setText(rs.getString("fechaNacimiento"));
                cbGrupoSanguineoModificar.getSelectionModel().select(rs.getString("grupo_sanguineo.descripcion"));
                dpFechaIngresoModificar.getEditor().setText(rs.getString("fechaIngreso"));
                cbAreaModificar.getSelectionModel().select(rs.getString("area.descripcion"));
                cbPuestoModificar.getSelectionModel().select(rs.getString("puesto.descripcion"));
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

    //---------------------------------- Leer Empleados al seleccionar Eliminar ----------------------------------------
    public void listaEmpleadoSeleccionadoE(TextField textNombreEliminar, TextField textApellidoEliminar, TextField textLegajoEliminar, TextField textTelefonoEliminar, TextField textDireccionEliminar, TextField textEmailEliminar, ComboBox<String> cbGeneroEliminar, ComboBox<String> cbEstadoCivilEliminar, DatePicker dpFechaNaciminetoEliminar, ComboBox<String> cbGrupoSanguineoEliminar, DatePicker dpFechaIngresoEliminar, ComboBox<String> cbAreaEliminar, ComboBox<String> cbPuestoEliminar, Label labIDEliminar){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, telefono, direccion, email, genero.descripcion, estado_civil.descripcion, fechaNacimiento, grupo_sanguineo.descripcion, fechaIngreso, area.descripcion, puesto.descripcion FROM empleados INNER JOIN genero ON empleados.idGeneroFK = genero.idgenero INNER JOIN estado_civil ON empleados.idEstadoCivilFK = estado_civil.idestadoCivil INNER JOIN grupo_sanguineo ON empleados.idGrupoSanguineoFK = grupo_sanguineo.idgrupoSanguineo INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE idempleados = ?");
            pstm.setString(1, labIDEliminar.getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                textNombreEliminar.setText(rs.getString("nombre"));
                textApellidoEliminar.setText(rs.getString("apellido"));
                textLegajoEliminar.setText(String.valueOf(rs.getInt("legajo")));
                textTelefonoEliminar.setText(rs.getString("telefono"));
                textDireccionEliminar.setText(rs.getString("direccion"));
                textEmailEliminar.setText(rs.getString("email"));
                cbGeneroEliminar.getSelectionModel().select(rs.getString("genero.descripcion"));
                cbEstadoCivilEliminar.getSelectionModel().select(rs.getString("estado_civil.descripcion"));
                dpFechaNaciminetoEliminar.getEditor().setText(rs.getString("fechaNacimiento"));
                cbGrupoSanguineoEliminar.getSelectionModel().select(rs.getString("grupo_sanguineo.descripcion"));
                dpFechaIngresoEliminar.getEditor().setText(rs.getString("fechaIngreso"));
                cbAreaEliminar.getSelectionModel().select(rs.getString("area.descripcion"));
                cbPuestoEliminar.getSelectionModel().select(rs.getString("puesto.descripcion"));
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

    //------------------------------------------- Buscar Empleado ------------------------------------------------
    public static ObservableList<LeerEmpleado> buscarEmpleado(TextField textBuscarEmpleado){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND empleados.nombre LIKE ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, textBuscarEmpleado.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaBuscar.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fechaIngreso"), rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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

    //------------------------------------------- Filtro Empleado ------------------------------------------------
    public static ObservableList<LeerEmpleado> filtroEmpleado(ComboBox<String> cbTiposfiltrosEmpleados){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaFiltro = FXCollections.observableArrayList();
        String dato = cbTiposfiltrosEmpleados.getSelectionModel().getSelectedItem().toLowerCase();
        if(dato.equals("area")){
            dato = "idAreaFK";
        } else if(dato.equals("puesto"))
            dato = "idPuestoFK";

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? ORDER BY " + dato + " ASC");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaFiltro.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fechaIngreso"), rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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

    //------------------------------------------- Leer Empleado en Ausencia ------------------------------------------------
    public static ObservableList<LeerEmpleado> listaEmpleadoAusencia(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, telefono, email, idempleados FROM empleados  WHERE estadoEmpleado = ?");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getString("email"), Integer.parseInt(rs.getString("idempleados"))));
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
