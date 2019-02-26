/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Date;

/**
 *
 * @author Raimon
 */
public class vingreso {
    
    private int id_ingreso;
    private int id_articulo;
    private int id_trabajador;
    private int id_proveedor;
    private String fecha;
    private String estado;
    private Double precio_total;

    public vingreso() {
    }

    public vingreso(int id_ingreso, int id_articulo, int id_trabajador, int id_proveedor, String fecha, String estado, Double precio_total) {
        this.id_ingreso = id_ingreso;
        this.id_articulo = id_articulo;
        this.id_trabajador = id_trabajador;
        this.id_proveedor = id_proveedor;
        this.fecha = fecha;
        this.estado = estado;
        this.precio_total = precio_total;
    }

    public int getId_ingreso() {
        return id_ingreso;
    }

    public void setId_ingreso(int id_ingreso) {
        this.id_ingreso = id_ingreso;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(Double precio_total) {
        this.precio_total = precio_total;
    }

    
    
    
}
