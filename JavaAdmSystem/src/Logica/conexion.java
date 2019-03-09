/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Raimon
 */
public class conexion {
    //se asigna como se llama la bd en el myphpadmin, la direccion url básica y el usuario básico
    public String db="baseventas";
    public String url="jdbc:mysql://127.0.0.1/" +db;
    public String user="root";
    public String pass="";
    
    public conexion(){
    }
    
    public Connection conectar() {
        //se empieza colocando la direccion nula para que empieze cada funcion conectando a la BD
       Connection link=null; 
       
        try {
            //se coloca el driver que se instaló en el netbeans para poder conectar con mysql workbench
            Class.forName("org.gjt.mm.mysql.Driver");
            
            //aca es donde por fin se conecta a myphpadmin, colocando el url, el usuario y la contraseña base
            link=DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        //aca retorna el link, si es nula muestra un mensaje de error como se ve en la linea anterior, si conecta no muestra nada
        return link;

    }
    
    
}


