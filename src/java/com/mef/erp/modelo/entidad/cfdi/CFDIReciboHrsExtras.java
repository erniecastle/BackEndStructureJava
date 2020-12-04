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
public class CFDIReciboHrsExtras implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer dias;
    @Column(nullable = false)
    private String tipoHoras;
    @Column(nullable = false)
    private Integer horasExtras;
    @Column(nullable = false)
    private Double importeGravable;
    @Column(nullable = false)
    private Double importeExento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getTipoHoras() {
        return tipoHoras;
    }

    public void setTipoHoras(String tipoHoras) {
        this.tipoHoras = tipoHoras;
    }

    public Integer getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(Integer horasExtras) {
        this.horasExtras = horasExtras;
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

}
