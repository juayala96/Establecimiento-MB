package com.secadero.modelo.ausencia;

import com.secadero.conexion.Conexion;
import com.secadero.modelo.cronograma.LeerCronograma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class ConsultaAsistencia {
    private String nombre;
    private String apellido;
    private int legajo;
    private String turno;
    private String fecha;
    private String horaEntrada;
    private String horaSalida;
    private int idCronograma;
    private int idEmpleado;

    public ConsultaAsistencia() {
    }

    public ConsultaAsistencia(String nombre, String apellido, int legajo, String turno, String fecha, String horaEntrada, String horaSalida, int idCronograma) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.turno = turno;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.idCronograma = idCronograma;
    }

    public ConsultaAsistencia(String nombre, String apellido, int legajo, String fecha, String horaEntrada, int idEmpleado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.idEmpleado = idEmpleado;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(int idCronograma) {
        this.idCronograma = idCronograma;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    //-------------------------------- Leer Cronograma Consulta Asistencia (CE) ----------------------------------------
    public static ObservableList<ConsultaAsistencia> listaCronogramaCE(DatePicker dpBuscarFechaAsistencia, ComboBox<String> cbTurnoAsistencia) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<ConsultaAsistencia> lista = FXCollections.observableArrayList();
        String dato = cbTurnoAsistencia.getSelectionModel().getSelectedItem().toLowerCase();
        String hora_entrada;
        String hora_salida;

        String fechaBuscar = dpBuscarFechaAsistencia.getEditor().getText();
        String fechaAnio = fechaBuscar.substring(6, 10);
        String fechaMes = fechaBuscar.substring(3, 5);
        String fechaDia = fechaBuscar.substring(0, 2);
        String fechaBusqueda = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        try {
            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, turno, fecha, horario_entrada AS horaEntrada, horario_salida AS horaSalida, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND fecha = ? AND turno = ? ORDER BY empleados.legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Disponible");
            pstm.setString(3, fechaBusqueda);
            pstm.setString(4, dato);
            rs = pstm.executeQuery();

            while (rs.next()){
                hora_entrada = String.valueOf(rs.getTime("horaEntrada")).substring(0, 5);
                hora_salida = String.valueOf(rs.getTime("horaSalida")).substring(0, 5);

                String fechaClave = rs.getString("fecha");
                String fechaAnio2 = fechaClave.substring(0, 4);
                String fechaMes2 = fechaClave.substring(5, 7);
                String fechaDia2 = fechaClave.substring(8, 10);
                String fechaModificada = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                lista.add(new ConsultaAsistencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("turno"), fechaModificada, hora_entrada, hora_salida, rs.getInt("idCronograma")));
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

    //--------------------------------------- Leer Entrada (Presentes) -------------------------------------------------
    public static ObservableList<ConsultaAsistencia> listaEntrada(DatePicker dpBuscarFechaAsistencia, ComboBox<String> cbTurnoAsistencia) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<ConsultaAsistencia> lista = FXCollections.observableArrayList();
        String dato = cbTurnoAsistencia.getSelectionModel().getSelectedItem().toLowerCase();
        String hora_entrada;

        String fechaBuscar = dpBuscarFechaAsistencia.getEditor().getText();
        String fechaAnio = fechaBuscar.substring(6, 10);
        String fechaMes = fechaBuscar.substring(3, 5);
        String fechaDia = fechaBuscar.substring(0, 2);
        String fechaBusqueda = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        LocalTime horaActual = LocalTime.now();
        int hora = horaActual.getHour();
        int minuto = horaActual.getMinute();
        int segundo = horaActual.getSecond();

        if(dato.equals("Dia")){

        } else if (dato.equals("Noche")){

        }

        try {
            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora, empleados.idempleados FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) ORDER BY empleados.legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, fechaBusqueda);
            pstm.setString(3, hora + ":" + minuto + ":" + segundo);
            pstm.setString(4, hora + ":" + minuto + ":" + segundo);
            rs = pstm.executeQuery();

            while (rs.next()){
                hora_entrada = String.valueOf(rs.getTime("hora")).substring(0, 5);

                String fechaClave = rs.getString("fecha");
                String fechaAnio2 = fechaClave.substring(0, 4);
                String fechaMes2 = fechaClave.substring(5, 7);
                String fechaDia2 = fechaClave.substring(8, 10);
                String fechaModificada = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                lista.add(new ConsultaAsistencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), fechaModificada, hora_entrada, rs.getInt("idempleados")));
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
