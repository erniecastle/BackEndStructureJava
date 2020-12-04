/**
 * @author: Jose Ernesto Valenzuela Castillo Fecha de Creación: 02/06/2016
 * Compañía: Exito Software. Descripción del programa: Entidad de PtuEmpleados
 * para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ernesto
 */
@Entity
public class PtuEmpleados implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @JoinColumn(nullable = false)
    private Integer ejercicio;
    @ManyToOne
    @JoinColumn(name = "empleados_ID", nullable = false, insertable = true, updatable = true)
    private Empleados empleados;
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @ManyToOne
    private Puestos puestos;
    private boolean esDirectivo = false;
    private String clasificacion;
    private Double diasLaborados;
    private Double percepciones;
    private Double ptuDias;
    private Double ptuPercepciones;
    private String observaciones;
    private boolean participa = true;
    @ManyToOne
    @JoinColumn(name = "tipoCorridaPtuDias_ID", nullable = true, insertable = true, updatable = true)
    private TipoCorrida tipoCorridaPtuDias;
    @ManyToOne
    @JoinColumn(name = "tipoNominaPtuDias_ID", nullable = true, insertable = true, updatable = true)
    private TipoNomina tipoNominaPtuDias;
    @ManyToOne
    @JoinColumn(name = "periodoPtuDias_ID", nullable = true, insertable = true, updatable = true)
    private PeriodosNomina periodoPtuDias;
    @ManyToOne
    @JoinColumn(name = "tipoCorridaPtuPercep_ID", nullable = true, insertable = true, updatable = true)
    private TipoCorrida tipoCorridaPtuPercep;
    @ManyToOne
    @JoinColumn(name = "tipoNominaPtuPercep_ID", nullable = true, insertable = true, updatable = true)
    private TipoNomina tipoNominaPtuPercep;
    @ManyToOne
    @JoinColumn(name = "periodoPtuPercep_ID", nullable = true, insertable = true, updatable = true)
    private PeriodosNomina periodoPtuPercep;

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

    public Integer getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Puestos getPuestos() {
        return puestos;
    }

    public void setPuestos(Puestos puestos) {
        this.puestos = puestos;
    }

    public boolean isEsDirectivo() {
        return esDirectivo;
    }

    public void setEsDirectivo(boolean esDirectivo) {
        this.esDirectivo = esDirectivo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Double getDiasLaborados() {
        return diasLaborados;
    }

    public void setDiasLaborados(Double diasLaborados) {
        this.diasLaborados = diasLaborados;
    }

    public Double getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(Double percepciones) {
        this.percepciones = percepciones;
    }

    public Double getPtuDias() {
        return ptuDias;
    }

    public void setPtuDias(Double ptuDias) {
        this.ptuDias = ptuDias;
    }

    public Double getPtuPercepciones() {
        return ptuPercepciones;
    }

    public void setPtuPercepciones(Double ptuPercepciones) {
        this.ptuPercepciones = ptuPercepciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isParticipa() {
        return participa;
    }

    public void setParticipa(boolean participa) {
        this.participa = participa;
    }

    public TipoCorrida getTipoCorridaPtuDias() {
        return tipoCorridaPtuDias;
    }

    public void setTipoCorridaPtuDias(TipoCorrida tipoCorridaPtuDias) {
        this.tipoCorridaPtuDias = tipoCorridaPtuDias;
    }

    public TipoNomina getTipoNominaPtuDias() {
        return tipoNominaPtuDias;
    }

    public void setTipoNominaPtuDias(TipoNomina tipoNominaPtuDias) {
        this.tipoNominaPtuDias = tipoNominaPtuDias;
    }

    public PeriodosNomina getPeriodoPtuDias() {
        return periodoPtuDias;
    }

    public void setPeriodoPtuDias(PeriodosNomina periodoPtuDias) {
        this.periodoPtuDias = periodoPtuDias;
    }

    public TipoCorrida getTipoCorridaPtuPercep() {
        return tipoCorridaPtuPercep;
    }

    public void setTipoCorridaPtuPercep(TipoCorrida tipoCorridaPtuPercep) {
        this.tipoCorridaPtuPercep = tipoCorridaPtuPercep;
    }

    public TipoNomina getTipoNominaPtuPercep() {
        return tipoNominaPtuPercep;
    }

    public void setTipoNominaPtuPercep(TipoNomina tipoNominaPtuPercep) {
        this.tipoNominaPtuPercep = tipoNominaPtuPercep;
    }

    public PeriodosNomina getPeriodoPtuPercep() {
        return periodoPtuPercep;
    }

    public void setPeriodoPtuPercep(PeriodosNomina periodoPtuPercep) {
        this.periodoPtuPercep = periodoPtuPercep;
    }

}
