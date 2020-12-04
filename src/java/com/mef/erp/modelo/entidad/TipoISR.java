/**
 * @author: Victor Lopez
 * Fecha de Creación: 26/07/2011
 * Compañía: MacroPro.
 * Descripción del programa: Enumerador de TipoAcceso para controlar accesos por ip, dominio o subredes
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

public enum TipoISR {

    NORMAL(0),
    DIRECTO(1),
    ANUAL(2);
    
    private final int tipoISR;

    TipoISR(int tipoISR) {
        this.tipoISR = tipoISR;
    }

    public int getTipoISR() {
        return tipoISR;
    }
}
