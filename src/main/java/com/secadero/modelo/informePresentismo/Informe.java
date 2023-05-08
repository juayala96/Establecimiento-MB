package com.secadero.modelo.informePresentismo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
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
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Informe extends Component {
    FileOutputStream archivo;

    public Informe() {
    }

    public void informePresentismo(Label labLegajoEmpleado, DatePicker dpFechaDesde, DatePicker dpFechaHasta) throws ParseException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String resultado2;
        String fechaIncioMax;
        String fechaFinMax;
        String fechaEntrada = "";
        String fechaSalida = "";
        int cantAusencias = 0;
        int cantLicencias = 0;
        int cantDiasTrabajadas = 0;
        int cantHorasTrabajadas = 0;
        Time horaEntrada = null;
        Time horaSalida = null;
        String nombre = "";
        String apellido = "";
        String telefono = "";
        String email = "";
        String area = "";
        String puesto = "";
        int legajo = 0;
        int dni = 0;
        Set<String> fechasDiasTrabajados = new HashSet<>();
        ArrayList<String> fechassEntrada = new ArrayList<>();
        ArrayList<String> fechassSalida = new ArrayList<>();
        ArrayList<String> horassEntrada = new ArrayList<>();
        ArrayList<String> horassSalida = new ArrayList<>();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        try {
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


            String fechaInicioClave = dpFechaDesde.getEditor().getText();
            String fechaAnio = fechaInicioClave.substring(6, 10);
            String fechaMes = fechaInicioClave.substring(3, 5);
            String fechaDia = fechaInicioClave.substring(0, 2);
            String fechaModificadaInicio = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
            String fechaFinClave = dpFechaHasta.getEditor().getText();
            String fechaAnio2 = fechaFinClave.substring(6, 10);
            String fechaMes2 = fechaFinClave.substring(3, 5);
            String fechaDia2 = fechaFinClave.substring(0, 2);
            String fechaModificadaFin = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);

            // ------------------------- Fecha Inicio -------------------------------
            String fechaI = fechaModificadaInicio;
            Date fechaInicio2 = formatoFecha.parse(fechaI);
            // --------------------------- Fecha Fin --------------------------------
            String fechaF = fechaModificadaFin;
            Date fechaFin2 = formatoFecha.parse(fechaF);


            // Establecer la fecha inicial y final
            LocalDate fechaInicial = LocalDate.parse(fechaI, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate fechaFinal = LocalDate.parse(fechaF, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Iterar sobre las fechas
            LocalDate resultadoFecha = fechaInicial;
            while (!resultadoFecha.isAfter(fechaFinal)) {
                // ----------------------------------- Cantidad de Ausencias ----------------------------------------
                try {
                    String consulta2 = "SELECT fecha FROM ausencias INNER JOIN empleados ON ausencias.idEmpleadoFK = empleados.idempleados WHERE legajo = ? AND fecha = ?";
                    pstm = con.prepareStatement(consulta2);
                    pstm.setString(1, labLegajoEmpleado.getText());
                    pstm.setString(2, String.valueOf(resultadoFecha));
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        cantAusencias += 1;
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
                        fechaIncioMax = rs.getString("fecha_inicio");
                        fechaFinMax = rs.getString("fecha_fin");

                        // Establecer la fecha inicial y final
                        LocalDate fechaInicial2 = LocalDate.parse(fechaIncioMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate fechaFinal2 = LocalDate.parse(fechaFinMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Iterar sobre las fechas
                        LocalDate resultadoFecha2 = fechaInicial2;
                        while (!resultadoFecha2.isAfter(fechaFinal2)) {
                            if (String.valueOf(resultadoFecha).equals(String.valueOf(resultadoFecha2))){
                                cantLicencias += 1;
                            }
                            resultadoFecha2 = resultadoFecha2.plusDays(1);
                        }
                    }
                } catch (Exception e1) {
                    System.err.println("Error: " + e1.getMessage());
                }

                // ---------------------------------- Cantidad de Horas Trabajadas ------------------------------
                try {
                    String consultaEntrada = "SELECT fecha AS fecha_entrada, hora FROM entrada INNER JOIN empleados ON empleados.idempleados = entrada.idEmpleadoFK WHERE empleados.legajo = ? AND entrada.fecha = ?";
                    pstm = con.prepareStatement(consultaEntrada);
                    pstm.setInt(1, Integer.parseInt(labLegajoEmpleado.getText()));
                    pstm.setString(2, String.valueOf(resultadoFecha));
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        String fechaEntradaX = rs.getString("fecha_entrada");
                        horaEntrada = rs.getTime("hora");
                        String horaEntrad = String.valueOf(horaEntrada);
                        fechassEntrada.add(fechaEntradaX);
                        horassEntrada.add(horaEntrad);
                    }

                    String consultaSalida = "SELECT fecha AS fecha_salida, hora FROM salida INNER JOIN empleados ON empleados.idempleados = salida.idEmpleadoFK WHERE empleados.legajo = ? AND salida.fecha = ?";
                    pstm = con.prepareStatement(consultaSalida);
                    pstm.setInt(1, Integer.parseInt(labLegajoEmpleado.getText()));
                    pstm.setString(2, String.valueOf(resultadoFecha));
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        String fechaSalidaX = rs.getString("fecha_salida");
                        horaSalida = rs.getTime("hora");
                        String horaSalid = String.valueOf(horaSalida);
                        fechassSalida.add(fechaSalidaX);
                        horassSalida.add(horaSalid);
                    }

                } catch (Exception e1) {
                    System.err.println("Error: " + e1.getMessage());
                }

                resultadoFecha = resultadoFecha.plusDays(1);
            }

            // ---------------------------------- Cantidad de Dias Trabajadas ------------------------------
            try {
                String consultaEntrada2 = "SELECT fecha FROM entrada INNER JOIN empleados ON empleados.idempleados = entrada.idEmpleadoFK WHERE empleados.legajo = ? AND (fecha BETWEEN ? and ?)";
                pstm = con.prepareStatement(consultaEntrada2);
                pstm.setInt(1, Integer.parseInt(labLegajoEmpleado.getText()));
                pstm.setString(2, fechaModificadaInicio);
                pstm.setString(3, fechaModificadaFin);
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
                cantDiasTrabajadas += 1;
            }

            // -------------------------------------------- Horas Trabajadas -------------------------------------------
            for (int i = 0; i < fechassEntrada.size() && i < horassEntrada.size() && i < fechassSalida.size() && i < horassSalida.size(); i++) {
                // Fecha Entrada
                String fechaAnioEntrada = fechassEntrada.get(i).substring(0, 4);
                String fechaMesEntrada = fechassEntrada.get(i).substring(5, 7);
                String fechaDiaEntrada = fechassEntrada.get(i).substring(8, 10);
                // Hora Entrada
                String horaE = horassEntrada.get(i).substring(0, 2);
                String horaEM = horassEntrada.get(i).substring(3, 5);
                // Fecha Salida
                String fechaAnioSalida = fechassSalida.get(i).substring(0, 4);
                String fechaMesSalida = fechassSalida.get(i).substring(5, 7);
                String fechaDiaSalida = fechassSalida.get(i).substring(8, 10);
                // Hora Salida
                String horaS = horassSalida.get(i).substring(0, 2);
                String horaSM = horassSalida.get(i).substring(3, 5);

                LocalDateTime fechaHoraENTRADA = LocalDateTime.of(Integer.parseInt(fechaAnioEntrada), Integer.parseInt(fechaMesEntrada), Integer.parseInt(fechaDiaEntrada), Integer.parseInt(horaE), Integer.parseInt(horaEM));
                LocalDateTime fechaHoraSALIDA = LocalDateTime.of(Integer.parseInt(fechaAnioSalida), Integer.parseInt(fechaMesSalida), Integer.parseInt(fechaDiaSalida), Integer.parseInt(horaS), Integer.parseInt(horaSM));

                // calcular la diferencia de tiempo entre las dos horas
                Duration duracion = Duration.between(fechaHoraENTRADA, fechaHoraSALIDA);
                long horas = duracion.toHours();

                cantHorasTrabajadas += horas;
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
                tableDatos.addCell("Legajo:"); tableDatos.addCell("" + legajo);
                tableDatos.addCell("Nombre:"); tableDatos.addCell("" + nombre);
                tableDatos.addCell("Apellido:"); tableDatos.addCell("" + apellido);
                tableDatos.addCell("DNI:"); tableDatos.addCell("" + dni);
                tableDatos.addCell("Teléfono:"); tableDatos.addCell("" + telefono);
                tableDatos.addCell("E-mail:"); tableDatos.addCell("" + email);
                tableDatos.addCell("Area:"); tableDatos.addCell("" + area);
                tableDatos.addCell("Puesto:"); tableDatos.addCell("" + puesto);

                PdfPTable table = new PdfPTable(3);
                table.addCell("Unidades"); table.addCell("Concepto"); table.addCell("Cantidad");

                table.addCell("(Días)");
                table.addCell("Total de Ausencias:");
                table.addCell( "" + cantAusencias);

                table.addCell("(Días)");
                table.addCell("Total de Licencias:");
                table.addCell( "" + cantLicencias);

                table.addCell("(Días)");
                table.addCell("Total Trabajadas:");
                table.addCell( "" + cantDiasTrabajadas);

                table.addCell("(Horas)");
                table.addCell("Total Trabajadas:");
                table.addCell("" + cantHorasTrabajadas);

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
        } catch (Exception e1){
            System.err.println("Error: " + e1.getMessage());
        } finally {
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
