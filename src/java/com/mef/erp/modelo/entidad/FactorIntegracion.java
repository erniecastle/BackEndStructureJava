/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Admin
 */
public class FactorIntegracion {

    private Integer antiguedad;
    private Double factor;
    private Integer diasAguinaldo;
    private Integer diasVacaciones;
    private Double primaVacacional;
    private Double diasVacacionesTotales;

    public FactorIntegracion(Object[] valorFactorInt) {
        antiguedad = Integer.valueOf(valorFactorInt[0].toString());
        factor = Double.valueOf(valorFactorInt[1].toString());
        diasAguinaldo = Integer.valueOf(valorFactorInt[2].toString());
        diasVacaciones = Integer.valueOf(valorFactorInt[3].toString());
        primaVacacional = Double.valueOf(valorFactorInt[4].toString());
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Integer getDiasAguinaldo() {
        return diasAguinaldo;
    }

    public void setDiasAguinaldo(Integer diasAguinaldo) {
        this.diasAguinaldo = diasAguinaldo;
    }

    public Integer getDiasVacaciones() {
        return diasVacaciones;
    }

    public void setDiasVacaciones(Integer diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Double getPrimaVacacional() {
        return primaVacacional;
    }

    public void setPrimaVacacional(Double primaVacacional) {
        this.primaVacacional = primaVacacional;
    }

    public Double getDiasVacacionesTotales() {
        return diasVacacionesTotales;
    }

    public void setDiasVacacionesTotales(Double diasVacacionesTotales) {
        this.diasVacacionesTotales = diasVacacionesTotales;
    }
}
