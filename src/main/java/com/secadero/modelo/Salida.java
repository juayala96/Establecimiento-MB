package com.secadero.modelo;

import java.sql.Time;
import java.util.Date;

public class Salida {
    private Date fecha;
    private Time hora;
    private Empleado empleados;

    public Salida(Date fecha, Time hora, Empleado empleados) {
        this.fecha = fecha;
        this.hora = hora;
        this.empleados = empleados;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Empleado getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleado empleados) {
        this.empleados = empleados;
    }

    private void registrarSalida(){

    }
}
