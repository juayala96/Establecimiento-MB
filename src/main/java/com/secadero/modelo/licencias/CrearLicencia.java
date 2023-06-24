package com.secadero.modelo.licencias;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class CrearLicencia {

    public CrearLicencia() {
    }

    //--------------------------------------------- Crear Licencia ---------------------------------------------------
    public void agregarLicencia(Label labIDEmpleadoCrear, Label labDiasDisponiblesCrear, DatePicker dpFechaInicioCrear, DatePicker dpFechaFinCrear, ComboBox<String> cbTipoLicenciaCrear, TextField textDescripcionLicenciaCrear, Label labLimpiarCamposCrear) throws ParseException, SQLException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String resultado2 = "YES";
        int diasVacaciones = 0;
        int diasEnfermedad = 0;
        int diasAccidente = 0;
        int diasMatrimonio = 0;
        int diasMuerteFamiliar = 0;
        final int VACACIONES = 60;
        final int ENFERMEDAD = 15;
        final int ACCIDENTE = 15;
        final int MATRIMONIO = 10;
        final int MUERTE_FAMILIAR = 4;
        int cont = 0;
        int cont2 = 0;
        int cont3 = 0;
        String respuestaAnio = "";
        int datoIdTipoLicenciaFK;
        int datoIDLicenciaFK;
        String datoFechaInicioMax;
        String datoFechaFinMax;
        String resultado3 = "YES";

        String fechaInicioClave = dpFechaInicioCrear.getEditor().getText();
        String fechaAnio = fechaInicioClave.substring(6, 10);
        String fechaMes = fechaInicioClave.substring(3, 5);
        String fechaDia = fechaInicioClave.substring(0, 2);
        String fechaModificadaInicio = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
        String fechaFinClave = dpFechaFinCrear.getEditor().getText();
        String fechaAnio2 = fechaFinClave.substring(6, 10);
        String fechaMes2 = fechaFinClave.substring(3, 5);
        String fechaDia2 = fechaFinClave.substring(0, 2);
        String fechaModificadaFin = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // ------------------------- Fecha Inicio -------------------------------
        String fechaI = fechaModificadaInicio;
        Date fechaInicio = formatoFecha.parse(fechaI);
        // --------------------------- Fecha Fin --------------------------------
        String fechaF = fechaModificadaFin;
        Date fechaFin = formatoFecha.parse(fechaF);
        // --------------------- Diferencia entre Fechas ------------------------
        long Diferencias = fechaInicio.getTime() - fechaFin.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);


        // --------------- Comprobación del año de las fechas que se encuentra o no en la Base de Datos----------------
        String fecha = fechaModificadaInicio;
        int anio2 = Integer.parseInt(fecha.substring(0, 4));

        try {
            String consulta2 = "SELECT YEAR(fecha_Inicio) AS anio FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE empleados.estadoEmpleado = ? AND idEmpleadoFK = ? AND tipo_licencias.descripcion = ? AND YEAR(fecha_Inicio) = ?";
            pstm = con.prepareStatement(consulta2);
            pstm.setString(1, "Vigente");
            pstm.setString(2, labIDEmpleadoCrear.getText());
            pstm.setString(3, cbTipoLicenciaCrear.getSelectionModel().getSelectedItem());
            pstm.setInt(4, anio2);
            rs = pstm.executeQuery();
            while (rs.next()) {
                respuestaAnio = "YES";
                cont3 += 1;
            }
            if(cont3 == 0){
                respuestaAnio = "NO";
            }
        } catch (Exception e1) {
            System.err.println("Error: " + e1.getMessage());
        }

        if (respuestaAnio.equals("YES")) {
            // --------------- Comprobación de el Tipo de Licencia para realizar la operación de fechas------------------
            try {
                String consulta3 = "SELECT MIN(dias_disponibles) AS dias_disponibles, empleados.idempleados FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ? AND tipo_licencias.descripcion = ?";
                pstm = con.prepareStatement(consulta3);
                pstm.setString(1, labIDEmpleadoCrear.getText());
                pstm.setString(2, cbTipoLicenciaCrear.getSelectionModel().getSelectedItem());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int datoDiaDisponible = rs.getInt("dias_disponibles");
                    int datoIDEmpleado = rs.getInt("idempleados");

                    if(datoIDEmpleado == Integer.parseInt(labIDEmpleadoCrear.getText())){
                        if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                            diasVacaciones = (int) (datoDiaDisponible - (-Cant_Dias) - 1);
                        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                            diasEnfermedad = (int) (datoDiaDisponible - (-Cant_Dias) - 1);
                        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Accidente")) {
                            diasAccidente = (int) (datoDiaDisponible - (-Cant_Dias) - 1);
                        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                            diasMatrimonio = (int) (datoDiaDisponible - (-Cant_Dias) - 1);
                        } else {
                            diasMuerteFamiliar = (int) (datoDiaDisponible - (-Cant_Dias) - 1);
                        }
                    }
                    cont += 1;
                }
                if (cont == 0) {
                    if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                        diasVacaciones = (int) (VACACIONES - (-Cant_Dias) - 1);
                    } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                        diasEnfermedad = (int) (ENFERMEDAD - (-Cant_Dias) - 1);
                    } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Accidente")) {
                        diasAccidente = (int) (ACCIDENTE - (-Cant_Dias) - 1);
                    } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                        diasMatrimonio = (int) (MATRIMONIO - (-Cant_Dias) - 1);
                    } else {
                        diasMuerteFamiliar = (int) (MUERTE_FAMILIAR - (-Cant_Dias) - 1);
                    }
                }

            } catch (Exception e1) {
                System.err.println("Error: " + e1.getMessage());
            }
        } else {
            if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                labDiasDisponiblesCrear.setText(String.valueOf(VACACIONES));
                diasVacaciones = (int) (VACACIONES - (-Cant_Dias) - 1);
            } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                labDiasDisponiblesCrear.setText(String.valueOf(ENFERMEDAD));
                diasEnfermedad = (int) (ENFERMEDAD - (-Cant_Dias) - 1);
            } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Accidente")) {
                labDiasDisponiblesCrear.setText(String.valueOf(ACCIDENTE));
                diasAccidente = (int) (ACCIDENTE - (-Cant_Dias) - 1);
            } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                labDiasDisponiblesCrear.setText(String.valueOf(MATRIMONIO));
                diasMatrimonio = (int) (MATRIMONIO - (-Cant_Dias) - 1);
            } else {
                labDiasDisponiblesCrear.setText(String.valueOf(MUERTE_FAMILIAR));
                diasMuerteFamiliar = (int) (MUERTE_FAMILIAR - (-Cant_Dias) - 1);
            }
        }

        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long Diferencias2 = fechaActual.getTime() - fechaInicio.getTime();
        long Cant_Dias2 = Diferencias2 / (1000 * 60 * 60 * 24);

        // -------------------------------------------- Crear Licencia --------------------------------------------
        if (-Cant_Dias >= 0) {
            if (-Cant_Dias2 > 0) {
                if (diasVacaciones >= 0 && diasEnfermedad >= 0 && diasAccidente >= 0 && diasMatrimonio >= 0 && diasMuerteFamiliar >= 0) {
                    try {
                        String consultaFecha = "SELECT fecha_Inicio, fecha_Fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ?";
                        pstm = con.prepareStatement(consultaFecha);
                        pstm.setString(1, labIDEmpleadoCrear.getText());
                        rs = pstm.executeQuery();
                        while (rs.next()) {
                            datoFechaInicioMax = rs.getString("fecha_Inicio");
                            datoFechaFinMax = rs.getString("fecha_Fin");

                            // Establecer la fecha inicial y final
                            LocalDate fechaInicial = LocalDate.parse(datoFechaInicioMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            LocalDate fechaFinal = LocalDate.parse(datoFechaFinMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                            // Iterar sobre las fechas y mostrarlas
                            LocalDate resultadoFecha = fechaInicial;
                            while (!resultadoFecha.isAfter(fechaFinal)) {
                                if (fechaModificadaInicio.equals(String.valueOf(resultadoFecha))) {
                                    resultado2 = "NO";
                                }
                                if (fechaModificadaFin.equals(String.valueOf(resultadoFecha))) {
                                    resultado2 = "NO";
                                }
                                resultadoFecha = resultadoFecha.plusDays(1);
                            }

                        }
                    } catch (Exception e1) {
                        System.err.println("Error: " + e1.getMessage());
                    }

                    // ---------- Comprobar las fechas ingresadas que no existan en la base de datos ------------
                    try {
                        String consultaFechaIngresada = "SELECT fecha_Inicio, fecha_Fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ? AND ((fecha_Inicio BETWEEN ? AND ?) AND (fecha_Fin BETWEEN ? AND ?))";
                        pstm = con.prepareStatement(consultaFechaIngresada);
                        pstm.setString(1, labIDEmpleadoCrear.getText());
                        pstm.setDate(2, java.sql.Date.valueOf(fechaModificadaInicio));
                        pstm.setDate(3, java.sql.Date.valueOf(fechaModificadaFin));
                        pstm.setDate(4, java.sql.Date.valueOf(fechaModificadaInicio));
                        pstm.setDate(5, java.sql.Date.valueOf(fechaModificadaFin));
                        rs = pstm.executeQuery();
                        while (rs.next()) {
                            resultado2 = "NO";
                        }
                    } catch (Exception e1) {
                        System.err.println("Error: " + e1.getMessage());
                    }

                    try {
                        String consultaFechaCronograma = "SELECT fecha FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.idempleados = ?";
                        pstm = con.prepareStatement(consultaFechaCronograma);
                        pstm.setString(1, labIDEmpleadoCrear.getText());
                        rs = pstm.executeQuery();
                        while (rs.next()) {
                            String fechaCalendario = rs.getString("fecha");
                            // Establecer la fecha inicial y final
                            LocalDate fechaInicial = LocalDate.parse(fechaModificadaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            LocalDate fechaFinal = LocalDate.parse(fechaModificadaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                            // Iterar sobre las fechas y mostrarlas
                            LocalDate resultadoFecha = fechaInicial;
                            while (!resultadoFecha.isAfter(fechaFinal)) {
                                if (fechaCalendario.equals(String.valueOf(resultadoFecha))) {
                                    resultado3 = "NO";
                                }
                                resultadoFecha = resultadoFecha.plusDays(1);
                            }
                        }
                    } catch (Exception e1) {
                        System.err.println("Error: " + e1.getMessage());
                    }

                    if (resultado2.equals("YES")) {
                        if(resultado3.equals("YES")){
                            String extraerAnio = fechaModificadaInicio;
                            int exAnio = Integer.parseInt(extraerAnio.substring(0, 4));
                            try {
                                // -------------------------------------- Creación -------------------------------------
                                String consulta4 = "SELECT idTipoLicencia FROM tipo_licencias WHERE descripcion = ?";
                                pstm = con.prepareStatement(consulta4);
                                pstm.setString(1, cbTipoLicenciaCrear.getSelectionModel().getSelectedItem());
                                rs = pstm.executeQuery();
                                while (rs.next()) {
                                    datoIdTipoLicenciaFK = Integer.parseInt(rs.getString("idTipoLicencia"));

                                    String consulta5 = "SELECT idempleados FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ? AND estadoEmpleado = ? AND idTipoLicenciaFK = ? AND YEAR(fecha_Inicio) = ?";
                                    pstm = con.prepareStatement(consulta5);
                                    pstm.setInt(1, Integer.parseInt(labIDEmpleadoCrear.getText()));
                                    pstm.setString(2, "Vigente");
                                    pstm.setInt(3, datoIdTipoLicenciaFK);
                                    pstm.setInt(4, exAnio);
                                    rs = pstm.executeQuery();

                                    while (rs.next()) {
                                        try {
                                            String consultaDiasDisponibles = "UPDATE licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados SET dias_disponibles = ? WHERE estadoEmpleado = ? AND empleado_licencia.idEmpleadoFK = ? AND idTipoLicenciaFK = ? AND YEAR(fecha_Inicio) = ?";

                                            pstm = con.prepareStatement(consultaDiasDisponibles);
                                            if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                                                pstm.setInt(1, diasVacaciones);
                                            } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                                                pstm.setInt(1, diasEnfermedad);
                                            } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Accidente")) {
                                                pstm.setInt(1, diasAccidente);
                                            } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                                                pstm.setInt(1, diasMatrimonio);
                                            } else {
                                                pstm.setInt(1, diasMuerteFamiliar);
                                            }
                                            pstm.setString(2, "Vigente");
                                            pstm.setInt(3, Integer.parseInt(labIDEmpleadoCrear.getText()));
                                            pstm.setInt(4, datoIdTipoLicenciaFK);
                                            pstm.setInt(5, exAnio);
                                            pstm.executeUpdate();

                                        } catch (Exception e1) {
                                            System.err.println("Error: " + e1.getMessage());
                                        }

                                        String consulta6 = "INSERT INTO licencias(fecha_Inicio, fecha_Fin, dias_disponibles, descripcionLicencia, idTipoLicenciaFK) VALUES (?, ?, ?, ?, ?)";
                                        pstm = con.prepareStatement(consulta6);
                                        pstm.setDate(1, java.sql.Date.valueOf(fechaModificadaInicio));
                                        pstm.setDate(2, java.sql.Date.valueOf(fechaModificadaFin));

                                        if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                                            pstm.setInt(3, diasVacaciones);
                                        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                                            pstm.setInt(3, diasEnfermedad);
                                        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Accidente")) {
                                            pstm.setInt(3, diasAccidente);
                                        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                                            pstm.setInt(3, diasMatrimonio);
                                        } else {
                                            pstm.setInt(3, diasMuerteFamiliar);
                                        }

                                        pstm.setString(4, textDescripcionLicenciaCrear.getText());
                                        pstm.setInt(5, datoIdTipoLicenciaFK);
                                        pstm.executeUpdate();

                                        String consulta7 = "SELECT MAX(idLicencias) AS idLicencias FROM licencias";
                                        pstm = con.prepareStatement(consulta7);
                                        rs = pstm.executeQuery();
                                        while (rs.next()) {
                                            datoIDLicenciaFK = rs.getInt("idLicencias");

                                            String consulta8 = "INSERT INTO empleado_licencia(idEmpleadoFK, idLicenciaFK) VALUES (?, ?)";
                                            pstm = con.prepareStatement(consulta8);
                                            pstm.setInt(1, Integer.parseInt(labIDEmpleadoCrear.getText()));
                                            pstm.setInt(2, datoIDLicenciaFK);
                                            pstm.executeUpdate();

                                        }

                                        cont2 +=1;
                                    }

                                    if(cont2 == 0){
                                        String consulta6 = "INSERT INTO licencias(fecha_Inicio, fecha_Fin, dias_disponibles, descripcionLicencia, idTipoLicenciaFK) VALUES (?, ?, ?, ?, ?)";
                                        pstm = con.prepareStatement(consulta6);
                                        pstm.setDate(1, java.sql.Date.valueOf(fechaModificadaInicio));
                                        pstm.setDate(2, java.sql.Date.valueOf(fechaModificadaFin));

                                        if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                                            pstm.setInt(3, diasVacaciones);
                                        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                                            pstm.setInt(3, diasEnfermedad);
                                        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Accidente")) {
                                            pstm.setInt(3, diasAccidente);
                                        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                                            pstm.setInt(3, diasMatrimonio);
                                        } else {
                                            pstm.setInt(3, diasMuerteFamiliar);
                                        }

                                        pstm.setString(4, textDescripcionLicenciaCrear.getText());
                                        pstm.setInt(5, datoIdTipoLicenciaFK);
                                        pstm.executeUpdate();

                                        String consulta7 = "SELECT MAX(idLicencias) AS idLicencias FROM licencias";
                                        pstm = con.prepareStatement(consulta7);
                                        rs = pstm.executeQuery();
                                        while (rs.next()) {
                                            datoIDLicenciaFK = rs.getInt("idLicencias");

                                            String consulta8 = "INSERT INTO empleado_licencia(idEmpleadoFK, idLicenciaFK) VALUES (?, ?)";
                                            pstm = con.prepareStatement(consulta8);
                                            pstm.setInt(1, Integer.parseInt(labIDEmpleadoCrear.getText()));
                                            pstm.setInt(2, datoIDLicenciaFK);
                                            pstm.executeUpdate();

                                        }
                                    }
                                }
                                labLimpiarCamposCrear.setText("OK");
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.setTitle("");
                                alerta.setContentText("Se a Guardado los Datos.");
                                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(icon);
                                alerta.showAndWait();

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
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Error de Licencia!");
                            alerta.setContentText("La Fecha de Inicio y Fin que a Propuesto entre medio "+ "\n" +"se encuentra con Turno." + "\n" + "Para mas detalle ingrese a (Consulta Cronograma)");
                            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(icon);
                            alerta.showAndWait();
                        }
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error de Licencia!");
                        alerta.setContentText("La Fecha de Inicio y Fin que a Propuesto no se puede Crear porque ya existe una Licencia entre medio");
                        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(icon);
                        alerta.showAndWait();
                    }
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Días Disponibles!");
                    alerta.setContentText("La Fecha de Inicio y Fin que a Propuesto en dicho "+ "\n" +"tipo de licencia ya no quedan Días Disponibles");
                    Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(icon);
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Fecha!");
                alerta.setContentText("La Fecha de Inicio debe de realizarse un dia después de la Actual");
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Fechas!");
            alerta.setContentText("La Fecha de Inicio debe de ser Antes de la Fecha de Fin");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        }
    }
}