/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Desarrollo 094
 */
@Entity
public class AguinaldoPagos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false/*, columnDefinition = "Id auto_incremento"*/)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToOne
    @JoinColumn(name = "empleado_ID", nullable = true, insertable = true, updatable = true)
    private Empleados empleado;
    @Column(nullable = false)
    private Integer ejercicio;
    private Double diasAguinaldos;
    private Double diasPagados;
    private Double aguinaldo;
    private Double isr;
    @ManyToOne
    @JoinColumn(name = "tipoNomina_ID", nullable = true, insertable = true, updatable = true)
    private TipoNomina tipoNomina;
    @ManyToOne
    @JoinColumn(name = "periodosNomina_ID", nullable = true, insertable = true, updatable = true)
    private PeriodosNomina periodosNomina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Double getDiasAguinaldos() {
        return diasAguinaldos;
    }

    public void setDiasAguinaldos(Double diasAguinaldos) {
        this.diasAguinaldos = diasAguinaldos;
    }

    public Double getDiasPagados() {
        return diasPagados;
    }

    public void setDiasPagados(Double diasPagados) {
        this.diasPagados = diasPagados;
    }

    public Double getAguinaldo() {
        return aguinaldo;
    }

    public void setAguinaldo(Double aguinaldo) {
        this.aguinaldo = aguinaldo;
    }

    public Double getIsr() {
        return isr;
    }

    public void setIsr(Double isr) {
        this.isr = isr;
    }

    public TipoNomina getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public PeriodosNomina getPeriodosNomina() {
        return periodosNomina;
    }

    public void setPeriodosNomina(PeriodosNomina periodosNomina) {
        this.periodosNomina = periodosNomina;
    }
    
    

}
