package com.secadero.modelo.asistencia;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class Entrada {

    private int identrada;
    private Time hora;
    private Date fecha;
    private int idEmpleadoFK;

    public Entrada(){}

    public Entrada(int identrada, Time hora, Date fecha, int idEmpleadoFK) {
        this.identrada = identrada;
        this.hora = hora;
        this.fecha = fecha;
        this.idEmpleadoFK = idEmpleadoFK;
    }

    public int getIdentrada() {
        return identrada;
    }

    public void setIdentrada(int identrada) {
        this.identrada = identrada;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEmpleadoFK() {
        return idEmpleadoFK;
    }

    public void setIdEmpleadoFK(int idEmpleadoFK) {
        this.idEmpleadoFK = idEmpleadoFK;
    }

    //--------------------------------------------- Crear Entrada ---------------------------------------------------
    public void entradaEmpleado(Label labInformacion, TextField textCodigo, TextField textDNI) throws ParseException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int cont = 0;
        int cont2 = 0;
        int idEmpleadosFK;
        String turno;
        int hora1 = 0;
        int minuto1 = 0;
        int hora2 = 0;
        int minuto2 = 0;
        String existe = "NO";

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActualPais = new Date();
        String fecha_Actual = (formatoFecha.format(fechaActualPais));
        LocalTime horaActualPais = LocalTime.now();
        String hora_Actual = horaActualPais.toString().substring(0,5);
        LocalTime horaModificada = LocalTime.of(Integer.parseInt(hora_Actual.substring(0, 2)), Integer.parseInt(hora_Actual.substring(3, 5)));

        try {
            // ---------------------------- Tomo al Empleado según su (Legajo y DNI) ------------------------------
            String consulta = "SELECT idempleados FROM empleados WHERE legajo = ? AND dni = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1, Integer.parseInt(textCodigo.getText()));
            pstm.setInt(2, Integer.parseInt(textDNI.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                idEmpleadosFK = rs.getInt("idempleados");
                // ----------------------------- Pregunto por su Calendario y ver su Turno -----------------
                String consultaCronograma = "SELECT turno FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.idempleados = ? AND cronograma.fecha = ?";
                pstm = con.prepareStatement(consultaCronograma);
                pstm.setInt(1, idEmpleadosFK);
                pstm.setString(2, fecha_Actual);
                rs = pstm.executeQuery();
                while (rs.next()){
                    turno = rs.getString("turno");

                    // ---------------------- Verifico si a ingresado con anterioridad su entrada ----------------------
                    if(Objects.equals(turno, "Dia")){
                        LocalTime horaInicio = LocalTime.of(12, 0);
                        hora1 = horaInicio.getHour();
                        minuto1 = horaInicio.getMinute();
                        LocalTime horaFin = LocalTime.of(12, 30);
                        hora2 = horaFin.getHour();
                        minuto2 = horaFin.getMinute();

                        try {
                            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS horaEntrada, empleados.idempleados AS idEmpleado FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND entrada.idEmpleadoFK = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) ORDER BY empleados.legajo ASC");
                            pstm.setString(1, "Vigente");
                            pstm.setInt(2, idEmpleadosFK);
                            pstm.setString(3, fecha_Actual);
                            pstm.setString(4, hora1 + ":" + minuto1);
                            pstm.setString(5, hora2 + ":" + minuto2);
                            rs = pstm.executeQuery();

                            while (rs.next()){
                                existe = "YES";
                            }
                        } catch (SQLException ex) {
                            System.err.println("Error: " + ex.getMessage());
                        }

                    } else if(Objects.equals(turno, "Noche")){
                        LocalTime horaInicio = LocalTime.of(0, 0);
                        hora1 = horaInicio.getHour();
                        minuto1 = horaInicio.getMinute();
                        LocalTime horaFin = LocalTime.of(0, 30);
                        hora2 = horaFin.getHour();
                        minuto2 = horaFin.getMinute();

                        try {
                            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS hora, empleados.idempleados AS idempleados FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND entrada.idEmpleadoFK = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) ORDER BY empleados.legajo ASC");
                            pstm.setString(1, "Vigente");
                            pstm.setInt(2, idEmpleadosFK);
                            pstm.setString(3, fecha_Actual);
                            pstm.setString(4, hora1 + ":" + minuto1);
                            pstm.setString(5, hora2 + ":" + minuto2);
                            rs = pstm.executeQuery();

                            while (rs.next()){
                                existe = "YES";
                            }
                        } catch (SQLException ex) {
                            System.err.println("Error: " + ex.getMessage());
                        }
                    }

                    if(existe.equals("NO")){
                        // ------------------ Según su Turno veo si está en hora o esta afuera del rango ---------------
                        if(turno.equals("Dia")) {
                            LocalTime horaInicio = LocalTime.of(12, 0);
                            LocalTime horaFin = LocalTime.of(12, 30);

                            if ((horaModificada.equals(horaInicio) || horaModificada.isAfter(horaInicio)) && ((horaModificada.equals(horaFin) || horaModificada.isBefore(horaFin)))) {
                                // ------------------------- Creo la Entrada ---------------------------
                                String consultaCrear = "INSERT INTO entrada(hora, fecha, idEmpleadoFK) VALUES (?, ?, ?)";
                                pstm = con.prepareStatement(consultaCrear);
                                pstm.setString(1, hora_Actual);
                                pstm.setDate(2, java.sql.Date.valueOf(fecha_Actual));
                                pstm.setInt(3, idEmpleadosFK);
                                pstm.executeUpdate();

                                labInformacion.setText("OK");
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.setTitle("Registro de entrada");
                                alerta.setContentText("Se ha guardado la entrada correctamente.");
                                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(icon);
                                alerta.showAndWait();
                            } else {
                                Alert alerta = new Alert(Alert.AlertType.ERROR);
                                alerta.setTitle("Error - Hora");
                                alerta.setContentText("La hora se encuentra fuera del rango del turno. \nHora permitida: 12:00 a 12:30 hs.");
                                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(icon);
                                alerta.showAndWait();
                            }
                        } else if(turno.equals("Noche")){
                            LocalTime horaInicio = LocalTime.of(0, 0);
                            LocalTime horaFin = LocalTime.of(0, 30);

                            if ((horaModificada.equals(horaInicio) || horaModificada.isAfter(horaInicio)) && ((horaModificada.equals(horaFin) || horaModificada.isBefore(horaFin)))) {
                                // ------------------------- Creo la Entrada ---------------------------
                                String consultaCrear = "INSERT INTO entrada(hora, fecha, idEmpleadoFK) VALUES (?, ?, ?)";
                                pstm = con.prepareStatement(consultaCrear);
                                pstm.setString(1, hora_Actual);
                                pstm.setDate(2, java.sql.Date.valueOf(fecha_Actual));
                                pstm.setInt(3, idEmpleadosFK);
                                pstm.executeUpdate();

                                labInformacion.setText("OK");
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.setTitle("Registro de entrada");
                                alerta.setContentText("Se ha guardado la entrada correctamente.");
                                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(icon);
                                alerta.showAndWait();
                            } else {
                                Alert alerta = new Alert(Alert.AlertType.ERROR);
                                alerta.setTitle("Error - Hora");
                                alerta.setContentText("La hora se encuentra fuera del rango del turno. \nHora permitida: 00:00 a 00:30 hs.");
                                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(icon);
                                alerta.showAndWait();
                            }
                        }
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error - Registrar entrada");
                        alerta.setContentText("La entrada ya fue registrada anteriormente.");
                        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(icon);
                        alerta.showAndWait();
                        labInformacion.setText("Existe");
                    }
                    cont2 += 1;
                }
                if(cont2 == 0){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error - Turno");
                    alerta.setContentText("No tiene un turno asignado en esta fecha.");
                    Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(icon);
                    alerta.showAndWait();
                    labInformacion.setText("Existe");
                }
                cont += 1;
            }

            if(cont == 0){
                labInformacion.setText("");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error - Registrar entrada");
                alerta.setContentText("El código o el DNI ingresado no es válido.");
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                alerta.showAndWait();
                textCodigo.requestFocus();
            }

        }  catch (SQLException ex) {
            labInformacion.setText("");
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                labInformacion.setText("");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error - Registrar entrada");
                alerta.setContentText("Error en la base de datos.");
                alerta.showAndWait();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }

    // ------------- Verificar Si a ingresado antes su entrada para poder ingresar Su Salida ---------------------------
    public void verificarRegistroEntrada(Label labInformacion, TextField textCodigo, TextField textDNI) throws ParseException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int cont = 0;
        int cont2 = 0;
        int idEmpleadosFK;
        String turno;
        int hora1 = 0;
        int minuto1 = 0;
        int hora2 = 0;
        int minuto2 = 0;
        String existe = "NO";

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActualPais = new Date();
        String fecha_Actual = (formatoFecha.format(fechaActualPais));
        LocalTime horaActualPais = LocalTime.now();
        String hora_Actual = horaActualPais.toString().substring(0,5);
        LocalTime horaModificada = LocalTime.of(Integer.parseInt(hora_Actual.substring(0, 2)), Integer.parseInt(hora_Actual.substring(3, 5)));

        try {
            // ---------------------------- Tomo al Empleado según su (Legajo y DNI) ------------------------------
            String consulta = "SELECT idempleados FROM empleados WHERE legajo = ? AND dni = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1, Integer.parseInt(textCodigo.getText()));
            pstm.setInt(2, Integer.parseInt(textDNI.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                idEmpleadosFK = rs.getInt("idempleados");
                // ----------------------------- Pregunto por su Calendario y ver su Turno -----------------
                String consultaCronograma = "SELECT turno FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.idempleados = ? AND cronograma.fecha = ?";
                pstm = con.prepareStatement(consultaCronograma);
                pstm.setInt(1, idEmpleadosFK);
                pstm.setString(2, fecha_Actual);
                rs = pstm.executeQuery();
                while (rs.next()){
                    turno = rs.getString("turno");

                    // ---------------------- Verifico si a ingresado con anterioridad su entrada ----------------------
                    if(Objects.equals(turno, "Dia")){
                        LocalTime horaInicio = LocalTime.of(12, 0);
                        hora1 = horaInicio.getHour();
                        minuto1 = horaInicio.getMinute();
                        LocalTime horaFin = LocalTime.of(12, 30);
                        hora2 = horaFin.getHour();
                        minuto2 = horaFin.getMinute();

                        try {
                            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS horaEntrada, empleados.idempleados AS idEmpleado FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND entrada.idEmpleadoFK = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) ORDER BY empleados.legajo ASC");
                            pstm.setString(1, "Vigente");
                            pstm.setInt(2, idEmpleadosFK);
                            pstm.setString(3, fecha_Actual);
                            pstm.setString(4, hora1 + ":" + minuto1);
                            pstm.setString(5, hora2 + ":" + minuto2);
                            rs = pstm.executeQuery();

                            while (rs.next()){
                                existe = "YES";
                            }
                        } catch (SQLException ex) {
                            System.err.println("Error: " + ex.getMessage());
                        }

                    } else if(Objects.equals(turno, "Noche")){
                        LocalTime horaInicio = LocalTime.of(0, 0);
                        hora1 = horaInicio.getHour();
                        minuto1 = horaInicio.getMinute();
                        LocalTime horaFin = LocalTime.of(0, 30);
                        hora2 = horaFin.getHour();
                        minuto2 = horaFin.getMinute();

                        try {
                            pstm = con.prepareStatement("SELECT empleados.nombre, empleados.apellido, empleados.legajo, fecha, hora AS hora, empleados.idempleados AS idempleados FROM entrada INNER JOIN empleados ON entrada.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND entrada.idEmpleadoFK = ? AND fecha = ? AND (hora BETWEEN TIME ? AND TIME ?) ORDER BY empleados.legajo ASC");
                            pstm.setString(1, "Vigente");
                            pstm.setInt(2, idEmpleadosFK);
                            pstm.setString(3, fecha_Actual);
                            pstm.setString(4, hora1 + ":" + minuto1);
                            pstm.setString(5, hora2 + ":" + minuto2);
                            rs = pstm.executeQuery();

                            while (rs.next()){
                                existe = "YES";
                            }
                        } catch (SQLException ex) {
                            System.err.println("Error: " + ex.getMessage());
                        }
                    }

                    if(existe.equals("YES")){
                        labInformacion.setText("EntradaOK");
                    }
                    cont2 += 1;
                }
                if(cont2 == 0){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error - Turno");
                    alerta.setContentText("No tiene un turno asignado en esta fecha.");
                    Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(icon);
                    alerta.showAndWait();
                    labInformacion.setText("Existe");
                }
                cont += 1;
            }

            if(cont == 0){
                labInformacion.setText("");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error - Registrar entrada");
                alerta.setContentText("El código o el DNI ingresado no es válido.");
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                alerta.showAndWait();
                textCodigo.requestFocus();
            }

        }  catch (SQLException ex) {
            labInformacion.setText("");
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
