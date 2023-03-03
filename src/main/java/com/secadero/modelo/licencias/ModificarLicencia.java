package com.secadero.modelo.licencias;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModificarLicencia {
    public ModificarLicencia(){}

    //--------------------------------------------- Modificar Licencia ---------------------------------------------------
    public void modificarLicencia(Label labIDEmpleadoModificar, Label labIDLicenciaModificar,  Label labDiasDisponiblesModificar,  DatePicker dpFechaModificarDuplicadaInicio, DatePicker dpFechaModificarDuplicadaFin, DatePicker dpFechaInicioModificar, DatePicker dpFechaFinModificar, ComboBox<String> cbTipoLicenciaModificar, Label labLimpiarCamposModificar) throws ParseException, SQLException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int anioGeneral;
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
        int dato2;
        int cont = 0;
        int cont2 = 0;
        Date datoFechaInicioMax;
        Date datoFechaFinMax;
        String resultadoFecha;
        String resultado2 = "YES";
        int datoIdTipoLicenciaFK;

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String fechaI = dpFechaInicioModificar.getEditor().getText();
        Date fechaInicio = formatoFecha.parse(fechaI);

        String fechaF = dpFechaFinModificar.getEditor().getText();
        Date fechaFin = formatoFecha.parse(fechaF);

        long Diferencias = fechaInicio.getTime() - fechaFin.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

        // ----------------------------------------------------------------------
        String fechaIpredefinido = dpFechaModificarDuplicadaInicio.getEditor().getText();
        Date fechaInicioDuplicado = formatoFecha.parse(fechaIpredefinido);

        String fechaFpredefinido = dpFechaModificarDuplicadaFin.getEditor().getText();
        Date fechaFinDuplicado = formatoFecha.parse(fechaFpredefinido);

        long DiferenciasDuplicado = fechaInicioDuplicado.getTime() - fechaFinDuplicado.getTime();
        long Cant_Dias2 = DiferenciasDuplicado / (1000 * 60 * 60 * 24);


        // --------------- Comprobación del año de las fechas que se encuentra o no en la Base de Datos----------------
        String fecha = dpFechaInicioModificar.getEditor().getText();
        String anio = fecha.replace("-", ", ");
        int anio2 = Integer.parseInt(anio.substring(0, 4));

        try {
            String consulta2 = "SELECT YEAR(fecha_Inicio) AS anio FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE licencias.estadoLicencia = ? AND idEmpleadoFK = ? AND tipo_licencias.descripcion = ? AND YEAR(fecha_Inicio) = ?";
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
                String consulta3 = "SELECT MIN(dias_disponibles) AS dias FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ? AND tipo_licencias.descripcion = ?";
                pstm = con.prepareStatement(consulta3);
                pstm.setString(1, labIDEmpleadoModificar.getText());
                pstm.setString(2, cbTipoLicenciaModificar.getSelectionModel().getSelectedItem());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    dato2 = rs.getInt("dias");
                    if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                        diasVacaciones = (int) ((dato2 - (-Cant_Dias)) + (-Cant_Dias2));
                    } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                        diasEnfermedad = (int) ((dato2 - (-Cant_Dias)) + (-Cant_Dias2));
                    } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Accidente")) {
                        diasAccidente = (int) ((dato2 - (-Cant_Dias)) + (-Cant_Dias2));
                    } else if (cbTipoLicenciaModificar.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                        diasMatrimonio = (int) ((dato2 - (-Cant_Dias)) + (-Cant_Dias2));
                    } else {
                        diasMuerteFamiliar = (int) ((dato2 - (-Cant_Dias)) + (-Cant_Dias2));
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
                        datoFechaInicioMax = rs.getDate("fecha_Inicio");
                        datoFechaFinMax = rs.getDate("fecha_Fin");

                        // ------------------------- Fecha Inicio -------------------------------
                        String fechaI2 = String.valueOf(datoFechaInicioMax);
                        Date fechaInicio2 = formatoFecha.parse(fechaI2);

                        // --------------------------- Fecha Fin --------------------------------
                        String fechaF2 = String.valueOf(datoFechaFinMax);
                        Date fechaFin2 = formatoFecha.parse(fechaF2);

                        // --------------------- Diferencia entre Fechas ------------------------
                        long Diferencias3 = fechaInicio2.getTime() - fechaFin2.getTime();
                        long Cant_Dias3 = Diferencias3 / (1000 * 60 * 60 * 24);

                        if (-Cant_Dias3 >= 0) {
                            int dia1 = Integer.parseInt((fechaI2.substring(8, 10)));

                            // ---------- Generar las Fechas que Existe desde el Inicio y Fin ------------
                            for (int i = -1; i < ((-Cant_Dias3) -1); i++) {
                                String diaFecha = fechaI2.substring(0, 8) + dia1;
                                dia1 += 1;
                                int mes = Integer.parseInt(fechaI2.substring(5, 7));

                                if (dia1 >= 29) {
                                    if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia1 == 31) {
                                        resultadoFecha = fechaI2.substring(0, 4) + "-" + (1 + mes) + "-" + (dia1 - 30);
                                        if (dpFechaInicioModificar.getEditor().getText().equals(resultadoFecha)) {
                                            resultado2 = "NO";
                                        }
                                        if (dpFechaFinModificar.getEditor().getText().equals(resultadoFecha)) {
                                            resultado2 = "NO";
                                        }
                                    }
                                    if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && dia1 == 32) {
                                        resultadoFecha = fechaI2.substring(0, 4) + "-" + (1 + mes) + "-" + (dia1 - 31);
                                        if (dpFechaInicioModificar.getEditor().getText().equals(resultadoFecha)) {
                                            resultado2 = "NO";
                                        }
                                        if (dpFechaFinModificar.getEditor().getText().equals(resultadoFecha)) {
                                            resultado2 = "NO";
                                        }
                                    }
                                    if ((mes == 2) && dia1 == 29) {
                                        resultadoFecha = fechaI2.substring(0, 4) + "-" + (1 + mes) + "-" + (dia1 - 28);
                                        if (dpFechaInicioModificar.getEditor().getText().equals(resultadoFecha)) {
                                            resultado2 = "NO";
                                        }
                                        if (dpFechaFinModificar.getEditor().getText().equals(resultadoFecha)) {
                                            resultado2 = "NO";
                                        }
                                    }
                                    if (!(mes == 2) && (dia1 == 29 || dia1 == 30)) {
                                        resultadoFecha = fechaI2.substring(0, 4) + "-" + mes + "-" + dia1;
                                        if (dpFechaInicioModificar.getEditor().getText().equals(resultadoFecha)) {
                                            resultado2 = "NO";
                                        }
                                        if (dpFechaFinModificar.getEditor().getText().equals(resultadoFecha)) {
                                            resultado2 = "NO";
                                        }
                                    }
                                } else {
                                    resultadoFecha = fechaI2.substring(0, 4) + "-" + mes + "-" + dia1;
                                    if (dpFechaInicioModificar.getEditor().getText().equals(resultadoFecha)) {
                                        resultado2 = "NO";
                                    }
                                    if (dpFechaFinModificar.getEditor().getText().equals(resultadoFecha)) {
                                        resultado2 = "NO";
                                    }
                                }
                                if (dpFechaInicioModificar.getEditor().getText().equals(diaFecha)) {
                                    resultado2 = "NO";
                                }
                                if (dpFechaFinModificar.getEditor().getText().equals(diaFecha)) {
                                    resultado2 = "NO";
                                }
                            }
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
                    pstm.setDate(3, java.sql.Date.valueOf(dpFechaInicioModificar.getEditor().getText())
                    );
                    pstm.setDate(4, java.sql.Date.valueOf(dpFechaFinModificar.getEditor().getText()));
                    pstm.setDate(5, java.sql.Date.valueOf(dpFechaInicioModificar.getEditor().getText())
                    );
                    pstm.setDate(6, java.sql.Date.valueOf(dpFechaFinModificar.getEditor().getText()));
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

                if((-Cant_DiasInicio) >= 0 && (-Cant_DiasFin) >= 0){
                    if((-Cant_DiasInicio2) >= 0){
                        if (resultado2.equals("YES")) {
                            String extraerAnio = dpFechaInicioModificar.getEditor().getText();
                            int exAnio = Integer.parseInt(extraerAnio.substring(0, 4));

                            try {
                                // -------------------------------------- Modificación -------------------------------------
                                String consulta4 = "SELECT idTipoLicencia FROM tipo_licencias WHERE descripcion = ?";
                                pstm = con.prepareStatement(consulta4);
                                pstm.setString(1, cbTipoLicenciaModificar.getSelectionModel().getSelectedItem());
                                rs = pstm.executeQuery();
                                while (rs.next()) {
                                    datoIdTipoLicenciaFK = Integer.parseInt(rs.getString("idTipoLicencia"));
                                    try {
                                        String consulta6 = "UPDATE licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados SET dias_disponibles = ? WHERE empleado_licencia.idEmpleadoFK = ? AND estadoLicencia = ? AND idTipoLicenciaFK = ? AND YEAR(fecha_Inicio) = ?";

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
                                        pstm.setInt(2, Integer.parseInt(labIDEmpleadoModificar.getText()));
                                        pstm.setString(3, "Vigente");
                                        pstm.setInt(4, datoIdTipoLicenciaFK);
                                        pstm.setInt(5, exAnio);
                                        pstm.executeUpdate();

                                    } catch (Exception e1) {
                                        System.err.println("Error: " + e1.getMessage());
                                    }

                                    try {
                                        String consulta7 = "UPDATE licencias SET fecha_Inicio = ?, fecha_Fin = ?, idTipoLicenciaFK = ? WHERE idLicencias = ?";
                                        pstm = con.prepareStatement(consulta7);
                                        pstm.setDate(1, java.sql.Date.valueOf(dpFechaInicioModificar.getEditor().getText()));
                                        pstm.setDate(2, java.sql.Date.valueOf(dpFechaFinModificar.getEditor().getText()));
                                        pstm.setInt(3, datoIdTipoLicenciaFK);
                                        pstm.setInt(4, Integer.parseInt(labIDLicenciaModificar.getText()));
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
    }
}
