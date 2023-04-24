package com.secadero.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static Connection con = null;

    public static Connection leerConexion(){
        try {
            if(con == null || con.isClosed()){
                String usuario = "root";
                String contrasenia = "Supremo9";
                String drive = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/secadero";
                Class.forName(drive);
                con = DriverManager.getConnection(url, usuario, contrasenia);
            }
            return con;
        } catch (Exception ex){
            throw new RuntimeException("Error al crear la Conexión: " + ex);
        }
    }
}
