/**
 * @author: Victor Lopez Fecha de Creación: 03/07/2012 Compañía: MacroPro.
 * Descripción del programa: Entidad de IngresosReingresosBajas para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "IngReingresosBajas",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"razonesSociales_ID", "registroPatronal_ID", "empleados_ID", "fechaIngreso"})})
public class IngresosReingresosBajas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "empleados_ID", nullable = false, insertable = true, updatable = true)
    private Empleados empleados;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToOne
    @JoinColumn(name = "registroPatronal_ID", nullable = false, insertable = true, updatable = true)
    private RegistroPatronal registroPatronal;
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaIngreso", nullable = false, insertable = true, updatable = true)
    private Date fechaIngreso;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private PlazasPorEmpleado plazasPorEmpleado;
    @ManyToOne
    private FiniquitosLiquidaciones finiquitosLiquidaciones;
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "char(1)")
    // I=Ingreso, R=Reingreso
    private TipoMovimiento tipoMovimiento;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlazasPorEmpleado getPlazasPorEmpleado() {
        return plazasPorEmpleado;
    }

    public void setPlazasPorEmpleado(PlazasPorEmpleado plazasPorEmpleado) {
        this.plazasPorEmpleado = plazasPorEmpleado;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public RegistroPatronal getRegistroPatronal() {
        return registroPatronal;
    }

    public void setRegistroPatronal(RegistroPatronal registroPatronal) {
        this.registroPatronal = registroPatronal;
    }

    public FiniquitosLiquidaciones getFiniquitosLiquidaciones() {
        return finiquitosLiquidaciones;
    }

    public void setFiniquitosLiquidaciones(FiniquitosLiquidaciones finiquitosLiquidaciones) {
        this.finiquitosLiquidaciones = finiquitosLiquidaciones;
    }

    public enum TipoMovimiento {

        /** Ingreso */
        I,
        /** Reingreso */
        R;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

}
