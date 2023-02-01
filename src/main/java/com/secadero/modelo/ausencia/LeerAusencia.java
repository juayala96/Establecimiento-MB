package com.secadero.modelo.ausencia;

import java.util.Date;

public class LeerAusencia {
    private Date fecha;
    private String motivo;
    private String certificado;
    private int idEmpleadoFK;
    private int idausencias;

    public LeerAusencia(){}

    public LeerAusencia(Date fecha, String motivo, String certificado, int idEmpleadoFK, int idausencias) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.certificado = certificado;
        this.idEmpleadoFK = idEmpleadoFK;
        this.idausencias = idausencias;
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

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public int getIdEmpleadoFK() {
        return idEmpleadoFK;
    }

    public void setIdEmpleadoFK(int idEmpleadoFK) {
        this.idEmpleadoFK = idEmpleadoFK;
    }

    public int getIdausencias() {
        return idausencias;
    }

    public void setIdausencias(int idausencias) {
        this.idausencias = idausencias;
    }

    //------------------------------------------- Leer Ausencias --------------------------------------------------
}
