package com.secadero.modelo.empleados;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.sql.*;

public class LeerEmpleado {
    private int idempleados;
    private String nombre;
    private String apellido;
    private int legajo;
    private int dni;
    private String telefono;
    private String fechaIngreso;
    private String idAreaFK;
    private String idPuestoFK;
    private String email;

    public LeerEmpleado(){}

    public LeerEmpleado(String nombre, String apellido, int legajo, int dni, String telefono, String fechaIngreso, String idAreaFK, String idPuestoFK, int idempleados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaIngreso = fechaIngreso;
        this.idAreaFK = idAreaFK;
        this.idPuestoFK = idPuestoFK;
        this.idempleados = idempleados;
    }

    public LeerEmpleado(String nombre, String apellido, int legajo, int dni, String telefono, int idempleados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.dni = dni;
        this.telefono = telefono;
        this.idempleados = idempleados;
    }

    public LeerEmpleado(String nombre, String apellido, int legajo, int dni, int idempleados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.dni = dni;
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

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    ///////////////////////////////////////////////// EMPLEADOS ///////////////////////////////////////////////////
    //------------------------------------------- Leer Empleados --------------------------------------------------
    public static ObservableList<LeerEmpleado> listaEmpleados(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni,  telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE estadoEmpleado = ? ORDER BY legajo ASC");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                String fechaIngreso = rs.getString("fechaIngreso");
                String fechaIngresoAnio = fechaIngreso.substring(0, 4);
                String fechaIngresoMes = fechaIngreso.substring(5, 7);
                String fechaIngresoDia = fechaIngreso.substring(8, 10);
                String fechaIngresoModificada = (fechaIngresoDia + "-" + fechaIngresoMes + "-" + fechaIngresoAnio);

                lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), fechaIngresoModificada, rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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
    public void listaEmpleadoSeleccionadoM(TextField textNombreModificar, TextField textApellidoModificar, TextField textLegajoModificar, TextField textDNIModificar, TextField textTelefonoModificar, TextField textDireccionModificar, TextField textEmailModificar, ComboBox<String> cbGeneroModificar, ComboBox<String> cbEstadoCivilModificar, DatePicker dpFechaNaciminetoModificar, ComboBox<String> cbGrupoSanguineoModificar, DatePicker dpFechaIngresoModificar, ComboBox<String> cbAreaModificar, ComboBox<String> cbPuestoModificar, Label labIDModificar, Label labInformacionModificarLegajo, Label labInformacionModificarDNI){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        SimpleDateFormat formatoLatino = new SimpleDateFormat("dd/MM/yyyy");

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, direccion, email, genero.descripcion, estado_civil.descripcion, fechaNacimiento, grupo_sanguineo.descripcion, fechaIngreso, area.descripcion, puesto.descripcion FROM empleados INNER JOIN genero ON empleados.idGeneroFK = genero.idgenero INNER JOIN estado_civil ON empleados.idEstadoCivilFK = estado_civil.idestadoCivil INNER JOIN grupo_sanguineo ON empleados.idGrupoSanguineoFK = grupo_sanguineo.idgrupoSanguineo INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE idempleados = ?");
            pstm.setString(1, labIDModificar.getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                String fechaNacimiento = rs.getString("fechaNacimiento");
                String fechaNacimientoAnio = fechaNacimiento.substring(0, 4);
                String fechaNacimientoMes = fechaNacimiento.substring(5, 7);
                String fechaNacimientoDia = fechaNacimiento.substring(8, 10);
                String fechaNacimientoModificada = (fechaNacimientoDia + "-" + fechaNacimientoMes + "-" + fechaNacimientoAnio);
                String fechaIngreso = rs.getString("fechaIngreso");
                String fechaIngresoAnio = fechaIngreso.substring(0, 4);
                String fechaIngresoMes = fechaIngreso.substring(5, 7);
                String fechaIngresoDia = fechaIngreso.substring(8, 10);
                String fechaIngresoModificada = (fechaIngresoDia + "-" + fechaIngresoMes + "-" + fechaIngresoAnio);

                textNombreModificar.setText(rs.getString("nombre"));
                textApellidoModificar.setText(rs.getString("apellido"));
                textLegajoModificar.setText(String.valueOf(rs.getInt("legajo")));
                labInformacionModificarLegajo.setText(String.valueOf(rs.getInt("legajo")));
                textDNIModificar.setText(String.valueOf(rs.getInt("dni")));
                labInformacionModificarDNI.setText(String.valueOf(rs.getInt("dni")));
                textTelefonoModificar.setText(rs.getString("telefono"));
                textDireccionModificar.setText(rs.getString("direccion"));
                textEmailModificar.setText(rs.getString("email"));
                cbGeneroModificar.getSelectionModel().select(rs.getString("genero.descripcion"));
                cbEstadoCivilModificar.getSelectionModel().select(rs.getString("estado_civil.descripcion"));
                dpFechaNaciminetoModificar.getEditor().setText(fechaNacimientoModificada);
                cbGrupoSanguineoModificar.getSelectionModel().select(rs.getString("grupo_sanguineo.descripcion"));
                dpFechaIngresoModificar.getEditor().setText(fechaIngresoModificada);
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
    public void listaEmpleadoSeleccionadoE(TextField textNombreEliminar, TextField textApellidoEliminar, TextField textLegajoEliminar, TextField textDNIEliminar, TextField textTelefonoEliminar, TextField textDireccionEliminar, TextField textEmailEliminar, ComboBox<String> cbGeneroEliminar, ComboBox<String> cbEstadoCivilEliminar, DatePicker dpFechaNaciminetoEliminar, ComboBox<String> cbGrupoSanguineoEliminar, DatePicker dpFechaIngresoEliminar, ComboBox<String> cbAreaEliminar, ComboBox<String> cbPuestoEliminar, Label labIDEliminar){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, direccion, email, genero.descripcion, estado_civil.descripcion, fechaNacimiento, grupo_sanguineo.descripcion, fechaIngreso, area.descripcion, puesto.descripcion FROM empleados INNER JOIN genero ON empleados.idGeneroFK = genero.idgenero INNER JOIN estado_civil ON empleados.idEstadoCivilFK = estado_civil.idestadoCivil INNER JOIN grupo_sanguineo ON empleados.idGrupoSanguineoFK = grupo_sanguineo.idgrupoSanguineo INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE idempleados = ?");
            pstm.setString(1, labIDEliminar.getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                String fechaNacimiento = rs.getString("fechaNacimiento");
                String fechaNacimientoAnio = fechaNacimiento.substring(0, 4);
                String fechaNacimientoMes = fechaNacimiento.substring(5, 7);
                String fechaNacimientoDia = fechaNacimiento.substring(8, 10);
                String fechaNacimientoModificada = (fechaNacimientoDia + "-" + fechaNacimientoMes + "-" + fechaNacimientoAnio);
                String fechaIngreso = rs.getString("fechaIngreso");
                String fechaIngresoAnio = fechaIngreso.substring(0, 4);
                String fechaIngresoMes = fechaIngreso.substring(5, 7);
                String fechaIngresoDia = fechaIngreso.substring(8, 10);
                String fechaIngresoModificada = (fechaIngresoDia + "-" + fechaIngresoMes + "-" + fechaIngresoAnio);

                textNombreEliminar.setText(rs.getString("nombre"));
                textApellidoEliminar.setText(rs.getString("apellido"));
                textLegajoEliminar.setText(String.valueOf(rs.getInt("legajo")));
                textDNIEliminar.setText(String.valueOf(rs.getInt("dni")));
                textTelefonoEliminar.setText(rs.getString("telefono"));
                textDireccionEliminar.setText(rs.getString("direccion"));
                textEmailEliminar.setText(rs.getString("email"));
                cbGeneroEliminar.getSelectionModel().select(rs.getString("genero.descripcion"));
                cbEstadoCivilEliminar.getSelectionModel().select(rs.getString("estado_civil.descripcion"));
                dpFechaNaciminetoEliminar.getEditor().setText(fechaNacimientoModificada);
                cbGrupoSanguineoEliminar.getSelectionModel().select(rs.getString("grupo_sanguineo.descripcion"));
                dpFechaIngresoEliminar.getEditor().setText(fechaIngresoModificada);
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
    public static ObservableList<LeerEmpleado> buscarEmpleado(TextField textBuscarEmpleado, ComboBox<String> cbBuscarEmpleado){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();
        String dato = cbBuscarEmpleado.getSelectionModel().getSelectedItem().toLowerCase();;

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND empleados." + dato +" LIKE ? ORDER BY legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, textBuscarEmpleado.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                String fechaIngreso = rs.getString("fechaIngreso");
                String fechaIngresoAnio = fechaIngreso.substring(0, 4);
                String fechaIngresoMes = fechaIngreso.substring(5, 7);
                String fechaIngresoDia = fechaIngreso.substring(8, 10);
                String fechaIngresoModificada = (fechaIngresoDia + "-" + fechaIngresoMes + "-" + fechaIngresoAnio);

                listaBuscar.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), fechaIngresoModificada, rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? ORDER BY " + dato + " ASC");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                String fechaIngreso = rs.getString("fechaIngreso");
                String fechaIngresoAnio = fechaIngreso.substring(0, 4);
                String fechaIngresoMes = fechaIngreso.substring(5, 7);
                String fechaIngresoDia = fechaIngreso.substring(8, 10);
                String fechaIngresoModificada = (fechaIngresoDia + "-" + fechaIngresoMes + "-" + fechaIngresoAnio);

                listaFiltro.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), fechaIngresoModificada, rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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

    //---------------------------------------- Filtro Empleado (Búsqueda) ----------------------------------------------
    public static ObservableList<LeerEmpleado> filtroEmpleadoBusqueda(ComboBox<String> cbTiposfiltrosEmpleados, TextField textBuscarEmpleado){
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
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND empleados.legajo LIKE ? ORDER BY " + dato + " ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, textBuscarEmpleado.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                String fechaIngreso = rs.getString("fechaIngreso");
                String fechaIngresoAnio = fechaIngreso.substring(0, 4);
                String fechaIngresoMes = fechaIngreso.substring(5, 7);
                String fechaIngresoDia = fechaIngreso.substring(8, 10);
                String fechaIngresoModificada = (fechaIngresoDia + "-" + fechaIngresoMes + "-" + fechaIngresoAnio);

                listaFiltro.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), fechaIngresoModificada, rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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

    //////////////////////////////////////////////// TODOS LOS MÓDULOS ///////////////////////////////////////////////////
    //-------------------------------------------- Leer Empleado en General -------------------------------------------
    public static ObservableList<LeerEmpleado> listaEmpleadoGeneral(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, idempleados FROM empleados  WHERE estadoEmpleado = ? ORDER BY legajo ASC");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), Integer.parseInt(rs.getString("idempleados"))));
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

    //------------------------------------------- Buscar Empleado General ------------------------------------------------
    public static ObservableList<LeerEmpleado> buscarEmpleadoGeneral(TextField textBuscarLegajoEmpleado, ComboBox<String> cbBuscarEmpleadoLista){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();
        String dato = cbBuscarEmpleadoLista.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND empleados."+ dato +" LIKE ? ORDER BY legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, textBuscarLegajoEmpleado.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaBuscar.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), Integer.parseInt(rs.getString("idempleados"))));
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

    //--------------------------------------- Buscar Empleado General (Crear) ---------------------------------------------
    public static ObservableList<LeerEmpleado> buscarEmpleadoGeneralCrear(TextField textBuscarLegajoEmpleadoCrear, ComboBox<String> cbBuscarEmpleado){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();
        String dato = cbBuscarEmpleado.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND empleados."+ dato +" LIKE ? ORDER BY legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, textBuscarLegajoEmpleadoCrear.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaBuscar.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), Integer.parseInt(rs.getString("idempleados"))));
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

    ///////////////////////////////////////////////// CRONOGRAMA ///////////////////////////////////////////////////
    //------------------------------------- Leer Empleado Disponibles en Cronograma ---------------------------------
    public static ObservableList<LeerEmpleado> listaEmpleadoDisponible(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, idempleados FROM empleados  WHERE estadoEmpleado = ? AND estado = ? ORDER BY legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Disponible");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), Integer.parseInt(rs.getString("idempleados"))));
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

    //----------------------------------------- Buscar Empleado Disponibles en Cronograma ---------------------------------
    public static ObservableList<LeerEmpleado> buscarEmpleadoDisponible(TextField textBuscarLegajoEmpleado, ComboBox<String> cbBuscarEmpleadoDisponibleLista){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();
        String dato = cbBuscarEmpleadoDisponibleLista.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND empleados."+ dato +" LIKE ? ORDER BY legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Disponible");
            pstm.setString(3, textBuscarLegajoEmpleado.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaBuscar.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), Integer.parseInt(rs.getString("idempleados"))));
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

    //------------------------------------------- Reloj de Empleados Estado (Licencia) ---------------------------------
    public static void relojEmpleados() throws ParseException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String IDEmpleado;

        try {
            String consulta = "UPDATE empleados SET estado = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, "Disponible");
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        // Sumar un día a la fecha actual
        calendar.add(Calendar.DATE, 1);
        Date fecha = calendar.getTime();
        String fecha_Actual_Mas1 = (formatoFecha.format(fecha));

        try {
            pstm = con.prepareStatement("SELECT idempleados, fecha_Inicio, fecha_Fin FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND (((licencias.fecha_Inicio <= ?) AND (licencias.fecha_Fin >= ?)) OR ((licencias.fecha_Inicio >= ?) AND (licencias.fecha_Fin <= ?)))");
            pstm.setString(1, "Vigente");
            pstm.setDate(2, java.sql.Date.valueOf(fecha_Actual_Mas1));
            pstm.setDate(3, java.sql.Date.valueOf(fecha_Actual_Mas1));
            pstm.setDate(4, java.sql.Date.valueOf(fecha_Actual_Mas1));
            pstm.setDate(5, java.sql.Date.valueOf(fecha_Actual_Mas1));
            rs = pstm.executeQuery();

            while (rs.next()){
                IDEmpleado = rs.getString("idempleados");

                String consulta2 = "UPDATE empleados SET estado = ? WHERE idempleados = ?";
                pstm = con.prepareStatement(consulta2);
                pstm.setString(1, "Licencia");
                pstm.setInt(2, Integer.parseInt(IDEmpleado));
                pstm.executeUpdate();

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

    //------------------------------------------- Leer Empleado Agregado ---------------------------------
    public static void listaEmpleadoAgregado(Label labNombreEmpleadoAgregadoCrear, Label labApellidoEmpleadoAgregadoCrear, String legajo){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido FROM empleados  WHERE estadoEmpleado = ? AND legajo = ? ORDER BY legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(legajo));
            rs = pstm.executeQuery();

            while (rs.next()){
                labNombreEmpleadoAgregadoCrear.setText(rs.getString("nombre"));
                labApellidoEmpleadoAgregadoCrear.setText(rs.getString("apellido"));
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

    ///////////////////////////////////////////////// INFORMES ///////////////////////////////////////////////////
    //------------------------------------------- Buscar Empleado Informes ------------------------------------------------
    public static ObservableList<LeerEmpleado> buscarEmpleadoInformes(TextField textBuscarLegajoEmpleado, ComboBox<String> cbBuscarEmpleado){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();
        String dato = cbBuscarEmpleado.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, idempleados FROM empleados WHERE empleados.estadoEmpleado = ? AND empleados."+ dato +" LIKE ? ORDER BY legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, textBuscarLegajoEmpleado.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaBuscar.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), Integer.parseInt(rs.getString("idempleados"))));
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

    ///////////////////////////////////////////////// USUARIOS ///////////////////////////////////////////////////
    //------------------------------------ Leer Empleado (Crear Usuario) ---------------------------------------------
    public static ObservableList<LeerEmpleado> leerEmpleadoCrear(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();
        int cont = 0;

        try {
            String consulta = "SELECT * FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND area.descripcion = ? AND empleados.estadoSistema != ? ORDER BY legajo ASC";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Administracion");
            pstm.setString(3, "Permitido");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), Integer.parseInt(rs.getString("idempleados"))));
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

    //------------------------------------ Buscar Empleado (Crear Usuario) ---------------------------------------------
    public static ObservableList<LeerEmpleado> buscarEmpleadoCrear(TextField textBuscarLegajoEmpleadoCrear, ComboBox <String> cbBuscarEmpleado){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();
        String dato = cbBuscarEmpleado.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            String consulta = "SELECT * FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND area.descripcion = ? AND empleados.estadoSistema != ? AND empleados." + dato + " LIKE ? ORDER BY legajo ASC";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Administracion");
            pstm.setString(3, "Permitido");
            pstm.setString(4, textBuscarLegajoEmpleadoCrear.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaBuscar.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), Integer.parseInt(rs.getString("idempleados"))));
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

    /////////////////////////////////////////////////CONSULTAS///////////////////////////////////////////////////////
    public static ObservableList<LeerEmpleado> listaEmpleadoConsulta(ListView<String> listEmpleadosAgregados){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            for (int i = 0; i < listEmpleadosAgregados.getItems().size(); i++) {
                String consulta = "SELECT nombre, apellido, legajo, dni, idempleados FROM empleados WHERE legajo = ? ORDER BY legajo ASC";
                pstm = con.prepareStatement(consulta);
                pstm.setString(1, listEmpleadosAgregados.getItems().get(i));
                rs = pstm.executeQuery();

                while (rs.next()){
                    lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), Integer.parseInt(rs.getString("idempleados"))));
                }
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

