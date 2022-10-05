package com.secadero.modelo;

import java.util.Date;

public class Licencia {
    private Date fechaInicio;
    private Date fechaFin;
    private int diasDisponibles;
    private String tipoLicencia;
    private Empleado empleado;

    public Licencia(Date fechaInicio, Date fechaFin, int diasDisponibles, String tipoLicencia, Empleado empleado) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diasDisponibles = diasDisponibles;
        this.tipoLicencia = tipoLicencia;
        this.empleado = empleado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDiasDisponibles() {
        return diasDisponibles;
    }

    public void setDiasDisponibles(int diasDisponibles) {
        this.diasDisponibles = diasDisponibles;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void registrarLicenica(){

    }

    public void modificarLicencia(){

    }

    public void eliminarLicenica(){

    }

    public void disponibilidadLicencia(){

    }
}
