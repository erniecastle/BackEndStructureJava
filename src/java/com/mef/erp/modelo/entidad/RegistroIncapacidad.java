/**
 * @author: Victor Lopez Fecha de Creación: 16/12/2011 Compañía: MacroPro.
 * Descripción del programa: Entidad de RegistroIncapacidad para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "RegistroIncapacidad", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"clave"})})//JSA01
public class RegistroIncapacidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @ManyToOne
    private Empleados empleados;
    private Integer ramoSeguro;
    private Integer tipoRiesgo;
    private Integer secuelaConsecuencia;
    private Integer controlIncapacidad;
    @OneToOne
    private RegistroIncapacidad incapacidadAnterior;
    @Column(length = 30)
    private Integer diasIncapacidad;
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    private Integer porcentaje;
    private Boolean pagarTresPrimeroDias;

    public Integer getControlIncapacidad() {
        return controlIncapacidad;
    }

    public void setControlIncapacidad(Integer controlIncapacidad) {
        this.controlIncapacidad = controlIncapacidad;
    }

    public Integer getDiasIncapacidad() {
        return diasIncapacidad;
    }

    public void setDiasIncapacidad(Integer diasIncapacidad) {
        this.diasIncapacidad = diasIncapacidad;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistroIncapacidad getIncapacidadAnterior() {
        return incapacidadAnterior;
    }

    public void setIncapacidadAnterior(RegistroIncapacidad incapacidadAnterior) {
        this.incapacidadAnterior = incapacidadAnterior;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getRamoSeguro() {
        return ramoSeguro;
    }

    public void setRamoSeguro(Integer ramoSeguro) {
        this.ramoSeguro = ramoSeguro;
    }

    public Integer getSecuelaConsecuencia() {
        return secuelaConsecuencia;
    }

    public void setSecuelaConsecuencia(Integer secuelaConsecuencia) {
        this.secuelaConsecuencia = secuelaConsecuencia;
    }

    public Integer getTipoRiesgo() {
        return tipoRiesgo;
    }

    public void setTipoRiesgo(Integer tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public Boolean isPagarTresPrimeroDias() {
        return pagarTresPrimeroDias;
    }

    public void setPagarTresPrimeroDias(Boolean pagarTresPrimeroDias) {
        this.pagarTresPrimeroDias = pagarTresPrimeroDias;
    }

}
