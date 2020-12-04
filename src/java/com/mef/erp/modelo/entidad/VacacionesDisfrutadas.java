/**
 * @author: Ernesto Castillo Fecha de Creación: 13/05/2016 Compañía: Exito
 * Software. Descripción del programa: Entidad de Vacaciones Disfrutadas para
 * hibernate Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
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
public class VacacionesDisfrutadas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToOne
    @JoinColumn(name = "empleados_ID", nullable = false, insertable = true, updatable = true)
    private Empleados empleados;
    private Boolean pagarVacaciones;
    private Boolean pagarPrimavacacional;
    @Temporal(TemporalType.DATE)
    private Date salidaVacac;
    @Temporal(TemporalType.DATE)
    private Date regresoVac;
    private Integer diasVacDisfrutados;
    private Double diasPrimaDisfrutados;
    @ManyToOne
    private TipoNomina tipoNomina;
    private Integer ejercicioAplicacion;
    @ManyToOne
    private PeriodosNomina periodoAplicacion;
    @ManyToOne
    @JoinColumn(name = "periodoVacacional_ID", nullable = true, insertable = true, updatable = true)
    private PeriodosNomina periodoVacacional;
    @ManyToOne
    @JoinColumn(name = "periodoprimaVacacional_ID", nullable = true, insertable = true, updatable = true)
    private PeriodosNomina periodoprimaVacacional;
    @ManyToOne
    @JoinColumn(nullable = true)
    private TiposVacaciones tiposVacaciones;
    private Boolean registroInicial;
    private String observaciones;

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

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Boolean isPagarVacaciones() {
        return pagarVacaciones;
    }

    public void setPagarVacaciones(Boolean pagarVacaciones) {
        this.pagarVacaciones = pagarVacaciones;
    }

    public Boolean isPagarPrimavacacional() {
        return pagarPrimavacacional;
    }

    public void setPagarPrimavacacional(Boolean pagarPrimavacacional) {
        this.pagarPrimavacacional = pagarPrimavacacional;
    }

    public Date getSalidaVacac() {
        return salidaVacac;
    }

    public void setSalidaVacac(Date salidaVacac) {
        this.salidaVacac = salidaVacac;
    }

    public Date getRegresoVac() {
        return regresoVac;
    }

    public void setRegresoVac(Date regresoVac) {
        this.regresoVac = regresoVac;
    }

    public Integer getDiasVacDisfrutados() {
        return diasVacDisfrutados;
    }

    public void setDiasVacDisfrutados(Integer diasVacDisfrutados) {
        this.diasVacDisfrutados = diasVacDisfrutados;
    }

    public Double getDiasPrimaDisfrutados() {
        return diasPrimaDisfrutados;
    }

    public void setDiasPrimaDisfrutados(Double diasPrimaDisfrutados) {
        this.diasPrimaDisfrutados = diasPrimaDisfrutados;
    }

    public TipoNomina getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Integer getEjercicioAplicacion() {
        return ejercicioAplicacion;
    }

    public void setEjercicioAplicacion(Integer ejercicioAplicacion) {
        this.ejercicioAplicacion = ejercicioAplicacion;
    }

    public PeriodosNomina getPeriodoAplicacion() {
        return periodoAplicacion;
    }

    public void setPeriodoAplicacion(PeriodosNomina periodoAplicacion) {
        this.periodoAplicacion = periodoAplicacion;
    }

    public PeriodosNomina getPeriodoVacacional() {
        return periodoVacacional;
    }

    public void setPeriodoVacacional(PeriodosNomina periodoVacacional) {
        this.periodoVacacional = periodoVacacional;
    }

    public PeriodosNomina getPeriodoprimaVacacional() {
        return periodoprimaVacacional;
    }

    public void setPeriodoprimaVacacional(PeriodosNomina periodoprimaVacacional) {
        this.periodoprimaVacacional = periodoprimaVacacional;
    }

    public TiposVacaciones getTiposVacaciones() {
        return tiposVacaciones;
    }

    public void setTiposVacaciones(TiposVacaciones tiposVacaciones) {
        this.tiposVacaciones = tiposVacaciones;
    }

    public Boolean isRegistroInicial() {
        return registroInicial;
    }

    public void setRegistroInicial(Boolean registroInicial) {
        this.registroInicial = registroInicial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
