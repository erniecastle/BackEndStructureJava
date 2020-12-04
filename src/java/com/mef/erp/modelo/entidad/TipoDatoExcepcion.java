/**
 * @author: Jose Armando
 * Fecha de Creacion: 31/05/2013
 * Compania: Macropro
 * Descripción del programa: Entidad de TipoDatoExcepcion para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum TipoDatoExcepcion {

    SINDATO(0),//Este dato nos indicara que la excepcion su valor es 1.
    HORASMINUTOS(1);
    
    private final int TipoDatoExcepcion;

    TipoDatoExcepcion(int tipoISR) {
        this.TipoDatoExcepcion = tipoISR;
    }

    public int getTipoDatoExcepcion() {
        return TipoDatoExcepcion;
    }
}