/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 08/05/2012
 * Compañía: Exito Software.
 * Descripción del programa: Entidad de Detalle Asistencias para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DetalleAsistencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "tipoPantalla", nullable = false, insertable = true, updatable = true)
    private Integer tipoPantalla;
    @Temporal(TemporalType.DATE)
    private Date dia;
    private Double horaDoble;
    private Double horaTriple;

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

    public Integer getTipoPantalla() {
        return tipoPantalla;
    }

    public void setTipoPantalla(Integer tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Double getHoraDoble() {
        return horaDoble;
    }

    public void setHoraDoble(Double horaDoble) {
        this.horaDoble = horaDoble;
    }

    public Double getHoraTriple() {
        return horaTriple;
    }

    public void setHoraTriple(Double horaTriple) {
        this.horaTriple = horaTriple;
    }
}
