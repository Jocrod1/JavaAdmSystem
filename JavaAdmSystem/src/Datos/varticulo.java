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
public class varticulo {
    
    private String id_articulo;
    private String nombre;
    private String descripcion;

    public varticulo(String id_articulo, String nombre, String descripcion) {
        this.id_articulo = id_articulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public varticulo() {
    }

    public String getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(String id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
