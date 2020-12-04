/**
 * @author: Victor Lopez
 * Fecha de Creación: 25/09/2011
 * Compañía: Macropro.
 * Descripción del programa: interface Concepto de Nomina para llamados a metodos de HIBERNATE
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

import com.mef.erp.modelo.entidad.ConceptoDeNomina;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ConceptoDeNominaDAOIF {

    Mensaje agregar(ConceptoDeNomina entity, String uuidCxn);//JSA01

    Mensaje actualizar(ConceptoDeNomina entity, String uuidCxn);//JSA01

    Mensaje eliminar(ConceptoDeNomina entity, String uuidCxn);//JSA01

    Mensaje getConceptoDeNominaAll(String uuidCxn);//JSA01

    Mensaje getConceptoDeNominaPorClave(String clave, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01
    
}
