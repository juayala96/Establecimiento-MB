package com.secadero.modelo.licencias;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private int dias_disponibles;

    public LeerLicencia() {
    }

    public LeerLicencia(int dias_disponibles) {
        this.dias_disponibles = dias_disponibles;
    }

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
                lista.add(new LeerLicencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fecha_Inicio"), rs.getDate("fecha_Fin"), rs.getString("descripcion"), rs.getInt("idLicencias"), rs.getInt("idempleados")));
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

    //------------------------------------------- Leer Licencias --------------------------------------------------
    public static ObservableList<LeerLicencia> buscarLicencia(TextField textBuscarLicenciaLegajoE, DatePicker dpBuscarFechaInicio) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerLicencia> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, empleados.telefono, fecha_Inicio, fecha_Fin, tipo_licencias.descripcion, idLicencias, empleados.idempleados FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND licencias.estadoLicencia = ? AND licencias.fecha_Inicio = ? AND empleados.legajo = ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            pstm.setDate(3, java.sql.Date.valueOf(dpBuscarFechaInicio.getEditor().getText()));
            pstm.setString(4, textBuscarLicenciaLegajoE.getText());
            rs = pstm.executeQuery();

            while (rs.next()) {
                lista.add(new LeerLicencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fecha_Inicio"), rs.getDate("fecha_Fin"), rs.getString("descripcion"), rs.getInt("idLicencias"), rs.getInt("idempleados")));
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

    //------------------------------------------- Filtro Licencias --------------------------------------------------
    public static ObservableList<LeerLicencia> filtroLicencia(ComboBox<String> cbTiposFiltrosLicencia) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerLicencia> lista = FXCollections.observableArrayList();
        String dato = cbTiposFiltrosLicencia.getSelectionModel().getSelectedItem().toLowerCase();
        if (dato.equals("tipo_licencia")) {
            dato = "idTipoLicenciaFK";
        }

        if ((!dato.equals("fecha_inicio")) && (!dato.equals("fecha_fin"))) {
            try {
                pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, empleados.telefono, fecha_Inicio, fecha_Fin, tipo_licencias.descripcion, idLicencias, empleados.idempleados FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND licencias.estadoLicencia = ? ORDER BY " + dato + " ASC");
                pstm.setString(1, "Vigente");
                pstm.setString(2, "Vigente");
                rs = pstm.executeQuery();

                while (rs.next()) {
                    lista.add(new LeerLicencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fecha_Inicio"), rs.getDate("fecha_Fin"), rs.getString("descripcion"), rs.getInt("idLicencias"), rs.getInt("idempleados")));
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
        } else {
            try {
                pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, empleados.telefono, fecha_Inicio, fecha_Fin, tipo_licencias.descripcion, idLicencias, empleados.idempleados FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND licencias.estadoLicencia = ? ORDER BY " + dato + " DESC");
                pstm.setString(1, "Vigente");
                pstm.setString(2, "Vigente");
                rs = pstm.executeQuery();

                while (rs.next()) {
                    lista.add(new LeerLicencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fecha_Inicio"), rs.getDate("fecha_Fin"), rs.getString("descripcion"), rs.getInt("idLicencias"), rs.getInt("idempleados")));
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

    //------------------------------------------- Leer Dias Disponibles --------------------------------------------------
    public static String comboBoxTipoLicencia(String valorActual, Label labIDEmpleadoCrear) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String lista = null;
        int cont = 0;

        try {
            pstm = con.prepareStatement("SELECT licencias.dias_disponibles FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND licencias.estadoLicencia = ? AND empleado_licencia.idEmpleadoFK = ? AND tipo_licencias.descripcion = ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            pstm.setInt(3, Integer.parseInt(labIDEmpleadoCrear.getText()));
            pstm.setString(4, valorActual);
            rs = pstm.executeQuery();

            while (rs.next()) {
                lista = String.valueOf(rs.getInt("dias_disponibles"));
                cont += 1;
            }
            if(cont == 0){
                pstm = con.prepareStatement("SELECT tipo_licencias.dias_predefinidas FROM tipo_licencias WHERE tipo_licencias.descripcion = ?");
                pstm.setString(1, valorActual);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    lista = String.valueOf(rs.getInt("dias_predefinidas"));
                }
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