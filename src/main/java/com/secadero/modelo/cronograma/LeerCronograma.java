package com.secadero.modelo.cronograma;

import com.secadero.conexion.Conexion;
import com.secadero.modelo.empleados.LeerEmpleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.sql.*;
import java.util.Date;

public class LeerCronograma {
    private String nombre;
    private String apellido;
    private int legajo;
    private String telefono;
    private int idempleados;

    private Date fecha;
    private String turno;
    private String horario_entrada;
    private String horario_salida;
    private int idCronograma;

    public LeerCronograma(){}

    public LeerCronograma(Date fecha, String turno, String horario_entrada, String horario_salida, int idCronograma) {
        this.fecha = fecha;
        this.turno = turno;
        this.horario_entrada = horario_entrada;
        this.horario_salida = horario_salida;
        this.idCronograma = idCronograma;
    }

    public LeerCronograma(String nombre, String apellido, int legajo, String telefono, Date fecha, String turno, String horario_entrada, String horario_salida, int idempleados, int idCronograma) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.telefono = telefono;
        this.fecha = fecha;
        this.turno = turno;
        this.horario_entrada = horario_entrada;
        this.horario_salida = horario_salida;
        this.idempleados = idempleados;
        this.idCronograma = idCronograma;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHorario_entrada() {
        return horario_entrada;
    }

    public void setHorario_entrada(String horario_entrada) {
        this.horario_entrada = horario_entrada;
    }

    public String getHorario_salida() {
        return horario_salida;
    }

