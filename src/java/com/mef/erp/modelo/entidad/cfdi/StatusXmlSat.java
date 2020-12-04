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
public enum StatusXmlSat {

    ENVIADO_SAT(0), RECIBIDO_SAT(1);

    private final Integer tipo;

    StatusXmlSat(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getStatusXmlSat() {
        return tipo;
    }

    public static StatusXmlSat getEnum(String StatusXmlSat) {
        if (ENVIADO_SAT.name().equalsIgnoreCase(StatusXmlSat)) {
            return ENVIADO_SAT;
        } else if (RECIBIDO_SAT.name().equalsIgnoreCase(StatusXmlSat)) {
            return RECIBIDO_SAT;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
