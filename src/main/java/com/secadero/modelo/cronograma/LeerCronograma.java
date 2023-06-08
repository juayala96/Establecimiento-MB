package com.secadero.modelo.cronograma;

import com.secadero.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.sql.*;

public class LeerCronograma {
    private String nombre;
    private String apellido;
    private int legajo;
    private int dni;
    private int idempleados;

    private String fecha;
    private String turno;
    private String horario_entrada;
    private String horario_salida;
    private int idCronograma;

    public LeerCronograma(){}

    public LeerCronograma(String fecha, String turno, String horario_entrada, String horario_salida, int idCronograma) {
        this.fecha = fecha;
        this.turno = turno;
        this.horario_entrada = horario_entrada;
        this.horario_salida = horario_salida;
        this.idCronograma = idCronograma;
    }

    public LeerCronograma(String nombre, String apellido, int legajo, int dni, String fecha, String turno, String horario_entrada, String horario_salida, int idempleados, int idCronograma) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.dni = dni;
        this.fecha = fecha;
        this.turno = turno;
        this.horario_entrada = horario_entrada;
        this.horario_salida = horario_salida;
        this.idempleados = idempleados;
        this.idCronograma = idCronograma;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHorario_entrada() {
        return horario_entrada;
    }

    public void setHorario_entrada(String horario_entrada) {
        this.horario_entrada = horario_entrada;
    }

    public String getHorario_salida() {
        return horario_salida;
    }

