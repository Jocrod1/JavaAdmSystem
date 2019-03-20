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
    //se hace la conexion
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    //la tabla mostrar que se verá en el formulario, para cualquier usuario
        public DefaultTableModel mostrar(String buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID","Cedula", "Nombre", "Direccion", "Rif", "Empresa", "Telefono", "Correo"};
        
        String [] registro = new String [8];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        //al principio se mostrarán todos los registros pero cuando se coloque un dato en buscar, solo mostrara el registro comun de ese dato
        sSQL="select * from proveedor where cedula like '" + buscar + "%' order  by cedula";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //se repite para ver cuantos registros hay con ese dato
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
            
            //retorna la tabla, puede ser llena por no colocar ningun dato, o tambien puede ser vacia por ningun dato en comun, mostrando un error
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
        
        //se insertan los datos de un proveedor nuevo
        public boolean insertar (vproveedor dts)
        {
            sSQL="Insert into proveedor (id_proveedor,cedula, nombre, direccion, rif, empresa, telefono, correo)" + "values (?,?,?,?,?,?,?,?)";
            try {
                //se obtienen los datos del formulario a la BD
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
                
                
                //si ejecuta el update, muestra true la funcion, si no, da error
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
        
        //esta funcion edita el registro usando la comparacion del id del proveedor
        public boolean editar (vproveedor dts)
        {
            sSQL= "Update proveedor set cedula=?, nombre=?, direccion=?, rif=?, empresa=?, telefono=?, correo=?" + "where id_proveedor=?";
            
            try {
                //se colocan en el mismo orden que en el sql
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
                
                //si ejecuta el update devuelve true la funcion, si no da error
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
        
        //esta funcion busca el id del proveedor para luego eliminar el registro
        public boolean eliminar (vproveedor dts)
        {
            sSQL="delete from proveedor where cedula=?";
            try {
                //se busca un unico dato y de ahi lo facilita para eliminarlo por completo
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getCedula());

                int n=pst.executeUpdate();
                //si ejecuta el update devuelve true la funcion, si no da error
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
        
                 public DefaultTableModel usuariorepetido(String id_proveedor)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"Cedula"};
        
        String [] registro = new String [1];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select cedula from proveedor where cedula='" + id_proveedor+"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //se repite en cada uno de los registro para asegurar que no haya una cedula igual     
            while(rs.next())
            {
                registro [0]= rs.getString("cedula");
                
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
