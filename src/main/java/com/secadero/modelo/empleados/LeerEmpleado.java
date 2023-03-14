package com.secadero.modelo.empleados;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.CompactNumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.*;

public class LeerEmpleado {
    private int idempleados;
    private String nombre;
    private String apellido;
    private int legajo;
    private int dni;
    private String telefono;
    private Date fechaIngreso;
    private String idAreaFK;
    private String idPuestoFK;
    private String email;

    public LeerEmpleado(){}

    public LeerEmpleado(String nombre, String apellido, int legajo, int dni, String telefono, Date fechaIngreso, String idAreaFK, String idPuestoFK, int idempleados) {
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    //------------------------------------------- Leer Empleados --------------------------------------------------
    public static ObservableList<LeerEmpleado> listaEmpleados(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni,  telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE estadoEmpleado = ? ORDER BY legajo DESC");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), rs.getDate("fechaIngreso"), rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, direccion, email, genero.descripcion, estado_civil.descripcion, fechaNacimiento, grupo_sanguineo.descripcion, fechaIngreso, area.descripcion, puesto.descripcion FROM empleados INNER JOIN genero ON empleados.idGeneroFK = genero.idgenero INNER JOIN estado_civil ON empleados.idEstadoCivilFK = estado_civil.idestadoCivil INNER JOIN grupo_sanguineo ON empleados.idGrupoSanguineoFK = grupo_sanguineo.idgrupoSanguineo INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE idempleados = ?");
            pstm.setString(1, labIDModificar.getText());
            rs = pstm.executeQuery();

            while (rs.next()){
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
    public void listaEmpleadoSeleccionadoE(TextField textNombreEliminar, TextField textApellidoEliminar, TextField textLegajoEliminar, TextField textDNIEliminar, TextField textTelefonoEliminar, TextField textDireccionEliminar, TextField textEmailEliminar, ComboBox<String> cbGeneroEliminar, ComboBox<String> cbEstadoCivilEliminar, DatePicker dpFechaNaciminetoEliminar, ComboBox<String> cbGrupoSanguineoEliminar, DatePicker dpFechaIngresoEliminar, ComboBox<String> cbAreaEliminar, ComboBox<String> cbPuestoEliminar, Label labIDEliminar){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, direccion, email, genero.descripcion, estado_civil.descripcion, fechaNacimiento, grupo_sanguineo.descripcion, fechaIngreso, area.descripcion, puesto.descripcion FROM empleados INNER JOIN genero ON empleados.idGeneroFK = genero.idgenero INNER JOIN estado_civil ON empleados.idEstadoCivilFK = estado_civil.idestadoCivil INNER JOIN grupo_sanguineo ON empleados.idGrupoSanguineoFK = grupo_sanguineo.idgrupoSanguineo INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE idempleados = ?");
            pstm.setString(1, labIDEliminar.getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                textNombreEliminar.setText(rs.getString("nombre"));
                textApellidoEliminar.setText(rs.getString("apellido"));
                textLegajoEliminar.setText(String.valueOf(rs.getInt("legajo")));
                textDNIEliminar.setText(String.valueOf(rs.getInt("dni")));
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
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND empleados.legajo LIKE ? ORDER BY legajo DESC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, textBuscarEmpleado.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                listaBuscar.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), rs.getDate("fechaIngreso"), rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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
                listaFiltro.add(new LeerEmpleado(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), rs.getString("telefono"), rs.getDate("fechaIngreso"), rs.getString("area.descripcion"), rs.getString("puesto.descripcion"), Integer.parseInt(rs.getString("idempleados"))));
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

    //-------------------------------------------- Leer Empleado en General -------------------------------------------
    public static ObservableList<LeerEmpleado> listaEmpleadoGeneral(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, idempleados FROM empleados  WHERE estadoEmpleado = ? ORDER BY legajo DESC");
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
    public static ObservableList<LeerEmpleado> buscarEmpleadoGeneral(TextField textBuscarLegajoEmpleado){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND empleados.legajo LIKE ? ORDER BY legajo DESC");
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

    //------------------------------------------- Filtro Empleado General ------------------------------------------------
    public static ObservableList<LeerEmpleado> filtroEmpleadoGeneral(ComboBox<String> cbTiposFiltrosAusencias){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();
        String dato = cbTiposFiltrosAusencias.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, idempleados FROM empleados WHERE empleados.estadoEmpleado = ? ORDER BY " + dato + " ASC");
            pstm.setString(1, "Vigente");
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

    //------------------------------------------- Leer Empleado Disponibles en Cronograma ---------------------------------
    public static ObservableList<LeerEmpleado> listaEmpleadoDisponible(){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, idempleados FROM empleados  WHERE estadoEmpleado = ? AND estado = ? ORDER BY legajo DESC");
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
    public static ObservableList<LeerEmpleado> buscarEmpleadoDisponible(TextField textBuscarLegajoEmpleado){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, fechaIngreso, area.descripcion, puesto.descripcion, idempleados FROM empleados INNER JOIN area ON empleados.idAreaFK = area.idarea INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND empleados.legajo LIKE ? ORDER BY legajo DESC");
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

    //-------------------------------------- Filtro Empleado Disponibles en Cronograma -----------------------------------
    public static ObservableList<LeerEmpleado> filtroEmpleadoDisponible(ComboBox<String> cbTiposFiltrosAusencias){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();
        String dato = cbTiposFiltrosAusencias.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, idempleados FROM empleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? ORDER BY " + dato + " ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Disponible");
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
        ResultSet rs2 = null;
        String IDEmpleadoGeneral;
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
        Date fechaActualPais = new Date();
        String fecha_Actual = (formatoFecha.format(fechaActualPais));

        try {
            pstm = con.prepareStatement("SELECT idempleados, fecha_Inicio, fecha_Fin FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND licencias.estadoLicencia = ? AND (((licencias.fecha_Inicio <= ?) AND (licencias.fecha_Fin >= ?)) OR ((licencias.fecha_Inicio >= ?) AND (licencias.fecha_Fin <= ?)))");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            pstm.setDate(3, java.sql.Date.valueOf(fecha_Actual));
            pstm.setDate(4, java.sql.Date.valueOf(fecha_Actual));
            pstm.setDate(5, java.sql.Date.valueOf(fecha_Actual));
            pstm.setDate(6, java.sql.Date.valueOf(fecha_Actual));
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
            pstm = con.prepareStatement("SELECT nombre, apellido FROM empleados  WHERE estadoEmpleado = ? AND legajo = ? ORDER BY legajo DESC");
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

    //------------------------------------------- Buscar Empleado Informes ------------------------------------------------
    public static ObservableList<LeerEmpleado> buscarEmpleadoInformes(TextField textBuscarLegajoEmpleado){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerEmpleado> listaBuscar = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, telefono, idempleados FROM empleados WHERE empleados.estadoEmpleado = ? AND empleados.legajo LIKE ? ORDER BY legajo DESC");
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
}
