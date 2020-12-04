/**
 * @author: Jose Armando
 * Fecha de Creación: 26/08/2012
 * Compañía: Macropro
 * Descripción del programa: Conceptos especiales se creo con el fin de definir cuales
 * conceptos son de un tipo en especifico.
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

import com.mef.erp.modelo.entidad.ConceptosEspeciales;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoConceptosEspeciales;
import java.util.List;

public interface ConceptosEspecialesDAOIF extends GenericDAO<ConceptosEspeciales, String> {

    public Mensaje agregar(ConceptosEspeciales entity, String uuidCxn);

    public Mensaje actualizar(ConceptosEspeciales entity, String uuidCxn);

    public Mensaje eliminar(ConceptosEspeciales entity, String uuidCxn);

    public Mensaje getConceptosEspecialesPorTipo(TipoConceptosEspeciales claveTipo, String uuidCxn);

    public Mensaje getExisteConceptosEspecialesPorTipoYconcepto(String claveConceptoDeNomina, TipoConceptosEspeciales claveTipo, String uuidCxn);

    public Mensaje saveDeleteConceptosEspeciales(List<ConceptosEspeciales> AgreModif, Object[] clavesDelete, String uuidCxn);

    public Mensaje getConceptosNominaClavesPorTipo(TipoConceptosEspeciales claveTipo, String uuidCxn);

    public Mensaje getConceptosEspecialesAll(String uuidCxn);
}