    public void setHorario_salida(String horario_salida) {
        this.horario_salida = horario_salida;
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(int idCronograma) {
        this.idCronograma = idCronograma;
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

    public int getIdempleados() {
        return idempleados;
    }

    public void setIdempleados(int idempleados) {
        this.idempleados = idempleados;
    }

    //------------------------------------------- Leer Cronograma --------------------------------------------------
    public static ObservableList<LeerCronograma> listaCronograma(Label labIDEmpleadoLista){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();
        String hora_entrada;
        String hora_salida;

        try {
            pstm = con.prepareStatement("SELECT fecha, turno, horario_entrada, horario_salida, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND cronograma.estadoCronograma = ? AND empleado_cronograma.idEmpleadoFK = ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Disponible");
            pstm.setString(3, "Vigente");
            pstm.setInt(4, Integer.parseInt(labIDEmpleadoLista.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                hora_entrada = String.valueOf(rs.getTime("horario_entrada")).substring(0, 5);
                hora_salida = String.valueOf(rs.getTime("horario_salida")).substring(0, 5);

                lista.add(new LeerCronograma(rs.getDate("fecha"), rs.getString("turno"), hora_entrada, hora_salida, rs.getInt("idCronograma")));
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

    //-------------------------------------- Leer Cronograma Comprobación ----------------------------------------------
    public void listaCronogramaComprobacion(Label labIDEmpleadoLista, Label labIDCronogramaLista, Label labResultadoID){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int cont = 0;

        try {
            pstm = con.prepareStatement("SELECT idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND cronograma.estadoCronograma = ? AND empleado_cronograma.idEmpleadoFK = ? AND cronograma.idCronograma = ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            pstm.setInt(3, Integer.parseInt(labIDEmpleadoLista.getText()));
            pstm.setInt(4, Integer.parseInt(labIDCronogramaLista.getText()));
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

    //------------------------------------------- Buscar Cronograma --------------------------------------------------
    public static ObservableList<LeerCronograma> buscarFechaCronograma(Label labIDEmpleadoLista, DatePicker dpBuscarFecha) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();
        String hora_entrada;
        String hora_salida;

        try {
            pstm = con.prepareStatement("SELECT fecha, turno, horario_entrada, horario_salida, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND empleado_cronograma.idEmpleadoFK = ? AND cronograma.fecha = ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Disponible");
            pstm.setInt(3, Integer.parseInt(labIDEmpleadoLista.getText()));
            pstm.setDate(4, java.sql.Date.valueOf(dpBuscarFecha.getEditor().getText()));
            rs = pstm.executeQuery();

            while (rs.next()) {
                hora_entrada = String.valueOf(rs.getTime("horario_entrada")).substring(0, 5);
                hora_salida = String.valueOf(rs.getTime("horario_salida")).substring(0, 5);

                lista.add(new LeerCronograma(rs.getDate("fecha"), rs.getString("turno"), hora_entrada, hora_salida, rs.getInt("idCronograma")));
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

    //------------------------------------------- Filtro Cronograma --------------------------------------------------
    public static ObservableList<LeerCronograma> filtroCronograma(ComboBox<String> cbTiposFiltrosCronograma, Label labIDEmpleadoLista) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();
        String hora_entrada;
        String hora_salida;
        String dato = cbTiposFiltrosCronograma.getSelectionModel().getSelectedItem().toLowerCase();

        if(dato.equals("fecha")){
            try {
                pstm = con.prepareStatement("SELECT fecha, turno, horario_entrada, horario_salida, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND empleado_cronograma.idEmpleadoFK = ? ORDER BY " + dato + " DESC");
                pstm.setString(1, "Vigente");
                pstm.setString(2, "Disponible");
                pstm.setInt(3, Integer.parseInt(labIDEmpleadoLista.getText()));
                rs = pstm.executeQuery();

                while (rs.next()) {
                    hora_entrada = String.valueOf(rs.getTime("horario_entrada")).substring(0, 5);
                    hora_salida = String.valueOf(rs.getTime("horario_salida")).substring(0, 5);

                    lista.add(new LeerCronograma(rs.getDate("fecha"), rs.getString("turno"), hora_entrada, hora_salida, rs.getInt("idCronograma")));
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
        } else {
            try {
                pstm = con.prepareStatement("SELECT fecha, turno, horario_entrada, horario_salida, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND empleado_cronograma.idEmpleadoFK = ? ORDER BY " + dato + " ASC");
                pstm.setString(1, "Vigente");
                pstm.setString(2, "Disponible");
                pstm.setInt(3, Integer.parseInt(labIDEmpleadoLista.getText()));
                rs = pstm.executeQuery();

                while (rs.next()) {
                    hora_entrada = String.valueOf(rs.getTime("horario_entrada")).substring(0, 5);
                    hora_salida = String.valueOf(rs.getTime("horario_salida")).substring(0, 5);

                    lista.add(new LeerCronograma(rs.getDate("fecha"), rs.getString("turno"), hora_entrada, hora_salida, rs.getInt("idCronograma")));
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

    //-------------------------------------------Leer Cronograma Calendario --------------------------------------------------
    public static ObservableList<LeerCronograma> listaEmpleadoCalendario(DatePicker dpFechaCalendario) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, telefono, fecha, turno, horario_entrada, horario_salida, idempleados, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND cronograma.estadoCronograma = ? AND cronograma.fecha = ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            pstm.setString(3, dpFechaCalendario.getEditor().getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerCronograma(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fecha"), rs.getString("turno"), rs.getString("horario_entrada"), rs.getString("horario_salida"), rs.getInt("idempleados"), rs.getInt("idCronograma")));
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

    //---------------------------------------- Buscar Cronograma Calendario ----------------------------------------------
    public static ObservableList<LeerCronograma> buscarFechaCalendario(DatePicker dpFechaCalendario) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, telefono, fecha, turno, horario_entrada, horario_salida, idempleados, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND cronograma.estadoCronograma = ? AND cronograma.fecha = ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            pstm.setString(3, dpFechaCalendario.getEditor().getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerCronograma(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fecha"), rs.getString("turno"), rs.getString("horario_entrada"), rs.getString("horario_salida"), rs.getInt("idempleados"), rs.getInt("idCronograma")));
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

    //---------------------------------------- Buscar Legajo Calendario ----------------------------------------------
    public static ObservableList<LeerCronograma> buscarLegajoCalendario(TextField textBuscarLegajoEmpleadoCalendario, DatePicker dpFechaCalendario) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, telefono, fecha, turno, horario_entrada, horario_salida, idempleados, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND cronograma.estadoCronograma = ? AND cronograma.fecha = ? AND empleados.legajo LIKE ?");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            pstm.setString(3, dpFechaCalendario.getEditor().getText());
            pstm.setString(4, textBuscarLegajoEmpleadoCalendario.getText()  + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new LeerCronograma(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("telefono"), rs.getDate("fecha"), rs.getString("turno"), rs.getString("horario_entrada"), rs.getString("horario_salida"), rs.getInt("idempleados"), rs.getInt("idCronograma")));
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
