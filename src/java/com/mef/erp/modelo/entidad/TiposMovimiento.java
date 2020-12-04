/**
 * @author: Ernesto Valenzuela Fecha de Creación: 20/07/2013 Compañía: Exito.
 * Descripción del programa: Enumerador de TiposMovimiento para controlar los
 * Tipos de movimientos que afecten a los creditos
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Ernesto
 */
public enum TiposMovimiento {

    Abono(0),
    ModificarDescuento(1),
    Cargo(2),
    Bloqueo(3),
    AbonoSistema(4);
    private final Integer tipoMovimiento;

    private TiposMovimiento(Integer tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getTipoMovimiento() {
        return tipoMovimiento;
    }
}
