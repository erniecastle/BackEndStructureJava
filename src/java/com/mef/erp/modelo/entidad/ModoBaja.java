/**
 * @author: Victor Lopez Fecha de Creación: 03/07/2012 Compañía: MacroPro.
 * Descripción del programa: Entidad de FiniquitosLiquidaciones para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum ModoBaja {

    /** NORMAL */
    NORMAL(0),
    /** COMPLEMENTARIA */
    COMPLEMENTARIA(1),
    /** PROYECCION */
    PROYECCION(2);
    private final int modoBaja;

    ModoBaja(int modoBaja) {
        this.modoBaja = modoBaja;
    }

    public int getModoBaja() {
        return modoBaja;
    }

    public static ModoBaja getEnum(int clasificador) {
        if (NORMAL.getModoBaja() == clasificador) {
            return NORMAL;
        } else if (COMPLEMENTARIA.getModoBaja() == clasificador) {
            return COMPLEMENTARIA;
        } else if (PROYECCION.getModoBaja() == clasificador) {
            return PROYECCION;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
    
     public static ModoBaja getEnumString(String clasificador) {
        if (NORMAL.toString().equalsIgnoreCase(clasificador)) {
            return NORMAL;
        } else if (COMPLEMENTARIA.toString().equalsIgnoreCase(clasificador)) {
            return COMPLEMENTARIA;
        } else if (PROYECCION.toString().equalsIgnoreCase(clasificador)) {
            return PROYECCION;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