    public static ObservableList<LeerEmpleado> listaEmpleadoConsultaModificar(Label labIDEmpleadoModificar){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            String consulta = "SELECT nombre, apellido, legajo, dni, idempleados FROM empleados WHERE idempleados = ? ORDER BY legajo ASC";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, labIDEmpleadoModificar.getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), Integer.parseInt(rs.getString("idempleados"))));
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

    //----------------------------------------- Buscar Empleado (Consulta Licencia) ---------------------------------
    public static ObservableList<LeerEmpleado> buscarEmpleadoConsultaCrear(ListView<String> listEmpleadosAgregados, TextField textBuscarLegajoEmpleadoConsulta, ComboBox<String> cbBuscarEmpleado){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();
        String dato = cbBuscarEmpleado.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            for (int i = 0; i < listEmpleadosAgregados.getItems().size(); i++) {
                String consulta = "SELECT nombre, apellido, legajo, dni, idempleados FROM empleados WHERE legajo = ? AND "+ dato +" LIKE ? ORDER BY legajo ASC";
                pstm = con.prepareStatement(consulta);
                pstm.setString(1, listEmpleadosAgregados.getItems().get(i));
                pstm.setString(2, textBuscarLegajoEmpleadoConsulta.getText() + "%");
                rs = pstm.executeQuery();

                while (rs.next()){
                    listaBuscar.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), Integer.parseInt(rs.getString("idempleados"))));
                }
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
}
