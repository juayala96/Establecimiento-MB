package com.secadero.modelo;

import java.util.Date;

public class Ausencia {
    private Date fecha;
    private String motivo;
    private boolean justificado;
    private String certificado;
    private Empleado empleados;

    public Ausencia(Date fecha, String motivo, boolean justificado, String certificado, Empleado empleados) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.justificado = justificado;
        this.certificado = certificado;
        this.empleados = empleados;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isJustificado() {
        return justificado;
    }

    public void setJustificado(boolean justificado) {
        this.justificado = justificado;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public Empleado getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleado empleados) {
        this.empleados = empleados;
    }

    public void registrarAusencia(){

    }

    public void modificarAusencia(){

    }

    public void eliminarAusencia(){

    }

    public void busquedaDeAusencia(){

    }
}
