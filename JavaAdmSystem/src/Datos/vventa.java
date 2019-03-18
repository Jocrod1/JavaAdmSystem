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
public class vventa {
   
    private int id_venta;
    private String id_cliente;
    private String id_trabajador;
    private String fecha;
    private Double Subtotal;
    private Double Impuesto;
    private Double Total;

    public vventa() {
    }

    public vventa(int id_venta, String id_cliente, String id_trabajador, String fecha, Double Subtotal, Double Impuesto, Double Total) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.id_trabajador = id_trabajador;
        this.fecha = fecha;
        this.Subtotal = Subtotal;
        this.Impuesto = Impuesto;
        this.Total = Total;
    }
    
    

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(String id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public Double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public Double getImpuesto() {
        return Impuesto;
    }

    public void setImpuesto(Double Impuesto) {
        this.Impuesto = Impuesto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
