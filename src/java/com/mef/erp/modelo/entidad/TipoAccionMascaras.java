/**
 * @author: Jose Armando Sanchez Acosta Fecha de Creación: 21/01/2013 Compañía:
 * Macropro Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum TipoAccionMascaras {

    Redondear(0),
    Truncado(1);
    private final int accionMascara;

    TipoAccionMascaras(int accionMascara) {
        this.accionMascara = accionMascara;
    }

    public int getTipoAccionMascaras() {
        return accionMascara;
    }

    public static TipoAccionMascaras getEnum(int accionMascara) {
        if (Redondear.getTipoAccionMascaras() == accionMascara) {
            return Redondear;
        } else if (Truncado.getTipoAccionMascaras() == accionMascara) {
            return Truncado;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
