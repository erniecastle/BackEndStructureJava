/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author daniel
 */
@Entity
@Table(name="ConceptoPorTipoCorrida",
uniqueConstraints = {@UniqueConstraint(columnNames={"concepNomDefi_ID", "tipoCorrida_ID"})})
public class ConceptoPorTipoCorrida implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
    @JoinColumn(name="concepNomDefi_ID")//, updatable = false, insertable = false)
    @ForeignKey(name="FK_concepNomDefi_ID")
    private ConcepNomDefi concepNomDefi;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name="tipoCorrida_ID", nullable=false, insertable=true, updatable=true)
    private TipoCorrida tipoCorrida;
    private boolean mostrar;
    private boolean opcional;
    private boolean incluir;
    private double cantidad;    
    private boolean modificarCantidad;
    private boolean modificarImporte;
    private DescuentoCreditos descuentoCreditos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public ConcepNomDefi getConcepNomDefi() {
        return concepNomDefi;
    }

    public void setConcepNomDefi(ConcepNomDefi concepNomDefi) {
        this.concepNomDefi = concepNomDefi;
    }

    public DescuentoCreditos getDescuentoCreditos() {
        return descuentoCreditos;
    }

    public void setDescuentoCreditos(DescuentoCreditos descuentoCreditos) {
        this.descuentoCreditos = descuentoCreditos;
    }

    public boolean isIncluir() {
        return incluir;
    }

    public void setIncluir(boolean incluir) {
        this.incluir = incluir;
    }

    public boolean isModificarCantidad() {
        return modificarCantidad;
    }

    public void setModificarCantidad(boolean modificarCantidad) {
        this.modificarCantidad = modificarCantidad;
    }

    public boolean isModificarImporte() {
        return modificarImporte;
    }

    public void setModificarImporte(boolean modificarImporte) {
        this.modificarImporte = modificarImporte;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public boolean isOpcional() {
        return opcional;
    }

    public void setOpcional(boolean opcional) {
        this.opcional = opcional;
    }

    public TipoCorrida getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(TipoCorrida tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }
    
}
