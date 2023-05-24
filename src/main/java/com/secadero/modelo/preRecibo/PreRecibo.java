package com.secadero.modelo.preRecibo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
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

            // ----------------------------------- Documento --------------------------------------
            try {
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

                PdfPTable tablaInformacionPersonal = new PdfPTable(7);
                tablaInformacionPersonal.setWidthPercentage(100);
                tablaInformacionPersonal.addCell(createCell("Legajo\n\n" + legajo, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Nombre y Apellido\n\n" + nombre + " " + apellido, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("DNI\n\n" + dni, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Fecha Ing.\n\n" + fechaModificadaIngreso, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Area\n\n" + area2, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Puesto\n\n" + puesto, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaInformacionPersonal.addCell(createCell("Sueldo (Día)\n\n" + "$10.000", FontFactory.getFont(FontFactory.HELVETICA, 9)));

                PdfPTable tablaDescripcion = new PdfPTable(4);
                tablaDescripcion.setWidthPercentage(100);
                tablaDescripcion.addCell(createCell2("CONCEPTO", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaDescripcion.addCell(createCell2("UNIDADES", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaDescripcion.addCell(createCell2("REMUNERACIÓN", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaDescripcion.addCell(createCell2("DEDUCCIONES", FontFactory.getFont(FontFactory.HELVETICA, 9)));

                PdfPTable tablaCuerpo = new PdfPTable(4);
                tablaCuerpo.setWidthPercentage(100);
                tablaCuerpo.addCell(createCellNormal("Días Laborales \n\nAntigüedad \n\nVacaciones \n\nRetroactive \n\nJubilación CCG \n\nLey 19032 CCG \n\nObra Social CCG \n\nSeguro de Sepelio \n\nCuota Solid. Agraria", FontFactory.getFont(FontFactory.HELVETICA, 10)));
                tablaCuerpo.addCell(createCellNormal(" ", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaCuerpo.addCell(createCellNormal(" ", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                tablaCuerpo.addCell(createCellNormal(" ", FontFactory.getFont(FontFactory.HELVETICA, 9)));

                PdfPTable tablaVacia = new PdfPTable(1);
                tablaVacia.setWidthPercentage(100);
                tablaVacia.addCell(createCell(" ", FontFactory.getFont(FontFactory.HELVETICA, 5)));

                PdfPTable tablaTotales = new PdfPTable(4);
                tablaTotales.setWidthPercentage(100);
                PdfPCell cellTotales = createCellCombinada("TOTALES", FontFactory.getFont(FontFactory.HELVETICA, 9), 2, Element.ALIGN_RIGHT);
                tablaTotales.addCell(cellTotales);
                PdfPCell cellTotalesRemuneracion = createCellCombinada(" $  ", FontFactory.getFont(FontFactory.HELVETICA, 10), 1, Element.ALIGN_LEFT);
                tablaTotales.addCell(cellTotalesRemuneracion);
                PdfPCell cellTotalesDeduccion = createCellCombinada(" $  ", FontFactory.getFont(FontFactory.HELVETICA, 10), 1, Element.ALIGN_LEFT);
                tablaTotales.addCell(cellTotalesDeduccion);

                PdfPTable tablaTotalNeto = new PdfPTable(4);
                tablaTotalNeto.setWidthPercentage(100);
                PdfPCell cellNeto = createCellCombinada("NETO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9), 2, Element.ALIGN_RIGHT);
                tablaTotalNeto.addCell(cellNeto);
                PdfPCell cellNetoPrecio = createCellCombinada(" $  ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10), 2, Element.ALIGN_LEFT);
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
