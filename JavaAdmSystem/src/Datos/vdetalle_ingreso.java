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
public class vdetalle_ingreso {
    
    private int id_detalle_ingreso;
    private int id_ingreso;
    private int id_articulo;
    private Double precio_compra;
    private Double precio_venta;
    private int stock_inicial;
    private int stock_actual;

    public vdetalle_ingreso() {
    }

    public vdetalle_ingreso(int id_detalle_ingreso, int id_ingreso, int id_articulo, Double precio_compra, Double precio_venta, int stock_inicial, int stock_actual) {
        this.id_detalle_ingreso = id_detalle_ingreso;
        this.id_ingreso = id_ingreso;
        this.id_articulo = id_articulo;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.stock_inicial = stock_inicial;
        this.stock_actual = stock_actual;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public int getId_detalle_ingreso() {
        return id_detalle_ingreso;
    }

    public void setId_detalle_ingreso(int id_detalle_ingreso) {
        this.id_detalle_ingreso = id_detalle_ingreso;
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

    public Double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(Double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getStock_inicial() {
        return stock_inicial;
    }

    public void setStock_inicial(int stock_inicial) {
        this.stock_inicial = stock_inicial;
    }
    
    
       
}
