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
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    
    public DefaultTableModel mostrar(String buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Nombre", "Descripcion"};
        
        String [] registro = new String [3];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select * from articulo where nombre like '%" + buscar + "%' order  by id_articulo";
    
            try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next())
            {
                registro [0]= rs.getString("id_articulo");
                registro [1]= rs.getString("nombre");
                registro [2]= rs.getString("descripcion");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        } 
    }
    
    public boolean insertar (varticulo dts)
        {
            sSQL="Insert into articulo (nombre, descripcion)" + "values (?,?)";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getNombre());
                pst.setString(2, dts.getDescripcion());

                
                int n=pst.executeUpdate();
                
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
            sSQL= "Update articulo set nombre=?, descripcion=?" + "where id_articulo=?";
            
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getNombre());
                pst.setString(2, dts.getDescripcion());

                
                int n=pst.executeUpdate();
                
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
            sSQL="delete from trabajador where id_articulo=?";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_articulo());

                int n=pst.executeUpdate();
                
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
    
    
    
    
    
}
