/**
 * @author: Victor Lopez
 * Fecha de Creación: 06/09/2011
 * Compañía: Macropro.
 * Descripción del programa: Enum de TipoDeValor
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

/**
 *
 * @author Admin
 */
public enum TipoDeValor {
    CANTIDAD("Cantidad"),
    IMPORTE("Importe"),
    HORA("Hora");
    private final String tipo;

    TipoDeValor(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoDeValor() {
        return tipo;
    }
}
