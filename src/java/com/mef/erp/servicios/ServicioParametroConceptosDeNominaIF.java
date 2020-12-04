/**
 * @author: Victor Lopez
 * Fecha de Creación: 27/09/2011
 * Compañía: MacroPro.
 * Descripción del programa: interface servicio parametro de nomina
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
import com.mef.erp.modelo.entidad.ParaConcepDeNom;
import java.util.List;

public interface ServicioParametroConceptosDeNominaIF {

    /*ParaConcepDeNom*/
    Mensaje agregar(ParaConcepDeNom entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(ParaConcepDeNom entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(ParaConcepDeNom entity, String uuidCxn);

    /*List<ParaConcepDeNom>*/
    Mensaje getParametroConceptosDeNominaAll(String uuidCxn);

    /*ParaConcepDeNom*/
    Mensaje getParametroConceptosDeNominaPorClave(String clave, String uuidCxn);

    /*List<ParaConcepDeNom>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<ParaConcepDeNom>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<ParaConcepDeNom>*/
    Mensaje agregarListaParametroConceptosDeNomina(List<ParaConcepDeNom> entitys, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn);
    
}
