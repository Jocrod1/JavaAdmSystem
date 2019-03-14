/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.varticulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raimon
 */
public class farticulo {
    //se hace la conexion con la BD de mysql
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    //se empieza colocando vacio para que no ocurran conflictos con llamadas anteriores
    private String sSQL="";
    public Integer totalregistros;
    
    public DefaultTableModel mostrar(String buscar) 
    { 
        DefaultTableModel modelo; 
         
        //se colocan los titulos que tendrá la tabla 
        String [] titulos = {"Codigo del Articulo", "Nombre del Articulo", "Descripcion del Articulo"}; 
         
        String [] registro = new String [3]; 
         
        totalregistros=0; 
         
        modelo= new DefaultTableModel(null, titulos); 
         
        //este es el código que se enviará a la BD, el cua se ordenará por el id del articulo y se regresará al programa 
        sSQL="select * from articulo where id_articulo like '%" + buscar + "%' order  by id_articulo"; 
     
            try { 
            Statement st= cn.createStatement(); 
            ResultSet rs=st.executeQuery(sSQL); 
             
            while(rs.next()) 
            { 
                //los parámetros que regresará de la BD 
                registro [0]= rs.getString("id_articulo"); 
                registro [1]= rs.getString("nombre"); 
                registro [2]= rs.getString("descripcion"); 
                 
                totalregistros= totalregistros+1; 
                modelo.addRow(registro); 
            } 
             
            return modelo; 
             
        } catch (Exception e) { 
            //muestra un error si no se conecta bien la BD o si no recibe los parámetros 
            JOptionPane.showConfirmDialog(null, e); 
            return null; 
        }  
    } 
    
    //esta es la tabla en la que se mostrarán todos los datos que se van a poder ver para el usuario
    public DefaultTableModel mostrarstocks(String buscar)
    {
        DefaultTableModel modelo;
        
        //se colocan los titulos que tendrá la tabla
        String [] titulos = {"Codigo del Articulo", "Nombre del Articulo", "Descripcion del Articulo", "Stock Total"};
        
        String [] registro = new String [4];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        //este es el código que se enviará a la BD, el cua se ordenará por el id del articulo y se regresará al programa
        sSQL="SELECT a.id_articulo, a.nombre, a.descripcion, SUM(d.stock_actual) AS Stock_Total\n" +
                "FROM articulo a INNER JOIN detalle_ingreso d ON a.id_articulo = d.id_articulo\n" +
                "INNER JOIN ingreso i ON d.id_ingreso = i.id_ingreso\n" +
                "WHERE a.id_articulo LIKE '%"+ buscar +"%'\n" +
                "GROUP BY a.id_articulo, a.nombre, a.descripcion\n" +
                "ORDER BY SUM(d.stock_actual) ASC";
    
            try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next())
            {
                //los parámetros que regresará de la BD
                registro [0]= rs.getString("id_articulo");
                registro [1]= rs.getString("nombre");
                registro [2]= rs.getString("descripcion");
                registro [3]= rs.getString("Stock_Total");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            //muestra un error si no se conecta bien la BD o si no recibe los parámetros
            JOptionPane.showConfirmDialog(null, e);
            return null;
        } 
    }
    
    public boolean insertar (varticulo dts)
        {
            //aca inserta en la tabla los datos con el mismo orden que en el mysql
            sSQL="Insert into articulo (id_articulo, nombre, descripcion)" + "values (?,?,?)";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                //se obtienen los datos del formulario y se envian a la BD
                pst.setString(1, dts.getId_articulo());
                pst.setString(2, dts.getNombre());
                pst.setString(3, dts.getDescripcion());

                
                int n=pst.executeUpdate();
                
                //si se actualiza el statement (se guarda) si guardó, si no, retorna la funcion
                if(n!=0)
                {
                   return true; 
                }
                else
                {
                    return false;
                }
                
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
                return false;
            }
        }
    
    public boolean editar (varticulo dts)
        {
            //aca se cambian los parámetros según el campo principal que le otorgue, en este caso, el ID
            sSQL= "Update articulo set nombre=?, descripcion=?"+" where id_articulo=?";
            
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                //se asignan los parámetros y se coloca al final con cual se comparará, en el mismo orden que en el comando
                pst.setString(1, dts.getNombre());
                pst.setString(2, dts.getDescripcion());
                pst.setString(3, dts.getId_articulo());

                
                int n=pst.executeUpdate();
                
                //se repite el mismo procedimiento para ver si guardó en la BD
                if(n!=0)
                {
                   return true; 
                }
                else
                {
                    return false;
                }
                
                
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
                return false;
            }
        }
    
    
            public boolean eliminar (varticulo dts)
        {
            //se ejecuta que se elimina cuando el ID sea igual
            sSQL="delete from articulo where id_articulo=?";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                //se coloca sólo el parámetro del id, ya que no se necesitan los demás
                pst.setString(1, dts.getId_articulo());

                int n=pst.executeUpdate();
                
                //y se repite el procedimiento a ver si se guardó el mensaje de eliminar
                if(n!=0)
                {
                   return true; 
                }
                else
                {
                    return false;
                }
                
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
                return false;
            }
        }
            
            
             public DefaultTableModel usuariorepetido(String id_articulo)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"Codigo"};
        
        String [] registro = new String [1];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select id_articulo from articulo where id_articulo='" + id_articulo+"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_articulo");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
    
    
    
    
    
}
