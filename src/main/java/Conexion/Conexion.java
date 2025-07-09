/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pablo
 */
public class Conexion {
    
    private static String url = "jdbc:mysql://database.c1qy2c4wwbk2.eu-north-1.rds.amazonaws.com:3306/guardado?serverTimezone=UTC"; 
    private static String user = "root"; 
    private static String pass = "pablongo03";   
    private static Connection con; 
    
    public static Connection getConnection() throws SQLException{
        if(con == null){
            con = DriverManager.getConnection(url,user,pass); 
        }
        return con; 
    }
    
}
