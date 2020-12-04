/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 15/03/2011
 * Compañía: Exito Software.
 * Descripción del programa: Interface para servicio cruce
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01
 * Autor: Abraham Daniel Arjona Peraza
 * Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Paises;
import java.util.List;

public interface ServicioPaisesIF {

    /*Paises*/
    Mensaje agregar(Paises entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Paises entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Paises entity, String uuidCxn);

    /*List<Paises>*/
    Mensaje getPaisAll(String uuidCxn);

    /*Paises*/
    Mensaje getPaisPorClave(String clave, String uuidCxn);//AAP01

    /*List<Paises>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<Paises>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<Paises>*/
    Mensaje saveDeletePaises(List<Paises> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
