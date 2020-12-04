/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 13/04/2011
 * Compañía: Exito Software.
 * Descripción del programa: Interface para servicio cruce
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
import com.mef.erp.modelo.entidad.Primas;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import java.util.List;

public interface ServicioRegistroPatronalIF {

    /*RegistroPatronal*/
    Mensaje agregar(RegistroPatronal entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(RegistroPatronal entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(RegistroPatronal entity, String uuidCxn);

    /*List<RegistroPatronal>*/
    Mensaje getRegistroPatronalAll(String claveRazonesSocial, String uuidCxn);

    /*RegistroPatronal*/
    Mensaje getRegistroPatronalPorClave(String clave, String claveRazonesSocial, String uuidCxn);

    /*RegistroPatronal*/
    Mensaje SaveRegistroPatronal(List<Primas> agrega, Object[] eliminados, RegistroPatronal entity, String uuidCxn);

    /*boolean*/
    Mensaje UpdateRegistroPatronal(List<Primas> agrega, Object[] eliminados, RegistroPatronal entity, String uuidCxn);

    /*boolean*/
    Mensaje DeleteRegistroPatronal(RegistroPatronal entity, String uuidCxn);

    /*List<RegistroPatronal>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);

    /*List<RegistroPatronal>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

	/*ArrayList*/
    Mensaje agregarListaRegistrosPatronales(List<RegistroPatronal> cambios, List<RegistroPatronal> temporales, List<Primas> cambioprima,
            Object[] clavesDelete, Object[] clavesPrimasDelete, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*boolean*/
    Mensaje deleteListClavesRegistroPatronal(Object[] valores, String uuidCxn);
    
}
