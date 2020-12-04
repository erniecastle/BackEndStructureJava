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
public class CalculoIMSS implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private MovNomConcep movNomConcep;
    @ManyToOne
    private ConfiguracionIMSS configuracionIMSS;
    private double valorEspecieEnfermeMater;
    private double valorDineEnfermeMater;
    private double valorGastosPension;
    private double valorInvalidezVida;
    private double valorCesantiaVejez;
    private Double diasCotizados;

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

    public double getValorCesantiaVejez() {
        return valorCesantiaVejez;
    }

    public void setValorCesantiaVejez(double valorCesantiaVejez) {
        this.valorCesantiaVejez = valorCesantiaVejez;
    }

    public double getValorDineEnfermeMater() {
        return valorDineEnfermeMater;
    }

    public void setValorDineEnfermeMater(double valorDineEnfermeMater) {
        this.valorDineEnfermeMater = valorDineEnfermeMater;
    }

    public double getValorEspecieEnfermeMater() {
        return valorEspecieEnfermeMater;
    }

    public void setValorEspecieEnfermeMater(double valorEspecieEnfermeMater) {
        this.valorEspecieEnfermeMater = valorEspecieEnfermeMater;
    }

    public double getValorGastosPension() {
        return valorGastosPension;
    }

    public void setValorGastosPension(double valorGastosPension) {
        this.valorGastosPension = valorGastosPension;
    }

    public double getValorInvalidezVida() {
        return valorInvalidezVida;
    }

    public void setValorInvalidezVida(double valorInvalidezVida) {
        this.valorInvalidezVida = valorInvalidezVida;
    }

    public Double getDiasCotizados() {
        return diasCotizados;
    }

    public void setDiasCotizados(Double diasCotizados) {
        this.diasCotizados = diasCotizados;
    }

}
