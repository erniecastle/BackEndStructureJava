/**
 * @author: Jose Armando Sanchez Acosta Fecha de Creación: 21/01/2013 Compañía:
 * Macropro Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum DiaSemana {

    Domingo(1),
    Lunes(2),
    Martes(3),
    Miercoles(4),
    Jueves(5),
    Viernes(6),
    Sabado(7);
    private final int diaSemana;

    DiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public static DiaSemana getEnum(int manejoDias) {
        if (Domingo.getDiaSemana() == manejoDias) {
            return Domingo;
        } else if (Lunes.getDiaSemana() == manejoDias) {
            return Lunes;
        } else if (Martes.getDiaSemana() == manejoDias) {
            return Martes;
        } else if (Miercoles.getDiaSemana() == manejoDias) {
            return Miercoles;
        } else if (Jueves.getDiaSemana() == manejoDias) {
            return Jueves;
        } else if (Viernes.getDiaSemana() == manejoDias) {
            return Viernes;
        } else if (Sabado.getDiaSemana() == manejoDias) {
            return Sabado;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
