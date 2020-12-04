/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad.cfdi;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class CFDIReciboConcepto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tipoNaturaleza;
    @Column(nullable = false)  // es tipo percepcion o deduccion
    private String claveSAT;
    @Column(nullable = false)
    private String claveConcepto;
    @Column(nullable = false)
    private String descripcionConcepto;
    @Column(nullable = false)
    private Double importeGravable;
    @Column(nullable = false)
    private Double importeExento;
    private boolean otroPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoNaturaleza() {
        return tipoNaturaleza;
    }

    public void setTipoNaturaleza(String tipoNaturaleza) {
        this.tipoNaturaleza = tipoNaturaleza;
    }

    public String getClaveSAT() {
        return claveSAT;
    }

    public void setClaveSAT(String claveSAT) {
        this.claveSAT = claveSAT;
    }

    public String getClaveConcepto() {
        return claveConcepto;
    }

    public void setClaveConcepto(String claveConcepto) {
        this.claveConcepto = claveConcepto;
    }

    public String getDescripcionConcepto() {
        return descripcionConcepto;
    }

    public void setDescripcionConcepto(String descripcionConcepto) {
        this.descripcionConcepto = descripcionConcepto;
    }

    public Double getImporteGravable() {
        return importeGravable;
    }

    public void setImporteGravable(Double importeGravable) {
        this.importeGravable = importeGravable;
    }

    public Double getImporteExento() {
        return importeExento;
    }

    public void setImporteExento(Double importeExento) {
        this.importeExento = importeExento;
    }

    public boolean isOtroPago() {
        return otroPago;
    }

    public void setOtroPago(boolean otroPago) {
        this.otroPago = otroPago;
    }
    
}
