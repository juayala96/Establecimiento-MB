package com.secadero.modelo.empleados;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

public class ModificarEmpleado {

    public ModificarEmpleado(){}

    //--------------------------------------------- Modificar Empleado ---------------------------------------------------
    public void modificarEmpleado(TextField textNombreModificar, TextField textApellidoModificar, TextField textLegajoModificar, TextField textTelefonoModificar, TextField textDireccionModificar, TextField textEmailModificar, ComboBox<String> cbGeneroModificar, ComboBox<String> cbEstadoCivilModificar, DatePicker dpFechaNaciminetoModificar, ComboBox<String> cbGrupoSanguineoModificar, DatePicker dpFechaIngresoModificar, ComboBox<String> cbAreaModificar, ComboBox<String> cbPuestoModificar, Label labLimpiarCamposModificar, Label labInformacionModificarLegajo, Label labIDModificar) throws ParseException, SQLException {

        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int datoIdGeneroFK;
        int datoIdEstadoCivilFK;
        int datoIdGrupoSanguineoFK;
        int datoIdAreaFK;
        int datoIdPuestoFK;
        String respuesta = "YES";

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

        if(respuesta.equals("YES")){
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

                                    String consulta6 = "UPDATE empleados SET nombre = ?, apellido = ?, legajo = ?, telefono = ?, direccion = ?, email = ?, idGeneroFK = ?,  idEstadoCivilFK = ?, fechaNacimiento = ?, idGrupoSanguineoFK = ?, fechaIngreso = ?, idAreaFK = ?, idPuestoFK = ? WHERE idempleados = ?";
                                    pstm = con.prepareStatement(consulta6);
                                    pstm.setString(1, textNombreModificar.getText());
                                    pstm.setString(2, textApellidoModificar.getText());
                                    pstm.setInt(3, Integer.parseInt(textLegajoModificar.getText()));
                                    pstm.setString(4, textTelefonoModificar.getText());
                                    pstm.setString(5, textDireccionModificar.getText());
                                    pstm.setString(6, textEmailModificar.getText());
                                    pstm.setInt(7, datoIdGeneroFK);
                                    pstm.setInt(8, datoIdEstadoCivilFK);
                                    pstm.setString(9, dpFechaNaciminetoModificar.getEditor().getText());
                                    pstm.setInt(10, datoIdGrupoSanguineoFK);
                                    pstm.setString(11, dpFechaIngresoModificar.getEditor().getText());
                                    pstm.setInt(12, datoIdAreaFK);
                                    pstm.setInt(13, datoIdPuestoFK);
                                    pstm.setInt(14, Integer.parseInt(labIDModificar.getText()));
                                    pstm.executeUpdate();
                                }
                            }
                        }
                    }
                }
                labLimpiarCamposModificar.setText("OK");
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Datos Modificados");
                alerta.setContentText("Se a Guardado los Datos Correctamente.");
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
            alerta.setContentText("No está permitido duplicar la LEGAJO de otro Empleado");
            alerta.showAndWait();
        }
    }
}
