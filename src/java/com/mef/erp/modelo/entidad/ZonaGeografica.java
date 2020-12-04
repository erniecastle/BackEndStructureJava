/**
 * @author: Jose Armando Sanchez Acosta
 * Fecha de Creación: 21/05/2011
 * Compañía: Macropro
 * Descripción del programa: 
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando   
 * Fecha:21/01/2013
 * Descripción:Se agrego como entidad..sera utilizado para conocer de que zonageografica sera.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum ZonaGeografica {

    ZonaGeograficaA(0),
    ZonaGeograficaB(1);
    
    private final int tipo;

    ZonaGeografica(int tipo) {
        this.tipo = tipo;
    }

    public int getZonaGeografica() {
        return tipo;
    }
}
