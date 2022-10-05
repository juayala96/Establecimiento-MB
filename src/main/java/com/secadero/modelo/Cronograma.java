package com.secadero.modelo;

import java.sql.Time;
import java.util.Date;

public class Cronograma {
    private Date fecha;
    private String turno;
    private Time horarioEntrada;
    private Time horarioSalida;
    private Empleado empleado;

    public Cronograma(Date fecha, String turno, Time horarioEntrada, Time horarioSalida, Empleado empleado) {
        this.fecha = fecha;
        this.turno = turno;
        this.horarioEntrada = horarioEntrada;
        this.horarioSalida = horarioSalida;
        this.empleado = empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Time getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(Time horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public Time getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(Time horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void crearCronograma(){

    }

    public void modificarCronograma(){

    }

    public void eliminarCronograma(){

    }
}
