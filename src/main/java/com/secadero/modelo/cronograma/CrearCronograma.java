package com.secadero.modelo.cronograma;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CrearCronograma {
    public CrearCronograma(){}

    //--------------------------------------------- Crear Cronograma ---------------------------------------------------
    public void agregarCronograma(ListView<String> listEmpleadosAgregados, DatePicker dpFechaCrear, ComboBox<String> cbTurnoCrear, TextField textHoraEntradaCrear, TextField textHoraSalidaCrear, Label labLimpiarCamposCrear){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String datoFechaInicio;
        String datoFechaFin;
        String respuesta = "YES";
        int datoIDCronogramaFK;
        int datoIDEmpleadoFK;
        int cont = 0;

        String fecha = dpFechaCrear.getEditor().getText();
        String fechaAnio = fecha.substring(6, 10);
        String fechaMes = fecha.substring(3, 5);
        String fechaDia = fecha.substring(0, 2);
        String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        for (int i = 0; i < listEmpleadosAgregados.getItems().size(); i++) {
            try {
                String consulta = "SELECT idempleados FROM empleados INNER JOIN empleado_cronograma ON empleados.idempleados = empleado_cronograma.idEmpleadoFK INNER JOIN cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK WHERE empleados.estadoEmpleado = ? AND empleados.legajo = ? AND cronograma.fecha = ?";
                pstm = con.prepareStatement(consulta);
                pstm.setString(1, "Vigente");
                pstm.setString(2, listEmpleadosAgregados.getItems().get(i));
                pstm.setString(3, fechaModificada);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    cont += 1;
                }

            } catch (Exception e1) {
                System.err.println("Error: " + e1.getMessage());
            }

            try {
                String consulta2 =  "SELECT fecha_Inicio, fecha_Fin FROM licencias INNER JOIN tipo_licencias ON licencias.idTipoLicenciaFK = tipo_licencias.idTipoLicencia INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE empleados.legajo = ?";
                pstm = con.prepareStatement(consulta2);
                pstm.setString(1, listEmpleadosAgregados.getItems().get(i));
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
                            respuesta = "NO";
                        }
                        resultadoFecha = resultadoFecha.plusDays(1);
                    }
                }
            } catch (Exception e1) {
                System.err.println("Error: " + e1.getMessage());
            }
        }

        if(cont == 0){
            if(respuesta.equals("YES")){
                try {
                    for (int i = 0; i < listEmpleadosAgregados.getItems().size(); i++) {
                        String consulta2 = "INSERT INTO cronograma(fecha, turno, horario_entrada, horario_salida) VALUES (?, ?, ?, ?)";
                        pstm = con.prepareStatement(consulta2);
                        pstm.setDate(1, java.sql.Date.valueOf(fechaModificada));
                        pstm.setString(2, cbTurnoCrear.getSelectionModel().getSelectedItem());
                        pstm.setString(3, textHoraEntradaCrear.getText());
                        pstm.setString(4, textHoraSalidaCrear.getText());
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
                    alerta.setTitle("Crear cronograma");
                    alerta.setContentText("Se han guardado los datos correctamente.");
                    Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(icon);
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
                        alerta.setTitle("Error - Crear cronograma");
                        alerta.setContentText("Error en la base de datos.");
                        alerta.showAndWait();
                        System.err.println("Error: " + ex.getMessage());
                    }
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error - Fecha");
                alerta.setContentText("Hay empleados seleccionados que tienen una licencia en la fecha asignada. \nIngrese a Consulta Licencia para ver más detalles.");
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error - Empleados");
            alerta.setContentText("No se puede crear el cronograma porque uno de los empleados ya tiene asignado un turno en esta fecha.");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        }
    }
}
