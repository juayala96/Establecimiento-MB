package com.secadero.modelo.licencias;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
            String fechaInicioClave = dpFechaInicioEliminar.getEditor().getText();
            String fechaAnio = fechaInicioClave.substring(6, 10);
            String fechaMes = fechaInicioClave.substring(3, 5);
            String fechaDia = fechaInicioClave.substring(0, 2);
            String fechaModificadaInicio = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
            String fechaFinClave = dpFechaFinEliminar.getEditor().getText();
            String fechaAnio2 = fechaFinClave.substring(6, 10);
            String fechaMes2 = fechaFinClave.substring(3, 5);
            String fechaDia2 = fechaFinClave.substring(0, 2);
            String fechaModificadaFin = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

            String fechaI = fechaModificadaInicio;
            Date fechaInicio = formatoFecha.parse(fechaI);
            String fechaF = fechaModificadaFin;
            Date fechaFin = formatoFecha.parse(fechaF);

            long Diferencias = fechaInicio.getTime() - fechaFin.getTime();
            long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

            String extraerAnio = fechaModificadaInicio;
            int exAnio = Integer.parseInt(extraerAnio.substring(0, 4));

            String consulta = "SELECT idTipoLicencia FROM tipo_licencias WHERE descripcion = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem());
            rs = pstm.executeQuery();
            while (rs.next()) {

                datoIdTipoLicenciaFK = Integer.parseInt(rs.getString("idTipoLicencia"));
                String consulta2 = "SELECT MIN(dias_disponibles) AS dias_disponibles, empleados.idempleados FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ? AND licencias.idTipoLicenciaFK = ? AND YEAR(fecha_Inicio) = ?";
                pstm = con.prepareStatement(consulta2);
                pstm.setString(1, labIDEmpleadoEliminar.getText());
                pstm.setInt(2, datoIdTipoLicenciaFK);
                pstm.setInt(3, exAnio);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    int datoDiaDisponible = rs.getInt("dias_disponibles");
                    int datoIDEmpleado = rs.getInt("idempleados");

                    if (datoIDEmpleado == Integer.parseInt(labIDEmpleadoEliminar.getText())) {
                        if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
                            diasVacaciones = (int) ((datoDiaDisponible + ((-Cant_Dias) + 1)));
                        } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
                            diasEnfermedad = (int) ((datoDiaDisponible + ((-Cant_Dias) + 1)));
                        } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Accidente")) {
                            diasAccidente = (int) ((datoDiaDisponible + ((-Cant_Dias) + 1)));
                        } else if (cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
                            diasMatrimonio = (int) ((datoDiaDisponible + ((-Cant_Dias) + 1)));
                        } else {
                            diasMuerteFamiliar = (int) ((datoDiaDisponible + ((-Cant_Dias) + 1)));
                        }
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
                    String consulta3 = "UPDATE licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados SET dias_disponibles = ? WHERE empleado_licencia.idEmpleadoFK = ? AND idTipoLicenciaFK = ? AND YEAR(fecha_Inicio) = ?";
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
                    pstm.setInt(3, datoIdTipoLicenciaFK);
                    pstm.setInt(4, exAnio);
                    pstm.executeUpdate();

                    String consulta4 = "DELETE FROM empleado_licencia WHERE idEmpleadoFK = ? AND idLicenciaFK = ?";
                    pstm = con.prepareStatement(consulta4);
                    pstm.setInt(1, Integer.parseInt(labIDEmpleadoEliminar.getText()));
                    pstm.setInt(2, Integer.parseInt(labIDLicenciaEliminar.getText()));
                    pstm.executeUpdate();

                    String consulta5 = "DELETE FROM licencias WHERE idLicencias = ?";
                    pstm = con.prepareStatement(consulta5);
                    pstm.setInt(1, Integer.parseInt(labIDLicenciaEliminar.getText()));
                    pstm.executeUpdate();

                } catch (Exception e1) {
                    System.err.println("Error: " + e1.getMessage());
                }
            }

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("");
            alerta.setContentText("Se a Eliminado Correctamente.");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
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
