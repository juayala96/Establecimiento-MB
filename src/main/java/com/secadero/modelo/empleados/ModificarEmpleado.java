package com.secadero.modelo.empleados;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

public class ModificarEmpleado {

    public ModificarEmpleado(){}

    //--------------------------------------------- Modificar Empleado ---------------------------------------------------
    public void modificarEmpleado(TextField textNombreModificar, TextField textApellidoModificar, TextField textLegajoModificar, TextField textDNIModificar, TextField textTelefonoModificar, TextField textDireccionModificar, TextField textEmailModificar, ComboBox<String> cbGeneroModificar, ComboBox<String> cbEstadoCivilModificar, DatePicker dpFechaNaciminetoModificar, ComboBox<String> cbGrupoSanguineoModificar, DatePicker dpFechaIngresoModificar, ComboBox<String> cbAreaModificar, ComboBox<String> cbPuestoModificar, Label labLimpiarCamposModificar, Label labInformacionModificarLegajo, Label labInformacionModificarDNI, Label labIDModificar) throws ParseException, SQLException {

        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int datoIdGeneroFK;
        int datoIdEstadoCivilFK;
        int datoIdGrupoSanguineoFK;
        int datoIdAreaFK;
        int datoIdPuestoFK;
        String respuesta = "YES";
        String respuesta2 = "YES";

        try {
            String consulta = "SELECT * FROM empleados WHERE legajo = ? AND estadoEmpleado = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1, Integer.parseInt(textLegajoModificar.getText()));
            pstm.setString(2, "Vigente");
            rs = pstm.executeQuery();
            if (rs.next()) {
                int legajo = rs.getInt("legajo");
                if (Objects.equals(Integer.parseInt(labInformacionModificarLegajo.getText()), legajo)) {
                    respuesta = "YES";
                } else {
                    respuesta = "NO";
                }
            }
        }catch (Exception e1){
            System.err.println("Error: " + e1.getMessage());
        }

        try {
            String consulta = "SELECT * FROM empleados WHERE dni = ? AND estadoEmpleado = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setInt(1, Integer.parseInt(textDNIModificar.getText()));
            pstm.setString(2, "Vigente");
            rs = pstm.executeQuery();
            if (rs.next()) {
                int dni = rs.getInt("dni");
                if (Objects.equals(Integer.parseInt(labInformacionModificarDNI.getText()), dni)) {
                    respuesta2 = "YES";
                } else {
                    respuesta2 = "NO";
                }
            }
        }catch (Exception e1){
            System.err.println("Error: " + e1.getMessage());
        }

        if(respuesta.equals("YES")){
            if(respuesta2.equals("YES")){
                String apellido = textApellidoModificar.getText().trim();
                String legajo = textLegajoModificar.getText().trim();
                String dni = textDNIModificar.getText().trim();
                String telefono = textTelefonoModificar.getText().trim();
                String email = textEmailModificar.getText().trim();
                apellido = apellido.replaceAll("\\s+", "");
                legajo = legajo.replaceAll("\\s+", "");
                dni = dni.replaceAll("\\s+", "");
                telefono = telefono.replaceAll("\\s+", "");
                email = email.replaceAll("\\s+", "");

                String fechaNacimiento = dpFechaNaciminetoModificar.getEditor().getText();
                String fechaNacimientoAnio = fechaNacimiento.substring(6, 10);
                String fechaNacimientoMes = fechaNacimiento.substring(3, 5);
                String fechaNacimientoDia = fechaNacimiento.substring(0, 2);
                String fechaNacimientoModificada = (fechaNacimientoAnio + "-" + fechaNacimientoMes + "-" + fechaNacimientoDia);
                String fechaIngreso = dpFechaIngresoModificar.getEditor().getText();
                String fechaIngresoAnio = fechaIngreso.substring(6, 10);
                String fechaIngresoMes = fechaIngreso.substring(3, 5);
                String fechaIngresoDia = fechaIngreso.substring(0, 2);
                String fechaIngresoModificada = (fechaIngresoAnio + "-" + fechaIngresoMes + "-" + fechaIngresoDia);

                try {
                    String consulta1 = "SELECT idgenero FROM genero WHERE descripcion = ?";
                    pstm = con.prepareStatement(consulta1);
                    pstm.setString(1, cbGeneroModificar.getSelectionModel().getSelectedItem());
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        datoIdGeneroFK = Integer.parseInt(rs.getString("idgenero"));

                        String consulta2 = "SELECT idestadoCivil FROM estado_civil WHERE descripcion = ? ";
                        pstm = con.prepareStatement(consulta2);
                        pstm.setString(1, cbEstadoCivilModificar.getSelectionModel().getSelectedItem());
                        rs = pstm.executeQuery();
                        while (rs.next()) {
                            datoIdEstadoCivilFK = Integer.parseInt(rs.getString("idestadoCivil"));

                            String consulta3 = "SELECT idgrupoSanguineo FROM grupo_sanguineo WHERE descripcion = ? ";
                            pstm = con.prepareStatement(consulta3);
                            pstm.setString(1, cbGrupoSanguineoModificar.getSelectionModel().getSelectedItem());
                            rs = pstm.executeQuery();
                            while (rs.next()) {
                                datoIdGrupoSanguineoFK = Integer.parseInt(rs.getString("idgrupoSanguineo"));

                                String consulta4 = "SELECT idarea FROM area WHERE descripcion = ? ";
                                pstm = con.prepareStatement(consulta4);
                                pstm.setString(1, cbAreaModificar.getSelectionModel().getSelectedItem());
                                rs = pstm.executeQuery();
                                while (rs.next()) {
                                    datoIdAreaFK = Integer.parseInt(rs.getString("idarea"));

                                    String consulta5 = "SELECT idpuesto FROM puesto WHERE descripcion = ? ";
                                    pstm = con.prepareStatement(consulta5);
                                    pstm.setString(1, cbPuestoModificar.getSelectionModel().getSelectedItem());
                                    rs = pstm.executeQuery();
                                    while (rs.next()) {
                                        datoIdPuestoFK = Integer.parseInt(rs.getString("idpuesto"));

                                        String consulta6 = "UPDATE empleados SET nombre = ?, apellido = ?, legajo = ?, dni = ?, telefono = ?, direccion = ?, email = ?, idGeneroFK = ?,  idEstadoCivilFK = ?, fechaNacimiento = ?, idGrupoSanguineoFK = ?, fechaIngreso = ?, idAreaFK = ?, idPuestoFK = ? WHERE idempleados = ?";
                                        pstm = con.prepareStatement(consulta6);
                                        pstm.setString(1, textNombreModificar.getText());
                                        pstm.setString(2, apellido);
                                        pstm.setInt(3, Integer.parseInt(legajo));
                                        pstm.setInt(4, Integer.parseInt(dni));
                                        pstm.setString(5, telefono);
                                        pstm.setString(6, textDireccionModificar.getText());
                                        pstm.setString(7, email);
                                        pstm.setInt(8, datoIdGeneroFK);
                                        pstm.setInt(9, datoIdEstadoCivilFK);
                                        pstm.setString(10, fechaNacimientoModificada);
                                        pstm.setInt(11, datoIdGrupoSanguineoFK);
                                        pstm.setString(12, fechaIngresoModificada);
                                        pstm.setInt(13, datoIdAreaFK);
                                        pstm.setInt(14, datoIdPuestoFK);
                                        pstm.setInt(15, Integer.parseInt(labIDModificar.getText()));
                                        pstm.executeUpdate();
                                    }
                                }
                            }
                        }
                    }
                    labLimpiarCamposModificar.setText("OK");
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("");
                    alerta.setContentText("Se a Guardado los Datos Correctamente.");
                    Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(icon);
                    alerta.showAndWait();
                } catch (SQLException e) {
                    labLimpiarCamposModificar.setText("");
                    System.err.println("Error: " + e.getMessage());
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (pstm != null) pstm.close();
                        if (con != null) con.close();
                    } catch (Exception ex){
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error!");
                        alerta.setContentText("Error en la Base de Datos");
                        alerta.showAndWait();
                        System.err.println("Error: " + ex.getMessage());
                    }
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR de Duplicación");
                alerta.setContentText("No está permitido duplicar el DNI de otro Empleado");
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                alerta.showAndWait();
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR de Duplicación");
            alerta.setContentText("No está permitido duplicar la LEGAJO de otro Empleado");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        }
    }
}
