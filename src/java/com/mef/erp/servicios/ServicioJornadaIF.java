/**
 * @author: Jose Armando Fecha de Creacion: 17/02/2014 Compañia: Macropro
 * Descripcion del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;

public interface ServicioJornadaIF {

    /*List<Jornada>*/
    Mensaje getJornadaAll(String uuidCxn);

}
