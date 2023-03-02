package com.secadero.modelo.cronograma;

import com.secadero.conexion.Conexion;
import com.secadero.modelo.ausencia.LeerAusencia;
import com.secadero.modelo.licencias.LeerLicencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.sql.*;
import java.util.Date;

public class LeerCronograma {
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
}
