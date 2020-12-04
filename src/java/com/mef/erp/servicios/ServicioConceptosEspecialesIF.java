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
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ConceptosEspeciales;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoConceptosEspeciales;
import java.util.List;

public interface ServicioConceptosEspecialesIF {

    /*ConceptosEspeciales*/
    public Mensaje agregar(ConceptosEspeciales entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ConceptosEspeciales entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ConceptosEspeciales entity, String uuidCxn);

    /*List<ConceptosEspeciales>*/
    public Mensaje getConceptosEspecialesPorTipo(TipoConceptosEspeciales claveTipo, String uuidCxn);

    /*boolean*/
    public Mensaje getExisteConceptosEspecialesPorTipoYconcepto(String claveConceptoDeNomina, TipoConceptosEspeciales claveTipo, String uuidCxn);

    /*boolean*/
    public Mensaje saveDeleteConceptosEspeciales(List<ConceptosEspeciales> AgreModif, Object[] clavesDelete, String uuidCxn);

    /*List<String> */
    public Mensaje getConceptosNominaClavesPorTipo(TipoConceptosEspeciales claveTipo, String uuidCxn);

    /*List<ConceptosEspeciales>*/
    public Mensaje getConceptosEspecialesAll(String uuidCxn);

}
