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

public enum Tipo {
    AUTOMATICO(0),
    PERIODO(1),
    REPETITIVO(2);
    private final Integer tipo;

    Tipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipo() {
        return tipo;
    }
}
