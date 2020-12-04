/**
 * @author: Jose Armando Fecha de Creacion: 10/12/2014 Compañía: Macropro. Descripcion
 * del programa: Para el parametro clavePagarNominaDiasNaturales
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Descripción: 
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum DesgloseInternoISR {

    DESGLOSEISRNORMALANUAL(0),
    DESGLOSEISRNORMALDIRECTOANUAL(1),
    DESGLOSEISRANUAL(2);
    private final int clasificacion;

    DesgloseInternoISR(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getDesgloseInternoISR() {
        return clasificacion;
    }

    public static DesgloseInternoISR getEnum(int clasificador) {
        if (DESGLOSEISRNORMALANUAL.getDesgloseInternoISR() == clasificador) {
            return DESGLOSEISRNORMALANUAL;
        } else if (DESGLOSEISRNORMALDIRECTOANUAL.getDesgloseInternoISR() == clasificador) {
            return DESGLOSEISRNORMALDIRECTOANUAL;
        } else if (DESGLOSEISRANUAL.getDesgloseInternoISR() == clasificador) {
            return DESGLOSEISRANUAL;
        } else {
            return DESGLOSEISRNORMALANUAL;
        }
    }
}
