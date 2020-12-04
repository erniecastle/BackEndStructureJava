/**
 * @author: Daniel
 * Fecha de Creación: --/--/--
 * Compañía: FineSoft
 * Descripción del programa: 
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:23/07/2012
 * Descripción:Se cambiaron los nombre de los campos y se añadieron mas, se agrego una fecha como control.
 * -----------------------------------------------------------------------------
 * Clave:JSA02
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:05/02/2013
 * Descripción:Se agrego un constraints a la fecha.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ConfiguracionIMSS",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id", "fechaAplica"})})//JSA02
public class ConfiguracionIMSS implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaAplica;
    private float excedenteEspecie;
    private float CuotaFijaPatron;
    //<editor-fold defaultstate="collapsed" desc="Tasas empleado">
    //factor para la Cuota en especie de Enfermedades y Maternidad
    private float tasaEspecieEnfermeMater;
    //factor para la cuota en dinero de Enfermedades y Maternidad
    private float tasaDineEnfermeMater;
    //factor para la cuota del asegurado de Enfermedades y Maternidad
    private float tasaGastosPension;
    //factor para la cuota fija de Invalidez y vida
    private float tasaInvalidezVida;
    //factor para la cuota fija de Cesantía y Vejez
    private float tasaCesantiaVejez;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Tasas Patronal">
    //tasa Fija del Patrón
    private float tasaFijaPatron;
    //tasa Excedente por parte del Patrón
    private float tasaExcedentePatron;
    //tasa de pensión o gastos pensionados por parte del patrón
    private float tasaGastosPensPatron;
    //tasa de Riesgos de trabajo por parte del Patrón
    private float tasaRiesgosPatron;
    //tasa de invalidez y vida por parte del patrón
    private float tasaInvaliVidaPatron;
    //tasa de Guardería y P.S. por parte el patrón
    private float tasaGuarderiaPatron;
    //tasa de Prestamos en dinero por parte del patrón
    private float tasaPrestDinePatron;
    //tasa de Cesantía y Vejez por parte del Patrón
    private float tasaCesanVejezPatron;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Topes Salariales">
    //tope de Salario para el concepto de Enfermedad y Maternidad
    private float topeEnfermedadMaternidad;
    //tope de salario para el concepto de Riesgo de Trabajo y Guarderías
    private float topeRiesgoTrabajoGuarderias;
    //tope de salario por concepto de Cesantía y Vejez e Invalidez y Vida
    private float topeCesanVejezInvaliVida;
    // tope de salario por concepto de Retiro
    private float topeRetiro;
    //tope de salario por concepto de Infonavit
    private float topeInfonavit;
    //</editor-fold>
    //Tasas infonavit
    private float tasaAportacionInfonavitPatron;
    //Tasas Retiro
    private float tasaAportacionRetiroPatron;
    private float AportacionInfonavitPatron;
    
    public ConfiguracionIMSS() {
    }

    public float getAportacionInfonavitPatron() {
        return AportacionInfonavitPatron;
    }

    public void setAportacionInfonavitPatron(float AportacionInfonavitPatron) {
        this.AportacionInfonavitPatron = AportacionInfonavitPatron;
    }

    public float getCuotaFijaPatron() {
        return CuotaFijaPatron;
    }

    public void setCuotaFijaPatron(float CuotaFijaPatron) {
        this.CuotaFijaPatron = CuotaFijaPatron;
    }

    public float getExcedenteEspecie() {
        return excedenteEspecie;
    }

    public void setExcedenteEspecie(float excedenteEspecie) {
        this.excedenteEspecie = excedenteEspecie;
    }

    public Date getFechaAplica() {
        return fechaAplica;
    }

    public void setFechaAplica(Date fechaAplica) {
        this.fechaAplica = fechaAplica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTasaCesanVejezPatron() {
        return tasaCesanVejezPatron;
    }

    public void setTasaCesanVejezPatron(float tasaCesanVejezPatron) {
        this.tasaCesanVejezPatron = tasaCesanVejezPatron;
    }

    public float getTasaCesantiaVejez() {
        return tasaCesantiaVejez;
    }

    public void setTasaCesantiaVejez(float tasaCesantiaVejez) {
        this.tasaCesantiaVejez = tasaCesantiaVejez;
    }

    public float getTasaDineEnfermeMater() {
        return tasaDineEnfermeMater;
    }

    public void setTasaDineEnfermeMater(float tasaDineEnfermeMater) {
        this.tasaDineEnfermeMater = tasaDineEnfermeMater;
    }

    public float getTasaEspecieEnfermeMater() {
        return tasaEspecieEnfermeMater;
    }

    public void setTasaEspecieEnfermeMater(float tasaEspecieEnfermeMater) {
        this.tasaEspecieEnfermeMater = tasaEspecieEnfermeMater;
    }

    public float getTasaExcedentePatron() {
        return tasaExcedentePatron;
    }

    public void setTasaExcedentePatron(float tasaExcedentePatron) {
        this.tasaExcedentePatron = tasaExcedentePatron;
    }

    public float getTasaFijaPatron() {
        return tasaFijaPatron;
    }

    public void setTasaFijaPatron(float tasaFijaPatron) {
        this.tasaFijaPatron = tasaFijaPatron;
    }

    public float getTasaGastosPensPatron() {
        return tasaGastosPensPatron;
    }

    public void setTasaGastosPensPatron(float tasaGastosPensPatron) {
        this.tasaGastosPensPatron = tasaGastosPensPatron;
    }

    public float getTasaGastosPension() {
        return tasaGastosPension;
    }

    public void setTasaGastosPension(float tasaGastosPension) {
        this.tasaGastosPension = tasaGastosPension;
    }

    public float getTasaGuarderiaPatron() {
        return tasaGuarderiaPatron;
    }

    public void setTasaGuarderiaPatron(float tasaGuarderiaPatron) {
        this.tasaGuarderiaPatron = tasaGuarderiaPatron;
    }

    public float getTasaInvaliVidaPatron() {
        return tasaInvaliVidaPatron;
    }

    public void setTasaInvaliVidaPatron(float tasaInvaliVidaPatron) {
        this.tasaInvaliVidaPatron = tasaInvaliVidaPatron;
    }

    public float getTasaInvalidezVida() {
        return tasaInvalidezVida;
    }

    public void setTasaInvalidezVida(float tasaInvalidezVida) {
        this.tasaInvalidezVida = tasaInvalidezVida;
    }

    public float getTasaPrestDinePatron() {
        return tasaPrestDinePatron;
    }

    public void setTasaPrestDinePatron(float tasaPrestDinePatron) {
        this.tasaPrestDinePatron = tasaPrestDinePatron;
    }

    public float getTasaRiesgosPatron() {
        return tasaRiesgosPatron;
    }

    public void setTasaRiesgosPatron(float tasaRiesgosPatron) {
        this.tasaRiesgosPatron = tasaRiesgosPatron;
    }

    public float getTopeCesanVejezInvaliVida() {
        return topeCesanVejezInvaliVida;
    }

    public void setTopeCesanVejezInvaliVida(float topeCesanVejezInvaliVida) {
        this.topeCesanVejezInvaliVida = topeCesanVejezInvaliVida;
    }

    public float getTopeEnfermedadMaternidad() {
        return topeEnfermedadMaternidad;
    }

    public void setTopeEnfermedadMaternidad(float topeEnfermedadMaternidad) {
        this.topeEnfermedadMaternidad = topeEnfermedadMaternidad;
    }

    public float getTopeInfonavit() {
        return topeInfonavit;
    }

    public void setTopeInfonavit(float topeInfonavit) {
        this.topeInfonavit = topeInfonavit;
    }

    public float getTopeRetiro() {
        return topeRetiro;
    }

    public void setTopeRetiro(float topeRetiro) {
        this.topeRetiro = topeRetiro;
    }

    public float getTopeRiesgoTrabajoGuarderias() {
        return topeRiesgoTrabajoGuarderias;
    }

    public void setTopeRiesgoTrabajoGuarderias(float topeRiesgoTrabajoGuarderias) {
        this.topeRiesgoTrabajoGuarderias = topeRiesgoTrabajoGuarderias;
    }

    public float getTasaAportacionInfonavitPatron() {
        return tasaAportacionInfonavitPatron;
    }

    public void setTasaAportacionInfonavitPatron(float tasaAportacionInfonavitPatron) {
        this.tasaAportacionInfonavitPatron = tasaAportacionInfonavitPatron;
    }

    public float getTasaAportacionRetiroPatron() {
        return tasaAportacionRetiroPatron;
    }

    public void setTasaAportacionRetiroPatron(float tasaAportacionRetiroPatron) {
        this.tasaAportacionRetiroPatron = tasaAportacionRetiroPatron;
    }
    
}
