/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Raimon
 */
public class conexion {
    private Statement sql;
    static String db="baseventas";
    static String url="jdbc:mysql://127.0.0.1/" +db;
    static String user="root";
    static String pass="";
    static Connection conn=null;

    public static Connection getConn() {
        return conn;
    }
    
    
    
    public conexion(){
    }

    
    
    
    public Connection conectar() {
       Connection link=null; 
       
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            
            link=DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        return link;

    }
    
    
     public static void conn() throws Exception{
            try {
            Class.forName("com.mysql.jdbc.Driver");
            
            conn=DriverManager.getConnection(url,user,pass);
         
            } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            }
         
         
     }
    
    
}


