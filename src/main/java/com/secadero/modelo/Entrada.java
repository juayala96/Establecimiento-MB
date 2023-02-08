package com.secadero.modelo;

import javafx.scene.control.Alert;

import java.sql.Time;
import java.util.Date;

public class Entrada {
    private Date fecha;
    private Time hora;

    public Entrada(Date fecha, Time hora) {
        this.fecha = fecha;
        this.hora = hora;;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    private void registrarEntrada(){

    }

    /*
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
                    String consulta5 = "SELECT idEmpleadoFK FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados INNER JOIN tipo_licencias ON tipo_licencias.idTipoLicencia = licencias.idTipoLicenciaFK WHERE idEmpleadoFK = ? AND estadoLicencia = ? AND idTipoLicenciaFK = ? AND YEAR(fecha_Inicio) = ?";
                    pstm = con.prepareStatement(consulta5);
                    pstm.setInt(1, Integer.parseInt(labIDEmpleadoModificar.getText()));
                    pstm.setString(2, "Vigente");
                    pstm.setInt(4, datoIdTipoLicenciaFK);
                    pstm.setInt(5, exAnio);
                    rs = pstm.executeQuery();

                    while (rs.next()) {
                        datoEmpleadoFK = rs.getInt("idEmpleadoFK");

                        String consulta5 = "UPDATE licencias SET dias_disponibles = ? WHERE idEmpleadoFK = ? AND estadoLicencia = ? AND idTipoLicenciaFK = ? AND YEAR(fecha_Inicio) = ?";
                        pstm = con.prepareStatement(consulta5);
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
                        String consulta6 = "UPDATE licencias SET fecha_Inicio = ?, fecha_Fin = ?, idTipoLicenciaFK = ? WHERE idLicencias = ?";
                        pstm = con.prepareStatement(consulta6);
                        pstm.setDate(1, java.sql.Date.valueOf(dpFechaInicioModificar.getEditor().getText()));
                        pstm.setDate(2, java.sql.Date.valueOf(dpFechaFinModificar.getEditor().getText()));
                        pstm.setInt(3, datoIdTipoLicenciaFK);
                        pstm.setInt(4, Integer.parseInt(labIDLicenciaModificar.getText()));
                        pstm.executeUpdate();

                    } catch (Exception e1) {
                        System.err.println("Error: " + e1.getMessage());
                    }
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

     */
}
