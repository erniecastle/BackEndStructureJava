/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 25/06/2012
 * Compañía: Exito Software.
 * Descripción del programa: Interface para servicio Formas De Pago
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;

public interface ServicioFormasDePagoIF {

    /*List<FormasDePago>*/
    Mensaje getFormasDePagoAll(String uuidCxn);
    
   
}
