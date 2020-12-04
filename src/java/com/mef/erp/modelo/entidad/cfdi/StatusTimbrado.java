/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mef.erp.modelo.entidad.cfdi;

/**
 *
 * @author User
 */
public enum StatusTimbrado {
    TIMBRADO(0),
    CANCELADO(1),
    ERROR(2),
    EN_PROCESO(3);
    private final Integer tipo;

    StatusTimbrado(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getStatusTimbrado() {
        return tipo;
    }

    public static StatusTimbrado getEnum(String statusTimbrado) {
        if (TIMBRADO.name().equalsIgnoreCase(statusTimbrado)) {
            return TIMBRADO;
        } else if (CANCELADO.name().equalsIgnoreCase(statusTimbrado)) {
            return CANCELADO;
        } else if (ERROR.name().equalsIgnoreCase(statusTimbrado)) {
            return ERROR;
        } else if (EN_PROCESO.name().equalsIgnoreCase(statusTimbrado)) {
            return EN_PROCESO;
        } 
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
