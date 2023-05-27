package com.secadero.modelo.preRecibo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.secadero.conexion.Conexion;
import com.secadero.modelo.preRecibo.modificaciones.Porcentaje;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PreRecibo {
    public PreRecibo (){}

    public void preRecibo(Label labLegajoEmpleado, DatePicker dpFechaDesde, DatePicker dpFechaHasta){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String nombre = "";         String apellido = "";
        String fechaIngreso = "";   String area = "";           String area2 = "";
        String puesto = "";         String fechaModificadaIngreso = "";
        int legajo = 0;             int dni = 0;
        String fechaEntrada = "";   String fechaSalida = "";
        int cantDiasTrabajadas = 0; int sueldoBase = 0;
        int salarioPorDia = 0;
        int antiguedad = 0;         int rectroactivo = 0;
        int jubilacion = 0;         int obraSocial = 0;
        int ley = 0;                int seguroSepelio = 0;
        int cuotaAgraria = 0;       int cantLicencias = 0;
        String fechaIncioMax;       String fechaFinMax;
        int totalSueldoLicencia = 0;String fechaIngresoX = "";
        int aniosAntiguedad = 0;    int porcentajeGananciaAntiguedad;
        double antiguedadTotal;

        Set<String> fechasDiasTrabajados = new HashSet<>();

        try{
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

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            // ------------------------- Fecha Inicio -------------------------------
            String fechaI = fechaModificadaInicio;
            Date fechaInicio2 = formatoFecha.parse(fechaI);
            // --------------------------- Fecha Fin --------------------------------
            String fechaF = fechaModificadaFin;
            Date fechaFin2 = formatoFecha.parse(fechaF);

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

            // ------------------------------------ Sueldo por Dia ------------------------------------
            try {
                String consultaSueldo = "SELECT sueldo FROM pre_recibo INNER JOIN area ON pre_recibo.idAreaFK = area.idarea INNER JOIN puesto ON pre_recibo.idPuestoFK = puesto.idpuesto INNER JOIN empleados ON empleados.idAreaFK = area.idarea AND empleados.idPuestoFK = puesto.idpuesto WHERE empleados.legajo = ?";
                pstm = con.prepareStatement(consultaSueldo);
                pstm.setInt(1, Integer.parseInt(labLegajoEmpleado.getText()));
                rs = pstm.executeQuery();
                while (rs.next()) {
                    sueldoBase = rs.getInt("sueldo");
                    salarioPorDia = sueldoBase * cantDiasTrabajadas;
                }
            } catch (Exception e1) {
                System.err.println("Error: " + e1.getMessage());
            }

            // ----------------------------------------- Unidades Porcentajes ---------------------------------------
            try {
                String consultaPorcentaje = "SELECT antiguedad, rectroactivo, jubilacion, ley, obraSocial, seguroSepelio, cuotaAgraria FROM porcentajes";
                pstm = con.prepareStatement(consultaPorcentaje);
                rs = pstm.executeQuery();

                while (rs.next()){
                    antiguedad = rs.getInt("antiguedad");
                    rectroactivo = rs.getInt("rectroactivo");
                    jubilacion = rs.getInt("jubilacion");
                    ley = rs.getInt("ley");
                    obraSocial = rs.getInt("obraSocial");
                    seguroSepelio = rs.getInt("seguroSepelio");
                    cuotaAgraria = rs.getInt("cuotaAgraria");
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex.getMessage());
            }


            // ---------------------------------- Cantidad de Licencias ------------------------------
            try {
                // Establecer la fecha inicial y final
                LocalDate fechaInicial = LocalDate.parse(fechaI, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate fechaFinal = LocalDate.parse(fechaF, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // Iterar sobre las fechas
                LocalDate resultadoFechaX = fechaInicial;
                while (!resultadoFechaX.isAfter(fechaFinal)) {
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
                            if (String.valueOf(resultadoFechaX).equals(String.valueOf(resultadoFecha2))){
                                cantLicencias += 1;
                            }
                            resultadoFecha2 = resultadoFecha2.plusDays(1);
                        }
                    }
                    resultadoFechaX = resultadoFechaX.plusDays(1);
                }
            } catch (Exception e1) {
                System.err.println("Error: " + e1.getMessage());
            }

            // ----------------------------------------- Antigüedad ---------------------------------------
            try {
                String consultaAntiguedad = "SELECT fechaIngreso FROM empleados WHERE legajo = ?";
                pstm = con.prepareStatement(consultaAntiguedad);
                pstm.setInt(1, Integer.parseInt(labLegajoEmpleado.getText()));
                rs = pstm.executeQuery();

                while (rs.next()){
                    fechaIngresoX = rs.getString("fechaIngreso");
                }

                // Fecha de ingreso
                LocalDate fechaIngresoX2 = LocalDate.of(Integer.parseInt(fechaIngresoX.substring(0, 4)), Integer.parseInt(fechaIngresoX.substring(5, 7)), Integer.parseInt(fechaIngresoX.substring(8, 10)));
                // Fecha actual
                LocalDate fechaInicioM = LocalDate.parse(fechaModificadaInicio);
                // Calcular la antigüedad
                Period antiguedadX = Period.between(fechaIngresoX2, fechaInicioM);

                // Obtener los años de antigüedad
                aniosAntiguedad = antiguedadX.getYears();

            } catch (SQLException ex) {
                System.err.println("Error: " + ex.getMessage());
            }

            // ----------------------------------- Documento --------------------------------------
            try {
                // ------------------------ Cálculos ------------------------
                totalSueldoLicencia = sueldoBase * cantLicencias;
                if(aniosAntiguedad > 0){
                    porcentajeGananciaAntiguedad = antiguedad * aniosAntiguedad;
                } else {
                    porcentajeGananciaAntiguedad = aniosAntiguedad;
                }
                double porcentajeGananciaAntiguedadFlotante = porcentajeGananciaAntiguedad;
                antiguedadTotal = salarioPorDia * (porcentajeGananciaAntiguedadFlotante / 100);
                double rectroactivoFloat = rectroactivo;
                double rectroactivoTotal = ((salarioPorDia + antiguedadTotal + totalSueldoLicencia) * (rectroactivoFloat / 100));
                double totalesRemuneracion = salarioPorDia + antiguedadTotal + totalSueldoLicencia + rectroactivoTotal;
                double jubilacionFloat = jubilacion;
                double jublilacionTotal = totalesRemuneracion * (jubilacionFloat / 100);
                double leyFloat = ley;
                double leyTotal = totalesRemuneracion * (leyFloat / 100);
                double obraSocialFloat = obraSocial;
                double obraSocialTotal = totalesRemuneracion * (obraSocialFloat / 100);
                double seguroSepelioFloat = seguroSepelio;
                double seguroSepelioTotal = totalesRemuneracion * (seguroSepelioFloat / 100);
                double cuotaAgrariaFloat = cuotaAgraria;
                double cuotaAgrariaTotal = totalesRemuneracion * (cuotaAgrariaFloat / 100);
                double totalesDeducciones = jublilacionTotal + leyTotal + obraSocialTotal + seguroSepelioTotal + cuotaAgrariaTotal;
                double netoTotal = totalesRemuneracion - totalesDeducciones;


                DecimalFormat formato = new DecimalFormat("#,###"); // Crea el formato con el punto
                String sueldoBaseFormateado = formato.format(sueldoBase);
                String salarioPorDiaFormateado = formato.format(salarioPorDia);
                String antiguedadTotalFormateado = formato.format((int)(antiguedadTotal));
                String totalSueldoLicenciaFormateado = formato.format((int)(totalSueldoLicencia));
                String rectroactivoTotalFormateado = formato.format((int)(rectroactivoTotal));
                String jubilacionTotalFormateado = formato.format((int)(jublilacionTotal));
                String leyTotalFormateado = formato.format((int)(leyTotal));
                String obraSocialTotalFormateado = formato.format((int)(obraSocialTotal));
                String seguroSepelioTotalFormateado = formato.format((int)(seguroSepelioTotal));
                String cuotaAgrariaTotalFormateado = formato.format((int)(cuotaAgrariaTotal));
                String totalesRemuneracionFormateado = formato.format((int)(totalesRemuneracion));
                String totalesDeduccionesFormateado = formato.format((int)(totalesDeducciones));
                String netoTotalFormateado = formato.format((int)(netoTotal));

                var doc = new Document();
                // Nombre del Archivo
                PdfWriter.getInstance(doc, new FileOutputStream("PreRecibo_" + nombre + "_" + apellido + "_" + labLegajoEmpleado.getText() + "_" + dpFechaDesde.getEditor().getText() + "_" + dpFechaHasta.getEditor().getText() + ".pdf"));
                doc.open();
                // Crear una tabla
                PdfPTable tablaGeneral = new PdfPTable(1);
                tablaGeneral.setWidthPercentage(100);
                // Agregar contenido al documento
                tablaGeneral.addCell(createCellNormal("Bida Miguel Marcelo Ivan                                                                                                                     PRE-RECIBO\nLote 8 - (3361) Los Helechos \nC.U.I.T.: 23-32844652-9                                                                                   Período:  " + dpFechaDesde.getEditor().getText() + "  |  " + dpFechaHasta.getEditor().getText(), FontFactory.getFont(FontFactory.HELVETICA, 10)));

                if(area.equals("Administracion")){
                    area2 = area.substring(0, 13);
                } else {
                    area2 = area;
                }

                // Datos Personales
                PdfPTable tablaInformacionPersonal = new PdfPTable(7);
                tablaInformacionPersonal.setWidthPercentage(100);
                tablaInformacionPersonal.addCell(createCell("Legajo\n\n" + legajo, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Nombre y Apellido\n\n" + nombre + " " + apellido, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("DNI\n\n" + dni, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Fecha Ing.\n\n" + fechaModificadaIngreso, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Area\n\n" + area2, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Puesto\n\n" + puesto, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Sueldo (Día)\n\n$ " + sueldoBaseFormateado, FontFactory.getFont(FontFactory.HELVETICA, 9)));

                // Descripción
                PdfPTable tablaDescripcion = new PdfPTable(4);
                tablaDescripcion.setWidthPercentage(100);
                tablaDescripcion.addCell(createCell2("CONCEPTO", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaDescripcion.addCell(createCell2("UNIDADES", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaDescripcion.addCell(createCell2("REMUNERACIÓN", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaDescripcion.addCell(createCell2("DEDUCCIONES", FontFactory.getFont(FontFactory.HELVETICA, 9)));

                // Concepto
                PdfPTable tablaCuerpo = new PdfPTable(4);
                tablaCuerpo.setWidthPercentage(100);
                if(cantLicencias > 0){
                    tablaCuerpo.addCell(createCellNormal("Días Laborales \n\nAntigüedad \n\nDías Licencias \n\nRetroactive \n\nJubilación CCG \n\nLey 19032 CCG \n\nObra Social CCG \n\nSeguro de Sepelio \n\nCuota Solid. Agraria", FontFactory.getFont(FontFactory.HELVETICA, 10)));

                    // Unidades
                    tablaCuerpo.addCell(createCellNormal(cantDiasTrabajadas +" \n\n" + porcentajeGananciaAntiguedad + " %\n\n" + cantLicencias + " \n\n" + rectroactivo + " %\n\n" + jubilacion + " %\n\n" + ley + " %\n\n"  + obraSocial + " %\n\n" + seguroSepelio + " %\n\n" + cuotaAgraria + " %",FontFactory.getFont(FontFactory.HELVETICA, 10)));

                    // Remuneración
                    tablaCuerpo.addCell(createCellNormal("$ " + salarioPorDiaFormateado +" \n\n$ " + antiguedadTotalFormateado + " \n\n$ " + totalSueldoLicenciaFormateado + "\n\n$ " + rectroactivoTotalFormateado, FontFactory.getFont(FontFactory.HELVETICA, 10)));

                    // Deducciones
                    tablaCuerpo.addCell(createCellNormal("\n\n\n\n\n\n\n\n$ " + jubilacionTotalFormateado + " \n\n$ " + leyTotalFormateado + " \n\n$ " + obraSocialTotalFormateado + " \n\n$ " + seguroSepelioTotalFormateado + " \n\n$ " + cuotaAgrariaTotalFormateado, FontFactory.getFont(FontFactory.HELVETICA, 10)));
                } else {
                    tablaCuerpo.addCell(createCellNormal("Días Laborales \n\nAntigüedad \n\nRetroactive \n\nJubilación CCG \n\nLey 19032 CCG \n\nObra Social CCG \n\nSeguro de Sepelio \n\nCuota Solid. Agraria", FontFactory.getFont(FontFactory.HELVETICA, 10)));

                    // Unidades
                    tablaCuerpo.addCell(createCellNormal(cantDiasTrabajadas +" \n\n" + porcentajeGananciaAntiguedad + " %\n\n" + rectroactivo + " %\n\n" + jubilacion + " %\n\n" + ley + " %\n\n"  + obraSocial + " %\n\n" + seguroSepelio + " %\n\n" + cuotaAgraria + " %",FontFactory.getFont(FontFactory.HELVETICA, 10)));

                    // Remuneración
                    tablaCuerpo.addCell(createCellNormal("$ " + salarioPorDiaFormateado +" \n\n$ " + antiguedadTotalFormateado + "\n\n$ " + rectroactivoTotalFormateado, FontFactory.getFont(FontFactory.HELVETICA, 10)));

                    // Deducciones
                    tablaCuerpo.addCell(createCellNormal("\n\n\n\n\n\n$ " + jubilacionTotalFormateado + " \n\n$ " + leyTotalFormateado + " \n\n$ " + obraSocialTotalFormateado + " \n\n$ " + seguroSepelioTotalFormateado + " \n\n$ " + cuotaAgrariaTotalFormateado, FontFactory.getFont(FontFactory.HELVETICA, 10)));
                }

                PdfPTable tablaVacia = new PdfPTable(1);
                tablaVacia.setWidthPercentage(100);
                tablaVacia.addCell(createCell(" ", FontFactory.getFont(FontFactory.HELVETICA, 5)));

                // Totales
                PdfPTable tablaTotales = new PdfPTable(4);
                tablaTotales.setWidthPercentage(100);
                PdfPCell cellTotales = createCellCombinada("TOTALES", FontFactory.getFont(FontFactory.HELVETICA, 9), 2, Element.ALIGN_RIGHT);
                tablaTotales.addCell(cellTotales);
                PdfPCell cellTotalesRemuneracion = createCellCombinada(" $  " + totalesRemuneracionFormateado, FontFactory.getFont(FontFactory.HELVETICA, 10), 1, Element.ALIGN_LEFT);
                tablaTotales.addCell(cellTotalesRemuneracion);
                PdfPCell cellTotalesDeduccion = createCellCombinada(" $  " + totalesDeduccionesFormateado, FontFactory.getFont(FontFactory.HELVETICA, 10), 1, Element.ALIGN_LEFT);
                tablaTotales.addCell(cellTotalesDeduccion);

                // Neto
                PdfPTable tablaTotalNeto = new PdfPTable(4);
                tablaTotalNeto.setWidthPercentage(100);
                PdfPCell cellNeto = createCellCombinada("NETO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9), 2, Element.ALIGN_RIGHT);
                tablaTotalNeto.addCell(cellNeto);
                PdfPCell cellNetoPrecio = createCellCombinada(" $  " + netoTotalFormateado, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10), 2, Element.ALIGN_LEFT);
                tablaTotalNeto.addCell(cellNetoPrecio);

                // Agregar la tabla al documento
                doc.add(tablaGeneral);
                doc.add(tablaInformacionPersonal);
                doc.add(tablaDescripcion);
                doc.add(tablaCuerpo);
                doc.add(tablaVacia);
                doc.add(tablaTotales);
                doc.add(tablaTotalNeto);
                doc.close();

                File archivo = new File("PreRecibo_" + nombre + "_" + apellido + "_" + labLegajoEmpleado.getText() + "_" + dpFechaDesde.getEditor().getText() + "_" + dpFechaHasta.getEditor().getText() + ".pdf");

                String url = archivo.getAbsolutePath();
                ProcessBuilder p = new ProcessBuilder();
                p.command("cmd.exe", "/c", url);
                p.start();

            } catch (DocumentException | FileNotFoundException e) {
                e.printStackTrace();
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
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    private static PdfPCell createCellNormal(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setPadding(10);
        return cell;
    }

    private static PdfPCell createCellCombinada(String content, Font font, int colSpan, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(5);
        cell.setColspan(colSpan);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
}
