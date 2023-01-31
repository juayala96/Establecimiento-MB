package com.secadero.modelo.empleados;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class CrearEmpleado {
    public CrearEmpleado(){}

    //--------------------------------------------- Crear Empleado ---------------------------------------------------
    public void agregarEmpleado(TextField textNombreCrear, TextField textApellidoCrear, TextField textLegajoCrear, TextField textTelefonoCrear, TextField textDireccionCrear, ComboBox<String> cbGeneroCrear, ComboBox<String> cbEstadoCivilCrear, DatePicker dpFechaNaciminetoCrear, ComboBox<String> cbGrupoSanguineoCrear, DatePicker dpFechaIngresoCrear, ComboBox<String> cbAreaCrear, ComboBox<String> cbPuestoCrear, Label labLimpiarCamposCrear) throws ParseException, SQLException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int dato;
        int datoIdGeneroFK;
        int datoIdEstadoCivilFK;
        int datoIdGrupoSanguineoFK;
        int datoIdAreaFK;
        int datoIdPuestoFK;
        String resultado = "YES";
        labLimpiarCamposCrear.getText();

        try {
            String consulta1 = "SELECT legajo FROM empleados WHERE legajo = ?";
            pstm = con.prepareStatement(consulta1);
            pstm.setString(1, textLegajoCrear.getText());
            rs = pstm.executeQuery();
            while (rs.next()){
                dato = Integer.parseInt(rs.getString("legajo"));
                if(Integer.parseInt(textLegajoCrear.getText()) == dato){
                    resultado = "NO";
                }
            }
        } catch (Exception e1){
            System.err.println("Error: " + e1.getMessage());
        }

        if(resultado.equals("YES")){
            try {
                String consulta1 = "SELECT idgenero FROM genero WHERE descripcion = ?";
                pstm = con.prepareStatement(consulta1);
                pstm.setString(1, cbGeneroCrear.getSelectionModel().getSelectedItem());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    datoIdGeneroFK = Integer.parseInt(rs.getString("idgenero"));

                    String consulta2 = "SELECT idestadoCivil FROM estado_civil WHERE descripcion = ? ";
                    pstm = con.prepareStatement(consulta2);
                    pstm.setString(1, cbEstadoCivilCrear.getSelectionModel().getSelectedItem());
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        datoIdEstadoCivilFK = Integer.parseInt(rs.getString("idestadoCivil"));

                        String consulta3 = "SELECT idgrupoSanguineo FROM grupo_sanguineo WHERE descripcion = ? ";
                        pstm = con.prepareStatement(consulta3);
                        pstm.setString(1, cbGrupoSanguineoCrear.getSelectionModel().getSelectedItem());
                        rs = pstm.executeQuery();
                        while (rs.next()) {
                            datoIdGrupoSanguineoFK = Integer.parseInt(rs.getString("idgrupoSanguineo"));

                            String consulta4 = "SELECT idarea FROM area WHERE descripcion = ? ";
                            pstm = con.prepareStatement(consulta4);
                            pstm.setString(1, cbAreaCrear.getSelectionModel().getSelectedItem());
                            rs = pstm.executeQuery();
                            while (rs.next()) {
                                datoIdAreaFK = Integer.parseInt(rs.getString("idarea"));

                                String consulta5 = "SELECT idpuesto FROM puesto WHERE descripcion = ? ";
                                pstm = con.prepareStatement(consulta5);
                                pstm.setString(1, cbPuestoCrear.getSelectionModel().getSelectedItem());
                                rs = pstm.executeQuery();
                                while (rs.next()) {
                                    datoIdPuestoFK = Integer.parseInt(rs.getString("idpuesto"));

                                    String consulta6 = "INSERT INTO empleados(nombre, apellido, legajo, telefono, direccion, idGeneroFK, idEstadoCivilFK, fechaNacimiento, idGrupoSanguineoFK, fechaIngreso, idAreaFK, idPuestoFK, estado, estadoEmpleado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                    pstm = con.prepareStatement(consulta6);
                                    pstm.setString(1, textNombreCrear.getText());
                                    pstm.setString(2, textApellidoCrear.getText());
                                    pstm.setInt(3, Integer.parseInt(textLegajoCrear.getText()));
                                    pstm.setString(4, textTelefonoCrear.getText());
                                    pstm.setString(5, textDireccionCrear.getText());
                                    pstm.setInt(6, datoIdGeneroFK);
                                    pstm.setInt(7, datoIdEstadoCivilFK);
                                    pstm.setString(8, dpFechaNaciminetoCrear.getEditor().getText());
                                    pstm.setInt(9, datoIdGrupoSanguineoFK);
                                    pstm.setString(10, dpFechaIngresoCrear.getEditor().getText());
                                    pstm.setInt(11, datoIdAreaFK);
                                    pstm.setInt(12, datoIdPuestoFK);
                                    pstm.setString(13, "");
                                    pstm.setString(14, "Vigente");
                                    pstm.executeUpdate();
                                }
                            }
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
            labLimpiarCamposCrear.setText("");
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error!");
            alerta.setContentText("Error: No se puede duplicar Legajos de otros empleados");
            alerta.showAndWait();
        }
    }
}
