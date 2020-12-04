/**
 * @author: Victor Lopez
 * Fecha de Creación: 06/08/2013
 * Compañía: MacroPro.
 * Descripción del programa: Enumerador de ClasificadorParametro
 * -----------------------------------------------------------------------------
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

public enum ClasificadorParametro {

    ENTRADA(0),
    ESPECIAL(1);
    private final Integer tipo;

    ClasificadorParametro(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getClasificadorParametro() {
        return tipo;
    }

    public static ClasificadorParametro getEnum(String nombreNaturaleza) {
        if (ENTRADA.name().equalsIgnoreCase(nombreNaturaleza)) {
            return ENTRADA;
        } else if (ESPECIAL.name().equalsIgnoreCase(nombreNaturaleza)) {
            return ESPECIAL;
        } 
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
