/**
 * @author: Victor Lopez Fecha de Creación: 29/08/2011 Compañía: Macropro.
 * Descripción del programa: Entidad de Horarios con Hibernate
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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "Horario",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "clave"})})//JSA01
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    private Double topeDiarioHrsExtras;
    @Temporal(TemporalType.TIME)
    private Date horaInicioHrsExtra;
    private Double tiempoComer;
    @Temporal(TemporalType.TIME)
    private Date horaInicioComer;
    @Temporal(TemporalType.TIME)
    private Date horaFinalComer;
    private Double tiempo1erCoffeBreak;
    @Temporal(TemporalType.TIME)
    private Date horaInicio1erCoffeBreak;
    @Temporal(TemporalType.TIME)
    private Date horaFinal1erCoffeBreak;
    private Double tiempo2doCoffeBreak;
    @Temporal(TemporalType.TIME)
    private Date horaInicio2doCoffeBreak;
    @Temporal(TemporalType.TIME)
    private Date horaFinal2doCoffeBreak;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)//JSA01
    private RazonesSociales razonesSociales;//JSA01

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Double getTopeDiarioHrsExtras() {
        return topeDiarioHrsExtras;
    }

    public void setTopeDiarioHrsExtras(Double topeDiarioHrsExtras) {
        this.topeDiarioHrsExtras = topeDiarioHrsExtras;
    }

    public Date getHoraInicioHrsExtra() {
        return horaInicioHrsExtra;
    }

    public void setHoraInicioHrsExtra(Date horaInicioHrsExtra) {
        this.horaInicioHrsExtra = horaInicioHrsExtra;
    }

    public Double getTiempoComer() {
        return tiempoComer;
    }

    public void setTiempoComer(Double tiempoComer) {
        this.tiempoComer = tiempoComer;
    }

    public Date getHoraInicioComer() {
        return horaInicioComer;
    }

    public void setHoraInicioComer(Date horaInicioComer) {
        this.horaInicioComer = horaInicioComer;
    }

    public Date getHoraFinalComer() {
        return horaFinalComer;
    }

    public void setHoraFinalComer(Date horaFinalComer) {
        this.horaFinalComer = horaFinalComer;
    }

    public Double getTiempo1erCoffeBreak() {
        return tiempo1erCoffeBreak;
    }

    public void setTiempo1erCoffeBreak(Double tiempo1erCoffeBreak) {
        this.tiempo1erCoffeBreak = tiempo1erCoffeBreak;
    }

    public Date getHoraInicio1erCoffeBreak() {
        return horaInicio1erCoffeBreak;
    }

    public void setHoraInicio1erCoffeBreak(Date horaInicio1erCoffeBreak) {
        this.horaInicio1erCoffeBreak = horaInicio1erCoffeBreak;
    }

    public Date getHoraFinal1erCoffeBreak() {
        return horaFinal1erCoffeBreak;
    }

    public void setHoraFinal1erCoffeBreak(Date horaFinal1erCoffeBreak) {
        this.horaFinal1erCoffeBreak = horaFinal1erCoffeBreak;
    }

    public Double getTiempo2doCoffeBreak() {
        return tiempo2doCoffeBreak;
    }

    public void setTiempo2doCoffeBreak(Double tiempo2doCoffeBreak) {
        this.tiempo2doCoffeBreak = tiempo2doCoffeBreak;
    }

    public Date getHoraInicio2doCoffeBreak() {
        return horaInicio2doCoffeBreak;
    }

    public void setHoraInicio2doCoffeBreak(Date horaInicio2doCoffeBreak) {
        this.horaInicio2doCoffeBreak = horaInicio2doCoffeBreak;
    }

    public Date getHoraFinal2doCoffeBreak() {
        return horaFinal2doCoffeBreak;
    }

    public void setHoraFinal2doCoffeBreak(Date horaFinal2doCoffeBreak) {
        this.horaFinal2doCoffeBreak = horaFinal2doCoffeBreak;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

}
