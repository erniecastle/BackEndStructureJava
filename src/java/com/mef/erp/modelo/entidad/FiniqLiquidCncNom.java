/**
 * @author: Victor Lopez Fecha de Creación: 04/07/2012 Compañía: MacroPro.
 * Descripción del programa: Entidad de FiniqLiquidCncNom para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "FiniqLiquidCncNom",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"finiquitosLiquid_ID", "id"})})
public class FiniqLiquidCncNom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "finiquitosLiquid_ID", nullable = false, insertable = true, updatable = true)
    private FiniquitosLiquidaciones finiquitosLiquidacion;
    @ManyToOne
    private ConceptoPorTipoCorrida conceptoPorTipoCorrida;
    private Double cantidad;
    private Double importe;

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public ConceptoPorTipoCorrida getConceptoPorTipoCorrida() {
        return conceptoPorTipoCorrida;
    }

    public void setConceptoPorTipoCorrida(ConceptoPorTipoCorrida conceptoPorTipoCorrida) {
        this.conceptoPorTipoCorrida = conceptoPorTipoCorrida;
    }

    public FiniquitosLiquidaciones getFiniquitosLiquidacion() {
        return finiquitosLiquidacion;
    }

    public void setFiniquitosLiquidacion(FiniquitosLiquidaciones finiquitosLiquidacion) {
        this.finiquitosLiquidacion = finiquitosLiquidacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

}
