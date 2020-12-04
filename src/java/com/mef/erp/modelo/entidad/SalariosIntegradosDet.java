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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SalariosIntegradosDet",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"salarioIntegrado_ID", "ConcepNomDefi_ID", "plazasPorEmpleado_ID"})})
public class SalariosIntegradosDet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "salarioIntegrado_ID", nullable = false, insertable = true, updatable = true)
    private SalariosIntegrados salarioIntegrado;
    @ManyToOne
    @JoinColumn(name = "ConcepNomDefi_ID", nullable = false, insertable = true, updatable = true)
    private ConcepNomDefi concepNomDefi;
    private double horas;
    private double importe;
    @Column(length = 1, nullable = true)
    private Cambio cambio;
    @Temporal(TemporalType.DATE)
    private Date fechaCambio;
    @ManyToOne
    @JoinColumn(name = "plazasPorEmpleado_ID", nullable = false, insertable = true, updatable = true)
    private PlazasPorEmpleado plazasPorEmpleado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalariosIntegrados getSalarioIntegrado() {
        return salarioIntegrado;
    }

    public void setSalarioIntegrado(SalariosIntegrados salarioIntegrado) {
        this.salarioIntegrado = salarioIntegrado;
    }

    public ConcepNomDefi getConcepNomDefi() {
        return concepNomDefi;
    }

    public void setConcepNomDefi(ConcepNomDefi concepNomDefi) {
        this.concepNomDefi = concepNomDefi;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public PlazasPorEmpleado getPlazasPorEmpleado() {
        return plazasPorEmpleado;
    }

    public void setPlazasPorEmpleado(PlazasPorEmpleado plazasPorEmpleado) {
        this.plazasPorEmpleado = plazasPorEmpleado;
    }

    public enum Cambio {

        NO_CAMBIO(0),
        CAMBIO(1);
        private final int tipoISR;

        Cambio(int tipoISR) {
            this.tipoISR = tipoISR;
        }

        public int getCambio() {
            return tipoISR;
        }
    }
    
}
