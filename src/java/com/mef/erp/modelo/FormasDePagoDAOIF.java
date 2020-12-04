/**
 * @author: Ernesto c.
 * Fecha de Creación: 25/06/2012
 * Compañía: Exito Software
 * Descripción del programa: interface Formas De Pago para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.FormasDePago;
import com.mef.erp.modelo.entidad.Mensaje;

public interface FormasDePagoDAOIF extends GenericDAO<FormasDePago, Long> {

    public Mensaje getFormasDePagoAll(String uuidCxn);
}
