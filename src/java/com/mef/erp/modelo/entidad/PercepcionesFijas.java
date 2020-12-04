/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author daniel
 */
@Entity
public class PercepcionesFijas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 255, nullable = false)
    private String descripcion;
    private float cantidad;
    private float importe;
    @ManyToOne
    private CategoriasPuestos categoriasPuestos;
    @ManyToOne
    private Puestos puestos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cantidad
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the importe
     */
    public float getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(float importe) {
        this.importe = importe;
    }

    /**
     * @return the categoriasPuestos
     */
    public CategoriasPuestos getCategoriasPuestos() {
        return categoriasPuestos;
    }

    /**
     * @param categoriasPuestos the categoriasPuestos to set
     */
    public void setCategoriasPuestos(CategoriasPuestos categoriasPuestos) {
        this.categoriasPuestos = categoriasPuestos;
    }

    /**
     * @return the puestos
     */
    public Puestos getPuestos() {
        return puestos;
    }

    /**
     * @param puestos the puestos to set
     */
    public void setPuestos(Puestos puestos) {
        this.puestos = puestos;
    }
}
