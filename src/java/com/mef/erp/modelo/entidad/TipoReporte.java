/**
 * @author: Victor Lopez
 * Fecha de Creación: 26/07/2011
 * Compañía: MacroPro.
 * Descripción del programa: Enumerador de Naturaleza apra conceptos de nomina
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: 
 * Autor: 
 * Fecha: 
 * Descripción: 
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum TipoReporte {

    LISTADO(0),
    RECIBO(1),
    FINIQUITO(2);
    private final Integer tipo;

    TipoReporte(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipoReporte() {
        return tipo;
    }

    public static TipoReporte getEnum(String tipoReporte) {
        if (LISTADO.name().equalsIgnoreCase(tipoReporte)) {
            return LISTADO;
        } else if (RECIBO.name().equalsIgnoreCase(tipoReporte)) {
            return RECIBO;
        } else if (FINIQUITO.name().equalsIgnoreCase(tipoReporte)) {
            return FINIQUITO;
        } 
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
