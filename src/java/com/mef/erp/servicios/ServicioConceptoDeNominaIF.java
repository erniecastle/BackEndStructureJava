/**
 * @author: Victor Lopez Fecha de Creación: 25/09/2011 Compañía: MacroPro.
 * Descripción del programa: interface servicio conceptos de nomina
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ConceptoDeNomina;
import com.mef.erp.modelo.entidad.Mensaje;

public interface ServicioConceptoDeNominaIF {

    /*ConceptoDeNomina*/
    Mensaje agregar(ConceptoDeNomina entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(ConceptoDeNomina entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(ConceptoDeNomina entity, String uuidCxn);

    /*List<ConceptoDeNomina>*/
    Mensaje getConceptoDeNominaAll(String uuidCxn);

    /*ConceptoDeNomina*/
    Mensaje getConceptoDeNominaPorClave(String clave, String uuidCxn);

    /*List<ConceptoDeNomina>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

}
