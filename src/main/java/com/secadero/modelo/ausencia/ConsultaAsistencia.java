package com.secadero.modelo.ausencia;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ConsultaAsistencia {
    private String nombre;
    private String apellido;
    private int legajo;
    private int dni;
    private int idempleados;

    public ConsultaAsistencia(){}

    public ConsultaAsistencia(String nombre, String apellido, int legajo, int dni, int idempleados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.dni = dni;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getIdempleados() {
        return idempleados;
    }

    public void setIdempleados(int idempleados) {
        this.idempleados = idempleados;
    }

    //------------------------------------------ Leer Empleados Presentes -----------------------------------------------
    public static ObservableList<ConsultaAsistencia> listaEmpleadoPresentes() {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<ConsultaAsistencia> lista = FXCollections.observableArrayList();
        String fechaCronograma;
        String horaEntradaCronograma;
        String fechaEntrada;
        String horaEntrada;
        int idEmpleados;
        int contNO = 0;
        int contSI = 0;

        try {
            // ---------------------------- Leer Todos los Cronogramas de todos los Empleados ---------------------------
            String consulta1 = "SELECT empleados.idempleados, fecha, horario_entrada FROM empleados INNER JOIN empleado_cronograma ON empleados.idempleados = empleado_cronograma.idEmpleadoFK INNER JOIN cronograma ON empleado_cronograma.idCronogramaFK = cronograma.idCronograma";
            pstm = con.prepareStatement(consulta1);
            rs = pstm.executeQuery();

            while (rs.next()){
                idEmpleados = rs.getInt("idempleados");
                fechaCronograma = rs.getString("fecha");
                horaEntradaCronograma = rs.getString("horario_entrada");

                LocalDateTime fechaHoraActual = LocalDateTime.now(); // Tomo la Fecha Actual
                LocalDateTime fechaHoraTurno = LocalDateTime.of(Integer.parseInt(fechaCronograma.substring(0, 4)), Integer.parseInt(fechaCronograma.substring(5, 7)), Integer.parseInt(fechaCronograma.substring(8, 10)), Integer.parseInt(horaEntradaCronograma.substring(0, 2)), Integer.parseInt(horaEntradaCronograma.substring(3, 5))); // Tomo la Fecha y Hora del Turno

                /*
                // Comparar la fecha y hora actual con la fecha y hora del turno y la fecha y hora límite
                if (fechaHoraActual.isBefore(fechaHoraTurno)) {
                    System.out.println("El cliente aún no ha comenzado su turno.");
                } else if (fechaHoraEntrada == null) {
                    System.out.println("El cliente no ingresó su entrada. No está trabajando.");
                } else {
                    System.out.println("El cliente está trabajando.");
                }

                 */

                String consulta2 = "SELECT fecha FROM empleados INNER JOIN entrada ON empleados.idempleados = entrada.idEmpleadoFK";
                pstm = con.prepareStatement(consulta2);
                rs = pstm.executeQuery();

                // Obtener la fecha y hora del turno del cliente si hay resultados
                if (rs.next()) {
                    fechaEntrada = rs.getString("fecha");
                    horaEntrada = rs.getString("hora_entrada");
                }

                String consulta3 = "SELECT nombre, apellido, legajo, dni, idempleados FROM empleados INNER JOIN empleado_cronograma ON empleados.idempleados = empleado_cronograma.idEmpleadoFK INNER JOIN cronograma ON empleado_cronograma.idCronogramaFK = cronograma.idCronograma INNER JOIN entrada ON empleados.idempleados = entrada.idEmpleadoFK";
                pstm = con.prepareStatement(consulta3);
                rs = pstm.executeQuery();

                while (rs.next()){

                }
            }
            System.out.println(contNO);
            System.out.println(contSI);
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

    //----------------------------------------- Leer Empleados Ausentes ------------------------------------------------
    public static ObservableList<ConsultaAsistencia> listaEmpleadoAsusenteC() {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<ConsultaAsistencia> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT fecha, motivo, justificado, certificado, idausencias FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND ausencias.idEmpleadoFK = ? ORDER BY fecha DESC");
            pstm.setString(1, "Vigente");
            rs = pstm.executeQuery();

            while (rs.next()){

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
