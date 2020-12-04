/**
 * @author: Victor Lopez Fecha de Creación: 11/10/2014 Compañía: MacroPro
 * Descripción del programa: Clase entidad para SemaforoCalculoNomina.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 */
package com.mef.erp.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SemaforoCalculoNomina",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"tipoNomina_ID", "periodoNomina_ID"})})
public class SemaforoCalculoNomina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tipoNomina_ID", nullable = false)//, updatable = false, insertable = false)
    private TipoNomina tipoNomina;
    @ManyToOne
    @JoinColumn(name = "periodoNomina_ID", nullable = false)
    private PeriodosNomina periodoNomina;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date tiempoInicio;
    @Column(length = 30, nullable = false)
    private String usuario;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Tipo tipo;

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

    public PeriodosNomina getPeriodoNomina() {
        return periodoNomina;
    }

    public void setPeriodoNomina(PeriodosNomina periodoNomina) {
        this.periodoNomina = periodoNomina;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(Date tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public enum Tipo {

        CALCULO_NOMINA, CIERRE_PERIODO, AGREGA_MOVIMIENTOS;
    }
}
