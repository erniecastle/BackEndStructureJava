/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Admin
 */
public class Isr {
    private Double limiteInferior;
    private Double cuotaFija;
    private Double porcentaje;
    
   
    public Isr(Object[] valorISR) {
        limiteInferior = Double.valueOf(valorISR[0].toString());
        cuotaFija = Double.valueOf(valorISR[1].toString());
        porcentaje = Double.valueOf(valorISR[2].toString());
    }

    
    public Double getCuotaFija() {
        return cuotaFija;
    }

    public void setCuotaFija(Double cuotaFija) {
        this.cuotaFija = cuotaFija;
    }

    public Double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

}
