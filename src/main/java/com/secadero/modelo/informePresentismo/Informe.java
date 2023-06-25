package com.secadero.modelo.informePresentismo;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
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
        String resultado2;          String fechaIncioMax;
        String fechaFinMax;         String fechaEntrada = "";
        String fechaSalida = "";    int cantAusencias = 0;
        int cantLicencias = 0;      int cantDiasTrabajadas = 0;
        int cantHorasTrabajadas = 0;Time horaEntrada = null;
        Time horaSalida = null;     String nombre = "";
        String apellido = "";       String area = "";
        String puesto = "";         int legajo = 0;
        int dni = 0;                String fechaModificadaIngreso = "";
        Set<String> fechasDiasTrabajados = new HashSet<>();
        ArrayList<String> fechassEntrada = new ArrayList<>();
        ArrayList<String> fechassSalida = new ArrayList<>();
        ArrayList<String> horassEntrada = new ArrayList<>();
        ArrayList<String> horassSalida = new ArrayList<>();
        int cantLicenciasVacaciones = 0;    String cantLicenciasVacacionesString = "";
        int cantLicenciasEnfermedad = 0;    String cantLicenciasEnfermedadString = "";
        int cantLicenciasAccidente = 0;     String cantLicenciasAccidenteString = "";
        int cantLicenciasMatrimonio = 0;    String cantLicenciasMatrimonioString = "";
        int cantLicenciasMuerteFamiliar = 0;String cantLicenciasMuerteFamiliarString = "";

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // ----------------------- Tomo el Nombre y Apellido del Empleado ------------------------
            try {
                String consulta1 = "SELECT nombre, apellido, legajo, dni, fechaIngreso, area.descripcion AS area, puesto.descripcion AS puesto FROM empleados INNER JOIN puesto ON empleados.idPuestoFK = puesto.idpuesto INNER JOIN area ON empleados.idAreaFK = area.idarea WHERE legajo = ?";
                pstm = con.prepareStatement(consulta1);
                pstm.setString(1, labLegajoEmpleado.getText());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    nombre = rs.getString("nombre");
                    apellido = rs.getString("apellido");
                    legajo = rs.getInt("legajo");
                    dni = rs.getInt("dni");
                    String fechaClave5 = rs.getString("fechaIngreso");
                    String fechaAnio3 = fechaClave5.substring(0, 4);
                    String fechaMes3 = fechaClave5.substring(5, 7);
                    String fechaDia3 = fechaClave5.substring(8, 10);
                    fechaModificadaIngreso = (fechaDia3 + "-" + fechaMes3 + "-" + fechaAnio3);
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


                // ---------------------------------- Cantidad de Licencias (Tipos) ------------------------------
                try {
                    String consultaVacaciones = "SELECT fecha_inicio, fecha_fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia WHERE empleados.legajo = ? AND tipo_licencias.descripcion = ?";
                    pstm = con.prepareStatement(consultaVacaciones);
                    pstm.setString(1, labLegajoEmpleado.getText());
                    pstm.setString(2, "Vacaciones");
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        fechaIncioMax = rs.getString("fecha_inicio");
                        fechaFinMax = rs.getString("fecha_fin");

                        // Establecer la fecha inicial y final
                        LocalDate fechaInicial2 = LocalDate.parse(fechaIncioMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate fechaFinal2 = LocalDate.parse(fechaFinMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Iterar sobre las fechas
                        LocalDate resultadoFechaVacaciones = fechaInicial2;
                        while (!resultadoFechaVacaciones.isAfter(fechaFinal2)) {
                            if (String.valueOf(resultadoFecha).equals(String.valueOf(resultadoFechaVacaciones))){
                                cantLicenciasVacaciones += 1;
                            }
                            resultadoFechaVacaciones = resultadoFechaVacaciones.plusDays(1);
                        }
                    }

                    String consultaEnfermedad = "SELECT fecha_inicio, fecha_fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia WHERE empleados.legajo = ? AND tipo_licencias.descripcion = ?";
                    pstm = con.prepareStatement(consultaEnfermedad);
                    pstm.setString(1, labLegajoEmpleado.getText());
                    pstm.setString(2, "Enfermedad");
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        fechaIncioMax = rs.getString("fecha_inicio");
                        fechaFinMax = rs.getString("fecha_fin");

                        // Establecer la fecha inicial y final
                        LocalDate fechaInicial2 = LocalDate.parse(fechaIncioMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate fechaFinal2 = LocalDate.parse(fechaFinMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Iterar sobre las fechas
                        LocalDate resultadoFechaEnfermedad = fechaInicial2;
                        while (!resultadoFechaEnfermedad.isAfter(fechaFinal2)) {
                            if (String.valueOf(resultadoFecha).equals(String.valueOf(resultadoFechaEnfermedad))){
                                cantLicenciasEnfermedad += 1;
                            }
                            resultadoFechaEnfermedad = resultadoFechaEnfermedad.plusDays(1);
                        }
                    }

                    String consultaAccidente = "SELECT fecha_inicio, fecha_fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia WHERE empleados.legajo = ? AND tipo_licencias.descripcion = ?";
                    pstm = con.prepareStatement(consultaAccidente);
                    pstm.setString(1, labLegajoEmpleado.getText());
                    pstm.setString(2, "Accidente");
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        fechaIncioMax = rs.getString("fecha_inicio");
                        fechaFinMax = rs.getString("fecha_fin");

                        // Establecer la fecha inicial y final
                        LocalDate fechaInicial2 = LocalDate.parse(fechaIncioMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate fechaFinal2 = LocalDate.parse(fechaFinMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Iterar sobre las fechas
                        LocalDate resultadoFechaAccidente = fechaInicial2;
                        while (!resultadoFechaAccidente.isAfter(fechaFinal2)) {
                            if (String.valueOf(resultadoFecha).equals(String.valueOf(resultadoFechaAccidente))){
                                cantLicenciasAccidente += 1;
                            }
                            resultadoFechaAccidente = resultadoFechaAccidente.plusDays(1);
                        }
                    }

                    String consultaMatrimonio = "SELECT fecha_inicio, fecha_fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia WHERE empleados.legajo = ? AND tipo_licencias.descripcion = ?";
                    pstm = con.prepareStatement(consultaMatrimonio);
                    pstm.setString(1, labLegajoEmpleado.getText());
                    pstm.setString(2, "Matrimonio");
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        fechaIncioMax = rs.getString("fecha_inicio");
                        fechaFinMax = rs.getString("fecha_fin");

                        // Establecer la fecha inicial y final
                        LocalDate fechaInicial2 = LocalDate.parse(fechaIncioMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate fechaFinal2 = LocalDate.parse(fechaFinMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Iterar sobre las fechas
                        LocalDate resultadoFechaMatrimonio = fechaInicial2;
                        while (!resultadoFechaMatrimonio.isAfter(fechaFinal2)) {
                            if (String.valueOf(resultadoFecha).equals(String.valueOf(resultadoFechaMatrimonio))){
                                cantLicenciasMatrimonio += 1;
                            }
                            resultadoFechaMatrimonio = resultadoFechaMatrimonio.plusDays(1);
                        }
                    }

                    String consultaMuerteFamiliar = "SELECT fecha_inicio, fecha_fin FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia WHERE empleados.legajo = ? AND tipo_licencias.descripcion = ?";
                    pstm = con.prepareStatement(consultaMuerteFamiliar);
                    pstm.setString(1, labLegajoEmpleado.getText());
                    pstm.setString(2, "Muerte Familiar");
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        fechaIncioMax = rs.getString("fecha_inicio");
                        fechaFinMax = rs.getString("fecha_fin");

                        // Establecer la fecha inicial y final
                        LocalDate fechaInicial2 = LocalDate.parse(fechaIncioMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate fechaFinal2 = LocalDate.parse(fechaFinMax, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Iterar sobre las fechas
                        LocalDate resultadoFechaMuerteFamiliar = fechaInicial2;
                        while (!resultadoFechaMuerteFamiliar.isAfter(fechaFinal2)) {
                            if (String.valueOf(resultadoFecha).equals(String.valueOf(resultadoFechaMuerteFamiliar))){
                                cantLicenciasMuerteFamiliar += 1;
                            }
                            resultadoFechaMuerteFamiliar = resultadoFechaMuerteFamiliar.plusDays(1);
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
                pstm.setString(2, fechaModificadaInicio);
                pstm.setString(3, fechaModificadaFin);
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

            if(cantLicenciasVacaciones == 0){
                cantLicenciasVacacionesString = "---";
            } else {
                cantLicenciasVacacionesString = cantLicenciasVacaciones + "";
            }
            if(cantLicenciasEnfermedad == 0){
                cantLicenciasEnfermedadString = "---";
            } else {
                cantLicenciasEnfermedadString = cantLicenciasEnfermedad + "";
            }
            if(cantLicenciasAccidente == 0){
                cantLicenciasAccidenteString = "---";
            } else {
                cantLicenciasAccidenteString = cantLicenciasAccidente + "";
            }
            if(cantLicenciasMatrimonio == 0){
                cantLicenciasMatrimonioString = "---";
            } else {
                cantLicenciasMatrimonioString = cantLicenciasMatrimonio + "";
            }
            if(cantLicenciasMuerteFamiliar == 0){
                cantLicenciasMuerteFamiliarString = "---";
            } else {
                cantLicenciasMuerteFamiliarString = cantLicenciasMuerteFamiliar + "";
            }

            // ----------------------------------- Documento --------------------------------------
            try {
                var doc = new Document();
                // Nombre del Archivo
                PdfWriter.getInstance(doc, new FileOutputStream("Informe_" + nombre + "_" + apellido + "_" + labLegajoEmpleado.getText() + "_" + dpFechaDesde.getEditor().getText() + "_" + dpFechaHasta.getEditor().getText() + ".pdf"));
                doc.open();

                // Crear una tabla
                PdfPTable tablaGeneral = new PdfPTable(1);
                tablaGeneral.setWidthPercentage(100);
                // Agregar contenido al documento
                tablaGeneral.addCell(createCellNormal("Bida Miguel Marcelo Ivan                                                                                               INFORME-PRESENTISMO\nLote 8 - (3361) Los Helechos \nC.U.I.T.: 23-32844652-9                                                                                   Período:  " + dpFechaDesde.getEditor().getText() + "  |  " + dpFechaHasta.getEditor().getText(), FontFactory.getFont(FontFactory.HELVETICA, 10)));

                // Datos Personales
                PdfPTable tablaInformacionPersonal = new PdfPTable(6);
                tablaInformacionPersonal.setWidthPercentage(100);
                tablaInformacionPersonal.addCell(createCell("Legajo\n\n" + legajo, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Nombre y Apellido\n\n" + nombre + " " + apellido, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("DNI\n\n" + dni, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Fecha Ing.\n\n" + fechaModificadaIngreso, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Area\n\n" + area, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Puesto\n\n" + puesto, FontFactory.getFont(FontFactory.HELVETICA, 9)));

                // Descripción
                PdfPTable tablaDescripcion = new PdfPTable(2);
                tablaDescripcion.setWidthPercentage(100);
                tablaDescripcion.addCell(createCell2("DESCRIPCIÓN", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaDescripcion.addCell(createCell2("CANTIDAD", FontFactory.getFont(FontFactory.HELVETICA, 9)));

                // Datos
                PdfPTable tablaDatos = new PdfPTable(2);
                tablaDatos.setWidthPercentage(100);
                PdfPCell cellConcepto = createCellNormal("# Días Trabajados \n\n# Horas Trabajados \n\n# Días Ausentes", FontFactory.getFont(FontFactory.HELVETICA, 10));
                tablaDatos.addCell(cellConcepto);
                PdfPCell cellCantidad = createCellNormal("  " + cantDiasTrabajadas + "\n\n  " + cantHorasTrabajadas + "\n\n  " + cantAusencias, FontFactory.getFont(FontFactory.HELVETICA, 10));
                tablaDatos.addCell(cellCantidad);

                // Datos Tipos de Licencias
                PdfPTable tablaDatosLicencia = new PdfPTable(2);
                tablaDatosLicencia.setWidthPercentage(100);
                PdfPCell cellConcepto2 = createCellNormal("Vacaciones \n\nEnfermedad \n\nAccidente \n\nMatrimonio \n\nMuerte Familiar", FontFactory.getFont(FontFactory.HELVETICA, 10));
                tablaDatosLicencia.addCell(cellConcepto2);
                PdfPCell cellCantidad2 = createCellNormal("  " + cantLicenciasVacacionesString + "\n\n  " + cantLicenciasEnfermedadString + "\n\n  " + cantLicenciasAccidenteString + "\n\n  " + cantLicenciasMatrimonioString + "\n\n  " + cantLicenciasMuerteFamiliarString, FontFactory.getFont(FontFactory.HELVETICA, 10));
                tablaDatosLicencia.addCell(cellCantidad2);

                // Datos del Total de Días de Licencias
                PdfPTable tablaLicencia = new PdfPTable(2);
                tablaLicencia.setWidthPercentage(100);
                PdfPCell cellDescripcionLicencia = createCell3("  # Total Días Licencias", FontFactory.getFont(FontFactory.HELVETICA, 10));
                tablaLicencia.addCell(cellDescripcionLicencia);
                PdfPCell cellCantidadLicencia = createCell3("   " + cantLicencias, FontFactory.getFont(FontFactory.HELVETICA, 10));
                tablaLicencia.addCell(cellCantidadLicencia);

                // Agregar la tabla al documento
                doc.add(tablaGeneral);
                doc.add(tablaInformacionPersonal);
                doc.add(tablaDescripcion);
                doc.add(tablaDatos);
                doc.add(tablaDatosLicencia);
                doc.add(tablaLicencia);
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
                alerta.setTitle("Error - Informe");
                alerta.setContentText("Error en la base de datos.");
                alerta.showAndWait();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }

    private static PdfPCell createCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static PdfPCell createCell2(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static PdfPCell createCell3(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(6);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    private static PdfPCell createCellNormal(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setPadding(10);
        return cell;
    }
}
