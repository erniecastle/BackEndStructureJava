/**
 * @author: Victor Lopez
 * Fecha de Creación: 27/09/2011
 * Compañía: MacroPro.
 * Descripción del programa: interface servicio CampoDIM
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

import com.mef.erp.modelo.entidad.CampoDIM;
import com.mef.erp.modelo.entidad.Mensaje;

public interface ServicioCampoDIMIF {

    /*CampoDIM*/
    Mensaje agregar(CampoDIM entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(CampoDIM entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(CampoDIM entity, String uuidCxn);

    /*List<CampoDIM>*/
    Mensaje getCampoDIMAll(String uuidCxn);

    /*CampoDIM*/
    Mensaje getCampoDIMPorClave(String clave, String uuidCxn);

    /*List<CampoDIM>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);
    
}
