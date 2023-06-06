package com.secadero.modelo.ausencia;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Objects;

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

    public ConsultaAsistencia(String nombre, String apellido, int legajo, String turno, String fecha, String horaEntrada, String horaSalida, int idCronograma, int idEmpleado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.turno = turno;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.idCronograma = idCronograma;
        this.idEmpleado = idEmpleado;
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
    public static ObservableList<ConsultaAsistencia> listaCronogramaCE(DatePicker dpBuscarFechaAsistencia, ComboBox<String> cbTurnoAsistencia, Label labCantidadCronograma) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<ConsultaAsistencia> lista = FXCollections.observableArrayList();
        String dato = cbTurnoAsistencia.getSelectionModel().getSelectedItem();
        String hora_entrada;
        String hora_salida;

        String fechaBuscar = dpBuscarFechaAsistencia.getEditor().getText();
        String fechaAnio = fechaBuscar.substring(6, 10);
        String fechaMes = fechaBuscar.substring(3, 5);
        String fechaDia = fechaBuscar.substring(0, 2);
        String fechaBusqueda = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
        int cantCronograma = 0;

        try {
            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, turno, fecha, horario_entrada AS horaEntrada, horario_salida AS horaSalida, idCronograma, empleados.idempleados AS idEmpleado FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND fecha = ? AND turno = ? ORDER BY empleados.legajo ASC");
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
                lista.add(new ConsultaAsistencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("turno"), fechaModificada, hora_entrada, hora_salida, rs.getInt("idCronograma"), rs.getInt("idEmpleado")));
                cantCronograma += 1;
            }
            labCantidadCronograma.setText(String.valueOf(cantCronograma));
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
    public static ObservableList<ConsultaAsistencia> listaEntrada(DatePicker dpBuscarFechaAsistencia, ComboBox<String> cbTurnoAsistencia, Label labCantidadPresentes) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<ConsultaAsistencia> lista = FXCollections.observableArrayList();
        String dato = cbTurnoAsistencia.getSelectionModel().getSelectedItem();
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

        int hora1 = 0;
        int minuto1 = 0;
        int hora2 = 0;
        int minuto2 = 0;

        int cantPresentes = 0;

        try {
            if(Objects.equals(dato, "Dia")){
                LocalTime horaInicio = LocalTime.of(12, 0);
                hora1 = horaInicio.getHour();
                minuto1 = horaInicio.getMinute();
                LocalTime horaFin = LocalTime.of(12, 30);
                hora2 = horaFin.getHour();
                minuto2 = horaFin.getMinute();

                try {
                    pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS horaEntrada, empleados.idempleados AS idEmpleado FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) ORDER BY empleados.legajo ASC");
                    pstm.setString(1, "Vigente");
                    pstm.setString(2, fechaBusqueda);
                    pstm.setString(3, hora1 + ":" + minuto1);
                    pstm.setString(4, hora2 + ":" + minuto2);
                    rs = pstm.executeQuery();

                    while (rs.next()){
                        hora_entrada = String.valueOf(rs.getTime("horaEntrada")).substring(0, 5);

                        String fechaClave = rs.getString("fecha");
                        String fechaAnio2 = fechaClave.substring(0, 4);
                        String fechaMes2 = fechaClave.substring(5, 7);
                        String fechaDia2 = fechaClave.substring(8, 10);
                        String fechaModificada = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                        lista.add(new ConsultaAsistencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), fechaModificada, hora_entrada, rs.getInt("idEmpleado")));
                        cantPresentes += 1;
                    }
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }

            } else if(Objects.equals(dato, "Noche")){
                LocalTime horaInicio = LocalTime.of(0, 0);
                hora1 = horaInicio.getHour();
                minuto1 = horaInicio.getMinute();
                LocalTime horaFin = LocalTime.of(0, 30);
                hora2 = horaFin.getHour();
                minuto2 = horaFin.getMinute();

                try {
                    pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS hora, empleados.idempleados AS idempleados FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) ORDER BY empleados.legajo ASC");
                    pstm.setString(1, "Vigente");
                    pstm.setString(2, fechaBusqueda);
                    pstm.setString(3, hora1 + ":" + minuto1);
                    pstm.setString(4, hora2 + ":" + minuto2);
                    rs = pstm.executeQuery();

                    while (rs.next()){
                        hora_entrada = String.valueOf(rs.getTime("hora")).substring(0, 5);

                        String fechaClave = rs.getString("fecha");
                        String fechaAnio2 = fechaClave.substring(0, 4);
                        String fechaMes2 = fechaClave.substring(5, 7);
                        String fechaDia2 = fechaClave.substring(8, 10);
                        String fechaModificada = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                        lista.add(new ConsultaAsistencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), fechaModificada, hora_entrada, rs.getInt("idempleados")));
                        cantPresentes += 1;
                    }
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }

            }
            labCantidadPresentes.setText(String.valueOf(cantPresentes));
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

    /////////////////////////////////////////////// Buscar //////////////////////////////////////////////////////
    //-------------------------------- Leer Cronograma Consulta Asistencia (CE) ----------------------------------------
    public static ObservableList<ConsultaAsistencia> listaCronogramaCEBuscar(DatePicker dpBuscarFechaAsistencia, ComboBox<String> cbTurnoAsistencia, Label labCantidadCronograma, TextField textBuscarLegajoEmpleadoAsistencia) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<ConsultaAsistencia> lista = FXCollections.observableArrayList();
        String dato = cbTurnoAsistencia.getSelectionModel().getSelectedItem();
        String hora_entrada;
        String hora_salida;

        String fechaBuscar = dpBuscarFechaAsistencia.getEditor().getText();
        String fechaAnio = fechaBuscar.substring(6, 10);
        String fechaMes = fechaBuscar.substring(3, 5);
        String fechaDia = fechaBuscar.substring(0, 2);
        String fechaBusqueda = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
        int cantCronograma = 0;

        try {
            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, turno, fecha, horario_entrada AS horaEntrada, horario_salida AS horaSalida, idCronograma, empleados.idempleados AS idEmpleado FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND fecha = ? AND turno = ? AND empleados.legajo LIKE ? ORDER BY empleados.legajo ASC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Disponible");
            pstm.setString(3, fechaBusqueda);
            pstm.setString(4, dato);
            pstm.setString(5, textBuscarLegajoEmpleadoAsistencia.getText() + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                hora_entrada = String.valueOf(rs.getTime("horaEntrada")).substring(0, 5);
                hora_salida = String.valueOf(rs.getTime("horaSalida")).substring(0, 5);

                String fechaClave = rs.getString("fecha");
                String fechaAnio2 = fechaClave.substring(0, 4);
                String fechaMes2 = fechaClave.substring(5, 7);
                String fechaDia2 = fechaClave.substring(8, 10);
                String fechaModificada = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                lista.add(new ConsultaAsistencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getString("turno"), fechaModificada, hora_entrada, hora_salida, rs.getInt("idCronograma"), rs.getInt("idEmpleado")));
                cantCronograma += 1;
            }
            labCantidadCronograma.setText(String.valueOf(cantCronograma));
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
    public static ObservableList<ConsultaAsistencia> listaEntradaBuscar(DatePicker dpBuscarFechaAsistencia, ComboBox<String> cbTurnoAsistencia, Label labCantidadPresentes, TextField textBuscarLegajoEmpleadoAsistencia) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<ConsultaAsistencia> lista = FXCollections.observableArrayList();
        String dato = cbTurnoAsistencia.getSelectionModel().getSelectedItem();
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

        int hora1 = 0;
        int minuto1 = 0;
        int hora2 = 0;
        int minuto2 = 0;

        int cantPresentes = 0;

        try {
            if(Objects.equals(dato, "Dia")){
                LocalTime horaInicio = LocalTime.of(12, 0);
                hora1 = horaInicio.getHour();
                minuto1 = horaInicio.getMinute();
                LocalTime horaFin = LocalTime.of(12, 30);
                hora2 = horaFin.getHour();
                minuto2 = horaFin.getMinute();

                try {
                    pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS horaEntrada, empleados.idempleados AS idEmpleado FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) AND empleados.legajo LIKE ? ORDER BY empleados.legajo ASC");
                    pstm.setString(1, "Vigente");
                    pstm.setString(2, fechaBusqueda);
                    pstm.setString(3, hora1 + ":" + minuto1);
                    pstm.setString(4, hora2 + ":" + minuto2);
                    pstm.setString(5, textBuscarLegajoEmpleadoAsistencia.getText() + "%");
                    rs = pstm.executeQuery();

                    while (rs.next()){
                        hora_entrada = String.valueOf(rs.getTime("horaEntrada")).substring(0, 5);

                        String fechaClave = rs.getString("fecha");
                        String fechaAnio2 = fechaClave.substring(0, 4);
                        String fechaMes2 = fechaClave.substring(5, 7);
                        String fechaDia2 = fechaClave.substring(8, 10);
                        String fechaModificada = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                        lista.add(new ConsultaAsistencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), fechaModificada, hora_entrada, rs.getInt("idEmpleado")));
                        cantPresentes += 1;
                    }
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }

            } else if(Objects.equals(dato, "Noche")){
                LocalTime horaInicio = LocalTime.of(0, 0);
                hora1 = horaInicio.getHour();
                minuto1 = horaInicio.getMinute();
                LocalTime horaFin = LocalTime.of(0, 30);
                hora2 = horaFin.getHour();
                minuto2 = horaFin.getMinute();

                try {
                    pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS hora, empleados.idempleados AS idempleados FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) AND empleados.legajo LIKE ? ORDER BY empleados.legajo ASC");
                    pstm.setString(1, "Vigente");
                    pstm.setString(2, fechaBusqueda);
                    pstm.setString(3, hora1 + ":" + minuto1);
                    pstm.setString(4, hora2 + ":" + minuto2);
                    pstm.setString(5, textBuscarLegajoEmpleadoAsistencia.getText() + "%");
                    rs = pstm.executeQuery();

                    while (rs.next()){
                        hora_entrada = String.valueOf(rs.getTime("hora")).substring(0, 5);

                        String fechaClave = rs.getString("fecha");
                        String fechaAnio2 = fechaClave.substring(0, 4);
                        String fechaMes2 = fechaClave.substring(5, 7);
                        String fechaDia2 = fechaClave.substring(8, 10);
                        String fechaModificada = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                        lista.add(new ConsultaAsistencia(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), fechaModificada, hora_entrada, rs.getInt("idempleados")));
                        cantPresentes += 1;
                    }
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }

            }
            labCantidadPresentes.setText(String.valueOf(cantPresentes));
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

    //------------------------------ Verificar si Existe Entrada de dicho Empleado ---------------------------------------
    public void verificacionEntrada(Label labIDEmpleadoEntradaA, Label labFechaEntrada, Label labTurnoEntrada, Label labExisteEntrada) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String fecha = labFechaEntrada.getText();
        String fechaAnio = fecha.substring(6, 10);
        String fechaMes = fecha.substring(3, 5);
        String fechaDia = fecha.substring(0, 2);
        String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        int hora1 = 0;
        int minuto1 = 0;
        int hora2 = 0;
        int minuto2 = 0;

        try {
            if(Objects.equals(labTurnoEntrada.getText(), "Dia")){
                LocalTime horaInicio = LocalTime.of(12, 0);
                hora1 = horaInicio.getHour();
                minuto1 = horaInicio.getMinute();
                LocalTime horaFin = LocalTime.of(12, 30);
                hora2 = horaFin.getHour();
                minuto2 = horaFin.getMinute();

                try {
                    pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS horaEntrada, empleados.idempleados AS idEmpleado FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND entrada.idEmpleadoFK = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) ORDER BY empleados.legajo ASC");
                    pstm.setString(1, "Vigente");
                    pstm.setInt(2, Integer.parseInt(labIDEmpleadoEntradaA.getText()));
                    pstm.setString(3, fechaModificada);
                    pstm.setString(4, hora1 + ":" + minuto1);
                    pstm.setString(5, hora2 + ":" + minuto2);
                    rs = pstm.executeQuery();

                    while (rs.next()){
                        labExisteEntrada.setText("YES");
                    }
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }

            } else if(Objects.equals(labTurnoEntrada.getText(), "Noche")){
                LocalTime horaInicio = LocalTime.of(0, 0);
                hora1 = horaInicio.getHour();
                minuto1 = horaInicio.getMinute();
                LocalTime horaFin = LocalTime.of(0, 30);
                hora2 = horaFin.getHour();
                minuto2 = horaFin.getMinute();

                try {
                    pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS hora, empleados.idempleados AS idempleados FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND entrada.idEmpleadoFK = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) ORDER BY empleados.legajo ASC");
                    pstm.setString(1, "Vigente");
                    pstm.setInt(2, Integer.parseInt(labIDEmpleadoEntradaA.getText()));
                    pstm.setString(3, fechaModificada);
                    pstm.setString(4, hora1 + ":" + minuto1);
                    pstm.setString(5, hora2 + ":" + minuto2);
                    rs = pstm.executeQuery();

                    while (rs.next()){
                        labExisteEntrada.setText("YES");
                    }
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }
            }
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

    //------------------------------------------- Crear Entrada del Empleado -------------------------------------------
    public void crearEntrada(Label labIDEmpleadoEntradaA, Label labFechaEntrada, TextField textHoraEntrada) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String fecha = labFechaEntrada.getText();
        String fechaAnio = fecha.substring(6, 10);
        String fechaMes = fecha.substring(3, 5);
        String fechaDia = fecha.substring(0, 2);
        String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        try {
            String consulta = "INSERT INTO entrada(hora, fecha, idEmpleadoFK) VALUES (?, ?, ?)";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, textHoraEntrada.getText());
            pstm.setString(2, fechaModificada);
            pstm.setInt(3, Integer.parseInt(labIDEmpleadoEntradaA.getText()));
            pstm.executeUpdate();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito!!");
            alerta.setContentText("Se guardo correctamente la Entrada del Empleado.");
            alerta.showAndWait();

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
}