    public void setHorario_salida(String horario_salida) {
        this.horario_salida = horario_salida;
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(int idCronograma) {
        this.idCronograma = idCronograma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getIdempleados() {
        return idempleados;
    }

    public void setIdempleados(int idempleados) {
        this.idempleados = idempleados;
    }

    //------------------------------------------- Leer Cronograma --------------------------------------------------
    public static ObservableList<LeerCronograma> listaCronograma(Label labIDEmpleadoLista){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();
        String hora_entrada;
        String hora_salida;

        try {
            pstm = con.prepareStatement("SELECT fecha, turno, horario_entrada, horario_salida, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND empleado_cronograma.idEmpleadoFK = ? ORDER BY fecha DESC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Disponible");
            pstm.setInt(3, Integer.parseInt(labIDEmpleadoLista.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                hora_entrada = String.valueOf(rs.getTime("horario_entrada")).substring(0, 5);
                hora_salida = String.valueOf(rs.getTime("horario_salida")).substring(0, 5);

                String fechaClave = rs.getString("fecha");
                String fechaAnio = fechaClave.substring(0, 4);
                String fechaMes = fechaClave.substring(5, 7);
                String fechaDia = fechaClave.substring(8, 10);
                String fechaModificada = (fechaDia + "-" + fechaMes + "-" + fechaAnio);
                lista.add(new LeerCronograma(fechaModificada, rs.getString("turno"), hora_entrada, hora_salida, rs.getInt("idCronograma")));
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex){
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    //-------------------------------------- Leer Cronograma Comprobación ----------------------------------------------
    public void listaCronogramaComprobacion(Label labIDEmpleadoLista, Label labIDCronogramaLista, Label labResultadoID){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int cont = 0;

        try {
            pstm = con.prepareStatement("SELECT idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND empleado_cronograma.idEmpleadoFK = ? AND cronograma.idCronograma = ?");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(labIDEmpleadoLista.getText()));
            pstm.setInt(3, Integer.parseInt(labIDCronogramaLista.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                labResultadoID.setText("YES");
                cont +=1;
            }

            if(cont == 0){
                labResultadoID.setText("NO");
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex){
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }

    //------------------------------------------- Buscar Cronograma --------------------------------------------------
    public static ObservableList<LeerCronograma> buscarFechaCronograma(Label labIDEmpleadoLista, DatePicker dpBuscarFecha) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();
        String hora_entrada;
        String hora_salida;

        try {
            String fecha = dpBuscarFecha.getEditor().getText();
            String fechaAnio = fecha.substring(6, 10);
            String fechaMes = fecha.substring(3, 5);
            String fechaDia = fecha.substring(0, 2);
            String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

            pstm = con.prepareStatement("SELECT fecha, turno, horario_entrada, horario_salida, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleados.estado = ? AND empleado_cronograma.idEmpleadoFK = ? AND cronograma.fecha = ? ORDER BY turno DESC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, "Disponible");
            pstm.setInt(3, Integer.parseInt(labIDEmpleadoLista.getText()));
            pstm.setDate(4, java.sql.Date.valueOf(fechaModificada));
            rs = pstm.executeQuery();

            while (rs.next()) {
                hora_entrada = String.valueOf(rs.getTime("horario_entrada")).substring(0, 5);
                hora_salida = String.valueOf(rs.getTime("horario_salida")).substring(0, 5);

                String fechaClave = rs.getString("fecha");
                String fechaAnio2 = fechaClave.substring(0, 4);
                String fechaMes2 = fechaClave.substring(5, 7);
                String fechaDia2 = fechaClave.substring(8, 10);
                String fechaModificada2 = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                lista.add(new LeerCronograma(fechaModificada2, rs.getString("turno"), hora_entrada, hora_salida, rs.getInt("idCronograma")));
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    //-------------------------------------- Leer Cronograma Calendario -------------------------------------------------
    public static ObservableList<LeerCronograma> listaEmpleadoCalendario(DatePicker dpFechaCalendario) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();

        try {
            String fecha = dpFechaCalendario.getEditor().getText();
            String fechaAnio2 = fecha.substring(6, 10);
            String fechaMes2 = fecha.substring(3, 5);
            String fechaDia2 = fecha.substring(0, 2);
            String fechaModificada2 = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, fecha, turno, horario_entrada, horario_salida, idempleados, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND cronograma.fecha = ? ORDER BY turno DESC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, fechaModificada2);
            rs = pstm.executeQuery();

            while (rs.next()){
                String fechaClave = rs.getString("fecha");
                String fechaAnio = fechaClave.substring(0, 4);
                String fechaMes = fechaClave.substring(5, 7);
                String fechaDia = fechaClave.substring(8, 10);
                String fechaModificada = (fechaDia + "-" + fechaMes + "-" + fechaAnio);
                lista.add(new LeerCronograma(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), fechaModificada, rs.getString("turno"), rs.getString("horario_entrada"), rs.getString("horario_salida"), rs.getInt("idempleados"), rs.getInt("idCronograma")));
            }

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    //---------------------------------------- Buscar Cronograma Calendario ----------------------------------------------
    public static ObservableList<LeerCronograma> buscarFechaCalendario(DatePicker dpFechaCalendario) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();

        try {
            String fecha2 = dpFechaCalendario.getEditor().getText();
            String fechaAnio2 = fecha2.substring(6, 10);
            String fechaMes2 = fecha2.substring(3, 5);
            String fechaDia2 = fecha2.substring(0, 2);
            String fechaModificada2 = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);
            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, fecha, turno, horario_entrada, horario_salida, idempleados, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND cronograma.fecha = ? ORDER BY turno DESC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, fechaModificada2);
            rs = pstm.executeQuery();

            while (rs.next()){
                String fechaClave = rs.getString("fecha");
                String fechaAnio = fechaClave.substring(0, 4);
                String fechaMes = fechaClave.substring(5, 7);
                String fechaDia = fechaClave.substring(8, 10);
                String fechaModificada = (fechaDia + "-" + fechaMes + "-" + fechaAnio);
                lista.add(new LeerCronograma(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), fechaModificada, rs.getString("turno"), rs.getString("horario_entrada"), rs.getString("horario_salida"), rs.getInt("idempleados"), rs.getInt("idCronograma")));
            }

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    //---------------------------------------- Buscar Legajo Calendario ----------------------------------------------
    public static ObservableList<LeerCronograma> buscarLegajoCalendario(TextField textBuscarLegajoEmpleadoCalendario, DatePicker dpFechaCalendario, ComboBox<String> cbBuscarEmpleadoCalendario) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();
        String dato = cbBuscarEmpleadoCalendario.getSelectionModel().getSelectedItem().toLowerCase();


        try {
            String fechaBuscar = dpFechaCalendario.getEditor().getText();
            String fechaAnio = fechaBuscar.substring(6, 10);
            String fechaMes = fechaBuscar.substring(3, 5);
            String fechaDia = fechaBuscar.substring(0, 2);
            String fechaBusqueda = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

            pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, fecha, turno, horario_entrada, horario_salida, idempleados, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND cronograma.fecha = ? AND empleados."+ dato +" LIKE ? ORDER BY turno DESC");
            pstm.setString(1, "Vigente");
            pstm.setString(2, fechaBusqueda);
            pstm.setString(3, textBuscarLegajoEmpleadoCalendario.getText()  + "%");
            rs = pstm.executeQuery();

            while (rs.next()){
                String fechaClave = rs.getString("fecha");
                String fechaAnio2 = fechaClave.substring(0, 4);
                String fechaMes2 = fechaClave.substring(5, 7);
                String fechaDia2 = fechaClave.substring(8, 10);
                String fechaModificada2 = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                lista.add(new LeerCronograma(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), fechaModificada2, rs.getString("turno"), rs.getString("horario_entrada"), rs.getString("horario_salida"), rs.getInt("idempleados"), rs.getInt("idCronograma")));
            }

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    //---------------------------------------- Buscar Turno Calendario ----------------------------------------------
    public static ObservableList<LeerCronograma> buscarLegajoCalendarioTurno(TextField textBuscarLegajoEmpleadoCalendario, DatePicker dpFechaCalendario, ComboBox<String> cbBuscarEmpleadoCalendario, ComboBox<String> cbBuscarTurnoCalendario) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();
        String dato = cbBuscarEmpleadoCalendario.getSelectionModel().getSelectedItem().toLowerCase();
        String datoTurno = cbBuscarTurnoCalendario.getSelectionModel().getSelectedItem().toLowerCase();

        try {
            if(!textBuscarLegajoEmpleadoCalendario.getText().equals("")){
                String fechaBuscar = dpFechaCalendario.getEditor().getText();
                String fechaAnio = fechaBuscar.substring(6, 10);
                String fechaMes = fechaBuscar.substring(3, 5);
                String fechaDia = fechaBuscar.substring(0, 2);
                String fechaBusqueda = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

                pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, fecha, turno, horario_entrada, horario_salida, idempleados, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND cronograma.fecha = ? AND cronograma.turno = ? AND empleados."+ dato +" LIKE ? ORDER BY legajo ASC");
                pstm.setString(1, "Vigente");
                pstm.setString(2, fechaBusqueda);
                pstm.setString(3, datoTurno);
                pstm.setString(4, textBuscarLegajoEmpleadoCalendario.getText()  + "%");
                rs = pstm.executeQuery();

                while (rs.next()){
                    String fechaClave = rs.getString("fecha");
                    String fechaAnio2 = fechaClave.substring(0, 4);
                    String fechaMes2 = fechaClave.substring(5, 7);
                    String fechaDia2 = fechaClave.substring(8, 10);
                    String fechaModificada2 = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                    lista.add(new LeerCronograma(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), fechaModificada2, rs.getString("turno"), rs.getString("horario_entrada"), rs.getString("horario_salida"), rs.getInt("idempleados"), rs.getInt("idCronograma")));
                }
            } else {
                String fechaBuscar = dpFechaCalendario.getEditor().getText();
                String fechaAnio = fechaBuscar.substring(6, 10);
                String fechaMes = fechaBuscar.substring(3, 5);
                String fechaDia = fechaBuscar.substring(0, 2);
                String fechaBusqueda = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

                pstm = con.prepareStatement("SELECT nombre, apellido, legajo, dni, fecha, turno, horario_entrada, horario_salida, idempleados, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleados.idempleados = empleado_cronograma.idEmpleadoFK WHERE empleados.estadoEmpleado = ? AND cronograma.fecha = ? AND cronograma.turno = ? ORDER BY legajo ASC");
                pstm.setString(1, "Vigente");
                pstm.setString(2, fechaBusqueda);
                pstm.setString(3, datoTurno);
                rs = pstm.executeQuery();

                while (rs.next()){
                    String fechaClave = rs.getString("fecha");
                    String fechaAnio2 = fechaClave.substring(0, 4);
                    String fechaMes2 = fechaClave.substring(5, 7);
                    String fechaDia2 = fechaClave.substring(8, 10);
                    String fechaModificada2 = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                    lista.add(new LeerCronograma(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("legajo"), rs.getInt("dni"), fechaModificada2, rs.getString("turno"), rs.getString("horario_entrada"), rs.getString("horario_salida"), rs.getInt("idempleados"), rs.getInt("idCronograma")));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    //--------------------------------------- Leer Cronograma (Licencia) --------------------------------------------------
    public static ObservableList<LeerCronograma> listaCronogramaConsulta(Label labIDEmpleadoConsulta){
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();
        String hora_entrada;
        String hora_salida;

        try {
            pstm = con.prepareStatement("SELECT fecha, turno, horario_entrada, horario_salida, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleado_cronograma.idEmpleadoFK = ? ORDER BY fecha DESC");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(labIDEmpleadoConsulta.getText()));
            rs = pstm.executeQuery();

            while (rs.next()){
                hora_entrada = String.valueOf(rs.getTime("horario_entrada")).substring(0, 5);
                hora_salida = String.valueOf(rs.getTime("horario_salida")).substring(0, 5);

                String fechaClave = rs.getString("fecha");
                String fechaAnio = fechaClave.substring(0, 4);
                String fechaMes = fechaClave.substring(5, 7);
                String fechaDia = fechaClave.substring(8, 10);
                String fechaModificada = (fechaDia + "-" + fechaMes + "-" + fechaAnio);
                lista.add(new LeerCronograma(fechaModificada, rs.getString("turno"), hora_entrada, hora_salida, rs.getInt("idCronograma")));
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex){
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    //------------------------------------------- Buscar Cronograma --------------------------------------------------
    public static ObservableList<LeerCronograma> buscarFechaCronogramaConsulta(Label labIDEmpleadoConsulta, DatePicker dpBuscarFecha) {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<LeerCronograma> lista = FXCollections.observableArrayList();
        String hora_entrada;
        String hora_salida;

        try {
            String fecha = dpBuscarFecha.getEditor().getText();
            String fechaAnio = fecha.substring(6, 10);
            String fechaMes = fecha.substring(3, 5);
            String fechaDia = fecha.substring(0, 2);
            String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

            pstm = con.prepareStatement("SELECT fecha, turno, horario_entrada, horario_salida, idCronograma FROM cronograma INNER JOIN empleado_cronograma ON cronograma.idCronograma = empleado_cronograma.idCronogramaFK INNER JOIN empleados ON empleado_cronograma.idEmpleadoFK = empleados.idempleados WHERE empleados.estadoEmpleado = ? AND empleado_cronograma.idEmpleadoFK = ? AND cronograma.fecha = ?");
            pstm.setString(1, "Vigente");
            pstm.setInt(2, Integer.parseInt(labIDEmpleadoConsulta.getText()));
            pstm.setDate(3, java.sql.Date.valueOf(fechaModificada));
            rs = pstm.executeQuery();

            while (rs.next()) {
                hora_entrada = String.valueOf(rs.getTime("horario_entrada")).substring(0, 5);
                hora_salida = String.valueOf(rs.getTime("horario_salida")).substring(0, 5);

                String fechaClave = rs.getString("fecha");
                String fechaAnio2 = fechaClave.substring(0, 4);
                String fechaMes2 = fechaClave.substring(5, 7);
                String fechaDia2 = fechaClave.substring(8, 10);
                String fechaModificada2 = (fechaDia2 + "-" + fechaMes2 + "-" + fechaAnio2);
                lista.add(new LeerCronograma(fechaModificada2, rs.getString("turno"), hora_entrada, hora_salida, rs.getInt("idCronograma")));
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }
}
