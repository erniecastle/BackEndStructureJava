/**
 * @author: Victor Lopez
 * Fecha de Creación: 26/07/2011
 * Compañía: MacroPro.
 * Descripción del programa: Enumerador de TipoAcceso para controlar accesos por ip, dominio o subredes
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01
 * Autor: Armando Sanchez	
 * Fecha: 02/nov/2012
 * Descripción: Se cambio el tipo int por Integer, por cuestion de a la hora de convertir su clase a
 * int no la encuentra.
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum Naturaleza {

    PERCEPCION(0),
    DEDUCCION(1),
    DATO(2),
    CALCULO(3),
    INFORMATIVO(4);
    private final Integer tipo;//JSA01

    Naturaleza(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getNaturaleza() {
        return tipo;
    }

    public static Naturaleza getEnum(String nombreNaturaleza) {
        if (PERCEPCION.name().equalsIgnoreCase(nombreNaturaleza)) {
            return PERCEPCION;
        } else if (DEDUCCION.name().equalsIgnoreCase(nombreNaturaleza)) {
            return DEDUCCION;
        } else if (DATO.name().equalsIgnoreCase(nombreNaturaleza)) {
            return DATO;
        } else if (CALCULO.name().equalsIgnoreCase(nombreNaturaleza)) {
            return CALCULO;
        } else if (INFORMATIVO.name().equalsIgnoreCase(nombreNaturaleza)) {
            return INFORMATIVO;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
