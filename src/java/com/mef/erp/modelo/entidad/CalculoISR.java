package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Formula;

/**
 *
 * @author Admin
 */
@Entity
public class CalculoISR implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double isrRetenidoNormal;
    private Double isrRetenidoDirecto;
    private Double isrRetenidoAnual;
    private double isrACargoNormal;
    private double subsidioEmpleoNormal;
    private double isrNetoNormal;
    private double isrSubsidioNormal;
    private double isrACargoDirecto;
    private double subsidioEmpleoDirecto;
    private double isrNetoDirecto;
    private double isrSubsidioDirecto;
    private double isrACargoAnual;
    private double subsidioEmpleoAnual;
    private double isrNetoAnual;
    private double isrSubsidioAnual;
    @ManyToOne
    private MovNomConcep movNomConcep;
    @Formula(" isrNetoNormal + isrNetoDirecto + isrNetoAnual")
    private double isrNeto;
    @Formula(" isrACargoNormal + isrACargoDirecto + isrACargoAnual")
    private double isrACargo;
    @Formula(" isrSubsidioNormal + isrSubsidioDirecto + isrSubsidioAnual")
    private double isrSubsidio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovNomConcep getMovNomConcep() {
        return movNomConcep;
    }

    public void setMovNomConcep(MovNomConcep movNomConcep) {
        this.movNomConcep = movNomConcep;
    }

    public Double getIsrRetenidoAnual() {
        return isrRetenidoAnual;
    }

    public void setIsrRetenidoAnual(Double isrRetenidoAnual) {
        this.isrRetenidoAnual = isrRetenidoAnual;
    }

    public Double getIsrRetenidoDirecto() {
        return isrRetenidoDirecto;
    }

    public void setIsrRetenidoDirecto(Double isrRetenidoDirecto) {
        this.isrRetenidoDirecto = isrRetenidoDirecto;
    }

    public Double getIsrRetenidoNormal() {
        return isrRetenidoNormal;
    }

    public void setIsrRetenidoNormal(Double isrRetenidoNormal) {
        this.isrRetenidoNormal = isrRetenidoNormal;
    }

    public double getIsrACargoAnual() {
        return isrACargoAnual;
    }

    public void setIsrACargoAnual(double isrACargoAnual) {
        this.isrACargoAnual = isrACargoAnual;
    }

    public double getIsrACargoDirecto() {
        return isrACargoDirecto;
    }

    public void setIsrACargoDirecto(double isrACargoDirecto) {
        this.isrACargoDirecto = isrACargoDirecto;
    }

    public double getIsrACargoNormal() {
        return isrACargoNormal;
    }

    public void setIsrACargoNormal(double isrACargoNormal) {
        this.isrACargoNormal = isrACargoNormal;
    }

    public double getIsrNetoAnual() {
        return isrNetoAnual;
    }

    public void setIsrNetoAnual(double isrNetoAnual) {
        this.isrNetoAnual = isrNetoAnual;
    }

    public double getIsrNetoDirecto() {
        return isrNetoDirecto;
    }

    public void setIsrNetoDirecto(double isrNetoDirecto) {
        this.isrNetoDirecto = isrNetoDirecto;
    }

    public double getIsrNetoNormal() {
        return isrNetoNormal;
    }

    public void setIsrNetoNormal(double isrNetoNormal) {
        this.isrNetoNormal = isrNetoNormal;
    }

    public double getIsrSubsidioAnual() {
        return isrSubsidioAnual;
    }

    public void setIsrSubsidioAnual(double isrSubsidioAnual) {
        this.isrSubsidioAnual = isrSubsidioAnual;
    }

    public double getIsrSubsidioDirecto() {
        return isrSubsidioDirecto;
    }

    public void setIsrSubsidioDirecto(double isrSubsidioDirecto) {
        this.isrSubsidioDirecto = isrSubsidioDirecto;
    }

    public double getIsrSubsidioNormal() {
        return isrSubsidioNormal;
    }

    public void setIsrSubsidioNormal(double isrSubsidioNormal) {
        this.isrSubsidioNormal = isrSubsidioNormal;
    }

    public double getSubsidioEmpleoAnual() {
        return subsidioEmpleoAnual;
    }

    public void setSubsidioEmpleoAnual(double subsidioEmpleoAnual) {
        this.subsidioEmpleoAnual = subsidioEmpleoAnual;
    }

    public double getSubsidioEmpleoDirecto() {
        return subsidioEmpleoDirecto;
    }

    public void setSubsidioEmpleoDirecto(double subsidioEmpleoDirecto) {
        this.subsidioEmpleoDirecto = subsidioEmpleoDirecto;
    }

    public double getSubsidioEmpleoNormal() {
        return subsidioEmpleoNormal;
    }

    public void setSubsidioEmpleoNormal(double subsidioEmpleoNormal) {
        this.subsidioEmpleoNormal = subsidioEmpleoNormal;
    }

    public double getIsrNeto() {
        return isrNeto;
    }

    public void setIsrNeto(double isrNeto) {
        this.isrNeto = isrNeto;
    }

    public double getIsrACargo() {
        return isrACargo;
    }

    public void setIsrACargo(double isrACargo) {
        this.isrACargo = isrACargo;
    }

    public double getIsrSubsidio() {
        return isrSubsidio;
    }

    public void setIsrSubsidio(double isrSubsidio) {
        this.isrSubsidio = isrSubsidio;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public CalculoISR clonIsrRetenido() {
        try {
            return (CalculoISR) clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
