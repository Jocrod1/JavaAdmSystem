/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vdetalle_ingreso;
import Datos.vdetalle_venta;
import Datos.vingreso;
import Datos.vtrabajador;
import Datos.vventa;
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
public class fventa {
    
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    
    public DefaultTableModel mostrar()
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Trabajador", "Cliente", "Fecha", "Estado", "Subtotal", "Impuesto", "Total"};
        
        String [] registro = new String [8];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select v.id_venta, t.nombre as trabajador, c.nombre as Cliente, v.fecha, v.estado,\n" +
"v.subtotal, v.impuesto, v.total \n" +
"from venta v inner join cliente c on v.id_cliente = c.id_cliente\n" +
"inner join trabajador t on v.id_trabajador = t.id_trabajador\n" +
"order by v.id_venta desc";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_venta");
                registro [1]= rs.getString("trabajador");
                registro [2]= rs.getString("Cliente");
                registro [3]= rs.getString("fecha");
                registro [4]= rs.getString("estado");
                registro [5]= rs.getString("subtotal");
                registro [6]= rs.getString("impuesto");
                registro [7]= rs.getString("total");
                
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
        
        String [] titulos = {"ID", "Trabajador", "Cliente", "Fecha", "Estado", "Subtotal", "Impuesto", "Total"};
        
        String [] registro = new String [8];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select v.id_venta, t.nombre as trabajador, c.nombre as Cliente, v.fecha, v.estado,\n" +
"v.subtotal, v.impuesto, v.total \n" +
"from venta v inner join cliente c on v.id_cliente = c.id_cliente\n" +
"inner join trabajador t on v.id_trabajador = t.id_trabajador\n" +
"where v.fecha BETWEEN '"+ Buscar +"' and '"+ Buscar2 +"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_venta");
                registro [1]= rs.getString("trabajador");
                registro [2]= rs.getString("Cliente");
                registro [3]= rs.getString("fecha");
                registro [4]= rs.getString("estado");
                registro [5]= rs.getString("subtotal");
                registro [6]= rs.getString("impuesto");
                registro [7]= rs.getString("total");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
    
    public DefaultTableModel MostrarDetalle(int Buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"Articulo", "Cantidad", "Precio", "Subtotal"};
        
        String [] registro = new String [4];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select a.nombre as Articulo, d.cantidad ,d.precio,\n" +
" (d.precio * d.cantidad) as Subtotal\n" +
" from detalle_venta d inner join detalle_ingreso di on d.id_detalle_ingreso = di.id_detalle_ingreso\n" +
" inner join articulo a on di.id_articulo = a.id_articulo\n" +
" where d.id_venta = "+ Buscar +" ";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("Articulo");
                registro [1]= rs.getString("cantidad");
                registro [2]= rs.getString("precio");
                registro [3]= rs.getString("Subtotal");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
    
    public DefaultTableModel BuscarArticuloNombre(String Buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Articulo", "Descripcion", "En Stock", "Precio de Compra", "Precio de Venta"};
        
        String [] registro = new String [6];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select d.id_detalle_ingreso, a.nombre as Articulo, a.descripcion,\n" +
"  d.stock_actual, d.precio_compra, d.precio_venta\n" +
"  from articulo a inner join detalle_ingreso d on a.id_articulo = d.id_articulo\n" +
"  inner join ingreso i on d.id_ingreso = i.id_ingreso\n" +
"  where a.nombre like '"+ Buscar +"%'\n" +
"  and d.stock_actual>0\n" +
"  and i.estado <> 'ANULADO' ";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_detalle_ingreso");
                registro [1]= rs.getString("Articulo");
                registro [2]= rs.getString("descripcion");
                registro [3]= rs.getString("stock_actual");
                registro [4]= rs.getString("precio_compra");
                registro [5]= rs.getString("precio_venta");
                
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
        
        sSQL="SELECT id_venta FROM venta WHERE id_venta =(SELECT MAX(id_venta)FROM venta)";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro = rs.getInt("id_venta");
            }
                 
                 
            
            return registro;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return 0;
        }  
    }
            
        public boolean insertar (vventa dts)
        {
            sSQL="insert into venta (id_cliente,id_trabajador, fecha, subtotal, impuesto, total, estado)" + "values (?,?,?,?,?,?,'APROBADO')";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_cliente());
                pst.setInt(2, dts.getId_trabajador());
                pst.setString(3, dts.getFecha());
                pst.setDouble(4, dts.getSubtotal());
                pst.setDouble(5, dts.getImpuesto());
                pst.setDouble(6, dts.getTotal());
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
    
        public boolean insertarDetalle (vdetalle_venta dts)
        {
            sSQL="insert into detalle_venta (id_venta, id_detalle_ingreso, cantidad, precio)"
                    + "values (?,?,?,?)";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_venta());
                pst.setInt(2, dts.getId_detalle_ingreso());
                pst.setInt(3, dts.getCantidad());
                pst.setDouble(4, dts.getPrecio());
                
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
                
        public boolean Anular (vventa dts)
        {
            sSQL="update venta set estado = 'ANULADO'\n" +
" where id_venta = ?";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_venta());

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
        //IMPORTANTE: Este metodo es para disminuir o aumentar el stock
        //Se usa para la venta o para anular
        public boolean ActualizarStock (vdetalle_venta dts, int cantidad)
        {
            sSQL="update detalle_ingreso set stock_actual = stock_actual + "+ cantidad +"\n" +
"where id_detalle_ingreso = ?";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_detalle_ingreso());

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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

