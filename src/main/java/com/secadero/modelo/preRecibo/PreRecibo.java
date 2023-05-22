package com.secadero.modelo.preRecibo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
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
        String nombre = "";     String apellido = "";
        String telefono = "";   String email = "";
        String area = "";       String puesto = "";
        int legajo = 0;         int dni = 0;

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

            // ----------------------------------- Documento --------------------------------------
            try {
                var doc = new Document();
                // Nombre del Archivo
                PdfWriter.getInstance(doc, new FileOutputStream("PreRecibo_" + nombre + "_" + apellido + "_" + labLegajoEmpleado.getText() + "_" + dpFechaDesde.getEditor().getText() + "_" + dpFechaHasta.getEditor().getText() + ".pdf"));
                doc.open();
                var paragraph = new Paragraph("------------------------------------ PRE-RECIBO DE " + nombre.toUpperCase() + " " + apellido.toUpperCase() + " ------------------------------------");
                var saltoLinea = new Paragraph(" ");
                var fechas = new Paragraph("Fechas Desde: " + dpFechaDesde.getEditor().getText() + "                                                                  Fecha Hasta: " + dpFechaHasta.getEditor().getText());
                PdfPTable tableTitulo = new PdfPTable(1);
                tableTitulo.addCell("DATOS PERSONALES");

                float borderWidth = 4f; // Grosor en puntos
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

                PdfPTable table = new PdfPTable(4);
                table.addCell("Unidad");
                table.addCell("Concepto");
                table.addCell("Remuneración");
                table.addCell("Descuento");

                // Cuento los años de dicho empleado y referente a eso lo calculo por la antiguedad ej 5, 10, 25 años
                table.addCell("(Hora)");
                table.addCell("Sueldo Básico");
                // El sueldo basico de todos
                table.addCell("$");
                // Descuento
                table.addCell("");


                table.addCell("");
                table.addCell("Antigüedad");
                table.addCell("$");
                table.addCell("");

                table.addCell("(Horas)");
                table.addCell("Horas Trabajadas");
                table.addCell("$");
                table.addCell("");

                table.addCell("(Días)");
                table.addCell("Vacaciones");
                table.addCell("$");
                table.addCell("");

                table.addCell("");
                table.addCell("Aporte Jubilatorio");
                table.addCell("");
                table.addCell("$");

                table.addCell("");
                table.addCell("Aporte a la Obra Social");
                table.addCell("");
                table.addCell("$");

                table.addCell("");
                table.addCell("Impuestos (Retención de Ganancias)");
                table.addCell("");
                table.addCell("$");

                PdfPTable table2 = new PdfPTable(1);
                table2.addCell(" ");

                PdfPTable table3 = new PdfPTable(4);
                table3.addCell("");
                table3.addCell("TOTALES: ");
                table3.addCell("$");
                table3.addCell("$");

                PdfPTable table4 = new PdfPTable(2);
                table4.addCell("TOTAL NETO A PAGAR: ");
                table4.addCell("$");

                doc.add(paragraph);
                doc.add(saltoLinea);
                doc.add(fechas);
                doc.add(saltoLinea);
                doc.add(tableTitulo);
                doc.add(tableDatos);
                doc.add(saltoLinea);
                doc.add(table);
                doc.add(table2);
                doc.add(table3);
                doc.add(table4);
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
}
