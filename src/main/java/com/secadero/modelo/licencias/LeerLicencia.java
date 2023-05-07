package com.secadero.modelo.licencias;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.sql.*;

public class LeerLicencia {

    private String fecha_Inicio;
    private String fecha_Fin;
    private String descripcion;
    private int idLicencias;
    private int dias_disponibles;
    private String descripcionLicencia;

    public LeerLicencia() {
    }

    public LeerLicencia(int dias_disponibles) {
        this.dias_disponibles = dias_disponibles;
    }

    public LeerLicencia(String fecha_Inicio, String fecha_Fin, String descripcion, int idLicencias, String descripcionLicencia) {
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_Fin = fecha_Fin;
        this.descripcion = descripcion;
        this.idLicencias = idLicencias;
        this.descripcionLicencia = descripcionLicencia;
    }

    public String getFecha_Inicio() {
        return fecha_Inicio;
    }

    public void setFecha_Inicio(String fecha_Inicio) {
        this.fecha_Inicio = fecha_Inicio;
    }

    public String getFecha_Fin() {
        return fecha_Fin;
    }

    public void setFecha_Fin(String fecha_Fin) {
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

    public int getDias_disponibles() {
        return dias_disponibles;
    }

    public void setDias_disponibles(int dias_disponibles) {
        this.dias_disponibles = dias_disponibles;
    }

    public String getDescripcionLicencia() {
        return descripcionLicencia;
    }

    public void setDescripcionLicencia(String descripcionLicencia) {
        this.descripcionLicencia = descripcionLicencia;
    }

    //------------------------------------------- Leer Licencias --------------------------------------------------
    public static ObservableList<LeerLicencia> listaLicencia(Label labIDEmpleadoLista) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerLicencia> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT fecha_Inicio, fecha_Fin, tipo_licencias.descripcion, idLicencias, descripcionLicencia FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleado_licencia.idEmpleadoFK = ?");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(labIDEmpleadoLista.getText()));
            rs = pstm.executeQuery();

            while (rs.next()) {
                String fechaInicio = rs.getString("fecha_Inicio");
                String fechaAnio = fechaInicio.substring(0, 4);
                String fechaMes = fechaInicio.substring(5, 7);
                String fechaDia = fechaInicio.substring(8, 10);
                String fechaModificadaInicio = (fechaDia + "-" + fechaMes + "-" + fechaAnio);
                String fechaFin = rs.getString("fecha_Fin");
                String fechaAnio2 = fechaFin.substring(0, 4);
                String fechaMes2 = fechaFin.substring(5, 7);
                String fechaDia2 = fechaFin.substring(8, 10);
                String fechaModificadaFin = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);

                lista.add(new LeerLicencia(fechaModificadaInicio, fechaModificadaFin, rs.getString("descripcion"), rs.getInt("idLicencias"), rs.getString("descripcionLicencia")));
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

    //-------------------------------------- Leer Licencias Comprobación ----------------------------------------------
    public void listaLicenciaComprobacion(Label labIDEmpleadoLista, Label labIDLicenciaLista, Label labResultadoID){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int cont = 0;

        try {
            pstm = con.prepareStatement("SELECT idLicencias FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleados.idempleados = empleado_licencia.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND empleado_licencia.idEmpleadoFK = ? AND licencias.idLicencias = ?");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(labIDEmpleadoLista.getText()));
            pstm.setInt(3, Integer.parseInt(labIDLicenciaLista.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                labResultadoID.setText("YES");
                cont +=1;
            }

            if(cont == 0){
                labResultadoID.setText("NO");
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

    //------------------------------------------- Leer Dias Disponibles --------------------------------------------------
    public static String comboBoxTipoLicencia(String valorActual, Label labIDEmpleadoCrear, DatePicker dpFechaInicioCrear) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String lista = null;
        int cont = 0;
        int anioGeneral;
        String respuestaAnio = "";
        String dato;

        String fecha = dpFechaInicioCrear.getEditor().getText();
        int anio2 = Integer.parseInt(fecha.substring(6, 10));

        try {
            String consulta2 = "SELECT YEAR(fecha_Inicio) AS anio FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE empleados.estadoEmpleado = ? AND idEmpleadoFK = ? AND tipo_licencias.descripcion = ? AND YEAR(fecha_Inicio) = ?";
            pstm = con.prepareStatement(consulta2);
            pstm.setString(1, "Vigente");
            pstm.setString(2, labIDEmpleadoCrear.getText());
            pstm.setString(3, valorActual);
            pstm.setInt(4, anio2);
            rs = pstm.executeQuery();
            while (rs.next()) {
                anioGeneral = rs.getInt("anio");
                if(anioGeneral == anio2){
                    respuestaAnio = "YES";
                } else {
                    respuestaAnio = "NO";
                }
            }
        } catch (Exception e1) {
            System.err.println("Error: " + e1.getMessage());
        }

        if(respuestaAnio.equals("YES")){
            try {
                pstm = con.prepareStatement("SELECT MIN(licencias.dias_disponibles) AS dias_disponibles, YEAR(fecha_Inicio) AS anio FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleado_licencia.idEmpleadoFK = ? AND tipo_licencias.descripcion = ? AND YEAR(fecha_Inicio) = ?");
                pstm.setString(1, "Vigente");
                pstm.setInt(2, Integer.parseInt(labIDEmpleadoCrear.getText()));
                pstm.setString(3, valorActual);
                pstm.setInt(4, anio2);
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
            }
        } else {
            try {
                pstm = con.prepareStatement("SELECT tipo_licencias.dias_predefinidas FROM tipo_licencias WHERE tipo_licencias.descripcion = ?");
                pstm.setString(1, valorActual);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    lista = String.valueOf(rs.getInt("dias_predefinidas"));
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
        }
        return lista;
    }


    //------------------------------------------- Buscar Licencias --------------------------------------------------
    public static ObservableList<LeerLicencia> buscarLicenciaFechaInicio(Label labIDEmpleadoLista, DatePicker dpBuscarFechaInicio) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerLicencia> lista = FXCollections.observableArrayList();

        try {
            String fechaBuscar = dpBuscarFechaInicio.getEditor().getText();
            String fechaAnio = fechaBuscar.substring(6, 10);
            String fechaMes = fechaBuscar.substring(3, 5);
            String fechaDia = fechaBuscar.substring(0, 2);
            String fechaBusqueda = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

            pstm = con.prepareStatement("SELECT fecha_Inicio, fecha_Fin, tipo_licencias.descripcion, idLicencias, descripcionLicencia FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND licencias.fecha_Inicio = ? AND empleados.idempleados = ? ORDER BY fecha_Inicio DESC");
            pstm.setString(1, "Vigente");
            pstm.setDate(2, Date.valueOf(fechaBusqueda));
            pstm.setString(3, labIDEmpleadoLista.getText());
            rs = pstm.executeQuery();

            while (rs.next()) {
                String fechaInicio = rs.getString("fecha_Inicio");
                String fechaAnio2 = fechaInicio.substring(0, 4);
                String fechaMes2 = fechaInicio.substring(5, 7);
                String fechaDia2 = fechaInicio.substring(8, 10);
                String fechaModificadaInicio = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                String fechaFin = rs.getString("fecha_Fin");
                String fechaAnio3 = fechaFin.substring(0, 4);
                String fechaMes3 = fechaFin.substring(5, 7);
                String fechaDia3 = fechaFin.substring(8, 10);
                String fechaModificadaFin = (fechaDia3 + "-" + fechaMes3 + "-" + fechaAnio3);

                lista.add(new LeerLicencia(fechaModificadaInicio, fechaModificadaFin, rs.getString("descripcion"), rs.getInt("idLicencias"), rs.getString("descripcionLicencia")));
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
    public static ObservableList<LeerLicencia> filtroLicencia(ComboBox<String> cbTiposFiltrosLicencia, Label labIDEmpleadoLista) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerLicencia> lista = FXCollections.observableArrayList();
        String dato = cbTiposFiltrosLicencia.getSelectionModel().getSelectedItem().toLowerCase();
        if (dato.equals("tipo_licencia")) {
            dato = "idTipoLicenciaFK";
        }
        if(labIDEmpleadoLista.getText().equals("")){
            labIDEmpleadoLista.setText("0");
        }

        if ((!dato.equals("fecha_inicio")) && (!dato.equals("fecha_fin"))) {
            try {
                pstm = con.prepareStatement("SELECT fecha_Inicio, fecha_Fin, tipo_licencias.descripcion, idLicencias, descripcionLicencia FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.idempleados = ? ORDER BY " + dato + " ASC");
                pstm.setString(1, "Vigente");
                pstm.setInt(2, Integer.parseInt(labIDEmpleadoLista.getText()));
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String fechaInicio = rs.getString("fecha_Inicio");
                    String fechaAnio = fechaInicio.substring(0, 4);
                    String fechaMes = fechaInicio.substring(5, 7);
                    String fechaDia = fechaInicio.substring(8, 10);
                    String fechaModificadaInicio = (fechaDia + "-" + fechaMes + "-" + fechaAnio);
                    String fechaFin = rs.getString("fecha_Fin");
                    String fechaAnio2 = fechaFin.substring(0, 4);
                    String fechaMes2 = fechaFin.substring(5, 7);
                    String fechaDia2 = fechaFin.substring(8, 10);
                    String fechaModificadaFin = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);

                    lista.add(new LeerLicencia(fechaModificadaInicio, fechaModificadaFin, rs.getString("descripcion"), rs.getInt("idLicencias"), rs.getString("descripcionLicencia")));
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
                pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, empleados.telefono, fecha_Inicio, fecha_Fin, tipo_licencias.descripcion, idLicencias, empleados.idempleados, descripcionLicencia FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.idempleados = ? ORDER BY " + dato + " DESC");
                pstm.setString(1, "Vigente");
                pstm.setInt(2, Integer.parseInt(labIDEmpleadoLista.getText()));
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String fechaInicio = rs.getString("fecha_Inicio");
                    String fechaAnio = fechaInicio.substring(0, 4);
                    String fechaMes = fechaInicio.substring(5, 7);
                    String fechaDia = fechaInicio.substring(8, 10);
                    String fechaModificadaInicio = (fechaDia + "-" + fechaMes + "-" + fechaAnio);
                    String fechaFin = rs.getString("fecha_Fin");
                    String fechaAnio2 = fechaFin.substring(0, 4);
                    String fechaMes2 = fechaFin.substring(5, 7);
                    String fechaDia2 = fechaFin.substring(8, 10);
                    String fechaModificadaFin = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);

                    lista.add(new LeerLicencia(fechaModificadaInicio, fechaModificadaFin, rs.getString("descripcion"), rs.getInt("idLicencias"), rs.getString("descripcionLicencia")));
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
}