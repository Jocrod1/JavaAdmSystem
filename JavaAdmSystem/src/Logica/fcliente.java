/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import Datos.vcliente;
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
public class fcliente {
        
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
        public DefaultTableModel mostrar(String buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Nombre", "Direccion", "Telefono", "Correo", "Sexo"};
        
        String [] registro = new String [6];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select * from cliente where id_cliente like '%" + buscar + "%' order  by id_cliente";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next())
            {
                registro [0]= rs.getString("id_cliente");
                registro [1]= rs.getString("nombre");
                registro [2]= rs.getString("direccion");;
                registro [3]= rs.getString("telefono");
                registro [4]= rs.getString("correo");
                registro [5]= rs.getString("sexo");
                
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
        
        
        public boolean insertar (vcliente dts)
        {
            sSQL="Insert into cliente (id_cliente,nombre, direccion, telefono, correo, sexo)" + "values (?,?,?,?,?,?)";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getId_cliente());
                pst.setString(2, dts.getNombre());
                pst.setString(3, dts.getDireccion());;
                pst.setString(4, dts.getTelefono());
                pst.setString(5, dts.getCorreo());
                pst.setString(6, dts.getSexo());
                
                
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
        
        public boolean editar (vcliente dts)
        {
            sSQL= "Update cliente set nombre=?, direccion=?, telefono=?, correo=?, sexo=?" + "where id_cliente=?";
            
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getNombre());
                pst.setString(2, dts.getDireccion());
                pst.setString(3, dts.getTelefono());
                pst.setString(4, dts.getCorreo());
                pst.setString(5, dts.getSexo());
                pst.setString(6, dts.getId_cliente());
                
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
        
         public boolean eliminar (vcliente dts)
        {
            sSQL="delete from cliente where id_cliente=?";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getId_cliente());

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
        
         
         
         
         public DefaultTableModel usuariorepetido(String id_cliente)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"Cedula"};
        
        String [] registro = new String [1];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select id_cliente from cliente where id_cliente='" + id_cliente+"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_cliente");
                
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
