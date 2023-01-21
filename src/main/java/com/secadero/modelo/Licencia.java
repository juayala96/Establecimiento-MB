package com.secadero.modelo;

import java.util.Date;

public class Licencia {
    private Date fechaInicio;
    private Date fechaFin;
    private int diasDisponibles;
    private String tipoLicencia;

    public Licencia(Date fechaInicio, Date fechaFin, int diasDisponibles, String tipoLicencia) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diasDisponibles = diasDisponibles;
        this.tipoLicencia = tipoLicencia;
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

    public void registrarLicenica(){

    }

    public void modificarLicencia(){

    }

    public void eliminarLicenica(){

    }

    public void disponibilidadLicencia(){

    }
}
