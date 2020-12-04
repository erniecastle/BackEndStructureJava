/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.awt.Font;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ernesto
 */
@Entity
@Table(name = "ReporteEstilos")
public class ReporteEstilos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Font font;
    private Integer alineacion;
    private String bordes;

    public ReporteEstilos() {
    }

    public ReporteEstilos(Font font) {
        this.font = font;
    }

    public ReporteEstilos(Integer alineacion) {
        this.alineacion = alineacion;
    }

    public ReporteEstilos(Font font, Integer alineacion) {
        this.font = font;
        this.alineacion = alineacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Integer getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(Integer alineacion) {
        this.alineacion = alineacion;
    }

    public String getBordes() {
        return bordes;
    }

    public void setBordes(String bordes) {
        this.bordes = bordes;
    }

}
