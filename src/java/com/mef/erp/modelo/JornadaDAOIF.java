/**
 * @author: Jose Armando
 * Fecha de Creacion: 17/02/2014
 * Compañia: Macropro
 * Descripcion del programa: 
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

import com.mef.erp.modelo.entidad.Jornada;
import com.mef.erp.modelo.entidad.Mensaje;

public interface JornadaDAOIF extends GenericDAO<Jornada, String> {//AAP01

    Mensaje getJornadaAll(String uuidCxn);
}
