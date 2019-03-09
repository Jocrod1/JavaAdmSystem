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
    //se realiza la conexion
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    //esta es la tabla donde se verán todos los datos visibles para cualquier usuario
        public DefaultTableModel mostrar(String buscar)
    {
        DefaultTableModel modelo;
        
        //los titulos de las columnas de la tabla
        String [] titulos = {"ID", "Nombre", "Direccion", "Telefono", "Correo", "Sexo"};
        
        String [] registro = new String [6];
        
        totalregistros=0;
        
        modelo= new DefaultTableModel(null, titulos);
        
        //se ordena la tabla por el id que busque el usuario
        sSQL="select * from cliente where id_cliente like '%" + buscar + "%' order  by id_cliente";
        
        try {
            //se coloca un try para intentar conectar el query, si no lo hace muestra un error
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next())
            {
                //se asigna cada parámetro en cada columna(en el mismo orden)
                registro [0]= rs.getString("id_cliente");
                registro [1]= rs.getString("nombre");
                registro [2]= rs.getString("direccion");;
                registro [3]= rs.getString("telefono");
                registro [4]= rs.getString("correo");
                registro [5]= rs.getString("sexo");
                
                
                totalregistros= totalregistros+1;
                modelo.addRow(registro);
            }
            //y retorna el modelo mostrando o la tabla finalizada, o un error si no conecta la tabla de mysql
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }  
    }
        
        
        public boolean insertar (vcliente dts)
        {
            //se insertan los datos en el mismo orden como está en el mysql
            sSQL="Insert into cliente (id_cliente,nombre, direccion, telefono, correo, sexo)" + "values (?,?,?,?,?,?)";
            try {
                //aca se envian los datos de el formulario a la BD
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getId_cliente());
                pst.setString(2, dts.getNombre());
                pst.setString(3, dts.getDireccion());;
                pst.setString(4, dts.getTelefono());
                pst.setString(5, dts.getCorreo());
                pst.setString(6, dts.getSexo());
                
                
                int n=pst.executeUpdate();
                
                //si el update se ejecuta corractamente, muestra verdadero, si no muestra un error
                if(n!=0)
                {
                   return true; 
                }
                else
                {
                    return false;
                }
                
            } catch (Exception e) {
                //si no conecta muestra un error con su respectivo mensaje de ello, que es "e"
                JOptionPane.showConfirmDialog(null, e);
                return false;
            }
        }
        
        public boolean editar (vcliente dts)
        {
            //se edita la fila dependiendo del id del cliente
            sSQL= "Update cliente set nombre=?, direccion=?, telefono=?, correo=?, sexo=?" + "where id_cliente=?";
            
            try {
                //si hay un id similar, se editan todas las demas columnas de esa fila con la que se ingresa en el formulario
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getNombre());
                pst.setString(2, dts.getDireccion());
                pst.setString(3, dts.getTelefono());
                pst.setString(4, dts.getCorreo());
                pst.setString(5, dts.getSexo());
                //al final se coloca el id porque se coloca en el mismo orden que en el sql, dejando al final la comparacion
                pst.setString(6, dts.getId_cliente());
                
                int n=pst.executeUpdate();
                
                //si el update se ejecuta corractamente, muestra verdadero, si no muestra un error
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
            //aca se elimina el registro cuando el id del cliente sea el mismo
            sSQL="delete from cliente where id_cliente=?";
            try {
                
                PreparedStatement pst=cn.prepareStatement(sSQL);
                pst.setString(1, dts.getId_cliente());

                int n=pst.executeUpdate();
                
                //si el update se ejecuta corractamente, muestra verdadero, si no muestra un error
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
        
         
         
         //esta funcion es para mostrar cuando la cedula del cliente es igual a una que este en la bd, muestr un mensaje de error diciendo que esa cedula ya se registro
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
            
            //se repite en cada uno de los registro para asegurar que no haya una cedula igual     
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
