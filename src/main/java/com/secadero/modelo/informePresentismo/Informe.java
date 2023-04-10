package com.secadero.modelo.informePresentismo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
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
import java.util.HashSet;
import java.util.Set;

public class Informe extends Component {
    FileOutputStream archivo;
    public Informe(){}

    public void informePresentismo(Label labLegajoEmpleado, DatePicker dpFechaDesde, DatePicker dpFechaHasta) throws ParseException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String resultadoFecha = "";
        String resultadoFecha2 = "";
        String fechaIncioL;
        String fechaFinL;
        String fechaEntrada = "";
        String fechaSalida = "";
        String fechaLaburado = "";
        int cont = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        Time horaEntrada = null;
        Time horaSalida = null;
        Set<String> fechasDiasTrabajados = new HashSet<>();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        try{
            String nombre = "";
            String apellido = "";
            String telefono = "";
            String email = "";
            String area = "";
            String puesto = "";
            int legajo = 0;
            int dni = 0;

            // ----------------------- Tomo el Nombre y Apellido del Empleado ------------------------
            try {
                String consulta1 = "SELECT nombre, apellido, legajo, dni, telefono, email, area.descripcion AS area, puesto.descripcion AS puesto FROM empleados INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto INNER JOIN area ON empleados.idAreaFK = area.idarea WHERE legajo = ?";
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
                    area = rs.getString("area");
                    puesto = rs.getString("puesto");
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
                for (int i = -2; i < ((-Cant_Dias3) - 1); i++) {
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

                    // ---------------------------------- Cantidad de Horas Trabajadas ------------------------------
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
                                    cont4 += 1;
                                }
                                for (int j = Integer.parseInt(horaS); j < Integer.parseInt(horaE); j++) {
                                    cont4 += 1;
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

                                    if (resultadoFecha.equals(resultadoFecha2)) {
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
                // ---------------------------------- Cantidad de Dias Trabajadas ------------------------------
                try {
                    String consultaEntrada2 = "SELECT fecha FROM entrada INNER JOIN empleados ON empleados.idempleados = entrada.idEmpleadoFK WHERE empleados.legajo = ? AND (fecha BETWEEN ? and ?)";
                    pstm = con.prepareStatement(consultaEntrada2);
                    pstm.setInt(1, Integer.parseInt(labLegajoEmpleado.getText()));
                    pstm.setString(2, dpFechaDesde.getEditor().getText());
                    pstm.setString(3, dpFechaHasta.getEditor().getText());
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        fechaEntrada = rs.getString("fecha");
                        fechasDiasTrabajados.add(fechaEntrada);
                    }
                    String consultaSalida2 = "SELECT fecha FROM salida INNER JOIN empleados ON empleados.idempleados = salida.idEmpleadoFK WHERE empleados.legajo = ? AND (fecha BETWEEN ? and ?)";
                    pstm = con.prepareStatement(consultaSalida2);
                    pstm.setInt(1, Integer.parseInt(labLegajoEmpleado.getText()));
                    pstm.setString(2, dpFechaDesde.getEditor().getText());
                    pstm.setString(3, dpFechaHasta.getEditor().getText());
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        fechaSalida = rs.getString("fecha");
                        fechasDiasTrabajados.add(fechaSalida);
                    }

                } catch (Exception e1) {
                    System.err.println("Error: " + e1.getMessage());
                }
                for (String elementos : fechasDiasTrabajados) {
                    cont3 += 1;
                }

                // ----------------------------------- Documento --------------------------------------
                try {
                    var doc = new Document();
                    // Nombre del Archivo
                    PdfWriter.getInstance(doc, new FileOutputStream("Informe_" + nombre + "_" + apellido + "_" + labLegajoEmpleado.getText() + "_" + dpFechaDesde.getEditor().getText() + "_" + dpFechaHasta.getEditor().getText() + ".pdf"));
                    doc.open();

                    var paragraph = new Paragraph("------------------------ INFORME DE PRESENTISMO DE " + nombre.toUpperCase() + " " + apellido.toUpperCase() + " ------------------------");
                    var saltoLinea = new Paragraph(" ");
                    var fechas = new Paragraph("Fechas Desde: " + dpFechaDesde.getEditor().getText() + "                                                                  Fecha Hasta: " + dpFechaHasta.getEditor().getText());
                    PdfPTable tableTitulo = new PdfPTable(1);
                    tableTitulo.addCell("DATOS PERSONALES");

                    PdfPTable tableDatos = new PdfPTable(2);
                    tableDatos.addCell("Legajo:");
                    tableDatos.addCell("" + legajo);
                    tableDatos.addCell("Nombre:");
                    tableDatos.addCell("" + nombre);
                    tableDatos.addCell("Apellido:");
                    tableDatos.addCell("" + apellido);
                    tableDatos.addCell("DNI:");
                    tableDatos.addCell("" + dni);
                    tableDatos.addCell("Teléfono:");
                    tableDatos.addCell("" + telefono);
                    tableDatos.addCell("E-mail:");
                    tableDatos.addCell("" + email);
                    tableDatos.addCell("Area:");
                    tableDatos.addCell("" + area);
                    tableDatos.addCell("Puesto:");
                    tableDatos.addCell("" + puesto);

                    PdfPTable table = new PdfPTable(3);
                    table.addCell("Unidades");
                    table.addCell("Concepto");
                    table.addCell("Cantidad");

                    table.addCell("(Días)");
                    table.addCell("Total de Ausencias:");
                    table.addCell( "" + cont);

                    table.addCell("(Días)");
                    table.addCell("Total de Licencias:");
                    table.addCell( "" + cont2);

                    table.addCell("(Días)");
                    table.addCell("Total Trabajadas:");
                    table.addCell( "" + cont3);

                    table.addCell("(Horas)");
                    table.addCell("Total Trabajadas:");
                    table.addCell("" + cont4);

                    doc.add(paragraph);
                    doc.add(saltoLinea);
                    doc.add(fechas);
                    doc.add(saltoLinea);
                    doc.add(tableTitulo);
                    doc.add(tableDatos);
                    doc.add(saltoLinea);
                    doc.add(table);

                    doc.close();

                    File archivo = new File("Informe_" + nombre + "_" + apellido + "_" + labLegajoEmpleado.getText() + "_" + dpFechaDesde.getEditor().getText() + "_" + dpFechaHasta.getEditor().getText() + ".pdf");

                    String url = archivo.getAbsolutePath();
                    ProcessBuilder p = new ProcessBuilder();
                    p.command("cmd.exe", "/c", url);
                    p.start();

                } catch (DocumentException | FileNotFoundException e) {
                    e.printStackTrace();
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
