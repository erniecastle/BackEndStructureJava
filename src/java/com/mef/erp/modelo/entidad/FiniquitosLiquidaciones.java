/**
 * @author: Victor Lopez Fecha de Creación: 03/07/2012 Compañía: MacroPro.
 * Descripción del programa: Entidad de FiniquitosLiquidaciones para Hibernate
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "FiniquitosLiquida",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"razonesSociales_ID", "tipoBaja", "modoBaja", "referencia"})})
public class FiniquitosLiquidaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    private TipoBaja tipoBaja;
    private ModoBaja modoBaja;
    @Column(name = "referencia", length = 30, nullable = false)
    private String clave;
    @ManyToOne
    private Empleados empleados;
    @OneToOne
    private FiniquitosLiquidaciones finiquitosComplementario;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    private int causaBaja;
    private boolean bajaPorRiesgo = false;
    private String descripcionBaja;
    private boolean calculado = false;
    @Temporal(TemporalType.DATE)
    private Date fechaCalculo;
    private String observaciones;
    private int status; //1 guardar,2 calcular, 3 imprimir,4 cerrar, 5 Cancela Finiquito
    private int contImpreso;

    public boolean isBajaPorRiesgo() {
        return bajaPorRiesgo;
    }

    public void setBajaPorRiesgo(boolean bajaPorRiesgo) {
        this.bajaPorRiesgo = bajaPorRiesgo;
    }

    public boolean isCalculado() {
        return calculado;
    }

    public void setCalculado(boolean calculado) {
        this.calculado = calculado;
    }

    public int getCausaBaja() {
        return causaBaja;
    }

    public void setCausaBaja(int causaBaja) {
        this.causaBaja = causaBaja;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getContImpreso() {
        return contImpreso;
    }

    public void setContImpreso(int contImpreso) {
        this.contImpreso = contImpreso;
    }

    public String getDescripcionBaja() {
        return descripcionBaja;
    }

    public void setDescripcionBaja(String descripcionBaja) {
        this.descripcionBaja = descripcionBaja;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public FiniquitosLiquidaciones getFiniquitosComplementario() {
        return finiquitosComplementario;
    }

    public void setFiniquitosComplementario(FiniquitosLiquidaciones finiquitosComplementario) {
        this.finiquitosComplementario = finiquitosComplementario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModoBaja getModoBaja() {
        return modoBaja;
    }

    public void setModoBaja(ModoBaja modoBaja) {
        this.modoBaja = modoBaja;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TipoBaja getTipoBaja() {
        return tipoBaja;
    }

    public void setTipoBaja(TipoBaja tipoBaja) {
        this.tipoBaja = tipoBaja;
    }
    
}
