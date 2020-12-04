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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "InasistenciaPorHora")
public class InasistenciaPorHora implements Serializable {

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
    @JoinColumn(name = "plazasPorEmpleadosMov", nullable = false, insertable = true)
    private PlazasPorEmpleadosMov plazasPorEmpleadosMov;
    private Integer horas;

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

    public PlazasPorEmpleadosMov getPlazasPorEmpleadosMov() {
        return plazasPorEmpleadosMov;
    }

    public void setPlazasPorEmpleadosMov(PlazasPorEmpleadosMov plazasPorEmpleadosMov) {
        this.plazasPorEmpleadosMov = plazasPorEmpleadosMov;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

}
