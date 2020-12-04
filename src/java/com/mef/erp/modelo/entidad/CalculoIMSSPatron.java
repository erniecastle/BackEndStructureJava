/**
 * @author: Victor Fecha de Creación: 19/09/2012 Compañía: Macropro Descripción
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.ManyToOne;

@Entity
public class CalculoIMSSPatron implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private MovNomConcep movNomConcep;
    @ManyToOne
    private ConfiguracionIMSS configuracionIMSS;
    private double valorTasaFijaPatron;
    private double valorTasaExcedentePatron;
    private double valorTasaPrestDinePatron;
    private double valorTasaGastosPensPatron;
    private double valorTasaInvaliVidaPatron;
    private double valorTasaCesanVejezPatron;
    private double valorTasaRiesgosPatron;
    private double valorTasaGuarderiaPatron;
    private double valorTasaAportacionRetiroPatron;
    private double valorTasaAportacionInfonavitPatron;
    
    public ConfiguracionIMSS getConfiguracionIMSS() {
        return configuracionIMSS;
    }

    public void setConfiguracionIMSS(ConfiguracionIMSS configuracionIMSS) {
        this.configuracionIMSS = configuracionIMSS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovNomConcep getMovNomConcep() {
        return movNomConcep;
    }

    public void setMovNomConcep(MovNomConcep movNomConcep) {
        this.movNomConcep = movNomConcep;
    }

    public double getValorTasaAportacionInfonavitPatron() {
        return valorTasaAportacionInfonavitPatron;
    }

    public void setValorTasaAportacionInfonavitPatron(double valorTasaAportacionInfonavitPatron) {
        this.valorTasaAportacionInfonavitPatron = valorTasaAportacionInfonavitPatron;
    }

    public double getValorTasaAportacionRetiroPatron() {
        return valorTasaAportacionRetiroPatron;
    }

    public void setValorTasaAportacionRetiroPatron(double valorTasaAportacionRetiroPatron) {
        this.valorTasaAportacionRetiroPatron = valorTasaAportacionRetiroPatron;
    }

    public double getValorTasaCesanVejezPatron() {
        return valorTasaCesanVejezPatron;
    }

    public void setValorTasaCesanVejezPatron(double valorTasaCesanVejezPatron) {
        this.valorTasaCesanVejezPatron = valorTasaCesanVejezPatron;
    }

    public double getValorTasaExcedentePatron() {
        return valorTasaExcedentePatron;
    }

    public void setValorTasaExcedentePatron(double valorTasaExcedentePatron) {
        this.valorTasaExcedentePatron = valorTasaExcedentePatron;
    }

    public double getValorTasaFijaPatron() {
        return valorTasaFijaPatron;
    }

    public void setValorTasaFijaPatron(double valorTasaFijaPatron) {
        this.valorTasaFijaPatron = valorTasaFijaPatron;
    }

    public double getValorTasaGastosPensPatron() {
        return valorTasaGastosPensPatron;
    }

    public void setValorTasaGastosPensPatron(double valorTasaGastosPensPatron) {
        this.valorTasaGastosPensPatron = valorTasaGastosPensPatron;
    }

    public double getValorTasaGuarderiaPatron() {
        return valorTasaGuarderiaPatron;
    }

    public void setValorTasaGuarderiaPatron(double valorTasaGuarderiaPatron) {
        this.valorTasaGuarderiaPatron = valorTasaGuarderiaPatron;
    }

    public double getValorTasaInvaliVidaPatron() {
        return valorTasaInvaliVidaPatron;
    }

    public void setValorTasaInvaliVidaPatron(double valorTasaInvaliVidaPatron) {
        this.valorTasaInvaliVidaPatron = valorTasaInvaliVidaPatron;
    }

    public double getValorTasaPrestDinePatron() {
        return valorTasaPrestDinePatron;
    }

    public void setValorTasaPrestDinePatron(double valorTasaPrestDinePatron) {
        this.valorTasaPrestDinePatron = valorTasaPrestDinePatron;
    }

    public double getValorTasaRiesgosPatron() {
        return valorTasaRiesgosPatron;
    }

    public void setValorTasaRiesgosPatron(double valorTasaRiesgosPatron) {
        this.valorTasaRiesgosPatron = valorTasaRiesgosPatron;
    }
}
