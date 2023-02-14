package com.secadero.modelo.cronograma;

import java.sql.Time;
import java.util.Date;

public class LeerCronograma {
    private Date fecha;
    private String turno;
    private Time horario_entrada;
    private Time horario_salida;
    private int idCronograma;

    public LeerCronograma(){}

    public LeerCronograma(Date fecha, String turno, Time horario_entrada, Time horario_salida, int idCronograma) {
        this.fecha = fecha;
        this.turno = turno;
        this.horario_entrada = horario_entrada;
        this.horario_salida = horario_salida;
        this.idCronograma = idCronograma;
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

    public Time getHorario_entrada() {
        return horario_entrada;
    }

    public void setHorario_entrada(Time horario_entrada) {
        this.horario_entrada = horario_entrada;
    }

    public Time getHorario_salida() {
        return horario_salida;
    }

    public void setHorario_salida(Time horario_salida) {
        this.horario_salida = horario_salida;
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(int idCronograma) {
        this.idCronograma = idCronograma;
    }


}
