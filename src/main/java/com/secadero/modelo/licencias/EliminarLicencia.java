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

public class EliminarLicencia {

    public EliminarLicencia(){}

    //--------------------------------------------- Eliminar Licencia ---------------------------------------------------
    public void eliminarLicencia(Label labIDLicenciaEliminar, Label labIDEmpleadoEliminar, DatePicker dpFechaInicioEliminar, DatePicker dpFechaFinEliminar, ComboBox<String> cbTipoLicenciaEliminar) {
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
        int datoIdTipoLicenciaFK;
        int dias;
        int dato2;
        int cont = 0;

        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

            String fechaI = dpFechaInicioEliminar.getEditor().getText();
            Date fechaInicio = formatoFecha.parse(fechaI);

            String fechaF = dpFechaFinEliminar.getEditor().getText();
            Date fechaFin = formatoFecha.parse(fechaF);

            long Diferencias = fechaInicio.getTime() - fechaFin.getTime();
            long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

            String extraerAnio = dpFechaInicioEliminar.getEditor().getText();
            int exAnio = Integer.parseInt(extraerAnio.substring(0, 4));

            String consulta = "SELECT idTipoLicencia FROM tipo_licencias WHERE descripcion = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem());
            rs = pstm.executeQuery();
            while (rs.next()) {

                datoIdTipoLicenciaFK = Integer.parseInt(rs.getString("idTipoLicencia"));
                String consulta2 = "SELECT MIN(dias_disponibles) AS dias FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ? AND licencias.idTipoLicenciaFK = ? AND YEAR(fecha_Inicio) = ?";
                pstm = con.prepareStatement(consulta2);
                pstm.setString(1, labIDEmpleadoEliminar.getText());
                pstm.setInt(2, datoIdTipoLicenciaFK);
                pstm.setInt(3, exAnio);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    dato2 = rs.getInt("dias");
                    if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                        diasVacaciones = (int) ((dato2 + ((-Cant_Dias) + 1)));
                    } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                        diasEnfermedad = (int) ((dato2 + ((-Cant_Dias) + 1)));
                    } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Accidente")) {
                        diasAccidente = (int) ((dato2 + ((-Cant_Dias) + 1)));
                    } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                        diasMatrimonio = (int) ((dato2 + ((-Cant_Dias) + 1)));
                    } else {
                        diasMuerteFamiliar = (int) ((dato2 + ((-Cant_Dias) + 1)));
                    }
                    cont += 1;
                }

                if (cont == 0) {
                    if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                        diasVacaciones = VACACIONES;
                    } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                        diasEnfermedad = ENFERMEDAD;
                    } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Accidente")) {
                        diasAccidente = ACCIDENTE;
                    } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                        diasMatrimonio = MATRIMONIO;
                    } else {
                        diasMuerteFamiliar = MUERTE_FAMILIAR;
                    }
                }

                try {
                    String consulta3 = "UPDATE licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados SET dias_disponibles = ? WHERE empleado_licencia.idEmpleadoFK = ? AND estadoLicencia = ? AND idTipoLicenciaFK = ? AND YEAR(fecha_Inicio) = ?";
                    pstm = con.prepareStatement(consulta3);
                    if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                        pstm.setInt(1, diasVacaciones);
                    } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                        pstm.setInt(1, diasEnfermedad);
                    } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Accidente")) {
                        pstm.setInt(1, diasAccidente);
                    } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                        pstm.setInt(1, diasMatrimonio);
                    } else {
                        pstm.setInt(1, diasMuerteFamiliar);
                    }
                    pstm.setInt(2, Integer.parseInt(labIDEmpleadoEliminar.getText()));
                    pstm.setString(3, "Vigente");
                    pstm.setInt(4, datoIdTipoLicenciaFK);
                    pstm.setInt(5, exAnio);
                    pstm.executeUpdate();

                    String consulta4 = "UPDATE licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados SET estadoLicencia = ? WHERE empleado_licencia.idEmpleadoFK = ? AND estadoLicencia = ? AND idTipoLicenciaFK = ? AND licencias.idLicencias = ? AND YEAR(fecha_Inicio) = ?";
                    pstm = con.prepareStatement(consulta4);
                    pstm.setString(1, "Eliminado");
                    pstm.setInt(2, Integer.parseInt(labIDEmpleadoEliminar.getText()));
                    pstm.setString(3, "Vigente");
                    pstm.setInt(4, datoIdTipoLicenciaFK);
                    pstm.setInt(5, Integer.parseInt(labIDLicenciaEliminar.getText()));
                    pstm.setInt(6, exAnio);
                    pstm.executeUpdate();

                } catch (Exception e1) {
                    System.err.println("Error: " + e1.getMessage());
                }
            }

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Datos Eliminados");
            alerta.setContentText("Se a Eliminado Correctamente.");
            alerta.showAndWait();

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
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
