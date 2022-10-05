package com.secadero.modelo;

import java.util.Date;

public class Empleado {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private String telefono;
    private int legajo;
    private Date fechaDeIngreso;
    private String fotografia;
    private String genero;
    private String estadoCivil;
    private String area;
    private String puesto;
    private String grupoSanguineo;
    private String direccion;
    private String prueba;
    private String otraPrueba;

    public Empleado(String nombre, String apellido, Date fechaDeNacimiento, String telefono, int legajo,
                    Date fechaDeIngreso, String fotografia, String genero, String estadoCivil, String area,
                    String puesto, String grupoSanguineo, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.telefono = telefono;
        this.legajo = legajo;
        this.fechaDeIngreso = fechaDeIngreso;
        this.fotografia = fotografia;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.area = area;
        this.puesto = puesto;
        this.grupoSanguineo = grupoSanguineo;
        this.direccion = direccion;
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

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public Date getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(Date fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void crearEmpleado(){

    }

    public void modificarEmpleado(){

    }

    public void eliminarEmpleado(){

    }

    public void busquedaDeEmpleado(){

    }
}
