package com.secadero.modelo.asistencia;

import com.secadero.conexion.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Salida {
    private int idsalida;
    private Time hora;
    private Date fecha;
    private int idEmpleadoFK;

    public Salida(){}

    public Salida(int idsalida, Time hora, Date fecha, int idEmpleadoFK) {
        this.idsalida = idsalida;
        this.hora = hora;
        this.fecha = fecha;
        this.idEmpleadoFK = idEmpleadoFK;
    }

    public int getIdsalida() {
        return idsalida;
    }

    public void setIdsalida(int idsalida) {
        this.idsalida = idsalida;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEmpleadoFK() {
        return idEmpleadoFK;
    }

    public void setIdEmpleadoFK(int idEmpleadoFK) {
        this.idEmpleadoFK = idEmpleadoFK;
    }

    //--------------------------------------------- Crear Salida ---------------------------------------------------
    public void salidaEmpleado(Label labInformacion, TextField textCodigo, TextField textDNI) throws ParseException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int cont = 0;
        int idEmpleadosFK;

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActualPais = new Date();
        String fecha_Actual = (formatoFecha.format(fechaActualPais));
        LocalTime horaActualPais = LocalTime.now();
        String hora_Actual = horaActualPais.toString().substring(0,5);

        try {
            String consulta3 = "SELECT idempleados FROM empleados WHERE legajo = ? AND dni = ?";
            pstm = con.prepareStatement(consulta3);
            pstm.setInt(1, Integer.parseInt(textCodigo.getText()));
            pstm.setInt(2, Integer.parseInt(textDNI.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                idEmpleadosFK = rs.getInt("idempleados");
                String consulta4 = "INSERT INTO salida(hora, fecha, idEmpleadoFK) VALUES (?, ?, ?)";
                pstm = con.prepareStatement(consulta4);
                pstm.setString(1, hora_Actual);
                pstm.setDate(2, java.sql.Date.valueOf(fecha_Actual));
                pstm.setInt(3, idEmpleadosFK);
                pstm.executeUpdate();
                cont += 1;
            }

            if(cont == 0){
                labInformacion.setText("");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error!");
                alerta.setContentText("El código o el DNI ingresado no es Válido");
                alerta.showAndWait();
                textCodigo.requestFocus();
            } else{
                labInformacion.setText("OK");
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Registro de Salida Exitosa");
                alerta.setContentText("Se a guardado la Salida correctamente");
                alerta.showAndWait();
            }

        }  catch (SQLException ex) {
            labInformacion.setText("");
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                labInformacion.setText("");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error!");
                alerta.setContentText("Error en la Base de Datos");
                alerta.showAndWait();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }
}
