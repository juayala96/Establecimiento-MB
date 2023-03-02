package com.secadero.modelo.cronograma;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.*;
import java.util.Objects;

public class ModificarCronograma {
    public ModificarCronograma(){}

    //--------------------------------------------- Modificar Cronograma ---------------------------------------------------
    public void modificarCronograma(Label labIDEmpleadoModificar, Label labIDCronogramaModificar, DatePicker dpFechaModificar,DatePicker dpFechaModificarDuplicada, ComboBox<String> cbTurnoModificar, TextField textHoraEntradaModificar, TextField textHoraSalidaModificar, Label labLimpiarCamposModificar){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String resultado = "YES";
        String dato;
        int cont = 0;

        try {
            pstm = con.prepareStatement("SELECT idempleados, fecha_Inicio, fecha_Fin FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND licencias.estadoLicencia = ? AND (((licencias.fecha_Inicio <= ?) AND (licencias.fecha_Fin >= ?)) OR ((licencias.fecha_Inicio >= ?) AND (licencias.fecha_Fin <= ?)))");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            pstm.setDate(3, java.sql.Date.valueOf(dpFechaModificar.getEditor().getText()));
            pstm.setDate(4, java.sql.Date.valueOf(dpFechaModificar.getEditor().getText()));
            pstm.setDate(5, java.sql.Date.valueOf(dpFechaModificar.getEditor().getText()));
            pstm.setDate(6, java.sql.Date.valueOf(dpFechaModificar.getEditor().getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                cont += 1;
            }

        } catch (Exception e1) {
            System.err.println("Error: " + e1.getMessage());
        }

        try {
            String consulta = "SELECT fecha FROM empleados INNER JOIN empleado_cronograma ON empleados.idempleados = empleado_cronograma.idEmpleadoFK INNER JOIN cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK WHERE empleados.estadoEmpleado = ? AND cronograma.estadoCronograma = ? AND empleados.idempleados = ? AND cronograma.fecha = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Vigente");
            pstm.setString(3, labIDEmpleadoModificar.getText());
            pstm.setString(4, dpFechaModificar.getEditor().getText());
            rs = pstm.executeQuery();
            while (rs.next()) {
                dato = String.valueOf(rs.getDate("fecha"));
                if (Objects.equals(dpFechaModificarDuplicada.getEditor().getText(), dato)) {
                    resultado = "YES";
                } else {
                    resultado = "NO";
                }
            }

        } catch (Exception e1) {
            System.err.println("Error: " + e1.getMessage());
        }

        if(resultado.equals("YES")){
            if(cont == 0){
                try {
                    String consulta2 = "UPDATE cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados SET fecha = ?, turno = ?, horario_entrada = ?, horario_salida = ? WHERE cronograma.idCronograma = ? AND empleado_cronograma.idEmpleadoFK = ? AND empleados.estadoEmpleado = ?";

                    pstm = con.prepareStatement(consulta2);
                    pstm.setString(1, dpFechaModificar.getEditor().getText());
                    pstm.setString(2, cbTurnoModificar.getSelectionModel().getSelectedItem());
                    pstm.setString(3, textHoraEntradaModificar.getText());
                    pstm.setString(4, textHoraSalidaModificar.getText());
                    pstm.setInt(5, Integer.parseInt(labIDCronogramaModificar.getText()));
                    pstm.setInt(6, Integer.parseInt(labIDEmpleadoModificar.getText()));
                    pstm.setString(7, "Vigente");
                    pstm.executeUpdate();

                    labLimpiarCamposModificar.setText("OK");
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Datos Modificados");
                    alerta.setContentText("Se a Guardado los Datos Correctamente");
                    alerta.showAndWait();

                } catch (SQLException ex) {
                    labLimpiarCamposModificar.setText("");
                    System.err.println("Error: " + ex.getMessage());
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (pstm != null) pstm.close();
                        if (con != null) con.close();
                    } catch (Exception ex) {
                        labLimpiarCamposModificar.setText("");
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error!");
                        alerta.setContentText("Error en la Base de Datos");
                        alerta.showAndWait();
                        System.err.println("Error: " + ex.getMessage());
                    }
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Fecha Asignada!");
                alerta.setContentText("El empleado tiene licencia en esta Fecha Ingresada");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Fecha!");
            alerta.setContentText("No esta permitido crear 2 cronograma del mismo empleado de la misma fecha");
            alerta.showAndWait();
        }
    }
}
