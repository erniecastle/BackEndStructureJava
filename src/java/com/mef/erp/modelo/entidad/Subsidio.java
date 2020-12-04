/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Admin
 */
public class Subsidio {
    private Double limiteInferior;
    private Double cuota;

    public Subsidio(Object[] valorSubsidio) {
        limiteInferior = Double.valueOf(valorSubsidio[0].toString());
        cuota = Double.valueOf(valorSubsidio[1].toString());
    }

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public Double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }
}
