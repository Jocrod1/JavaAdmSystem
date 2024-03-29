/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

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
public class ftrabajador {
    //se hace la conexion a la BD
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    //esta funcion coloca todos los datos en la tabla que aparece en el formulario
    public DefaultTableModel mostrar(String buscar)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"Cedula", "Nombre", "Direccion", "Sexo", "Acceso", "Contraseña", "Telefono", "Correo","Pregunta","Respuesta"};
        
        String [] registro = new String [10];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        //se busca el trabajador por el comienzo de su cedula o la cedula completa, ordenandola
        sSQL="select * from trabajador where id_trabajador like '" + buscar + "%' order  by id_trabajador";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //se revisa uno a uno los que tiene la cedula igual o parecida
            while(rs.next())
            {
                registro [0]= rs.getString("id_trabajador");
                registro [1]= rs.getString("nombre");
                registro [2]= rs.getString("direccion");
                registro [3]= rs.getString("sexo");
                registro [4]= rs.getString("acceso");
                registro [5]= rs.getString("contraseña");
                registro [6]= rs.getString("telefono");
                registro [7]= rs.getString("correo");
                registro [8]= rs.getString("pregunta");
                registro [9]= rs.getString("respuesta");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            //retorna la tabla, si no muestra ninguna, da error
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
    
        //aca inserta los datos del trabajador en el mismo orden que en el sql
        public boolean insertar (vtrabajador dts)
        {
            sSQL="Insert into trabajador (id_trabajador, nombre, direccion, sexo, acceso, contraseña, telefono, correo, pregunta, respuesta)" + "values (?,?,?,?,?,?,?,?,?,?)";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getId_trabajador());
                pst.setString(2, dts.getNombre());
                pst.setString(3, dts.getDireccion());
                pst.setString(4, dts.getSexo());
                pst.setString(5, dts.getAcceso());
                pst.setString(6, dts.getContraseña());
                pst.setString(7, dts.getTelefono());
                pst.setString(8, dts.getCorreo());
                pst.setString(9, dts.getPregunta());
                pst.setString(10, dts.getRespuesta());
                
                int n=pst.executeUpdate();
                
                //si ocurre el ejecute, la funcion devuelve true, sino false
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
    
        //en esta funcion edita los datos del trabajador comparandolo con la cedula que es el id
        public boolean editar (vtrabajador dts)
        {
            sSQL= "Update trabajador set nombre=?, direccion=?, sexo=?,acceso=?, contraseña=?, telefono=?, correo=?, pregunta=?, respuesta=?" + "where id_trabajador=?";
            
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getNombre());
                pst.setString(2, dts.getDireccion());
                pst.setString(3, dts.getSexo());
                pst.setString(4, dts.getAcceso());
                pst.setString(5, dts.getContraseña());
                pst.setString(6, dts.getTelefono());
                pst.setString(7, dts.getCorreo());
                pst.setString(8, dts.getPregunta());
                pst.setString(9, dts.getRespuesta());
                pst.setString(10, dts.getId_trabajador());
                
                int n=pst.executeUpdate();
                
                //si ocurre el ejecute, la funcion devuelve true, sino false
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
        
        //en esta funcion elimina el registro del trabajador buscandola por la cedula de ella
        public boolean eliminar (vtrabajador dts)
        {
            sSQL="delete from trabajador where id_trabajador=?";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getId_trabajador());

                int n=pst.executeUpdate();
                
                //si ocurre el ejecute, la funcion devuelve true, sino false
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
        
        
        
//esta funcion es la del login del programa, obteniendo la cedula y contraseña de la misma
    public DefaultTableModel usuario(String id_trabajador, String contraseña){
        DefaultTableModel modelo;
        
        String [] titulos = {"Cedula", "Contraseña", "Nombre", "Acceso"};
        
        String [] registro = new String [4];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select id_trabajador,contraseña,nombre,acceso from trabajador where id_trabajador='" + id_trabajador+ "' and contraseña='" + contraseña+"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            //busca una por una, y si encuentra una con los dos datos iguales, ingresa al sistema
            while(rs.next())
            {
                registro [0]= rs.getString("id_trabajador");
                registro [1]= rs.getString("contraseña");
                registro [2] =rs.getString("nombre");
                registro [3] =rs.getString("acceso");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
        
        }
        
        public DefaultTableModel usuariorepetido(String id_trabajador)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"Cedula"};
        
        String [] registro = new String [1];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select id_trabajador from trabajador where id_trabajador='" + id_trabajador+"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_trabajador");
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
                 
                 
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
        
        
        
        public DefaultTableModel contraseñaolvidada(String id_trabajador)
    {
        DefaultTableModel modelo;
        
        String [] titulos = {"Cedula","Contraseña","Pregunta","Respuesta"};
        
        String [] registro = new String [4];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        sSQL="select id_trabajador, contraseña, pregunta, respuesta from trabajador where id_trabajador='" + id_trabajador+"'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
                 
            while(rs.next())
            {
                registro [0]= rs.getString("id_trabajador");
                registro [1]= rs.getString("contraseña");
                registro [2]= rs.getString("pregunta");
                registro [3]= rs.getString("respuesta");
                
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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

