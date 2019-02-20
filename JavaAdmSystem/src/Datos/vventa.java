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
    private int id_cliente;
    private int id_trabajador;
    private Date fecha;
    private Double Subtotal;
    private Double Impuesto;
    private Double Total;

    public vventa() {
    }

    public vventa(int id_venta, int id_cliente, int id_trabajador, Date fecha, Double Subtotal, Double Impuesto, Double Total) {
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

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
    
    
    
}
