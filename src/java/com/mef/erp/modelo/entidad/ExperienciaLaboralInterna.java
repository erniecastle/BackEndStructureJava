/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author daniel
 */
@Entity
public class ExperienciaLaboralInterna implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Plazas plazas;
    @ManyToOne
    private Puestos puestos;
    @ManyToOne
    private CentroDeCosto centroCostos;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @ManyToOne
    private Empleados empleados;

    public CentroDeCosto getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(CentroDeCosto centroCostos) {
        this.centroCostos = centroCostos;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plazas getPlazas() {
        return plazas;
    }

    public void setPlazas(Plazas plazas) {
        this.plazas = plazas;
    }

    public Puestos getPuestos() {
        return puestos;
    }

    public void setPuestos(Puestos puestos) {
        this.puestos = puestos;
    }

        
}
