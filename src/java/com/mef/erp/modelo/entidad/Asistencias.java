/**
 * @author: Ernesto Castillo Fecha de Creación: 1/12/2011 Compañía: Exito
 * Software. Descripción del programa: Entidad de Asistencias para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Asistencias",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales", "tipoNomina", "periodosNomina", "centroDeCosto", "empleados", "excepciones", "fecha"})})
public class Asistencias implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id", nullable = false, insertable = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tipoNomina", nullable = false, insertable = true)
    private TipoNomina tipoNomina;
    @ManyToOne
    @JoinColumn(name = "periodosNomina", nullable = false, insertable = true)
    private PeriodosNomina periodosNomina;
    @ManyToOne
    @JoinColumn(name = "centroDeCosto", nullable = true, insertable = true)
    private CentroDeCosto centroDeCosto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empleados", nullable = false, insertable = true)
    private Empleados empleados;
    @ManyToOne
    @JoinColumn(name = "razonesSociales", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToOne
    @JoinColumn(name = "excepciones", nullable = false, insertable = true, updatable = true)
    private Excepciones excepciones;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Double jornada;
    private Double cantidad;
    @Column(length = 200, nullable = false)
    private Integer ordenId;
    @Column(length = 100, nullable = false)
    @JoinColumn(name = "tipoPantalla", nullable = false, insertable = true, updatable = true)
    private Integer tipoPantalla;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CentroDeCosto getCentroDeCosto() {
        return centroDeCosto;
    }

    public void setCentroDeCosto(CentroDeCosto centroDeCosto) {
        this.centroDeCosto = centroDeCosto;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public Excepciones getExcepciones() {
        return excepciones;
    }

    public void setExcepciones(Excepciones excepciones) {
        this.excepciones = excepciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getJornada() {
        return jornada;
    }

    public void setJornada(Double jornada) {
        this.jornada = jornada;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public Integer getTipoPantalla() {
        return tipoPantalla;
    }

    public void setTipoPantalla(Integer tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }
}
