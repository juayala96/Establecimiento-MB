package com.secadero.modelo.informePresentismo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.*;
import java.sql.Time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Informe extends Component {
    FileOutputStream archivo;
    public Informe(){}

    public void informePresentismo(Label labLegajoEmpleado, DatePicker dpFechaDesde, DatePicker dpFechaHasta, Button btnGenerarFolrmulario) throws ParseException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String dato;
        String resultado = "YES";
        String resultadoFecha = "";
        String resultadoFecha2 = "";
        String fechaIncioL;
        String fechaFinL;
        int cont = 0;
        int cont2 = 0;
        int cont3 = 0;
        String resultado2 = "YES";
        Time horaEntrada = null;
        Time horaSalida = null;
        String ruta_archivo = "";
        int datoHoras = 0;

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // ------------------------- Fecha Inicio -------------------------------
        String fechaI = dpFechaDesde.getEditor().getText();
        Date fechaInicio = formatoFecha.parse(fechaI);

        // --------------------------- Fecha Fin --------------------------------
        String fechaF = dpFechaHasta.getEditor().getText();
        Date fechaFin = formatoFecha.parse(fechaF);

        // --------------------- Diferencia entre Fechas ------------------------
        long Diferencias = fechaInicio.getTime() - fechaFin.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

        try{
            if(labLegajoEmpleado.getText().equals("")){
                resultado = "NO";
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error!");
                alerta.setContentText("Error debe de seleccionar un empleado antes");
                alerta.showAndWait();
            }

            if(resultado.equals("YES")) {
                if ((-Cant_Dias) >= 0) {
                    String nombre = "";
                    String apellido = "";
                    int legajo = 0;
                    int dni = 0;
                    String telefono = "";
                    String email = "";

                    // ----------------------- Tomo el Nombre y Apellido del Empleado ------------------------
                    try {
                        String consulta1 = "SELECT nombre, apellido, legajo, dni, telefono, email FROM empleados WHERE legajo = ?";
                        pstm = con.prepareStatement(consulta1);
                        pstm.setString(1, labLegajoEmpleado.getText());
                        rs = pstm.executeQuery();
                        while (rs.next()) {
                            nombre = rs.getString("nombre");
                            apellido = rs.getString("apellido");
                            legajo = rs.getInt("legajo");
                            dni = rs.getInt("dni");
                            telefono = rs.getString("telefono");
                            email = rs.getString("email");
                        }
                    } catch (Exception e1) {
                        System.err.println("Error: " + e1.getMessage());
                    }

                    // ---------------------------------- Cantidad de Ausencias ------------------------------
                    // ------------------------- Fecha Inicio -------------------------------
                    String fechaI2 = dpFechaDesde.getEditor().getText();
                    Date fechaInicio2 = formatoFecha.parse(fechaI2);

                    // --------------------------- Fecha Fin --------------------------------
                    String fechaF2 = dpFechaHasta.getEditor().getText();
                    Date fechaFin2 = formatoFecha.parse(fechaF2);

                    // --------------------- Diferencia entre Fechas ------------------------
                    long Diferencias3 = fechaInicio2.getTime() - fechaFin2.getTime();
                    long Cant_Dias3 = Diferencias3 / (1000 * 60 * 60 * 24);

                    if (-Cant_Dias3 >= 0) {
                        int dia1 = Integer.parseInt((fechaI2.substring(8, 10)));
                        int mes = Integer.parseInt(fechaI2.substring(5, 7));
                        int resultadoDia = 0;
                        // ---------- Generar las Fechas que Existe desde el Inicio y Fin ------------
                        for (int i = -2; i < ((-Cant_Dias3) -1); i++) {
                            resultadoFecha = fechaI2.substring(0, 8) + dia1;

                            if (dia1 >= 29) {
                                if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia1 == 31) {
                                    dia1 = dia1 - 30;
                                    mes = mes + 1;
                                    resultadoFecha = fechaI2.substring(0, 4) + "-" + mes + "-" + dia1;
                                }
                                if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && dia1 == 32) {
                                    dia1 = dia1 - 31;
                                    mes = mes + 1;
                                    resultadoFecha = fechaI2.substring(0, 4) + "-" + mes + "-" + dia1;
                                }
                                if ((mes == 2) && dia1 == 29) {
                                    dia1 = dia1 - 28;
                                    mes = mes + 1;
                                    resultadoFecha = fechaI2.substring(0, 4) + "-" + mes + "-" + dia1;
                                }
                                if (!(mes == 2) && (dia1 == 29 || dia1 == 30)) {
                                    resultadoFecha = fechaI2.substring(0, 4) + "-" + mes + "-" + dia1;
                                }
                            } else {
                                resultadoFecha = fechaI2.substring(0, 4) + "-" + mes + "-" + dia1;
                            }
                            try {
                                String consulta2 = "SELECT fecha FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE legajo = ? AND fecha = ?";
                                pstm = con.prepareStatement(consulta2);
                                pstm.setString(1, labLegajoEmpleado.getText());
                                pstm.setString(2, resultadoFecha);
                                rs = pstm.executeQuery();
                                while (rs.next()) {
                                    cont += 1;
                                }
                            } catch (Exception e1) {
                                System.err.println("Error: " + e1.getMessage());
                            }

                            try {
                                String consultaEntrada = "SELECT fecha, hora FROM entrada INNER JOIN empleados ON empleados.idempleados = entrada.idEmpleadoFK WHERE empleados.legajo = ? AND entrada.fecha = ?";
                                pstm = con.prepareStatement(consultaEntrada);
                                pstm.setInt(1, Integer.parseInt(labLegajoEmpleado.getText()));
                                pstm.setString(2, resultadoFecha);
                                rs = pstm.executeQuery();
                                while (rs.next()) {
                                    horaEntrada = rs.getTime("hora");
                                    String horaEntrad = String.valueOf(horaEntrada);
                                    String horaE = horaEntrad.substring(0, 2);

                                    String consultaSalida = "SELECT fecha, hora FROM salida INNER JOIN empleados ON empleados.idempleados = salida.idEmpleadoFK WHERE empleados.legajo = ? AND salida.fecha = ?";
                                    pstm = con.prepareStatement(consultaSalida);
                                    pstm.setInt(1, Integer.parseInt(labLegajoEmpleado.getText()));
                                    pstm.setString(2, resultadoFecha);
                                    rs = pstm.executeQuery();
                                    while (rs.next()) {
                                        horaSalida = rs.getTime("hora");
                                        String horaSalid = String.valueOf(horaSalida);
                                        String horaS = horaSalid.substring(0, 2);

                                        for (int j = Integer.parseInt(horaE); j < Integer.parseInt(horaS); j++) {
                                            cont3 += 1;
                                        }
                                        for (int j = Integer.parseInt(horaS); j < Integer.parseInt(horaE); j++) {
                                            cont3 += 1;
                                        }
                                    }
                                }
                            } catch (Exception e1) {
                                System.err.println("Error: " + e1.getMessage());
                            }


                            // ---------------------------------- Cantidad de Licencias ------------------------------
                            try {
                                String consulta3 = "SELECT fecha_inicio, fecha_fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.legajo = ?";
                                pstm = con.prepareStatement(consulta3);
                                pstm.setString(1, labLegajoEmpleado.getText());
                                rs = pstm.executeQuery();
                                while (rs.next()) {
                                    fechaIncioL = rs.getString("fecha_inicio");
                                    fechaFinL = rs.getString("fecha_fin");

                                    // ---------------------------------- Cantidad de Ausencias ------------------------------
                                    // ------------------------- Fecha Inicio -------------------------------
                                    String fechaI3 = fechaIncioL;
                                    Date fechaInicio3 = formatoFecha.parse(fechaI3);

                                    // --------------------------- Fecha Fin --------------------------------
                                    String fechaF3 = fechaFinL;
                                    Date fechaFin3 = formatoFecha.parse(fechaF3);

                                    // --------------------- Diferencia entre Fechas ------------------------
                                    long Diferencias4 = fechaInicio3.getTime() - fechaFin3.getTime();
                                    long Cant_Dias4 = Diferencias4 / (1000 * 60 * 60 * 24);

                                    if (-Cant_Dias4 >= 0) {
                                        int dia2 = Integer.parseInt((fechaI3.substring(8, 10)));
                                        int mes2 = Integer.parseInt(fechaI3.substring(5, 7));
                                        int resultadoDia2 = 0;
                                        // ---------- Generar las Fechas que Existe desde el Inicio y Fin ------------
                                        for (int j = -2; j < ((-Cant_Dias4) - 1); j++) {
                                            resultadoFecha2 = fechaI3.substring(0, 8) + dia2;

                                            if (dia2 >= 29) {
                                                if ((mes2 == 4 || mes2 == 6 || mes2 == 9 || mes2 == 11) && dia2 == 31) {
                                                    dia2 = dia2 - 30;
                                                    mes2 = mes2 + 1;
                                                    resultadoFecha2 = fechaI3.substring(0, 4) + "-" + mes2 + "-" + dia2;
                                                }
                                                if ((mes2 == 1 || mes2 == 3 || mes2 == 5 || mes2 == 7 || mes2 == 8 || mes2 == 10 || mes2 == 12) && dia2 == 32) {
                                                    dia2 = dia2 - 31;
                                                    mes2 = mes2 + 1;
                                                    resultadoFecha2 = fechaI3.substring(0, 4) + "-" + mes2 + "-" + dia2;
                                                }
                                                if ((mes2 == 2) && dia2 == 29) {
                                                    dia2 = dia2 - 28;
                                                    mes2 = mes2 + 1;
                                                    resultadoFecha2 = fechaI3.substring(0, 4) + "-" + mes2 + "-" + dia2;
                                                }
                                                if (!(mes2 == 2) && (dia2 == 29 || dia2 == 30)) {
                                                    resultadoFecha2 = fechaI3.substring(0, 4) + "-" + mes2 + "-" + dia2;
                                                }
                                            } else {
                                                resultadoFecha2 = fechaI3.substring(0, 4) + "-" + mes2 + "-" + dia2;
                                            }

                                            if(resultadoFecha.equals(resultadoFecha2)){
                                                cont2 += 1;
                                            }

                                            dia2 += 1;
                                        }
                                    }
                                }
                            } catch (Exception e1) {
                                System.err.println("Error: " + e1.getMessage());
                            }

                            dia1 += 1;

                        }

                        try {
                            var doc = new Document();
                            // Nombre del Archivo
                            PdfWriter.getInstance(doc, new FileOutputStream(apellido + "_" + nombre + "_" + labLegajoEmpleado.getText() + ".pdf"));
                            doc.open();

                            var paragraph = new Paragraph("------------------------ INFORME DE PRESENTISMO DE " + nombre.toUpperCase() + " " + apellido.toUpperCase() + " ------------------------");
                            var saltoLinea = new Paragraph(" ");
                            var fechas = new Paragraph("Fechas Desde: " + dpFechaDesde.getEditor().getText() + "                                                                  Fecha Hasta: " + dpFechaHasta.getEditor().getText());
                            var datosPersonales = new Paragraph("DATOS PERSONALES");
                            var datosLegajo = new Paragraph("Legajo:       " + legajo);
                            var datosNombre= new Paragraph("Nombre:     " + nombre);
                            var datosApellido = new Paragraph("Apellido:     " + apellido);
                            var datosDNI = new Paragraph("DNI:            " + dni);
                            var datosTelefono= new Paragraph("Teléfono:    " + telefono);
                            var datosEmail = new Paragraph("E-mail:        " + email);

                            var cantidadAusencia = new Paragraph("Cantidad de días Ausente:   " + cont + " días");
                            var cantidadLicencias = new Paragraph("Cantidad de días Licencias:  " + cont2 + " días");
                            var cantidadHorasTrabajadas= new Paragraph("Cantidad de Horas Trabajadas:  " + cont3 + " Horas");

                            doc.add(paragraph);
                            doc.add(saltoLinea);
                            doc.add(fechas);
                            doc.add(saltoLinea);
                            doc.add(datosPersonales);
                            doc.add(datosLegajo);
                            doc.add(datosNombre);
                            doc.add(datosApellido);
                            doc.add(datosDNI);
                            doc.add(datosTelefono);
                            doc.add(datosEmail);
                            doc.add(saltoLinea);
                            doc.add(cantidadAusencia);
                            doc.add(cantidadLicencias);
                            doc.add(cantidadHorasTrabajadas);

                            doc.close();

                            File archivo = new File(apellido + "_" + nombre + "_" + labLegajoEmpleado.getText() + ".pdf");

                            String url = archivo.getAbsolutePath();
                            ProcessBuilder p = new ProcessBuilder();
                            p.command("cmd.exe", "/c", url);
                            p.start();

                        } catch (DocumentException | FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }

                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Fechas!");
                    alerta.setContentText("La Fecha de Inicio debe de ser Antes de la Fecha de Fin");
                    alerta.showAndWait();
                }
            }

        } catch (Exception e1){
            System.err.println("Error: " + e1.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error!");
                alerta.setContentText("Error en la Base de Datos");
                alerta.showAndWait();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }
}
