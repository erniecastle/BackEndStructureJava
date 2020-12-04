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

public enum ManejoSalarioDiario {

    DIARIO(1),
    SEMANAL(2),
    QUINCENAL(3),
    MENSUAL(4),;
    private final int Salario;

    ManejoSalarioDiario(int Salario) {
        this.Salario = Salario;
    }

    public int getManejoSalarioDiario() {
        return Salario;
    }
}
