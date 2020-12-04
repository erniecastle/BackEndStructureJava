/**
 * @author: Victor Lopez
 * Fecha de Creación: 26/07/2011
 * Compañía: MacroPro.
 * Descripción del programa: Enumerador de TipoAcceso para controlar accesos por ip, dominio o subredes
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01
 * Autor: Jose Armando
 * Fecha: 09/05/2013
 * Descripción: Se quito el Enum de Anual ya que ese solo es una modalidad.
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum TipoTablaISR {

    PERIODICIDAD(0),
    NORMAL(1);//,
    //ANUAL(2);//JSA01
    
    private final int tipoTablaISR;

    TipoTablaISR(int tipoTablaISR) {
        this.tipoTablaISR = tipoTablaISR;
    }

    public int getTipoTablaISR() {
        return tipoTablaISR;
    }
}
