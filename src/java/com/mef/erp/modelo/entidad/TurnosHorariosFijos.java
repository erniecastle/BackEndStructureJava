/**
 * @author: José Ernesto Valenzuela Castillo Fecha de Creación: 30/09/2011
 * Compañía: Exito Software. Descripción del programa: Entidad de Turnos para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 02-12-2011
 * Descripción: Se agrego el campo de Razon social ya que esta informacion es
 * por empresa.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto Castillo
 */
@Entity
public class TurnosHorariosFijos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    private DiaSemana diaSemana;
    @ManyToOne
    private Horario Horario;
    private Integer statusDia;
    private Integer ordenDia;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "turnos_ID")//, updatable = false, insertable = false)
    @ForeignKey(name = "FK_turnos_ID")
    private Turnos turnos;

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

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Horario getHorario() {
        return Horario;
    }

    public void setHorario(Horario Horario) {
        this.Horario = Horario;
    }

    public Integer getStatusDia() {
        return statusDia;
    }

    public void setStatusDia(Integer statusDia) {
        this.statusDia = statusDia;
    }

    public Integer getOrdenDia() {
        return ordenDia;
    }

    public void setOrdenDia(Integer ordenDia) {
        this.ordenDia = ordenDia;
    }

    public Turnos getTurnos() {
        return turnos;
    }

    public void setTurnos(Turnos turnos) {
        this.turnos = turnos;
    }

}
