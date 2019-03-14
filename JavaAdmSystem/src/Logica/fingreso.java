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
    //se realiza la conexion
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    //la tabla mostrar que ordena cada columna ocultando las que no pueden ser vistas por el usuario
    public DefaultTableModel mostrar()
    {
        DefaultTableModel modelo;
        
        //los titulos para la tabla
        String [] titulos = {"ID", "Trabajador", "Proveedor", "Representante Legal", "Fecha", "Estado", "Precio Total"};
        
        String [] registro = new String [7];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        //este sql agarra varios campos de varias tablas de la BD, por sus conexiones, para poder generar el ingreso con todos sus similares
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
                //se obtienen los datos del formulario y se agrupan en el mismo orden
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
    
    //esta funcion es para hacer la busqueda de ingresos entre 2 fechas
    public DefaultTableModel buscarentrefechas(String Buscar, String Buscar2)
    {
        DefaultTableModel modelo;
        
        //se coloca la misma tabla con los mismos titulos
        String [] titulos = {"ID", "Trabajador", "Proveedor", "Representante Legal", "Fecha", "Estado", "Precio Total"};
        
        String [] registro = new String [7];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        //se hace el mismo sql pero con la diferencia que se coloca entre "Buscar y Buscar2" que son las 2 respectivas fechas
        sSQL="select i.id_ingreso, t.nombre as trabajador, p.empresa as Proveedor,\n" +
" p.nombre as RepresentanteLegal, i.fecha, i.estado, i.precio_total\n" +
" from ingreso i inner join proveedor p on i.id_proveedor = p.id_proveedor\n" +
" inner join  trabajador t on i.id_trabajador = t.id_trabajador\n" +
" where i.fecha BETWEEN '"+ Buscar +"' and '"+ Buscar2 +"' order by i.id_ingreso desc";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //aca busca registro por registro hasta encontrar una o varias filas con esas fechas, si no, no aparece nada
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
                 
                 
            //retorna la tabla con las fechas que se buscó
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
    
    
    //esta es la tabla del detalle del ingreso
    public DefaultTableModel MostrarDetalle(int Buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"Articulo", "Precio de Compra", "Precio de Venta", "Stock Inicial", "Stock Actual", "Subtotal"};
        
        String [] registro = new String [6];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        //en este sql se buscan los datos de los detalles del ingreso por el id del ingreso general
        sSQL="select d.id_articulo, a.nombre as Articulo, d.precio_compra, d.precio_venta, d.stock_inicial,\n" +
"d.stock_actual, (d.stock_inicial * d.precio_compra) as Subtotal\n" +
"from detalle_ingreso d inner join articulo a on d.id_articulo = a.id_articulo\n" +
"where d.id_ingreso = "+ Buscar +"";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                //se muestran en el mismo orden que el sql
                registro [0]= rs.getString("Articulo");
                registro [1]= rs.getString("precio_compra");
                registro [2]= rs.getString("precio_venta");
                registro [3]= rs.getString("stock_inicial");
                registro [4]= rs.getString("stock_actual");
                registro [5]= rs.getString("Subtotal");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            // y retorna la tabla de los detalles del ingreso
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
    
    public DefaultTableModel BuscarArticulo(String IdCliente){
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Nombre"};
        
        String [] registro = new String [2];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select id_articulo, nombre from articulo where id_articulo ='" + IdCliente+ "'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_articulo");
                registro [1]= rs.getString("nombre");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
        
        }
    
    
    //en esta funcion se obtiene el id del ingreso actual, el que se hace en el momento
    public int Obteneridentity()
    {
        DefaultTableModel modelo;
        
        //solo se va a mostrar el id, el resto lo busca el sql
        String [] titulos = {"ID"};
        
        int registro = 0;
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        //el comando se dice que busque el id del ingreso selecionando el id máximo
        sSQL="SELECT id_ingreso FROM ingreso WHERE id_ingreso =(SELECT MAX(id_ingreso)FROM ingreso)";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //se busca uno por uno cual es el maximo    
            while(rs.next())
            {
                registro = rs.getInt("id_ingreso");
            }
                 
                 
            //retorna la tabla del identity
            return registro;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return 0;
        }  
    }
    
    //aca es la funcion basice de insertar
        public boolean insertar (vingreso dts)
        {
            //se coloca en el sql la busqueda del formulario de todos los datos excepto en el de estado, que esta de inicio siempre como "APROBADO"
            sSQL="insert into ingreso (id_trabajador, id_proveedor, fecha, estado, precio_total)" + "values (?,?,?,'APROBADO',?)";
            try {
                
                //se coloca cada uno de los datos
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_trabajador());
                pst.setInt(2, dts.getId_proveedor());
                pst.setString(3, dts.getFecha());
                pst.setDouble(4, dts.getPrecio_total());
                
                int n=pst.executeUpdate();
                
                //si hace el update muestra true, si no, retorna false y muestra un error en el formulario
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
    
        //aca se inserta pero es cada detalle del ingreso
        public boolean insertarDetalle (vdetalle_ingreso dts)
        {
            //se escribe el comando pidiendo todos los detalles de cada uno, tomando en cuenta que un ingreso puede tener varios detalles
            sSQL="insert into detalle_ingreso (id_ingreso, id_articulo, precio_compra, precio_venta, stock_inicial, stock_actual)"
                    + "values (?,?,?,?,?,?)";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_ingreso());
                pst.setString(2, dts.getId_articulo());
                pst.setDouble(3, dts.getPrecio_compra());
                pst.setDouble(4, dts.getPrecio_venta());
                pst.setInt(5, dts.getStock_inicial());
                pst.setInt(6, dts.getStock_actual());
                
                int n=pst.executeUpdate();
                
                //si hace el update muestra true, si no, retorna false y muestra un error en el formulario
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
         
        //en esta parte se anula el registro, ningun registro de ingreso puede ser borrado en su totalidad
        public boolean Anular (vingreso dts)
        {
            //solo se le cambia el estado de aprovado a anulado, y respectivamente luego se borran los items que se ingresaron en el stock
            sSQL="update ingreso set estado = 'ANULADO'\n" +
" where id_ingreso = ?";
            try {
                //sólo necesita el id del ingreso para cambiar el estado
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getId_ingreso());

                int n=pst.executeUpdate();
                
                //si hace el update muestra true, si no, retorna false y muestra un error en el formulario
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
        
        
//en esta funcion se muestra el comprobante del ingreso con sus detalles cuando el numero que se pide en formulario existe
    public int ObtenerComprobante(int Buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID"};
        
        int registro = 0;
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="SELECT id_ingreso FROM ingreso WHERE id_ingreso ='"+Buscar+"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro = rs.getInt("id_ingreso");
            }
                 
                 
            //si no existe muestra en formulario que el comprobante no está en el registro
            return registro;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return 0;
        }  
    }
        
        
        
        
        
        
        
        
        
        
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

