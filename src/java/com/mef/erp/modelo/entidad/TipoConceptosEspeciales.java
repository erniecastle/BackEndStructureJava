/**
 * @author: Josa Armando
 * Fecha de Creación: 26/08/2012
 * Compañía: Macropro
 * Descripción del programa: Conceptos especiales se creo con el fin de definir cuales
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

public enum TipoConceptosEspeciales {

    ISR(1),
    IMSS(2);
    private final int tipo;

    TipoConceptosEspeciales(int tipo) {
        this.tipo = tipo;
    }

    public int getTipoConceptosEspeciales() {
        return tipo;
    }
}
