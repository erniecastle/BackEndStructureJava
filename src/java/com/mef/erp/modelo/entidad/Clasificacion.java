/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

public enum Clasificacion {

    GLOBAL(0),
    HORARIOSTURNO(1),
    CALCULO(2),
    ISR(3),
    IMSS(4),
    SUELDOSYSALARIOS(5),
    PRESTACIONES(6),
    INFORMACIONNOMINAL(7),
    MOVIMIENTOS(8),
    CONCEPTOS(9),
    REPORTES(10);
    private final int clasificacion;

    Clasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public static Clasificacion getEnum(int clasificador) {
        if (GLOBAL.getClasificacion() == clasificador) {
            return GLOBAL;
        } else if (HORARIOSTURNO.getClasificacion() == clasificador) {
            return HORARIOSTURNO;
        } else if (CALCULO.getClasificacion() == clasificador) {
            return CALCULO;
        } else if (ISR.getClasificacion() == clasificador) {
            return ISR;
        } else if (IMSS.getClasificacion() == clasificador) {
            return IMSS;
        } else if (SUELDOSYSALARIOS.getClasificacion() == clasificador) {
            return SUELDOSYSALARIOS;
        } else if (PRESTACIONES.getClasificacion() == clasificador) {
            return PRESTACIONES;
        } else if (INFORMACIONNOMINAL.getClasificacion() == clasificador) {
            return INFORMACIONNOMINAL;
        } else if (MOVIMIENTOS.getClasificacion() == clasificador) {
            return MOVIMIENTOS;
        } else if (CONCEPTOS.getClasificacion() == clasificador) {
            return CONCEPTOS;
        } else if (REPORTES.getClasificacion() == clasificador) {
            return REPORTES;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
