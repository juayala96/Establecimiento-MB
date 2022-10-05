package com.secadero.modelo;

import java.sql.Time;
import java.util.Date;

public class Entrada {
    private Date fecha;
    private Time hora;
    private Empleado empleados;

    public Entrada(Date fecha, Time hora, Empleado empleados) {
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

    private void registrarEntrada(){

    }
}
