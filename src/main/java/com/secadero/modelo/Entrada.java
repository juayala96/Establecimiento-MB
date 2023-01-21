package com.secadero.modelo;

import java.sql.Time;
import java.util.Date;

public class Entrada {
    private Date fecha;
    private Time hora;

    public Entrada(Date fecha, Time hora) {
        this.fecha = fecha;
        this.hora = hora;;
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

    private void registrarEntrada(){

    }
}
