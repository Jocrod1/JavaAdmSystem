/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vdetalle_ingreso;
import Datos.vingreso;
import Datos.vtrabajador;
import datechooser.beans.customizer.PropertyTableModel;
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
public class fingreso {
    
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    
    public DefaultTableModel mostrar()
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Trabajador", "Proveedor", "Representante Legal", "Fecha", "Estado", "Precio Total"};
        
        String [] registro = new String [7];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select i.id_ingreso, t.nombre as trabajador, p.empresa as Proveedor,\n" +
" p.nombre as RepresentanteLegal, i.fecha, i.estado, i.precio_total\n" +
" from ingreso i inner join proveedor p on i.id_proveedor = p.id_proveedor\n" +
" inner join  trabajador t on i.id_trabajador = t.id_trabajador\n" +
" order by i.id_ingreso desc";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_ingreso");
                registro [1]= rs.getString("trabajador");
                registro [2]= rs.getString("Proveedor");
                registro [3]= rs.getString("RepresentanteLegal");
                registro [4]= rs.getString("fecha");
                registro [5]= rs.getString("estado");
                registro [6]= rs.getString("precio_total");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
    
    public DefaultTableModel buscarentrefechas(String Buscar, String Buscar2)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Trabajador", "Proveedor", "Representante Legal", "Fecha", "Estado", "Precio Total"};
        
        String [] registro = new String [7];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select i.id_ingreso, t.nombre as trabajador, p.empresa as Proveedor,\n" +
" p.nombre as RepresentanteLegal, i.fecha, i.estado, i.precio_total\n" +
" from ingreso i inner join proveedor p on i.id_proveedor = p.id_proveedor\n" +
" inner join  trabajador t on i.id_trabajador = t.id_trabajador\n" +
" where i.fecha BETWEEN '"+ Buscar +"' and '"+ Buscar2 +"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_ingreso");
                registro [1]= rs.getString("trabajador");
                registro [2]= rs.getString("Proveedor");
                registro [3]= rs.getString("RepresentanteLegal");
                registro [4]= rs.getString("fecha");
                registro [5]= rs.getString("estado");
                registro [6]= rs.getString("precio_total");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
    
    public DefaultTableModel MostrarDetalle(String Buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Articulo", "Precio de Compra", "Precio de Venta", "Stock Inicial", "Stock Actual", "Subtotal"};
        
        String [] registro = new String [7];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select d.id_articulo, a.nombre as Articulo, d.precio_compra, d.precio_venta, d.stock_inicial,\n" +
"d.stock_actual, (d.stock_inicial * d.precio_compra) as Subtotal\n" +
"from detalle_ingreso d inner join articulo a on d.id_articulo = a.id_articulo\n" +
"where d.id_ingreso = '"+ Buscar +"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_articulo");
                registro [1]= rs.getString("Articulo");
                registro [2]= rs.getString("precio_compra");
                registro [3]= rs.getString("precio_venta");
                registro [4]= rs.getString("stock_inicial");
                registro [5]= rs.getString("stock_actual");
                registro [6]= rs.getString("Subtotal");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
    
    
    public int Obteneridentity()
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID"};
        
        int registro = 0;
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="SELECT id_ingreso FROM ingreso WHERE id_ingreso =(SELECT MAX(id_ingreso)FROM ingreso)";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro = rs.getInt("id_ingreso");
            }
                 
                 
            
            return registro;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return 0;
        }  
    }
        public boolean insertar (vingreso dts)
        {
            sSQL="insert into ingreso (id_trabajador, id_proveedor, fecha, estado, precio_total)" + "values (?,?,?,'APROBADO',?)";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_trabajador());
                pst.setInt(2, dts.getId_proveedor());
                pst.setDate(3, dts.getFecha());
                pst.setString(4, dts.getEstado());
                pst.setDouble(5, dts.getPrecio_total());
                
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
    
        public boolean insertarDetalle (vdetalle_ingreso dts)
        {
            sSQL="insert into detalle_ingreso (id_ingreso, id_articulo, precio_compra, precio_venta, stock_inicial, stock_actual)"
                    + "values (?,?,?,?,?,?)";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_ingreso());
                pst.setInt(2, dts.getId_articulo());
                pst.setDouble(3, dts.getPrecio_compra());
                pst.setDouble(4, dts.getPrecio_venta());
                pst.setInt(5, dts.getStock_inicial());
                pst.setInt(6, dts.getStock_actual());
                
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
                
        public boolean Anular (vingreso dts)
        {
            sSQL="update ingreso set estado = 'ANULADO'\n" +
" where id_ingreso = ?";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_ingreso());

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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

