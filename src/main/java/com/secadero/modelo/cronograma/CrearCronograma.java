package com.secadero.modelo.cronograma;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.*;

public class CrearCronograma {
    public CrearCronograma(){}

    //--------------------------------------------- Crear Cronograma ---------------------------------------------------
    public void agregarCronograma(ListView<String> listEmpleadosAgregados, DatePicker dpFechaCrear, ComboBox<String> cbTurnoCrear, TextField textHoraEntradaCrear, TextField textHoraSalidaCrear, Label labLimpiarCamposCrear){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int datoIDCronogramaFK;
        int datoIDEmpleadoFK;
        int cont = 0;

        for (int i = 0; i < listEmpleadosAgregados.getItems().size(); i++) {
            try {
                String consulta = "SELECT idempleados FROM empleados INNER JOIN empleado_cronograma ON empleados.idempleados = empleado_cronograma.idEmpleadoFK INNER JOIN cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK WHERE empleados.estadoEmpleado = ? AND empleados.legajo = ? AND cronograma.fecha = ?";
                pstm = con.prepareStatement(consulta);
                pstm.setString(1, "Vigente");
                pstm.setString(2, listEmpleadosAgregados.getItems().get(i));
                pstm.setString(3, dpFechaCrear.getEditor().getText());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    cont += 1;
                }

            } catch (Exception e1) {
                System.err.println("Error: " + e1.getMessage());
            }
        }

        if(cont == 0){
            try {
                for (int i = 0; i < listEmpleadosAgregados.getItems().size(); i++) {
                    String consulta2 = "INSERT INTO cronograma(fecha, turno, horario_entrada, horario_salida, estadoCronograma) VALUES (?, ?, ?, ?, ?)";
                    pstm = con.prepareStatement(consulta2);
                    pstm.setDate(1, java.sql.Date.valueOf(dpFechaCrear.getEditor().getText()));
                    pstm.setString(2, cbTurnoCrear.getSelectionModel().getSelectedItem());
                    pstm.setString(3, textHoraEntradaCrear.getText());
                    pstm.setString(4, textHoraSalidaCrear.getText());
                    pstm.setString(5, "Vigente");
                    pstm.executeUpdate();

                    String consulta3 = "SELECT MAX(idCronograma) AS idCronograma FROM cronograma";
                    pstm = con.prepareStatement(consulta3);
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        datoIDCronogramaFK = rs.getInt("idCronograma");

                        String consulta4 = "SELECT idempleados FROM empleados WHERE empleados.estadoEmpleado = ? AND empleados.legajo = ?";
                        pstm = con.prepareStatement(consulta4);
                        pstm.setString(1, "Vigente");
                        pstm.setString(2, listEmpleadosAgregados.getItems().get(i));
                        rs = pstm.executeQuery();
                        while (rs.next()) {
                            datoIDEmpleadoFK = rs.getInt("idempleados");

                            String consulta5 = "INSERT INTO empleado_cronograma(idEmpleadoFK, idCronogramaFK) VALUES (?, ?)";
                            pstm = con.prepareStatement(consulta5);
                            pstm.setInt(1, datoIDEmpleadoFK);
                            pstm.setInt(2, datoIDCronogramaFK);
                            pstm.executeUpdate();
                        }
                    }
                }
                labLimpiarCamposCrear.setText("OK");
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Datos Guardados");
                alerta.setContentText("Se a Guardado los Datos.");
                alerta.showAndWait();

            } catch (SQLException ex) {
                labLimpiarCamposCrear.setText("");
                System.err.println("Error: " + ex.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstm != null) pstm.close();
                    if (con != null) con.close();
                } catch (Exception ex) {
                    labLimpiarCamposCrear.setText("");
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error!");
                    alerta.setContentText("Error en la Base de Datos");
                    alerta.showAndWait();
                    System.err.println("Error: " + ex.getMessage());
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Empleados!");
            alerta.setContentText("Uno de los empleados ya tiene un turno en esta fecha "+ '\n' +"por lo tanto no se pude guardar");
            alerta.showAndWait();
        }
    }
}
