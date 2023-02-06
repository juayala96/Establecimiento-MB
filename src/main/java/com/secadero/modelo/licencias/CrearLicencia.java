package com.secadero.modelo.licencias;

import com.secadero.conexion.Conexion;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearLicencia {

    public CrearLicencia(){}


    //--------------------------------------------- Crear Licencia ---------------------------------------------------
    public void agregarLicencia(Label labIDEmpleadoCrear, DatePicker dpFechaInicioCrear, DatePicker dpFechaFinCrear, ComboBox<String> cbTipoLicenciaCrear, Label labLimpiarCamposCrear) throws ParseException {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String dato;
        String resultado = "YES";
        int diasVacaciones = 0;
        int diasEnfermedad = 0;
        int diasAccidente = 0;
        int diasMatrimonio = 0;
        int diasMuerteFamiliar = 0;

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // ------------------------- Fecha Inicio -------------------------------
        String fechaI = dpFechaInicioCrear.getEditor().getText();
        Date fechaInicio = formatoFecha.parse(fechaI);

        // --------------------------- Fecha Fin --------------------------------
        String fechaF = dpFechaFinCrear.getEditor().getText();
        Date fechaFin = formatoFecha.parse(fechaF);

        // --------------------- Diferencia entre Fechas ------------------------
        long Diferencias = fechaInicio.getTime() - fechaFin.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

        try {
            String consulta1 = "SELECT empleados.idempleados FROM licencias INNER JOIN empleado_licencia ON licencias.idLicencias = empleado_licencia.idLicenciaFK INNER JOIN empleados ON empleado_licencia.idEmpleadoFK = empleados.idempleados WHERE idEmpleadoFK = ? AND empleados.estado = ?";
            pstm = con.prepareStatement(consulta1);
            pstm.setString(1, labIDEmpleadoCrear.getText());
            pstm.setString(2, "Licencia");
            rs = pstm.executeQuery();
            while (rs.next()) {
                dato = String.valueOf(rs.getInt("idempleados"));
                if (dato.isEmpty()) {
                    resultado = "NO";
                }
            }
        } catch (Exception e1) {
            System.err.println("Error: " + e1.getMessage());
        }

        System.out.println("Dias: " + (-Cant_Dias));
        /*
        if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Vacaciones")) {
            diasVacaciones = (int) (VACACIONES - (-Cant_Dias) - 1);
        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Enfermedad")) {
            diasEnfermedad = (int) (ENFERMEDAD - (-Cant_Dias) - 1);
        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Accidente")) {
            diasAccidente = (int) (ACCIDENTE - (-Cant_Dias) - 1);
        } else if (cbTipoLicenciaCrear.getSelectionModel().getSelectedItem().equals("Matrimonio")) {
            diasMatrimonio = (int) (MATRIMONIO - (-Cant_Dias) - 1);
        } else {
            diasMuerteFamiliar = (int) (MUERTE_FAMILIAR - (-Cant_Dias) - 1);
        }

         */

        System.out.println(diasVacaciones);
        System.out.println(diasEnfermedad);
        System.out.println(diasAccidente);
        System.out.println(diasMatrimonio);
        System.out.println(diasMuerteFamiliar);


        if (resultado.equals("YES")) {
            if (-Cant_Dias >= 0) {
                if(diasVacaciones >= 0 && diasEnfermedad >= 0 && diasAccidente >= 0 && diasMatrimonio >= 0 && diasMuerteFamiliar >= 0){
                    System.out.println();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Días Disponibles!");
                    alerta.setContentText("La Fecha de Inicio y Fin que a Propuesto en dicho tipo de licencia ya no queda esa cantidad de Días Disponibles");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Fechas!");
                alerta.setContentText("La Fecha de Inicio debe de ser antes de la Fecha de Fin");
                alerta.showAndWait();
            }
        }
    }
}
