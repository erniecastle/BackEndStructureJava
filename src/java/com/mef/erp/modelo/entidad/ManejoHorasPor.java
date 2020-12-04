/**
 * @author: Jose Armando Sanchez Acosta
 * Fecha de Creación: 21/01/2013
 * Compañía: Macropro
 * Descripción del programa: 
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum ManejoHorasPor {

    HORASNATURALES(1),
    HSM(2);
    private final int horas;

    ManejoHorasPor(int horas) {
        this.horas = horas;
    }

    public int getManejoHorasPor() {
        return horas;
    }
    
    public static ManejoHorasPor getEnum(int manehoHoras) {
        if (HORASNATURALES.getManejoHorasPor() == manehoHoras) {
            return HORASNATURALES;
        } else if (HSM.getManejoHorasPor() == manehoHoras) {
            return HSM;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
