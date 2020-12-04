/**
 * @author: Ernesto Castillo Fecha de Creación: 15/03/2011 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio Estados
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Estados;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioEstadosIF {

    /*Estados*/
    Mensaje agregar(Estados entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Estados entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Estados entity, String uuidCxn);

    /*List<Estados>*/
    Mensaje getEstadosAll(String uuidCxn);

    /*List<Estados>*/
    Mensaje getEstadosPorPais(String clavePais, String uuidCxn);

    /*Estados*/
    Mensaje getEstadosPorClave(String clave, String uuidCxn);//AAP01

    /*List<Estados>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<Estados>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor , String uuidCxn);

    /*List<Estados>*/
    Mensaje saveDeleteEstados(List<Estados> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    
}
