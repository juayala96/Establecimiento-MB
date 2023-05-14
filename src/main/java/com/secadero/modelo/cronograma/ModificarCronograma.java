package com.secadero.modelo.cronograma;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ModificarCronograma {
    public ModificarCronograma(){}

    //--------------------------------------------- Modificar Cronograma ---------------------------------------------------
    public void modificarCronograma(Label labIDEmpleadoModificar, Label labIDCronogramaModificar, DatePicker dpFechaModificar,DatePicker dpFechaModificarDuplicada, ComboBox<String> cbTurnoModificar, TextField textHoraEntradaModificar, TextField textHoraSalidaModificar, Label labLimpiarCamposModificar){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String resultado = "YES";
        String resultado2 = "YES";
        String datoFechaInicio;
        String datoFechaFin;
        String dato;

        String fecha = dpFechaModificar.getEditor().getText();
        String fechaAnio = fecha.substring(6, 10);
        String fechaMes = fecha.substring(3, 5);
        String fechaDia = fecha.substring(0, 2);
        String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        String fecha2 = dpFechaModificarDuplicada.getEditor().getText();
        String fechaAnio2 = fecha2.substring(6, 10);
        String fechaMes2 = fecha2.substring(3, 5);
        String fechaDia2 = fecha2.substring(0, 2);
        String fechaModificadaDuplicada = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);

        try {
            String consulta2 =  "SELECT fecha_Inicio, fecha_Fin FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.idempleados = ?";
            pstm = con.prepareStatement(consulta2);
            pstm.setString(1, labIDEmpleadoModificar.getText());
            rs = pstm.executeQuery();

            while (rs.next()){
                datoFechaInicio = rs.getString("fecha_Inicio");
                datoFechaFin = rs.getString("fecha_Fin");

                // Establecer la fecha inicial y final
                LocalDate fechaInicial = LocalDate.parse(datoFechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate fechaFinal = LocalDate.parse(datoFechaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // Iterar sobre las fechas y mostrarlas
                LocalDate resultadoFecha = fechaInicial;
                while (!resultadoFecha.isAfter(fechaFinal)) {
                    if (fechaModificada.equals(String.valueOf(resultadoFecha))) {
                        resultado2 = "NO";
                    }
                    resultadoFecha = resultadoFecha.plusDays(1);
                }
            }
        } catch (Exception e1) {
            System.err.println("Error: " + e1.getMessage());
        }

        try {
            String consulta = "SELECT fecha FROM empleados INNER JOIN empleado_cronograma ON empleados.idempleados = empleado_cronograma.idEmpleadoFK INNER JOIN cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK WHERE empleados.estadoEmpleado = ? AND empleados.idempleados = ? AND cronograma.fecha = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, "Vigente");
            pstm.setString(2, labIDEmpleadoModificar.getText());
            pstm.setString(3, fechaModificada);
            rs = pstm.executeQuery();
            while (rs.next()) {
                dato = String.valueOf(rs.getDate("fecha"));
                if (Objects.equals(fechaModificadaDuplicada, dato)) {
                    resultado = "YES";
                } else {
                    resultado = "NO";
                }
            }
        } catch (Exception e1) {
            System.err.println("Error: " + e1.getMessage());
        }

        if(resultado.equals("YES")){
            if(resultado2.equals("YES")){
                try {
                    String consulta2 = "UPDATE cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados SET fecha = ?, turno = ?, horario_entrada = ?, horario_salida = ? WHERE cronograma.idCronograma = ? AND empleado_cronograma.idEmpleadoFK = ? AND empleados.estadoEmpleado = ?";

                    pstm = con.prepareStatement(consulta2);
                    pstm.setString(1, fechaModificada);
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
                alerta.setContentText("El empleado se encuentra con licencia en esta Fecha Asignada");
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
