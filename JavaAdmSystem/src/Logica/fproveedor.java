/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vproveedor;
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
public class fproveedor {
    
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
        public DefaultTableModel mostrar(String buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID","Cedula", "Nombre", "Direccion", "Rif", "Empresa", "Telefono", "Correo"};
        
        String [] registro = new String [8];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select * from proveedor where cedula like '%" + buscar + "%' order  by cedula";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next())
            {
                registro [0]= rs.getString("id_proveedor");
                registro [1]= rs.getString("cedula");
                registro [2]= rs.getString("nombre");
                registro [3]= rs.getString("direccion");
                registro [4]= rs.getString("rif");
                registro [5]= rs.getString("empresa");
                registro [6]= rs.getString("telefono");
                registro [7]= rs.getString("correo");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
        
        
        public boolean insertar (vproveedor dts)
        {
            sSQL="Insert into proveedor (id_proveedor,cedula, nombre, direccion, rif, empresa, telefono, correo)" + "values (?,?,?,?,?,?,?,?)";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_proveedor());
                pst.setString(2, dts.getCedula());
                pst.setString(3, dts.getNombre());
                pst.setString(4, dts.getDireccion());
                pst.setString(5, dts.getRif());
                pst.setString(6, dts.getEmpresa());
                pst.setString(7, dts.getTelefono());
                pst.setString(8, dts.getCorreo());
                
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
        
        public boolean editar (vproveedor dts)
        {
            sSQL= "Update proveedor set cedula=?, nombre=?, direccion=?, rif=?, empresa=?, telefono=?, correo=?" + "where id_proveedor=?";
            
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getCedula());
                pst.setString(2, dts.getNombre());
                pst.setString(3, dts.getDireccion());
                pst.setString(4, dts.getRif());
                pst.setString(5, dts.getEmpresa());
                pst.setString(6, dts.getTelefono());
                pst.setString(7, dts.getCorreo());
                pst.setInt(8, dts.getId_proveedor());
                
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
        
        
        public boolean eliminar (vproveedor dts)
        {
            sSQL="delete from proveedor where id_proveedor=?";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_proveedor());

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
