/**
 * @author: Victor Lopez Fecha de Creación: 03/07/2012 Compañía: MacroPro.
 * Descripción del programa: Entidad de FiniquitosLiquidaciones para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum TipoBaja {

    /**
     * FINIQUITO
     */
    FINIQUITO(0),
    /**
     * LIQUIDACION
     */
    LIQUIDACION(1);
    private final int tipoBaja;

    TipoBaja(int tipoBaja) {
        this.tipoBaja = tipoBaja;
    }

    public int getTipoBaja() {
        return tipoBaja;
    }

    public static TipoBaja getEnum(int clasificador) {
        if (FINIQUITO.getTipoBaja() == clasificador) {
            return FINIQUITO;
        } else if (LIQUIDACION.getTipoBaja() == clasificador) {
            return LIQUIDACION;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }

    public static TipoBaja getEnumString(String tipoBaja) {
        if (FINIQUITO.toString().equalsIgnoreCase(tipoBaja)) {
            return FINIQUITO;
        } else if (LIQUIDACION.toString().equalsIgnoreCase(tipoBaja)) {
            return LIQUIDACION;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
