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
public class vproveedor {
    
    private int id_proveedor;
    private String cedula;
    private String nombre;
    private String direccion;
    private String rif;
    private String empresa;
    private String telefono;
    private String correo;

    public vproveedor(int id_proveedor, String cedula, String nombre, String direccion, String rif, String empresa, String telefono, String correo) {
        this.id_proveedor = id_proveedor;
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.rif = rif;
        this.empresa = empresa;
        this.telefono = telefono;
        this.correo = correo;
    }

    public vproveedor() {
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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
