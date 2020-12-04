/**
 * @author: Victor Lopez Fecha de Creación: 27/06/2012 Compañía: MacroPro.
 * Descripción del programa: Entidad de SalariosIntegrados para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;

@Entity
@Table(name = "SalariosIntegrados",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"registroPatronal_ID", "empleados_ID", "fecha"})})
public class SalariosIntegrados implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "empleados_ID", nullable = false, insertable = true, updatable = true)
    private Empleados empleados;
    @ManyToOne
    @JoinColumn(name = "registroPatronal_ID", nullable = false, insertable = true, updatable = true)
    private RegistroPatronal registroPatronal;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    //0=Fijo, 1=Variable, 2=Mixto
    @Column(length = 1, nullable = true)
    private Integer tipoDeSalario;
    private double salarioDiarioFijo;
    private double salarioDiarioVariable;
    private double factorIntegracion;
    private double salarioDiarioIntegrado;
    @OneToOne
    private FiniquitosLiquidaciones finiquitosLiquidaciones;

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public double getFactorIntegracion() {
        return factorIntegracion;
    }

    public void setFactorIntegracion(double factorIntegracion) {
        this.factorIntegracion = factorIntegracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistroPatronal getRegistroPatronal() {
        return registroPatronal;
    }

    public void setRegistroPatronal(RegistroPatronal registroPatronal) {
        this.registroPatronal = registroPatronal;
    }

    public double getSalarioDiarioFijo() {
        return salarioDiarioFijo;
    }

    public void setSalarioDiarioFijo(double salarioDiarioFijo) {
        this.salarioDiarioFijo = salarioDiarioFijo;
    }

    public double getSalarioDiarioIntegrado() {
        return salarioDiarioIntegrado;
    }

    public void setSalarioDiarioIntegrado(double salarioDiarioIntegrado) {
        this.salarioDiarioIntegrado = salarioDiarioIntegrado;
    }

    public double getSalarioDiarioVariable() {
        return salarioDiarioVariable;
    }

    public void setSalarioDiarioVariable(double salarioDiarioVariable) {
        this.salarioDiarioVariable = salarioDiarioVariable;
    }

    public Integer getTipoDeSalario() {
        return tipoDeSalario;
    }

    public void setTipoDeSalario(Integer tipoDeSalario) {
        this.tipoDeSalario = tipoDeSalario;
    }

    public FiniquitosLiquidaciones getFiniquitosLiquidaciones() {
        return finiquitosLiquidaciones;
    }

    public void setFiniquitosLiquidaciones(FiniquitosLiquidaciones finiquitosLiquidaciones) {
        this.finiquitosLiquidaciones = finiquitosLiquidaciones;
    }
}
