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
public class vdetalle_venta {
   
    private int id_detalle_venta;
    private int id_venta;
    private int id_detalle_ingreso;
    private int cantidad;
    private Double precio;

    public vdetalle_venta() {
    }

    public vdetalle_venta(int id_detalle_venta, int id_venta, int id_detalle_ingreso, int cantidad, Double precio) {
        this.id_detalle_venta = id_detalle_venta;
        this.id_venta = id_venta;
        this.id_detalle_ingreso = id_detalle_ingreso;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getId_detalle_venta() {
        return id_detalle_venta;
    }

    public void setId_detalle_venta(int id_detalle_venta) {
        this.id_detalle_venta = id_detalle_venta;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_detalle_ingreso() {
        return id_detalle_ingreso;
    }

    public void setId_detalle_ingreso(int id_detalle_ingreso) {
        this.id_detalle_ingreso = id_detalle_ingreso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
