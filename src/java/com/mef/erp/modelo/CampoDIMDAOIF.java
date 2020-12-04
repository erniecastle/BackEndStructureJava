/**
 * @author: Victor Lopez
 * Compañía: Macropro.
 * Descripción del programa: interface CampoDIMDAOIF para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:10/11/2011
 * Descripción:Se cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CampoDIM;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface CampoDIMDAOIF {

    Mensaje agregar(CampoDIM entity, String uuidCxn);//JSA01

    Mensaje actualizar(CampoDIM entity, String uuidCxn);//JSA01

    Mensaje eliminar(CampoDIM entity, String uuidCxn);//JSA01

    Mensaje getCampoDIMAll(String uuidCxn);//JSA01

    Mensaje getCampoDIMPorClave(String clave, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01
}
