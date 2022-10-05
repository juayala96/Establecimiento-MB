package com.secadero.modelo;

public class Usuario {
    private String nombre;
    private String contrasenia;
    private String rol;

    public Usuario(String nombre, String contrasenia, String rol) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void crearUsuario(){

    }

    public void modificarUsuario(){

    }
    public void eliminarUsuario(){

    }

    public void consultaUsuario(){

    }

    public boolean login(String nombre, String contrasenia){
        return true;
    }
}
