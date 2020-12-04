/**
 * @author: Victor Lopez Fecha de Creación: 13/09/2011 Compañía: Macropro.
 * Descripción del programa: Entidad de DIM con Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: DRO01 Autor: Dayane Rocha Fecha: 30/08/2013 Descripción: Se agregó
 * campo Sistema
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;

@Entity
public class TipoCorrida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    private String descripcion;
    private boolean sistema;//DRO01
    private String detalleConceptoRecibo;
    private short tipoDeCalculo;
    private boolean usaCorrPeriodica;
    @OrderBy("orden")
    private short orden;
    private Boolean mostrarMenuCalc;

    public Long getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isSistema() {//DRO01
        return sistema;
    }

    public void setSistema(boolean sistema) {//DRO01
        this.sistema = sistema;
    }

    public String getDetalleConceptoRecibo() {
        return detalleConceptoRecibo;
    }

    public void setDetalleConceptoRecibo(String detalleConceptoRecibo) {
        this.detalleConceptoRecibo = detalleConceptoRecibo;
    }

    public short getTipoDeCalculo() {
        return tipoDeCalculo;
    }

    public void setTipoDeCalculo(short tipoDeCalculo) {
        this.tipoDeCalculo = tipoDeCalculo;
    }

    public boolean isUsaCorrPeriodica() {
        return usaCorrPeriodica;
    }

    public void setUsaCorrPeriodica(boolean usaCorrPeriodica) {
        this.usaCorrPeriodica = usaCorrPeriodica;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

    public Boolean isMostrarMenuCalc() {
        return mostrarMenuCalc;
    }

    public void setMostrarMenuCalc(Boolean mostrarMenuCalc) {
        this.mostrarMenuCalc = mostrarMenuCalc;
    }

}
