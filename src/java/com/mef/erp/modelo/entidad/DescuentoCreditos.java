/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author daniel
 */
public enum DescuentoCreditos {
    NoDescontar(1),
    DescontarSoloPeriodo(2),
    DescontarSoloMes(3),
    DescontarSaldoActual(4);
    
    private final int tipo;

    DescuentoCreditos(int tipo) {
        this.tipo = tipo;
    }

    public int getDescuentoCreditos() {
        return tipo;
    }
    
    public static DescuentoCreditos getEnum(String tipoDescuento) {
        if (NoDescontar.name().equalsIgnoreCase(tipoDescuento)) {
            return NoDescontar;
        } else if (DescontarSoloPeriodo.name().equalsIgnoreCase(tipoDescuento)) {
            return DescontarSoloPeriodo;
        } else if (DescontarSoloMes.name().equalsIgnoreCase(tipoDescuento)) {
            return DescontarSoloMes;
        } else if (DescontarSaldoActual.name().equalsIgnoreCase(tipoDescuento)) {
            return DescontarSaldoActual;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
