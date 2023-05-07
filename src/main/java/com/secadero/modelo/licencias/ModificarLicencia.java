package com.secadero.modelo.licencias;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ModificarLicencia {
    public ModificarLicencia(){}

    //--------------------------------------------- Modificar Licencia ---------------------------------------------------
    public void modificarLicencia(Label labIDEmpleadoModificar, Label labIDLicenciaModificar, Label labDiasDisponiblesModificar, DatePicker dpFechaModificarDuplicadaInicio, DatePicker dpFechaModificarDuplicadaFin, DatePicker dpFechaInicioModificar, DatePicker dpFechaFinModificar, ComboBox<String> cbTipoLicenciaModificar, TextField textDescripcionLicenciaModificar, Label labLimpiarCamposModificar) throws ParseException, SQLException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
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
        String respuestaAnio = "";
        int cont = 0;
        int cont2 = 0;
        String datoFechaInicioMax;
        String datoFechaFinMax;
        String resultado2 = "YES";
        int datoIdTipoLicenciaFK;

        String fechaInicioClave = dpFechaInicioModificar.getEditor().getText();
        String fechaAnio = fechaInicioClave.substring(6, 10);
        String fechaMes = fechaInicioClave.substring(3, 5);
        String fechaDia = fechaInicioClave.substring(0, 2);
        String fechaModificadaInicio = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
        String fechaFinClave = dpFechaFinModificar.getEditor().getText();
        String fechaAnio2 = fechaFinClave.substring(6, 10);
        String fechaMes2 = fechaFinClave.substring(3, 5);
        String fechaDia2 = fechaFinClave.substring(0, 2);
        String fechaModificadaFin = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);

        String fechaInicioClaveDup = dpFechaModificarDuplicadaInicio.getEditor().getText();
        String fechaAnioDup = fechaInicioClaveDup.substring(6, 10);
        String fechaMesDup = fechaInicioClaveDup.substring(3, 5);
        String fechaDiaDup = fechaInicioClaveDup.substring(0, 2);
        String fechaModificadaInicioDup = (fechaAnioDup + "-" + fechaMesDup + "-" + fechaDiaDup);
        String fechaFinClaveDup = dpFechaModificarDuplicadaFin.getEditor().getText();
        String fechaAnio2Dup = fechaFinClaveDup.substring(6, 10);
        String fechaMes2Dup = fechaFinClaveDup.substring(3, 5);
        String fechaDia2Dup = fechaFinClaveDup.substring(0, 2);
        String fechaModificadaFinDup = (fechaAnio2Dup + "-" + fechaMes2Dup + "-" + fechaDia2Dup);

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

        // ----------------------------------------------------------------------
        String fechaIpredefinido = fechaModificadaInicioDup;
        Date fechaInicioDuplicado = formatoFecha.parse(fechaIpredefinido);

        String fechaFpredefinido = fechaModificadaFinDup;
        Date fechaFinDuplicado = formatoFecha.parse(fechaFpredefinido);

        long DiferenciasDuplicado = fechaInicioDuplicado.getTime() - fechaFinDuplicado.getTime();
        long Cant_Dias2 = DiferenciasDuplicado / (1000 * 60 * 60 * 24);


        // --------------- Comprobación del año de las fechas que se encuentra o no en la Base de Datos----------------
        String fecha = fechaModificadaInicio;
        int anio2 = Integer.parseInt(fecha.substring(0, 4));

        try{
            try {
                String consulta2 = "SELECT YEAR(fecha_Inicio) AS anio FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE empleados.estadoEmpleado = ? AND idEmpleadoFK = ? AND tipo_licencias.descripcion = ? AND YEAR(fecha_Inicio) = ?";
                pstm = con.prepareStatement(consulta2);
                pstm.setString(1, "Vigente");
                pstm.setString(2, labIDEmpleadoModificar.getText());
                pstm.setString(3, cbTipoLicenciaModificar.getSelectionModel().getSelectedItem());
                pstm.setInt(4, anio2);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    respuestaAnio = "YES";
                    cont2 += 1;
                }
                if(cont2 == 0){
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
                    pstm.setString(1, labIDEmpleadoModificar.getText());
                    pstm.setString(2, cbTipoLicenciaModificar.getSelectionModel().getSelectedItem());
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        int datoDiaDisponible = rs.getInt("dias_disponibles");
                        int datoIDEmpleado = rs.getInt("idempleados");

                        if(datoIDEmpleado == Integer.parseInt(labIDEmpleadoModificar.getText())) {
                            if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                                diasVacaciones = (int) ((datoDiaDisponible - (-Cant_Dias)) + (-Cant_Dias2));
                            } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                                diasEnfermedad = (int) ((datoDiaDisponible - (-Cant_Dias)) + (-Cant_Dias2));
                            } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Accidente")) {
                                diasAccidente = (int) ((datoDiaDisponible - (-Cant_Dias)) + (-Cant_Dias2));
                            } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                                diasMatrimonio = (int) ((datoDiaDisponible - (-Cant_Dias)) + (-Cant_Dias2));
                            } else {
                                diasMuerteFamiliar = (int) ((datoDiaDisponible - (-Cant_Dias)) + (-Cant_Dias2));
                            }
                        }
                        cont += 1;
                    }
                    if (cont == 0) {
                        if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                            diasVacaciones = (int) (VACACIONES - (-Cant_Dias) - 1);
                        } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                            diasEnfermedad = (int) (ENFERMEDAD - (-Cant_Dias) - 1);
                        } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Accidente")) {
                            diasAccidente = (int) (ACCIDENTE - (-Cant_Dias) - 1);
                        } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                            diasMatrimonio = (int) (MATRIMONIO - (-Cant_Dias) - 1);
                        } else {
                            diasMuerteFamiliar = (int) (MUERTE_FAMILIAR - (-Cant_Dias) - 1);
                        }
                    }

                } catch (Exception e1) {
                    System.err.println("Error: " + e1.getMessage());
                }
            } else {
                if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                    labDiasDisponiblesModificar.setText(String.valueOf(VACACIONES));
                    diasVacaciones = (int) (VACACIONES - (-Cant_Dias) - 1);
                } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                    labDiasDisponiblesModificar.setText(String.valueOf(ENFERMEDAD));
                    diasEnfermedad = (int) (ENFERMEDAD - (-Cant_Dias) - 1);
                } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Accidente")) {
                    labDiasDisponiblesModificar.setText(String.valueOf(ACCIDENTE));
                    diasAccidente = (int) (ACCIDENTE - (-Cant_Dias) - 1);
                } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                    labDiasDisponiblesModificar.setText(String.valueOf(MATRIMONIO));
                    diasMatrimonio = (int) (MATRIMONIO - (-Cant_Dias) - 1);
                } else {
                    labDiasDisponiblesModificar.setText(String.valueOf(MUERTE_FAMILIAR));
                    diasMuerteFamiliar = (int) (MUERTE_FAMILIAR - (-Cant_Dias) - 1);
                }
            }

            if ((-Cant_Dias) >= 0){
                if (diasVacaciones >= 0 && diasEnfermedad >= 0 && diasAccidente >= 0 && diasMatrimonio >= 0 && diasMuerteFamiliar >= 0) {
                    try {
                        String consultaFecha = "SELECT fecha_Inicio, fecha_Fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ? AND idLicencias != ?";
                        pstm = con.prepareStatement(consultaFecha);
                        pstm.setString(1, labIDEmpleadoModificar.getText());
                        pstm.setString(2, labIDLicenciaModificar.getText());
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
                        String consultaFechaIngresada = "SELECT fecha_Inicio, fecha_Fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ? AND idLicencias != ? AND ((fecha_Inicio BETWEEN ? AND ?) AND (fecha_Fin BETWEEN ? AND ?))";
                        pstm = con.prepareStatement(consultaFechaIngresada);
                        pstm.setString(1, labIDEmpleadoModificar.getText());
                        pstm.setString(2, labIDLicenciaModificar.getText());
                        pstm.setDate(3, java.sql.Date.valueOf(fechaModificadaInicio));
                        pstm.setDate(4, java.sql.Date.valueOf(fechaModificadaFin));
                        pstm.setDate(5, java.sql.Date.valueOf(fechaModificadaInicio));
                        pstm.setDate(6, java.sql.Date.valueOf(fechaModificadaFin));
                        rs = pstm.executeQuery();
                        while (rs.next()) {
                            resultado2 = "NO";
                        }
                    } catch (Exception e1) {
                        System.err.println("Error: " + e1.getMessage());
                    }

                    Date fechaActualPais = new Date();
                    String Fecha_Actual = (formatoFecha.format(fechaActualPais));
                    Date fechaActual = formatoFecha.parse(Fecha_Actual);
                    long DiferenciasInicio = fechaActual.getTime() - fechaInicioDuplicado.getTime();
                    long Cant_DiasInicio = DiferenciasInicio / (1000 * 60 * 60 * 24);

                    long DiferenciasFin= fechaActual.getTime() - fechaFinDuplicado.getTime();
                    long Cant_DiasFin= DiferenciasFin/ (1000 * 60 * 60 * 24);

                    long DiferenciasInicio2 = fechaActual.getTime() - fechaInicio.getTime();
                    long Cant_DiasInicio2 = DiferenciasInicio2 / (1000 * 60 * 60 * 24);

                    // -------------------------------------- Modificación -----------------------------------
                    if((-Cant_DiasInicio) >= 0 && (-Cant_DiasFin) >= 0){
                        if((-Cant_DiasInicio2) >= 0){
                            if (resultado2.equals("YES")) {
                                try {
                                    String consulta4 = "SELECT idTipoLicencia FROM tipo_licencias WHERE descripcion = ?";
                                    pstm = con.prepareStatement(consulta4);
                                    pstm.setString(1, cbTipoLicenciaModificar.getSelectionModel().getSelectedItem());
                                    rs = pstm.executeQuery();
                                    while (rs.next()) {
                                        datoIdTipoLicenciaFK = Integer.parseInt(rs.getString("idTipoLicencia"));
                                        try {
                                            String consulta6 = "UPDATE licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados SET dias_disponibles = ? WHERE estadoEmpleado = ? AND empleado_licencia.idEmpleadoFK = ? AND idTipoLicenciaFK = ?";

                                            pstm = con.prepareStatement(consulta6);
                                            if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                                                pstm.setInt(1, diasVacaciones);
                                            } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                                                pstm.setInt(1, diasEnfermedad);
                                            } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Accidente")) {
                                                pstm.setInt(1, diasAccidente);
                                            } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                                                pstm.setInt(1, diasMatrimonio);
                                            } else {
                                                pstm.setInt(1, diasMuerteFamiliar);
                                            }
                                            pstm.setString(2, "Vigente");
                                            pstm.setInt(3, Integer.parseInt(labIDEmpleadoModificar.getText()));
                                            pstm.setInt(4, datoIdTipoLicenciaFK);
                                            pstm.executeUpdate();

                                        } catch (Exception e1) {
                                            System.err.println("Error: " + e1.getMessage());
                                        }

                                        try {
                                            String consulta7 = "UPDATE licencias SET fecha_Inicio = ?, fecha_Fin = ?, idTipoLicenciaFK = ?, descripcionLicencia = ? WHERE idLicencias = ?";
                                            pstm = con.prepareStatement(consulta7);
                                            pstm.setDate(1, java.sql.Date.valueOf(fechaModificadaInicio));
                                            pstm.setDate(2, java.sql.Date.valueOf(fechaModificadaFin));
                                            pstm.setInt(3, datoIdTipoLicenciaFK);
                                            pstm.setString(4, textDescripcionLicenciaModificar.getText());
                                            pstm.setInt(5, Integer.parseInt(labIDLicenciaModificar.getText()));
                                            pstm.executeUpdate();

                                        } catch (Exception e1) {
                                            System.err.println("Error: " + e1.getMessage());
                                        }
                                    }
                                    labLimpiarCamposModificar.setText("OK");
                                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                    alerta.setTitle("Datos Modificados");
                                    alerta.setContentText("Se a Guardado los Datos Correctamente.");
                                    alerta.showAndWait();

                                } catch (Exception e1) {
                                    System.err.println("Error: " + e1.getMessage());
                                }

                            } else {
                                Alert alerta = new Alert(Alert.AlertType.ERROR);
                                alerta.setTitle("Error de Licencia!");
                                alerta.setContentText("La Fecha de Inicio y Fin que a Propuesto no se puede Modificar porque ya existe una Licencia entre medio");
                                alerta.showAndWait();
                            }

                        } else {
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Error de Fecha Inicio!");
                            alerta.setContentText("La Fecha de Inicio que a Propuesto no se puede porque es una fecha antigua a la actual");
                            alerta.showAndWait();
                        }
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error de Modificar");
                        alerta.setContentText("La Licencia que desea modificar en este empleado ya no está" + "\n" + "permitido.  Motivo: es una Fecha Pasada o ya se encuentra" + "\n" + "con Licencia");
                        alerta.showAndWait();
                    }

                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Días Disponibles!");
                    alerta.setContentText("La Fecha de Inicio y Fin que a Propuesto en dicho "+ "\n" +"tipo de licencia ya no quedan Días Disponibles");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Fechas!");
                alerta.setContentText("La Fecha de Inicio debe de ser Antes de la Fecha de Fin");
                alerta.showAndWait();
            }
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
}
