/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Raimon
 */
public class vtrabajador {
    
    private String id_trabajador;
    private String nombre;
    private String direccion;
    private String sexo;
    private String acceso;
    private String contraseña;
    private String telefono;
    private String correo;

    public vtrabajador(String id_trabajador, String nombre, String direccion, String sexo, String acceso, String contraseña, String telefono, String correo) {
        this.id_trabajador = id_trabajador;
        this.nombre = nombre;
        this.direccion = direccion;
        this.sexo = sexo;
        this.acceso = acceso;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.correo = correo;
    }

    public vtrabajador() {
    }

    public String getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(String id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
