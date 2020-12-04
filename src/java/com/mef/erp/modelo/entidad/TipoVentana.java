/**
 * @author: Jose Armando Fecha de Creación: 10/09/2012 Compañía: MacroPro.
 * Descripción del programa: Enumerador de TipoVentana para identificar los
 * tipos de ventanas para asi asignar permisos.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author xp
 */
public enum TipoVentana {

    CATALOGO(1),
    CONFIGURACION(2),
    MOVIMIENTO(3),
    GRID(4),
    REPORTE(5),
    CATALOGODIALOG(6),
    CONFIGURACIONDIALOG(7),
    TABLA(8),
    CONSULTA(9),
    BASEFORM(10);
    private final int tipoVentana;

    TipoVentana(int tipoVentana) {
        this.tipoVentana = tipoVentana;
    }

    public int getTipoVentana() {
        return tipoVentana;
    }
}
