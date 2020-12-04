/**
 * @author: Jose Armando Fecha de Creación: 26/08/2012 Compañía: Macropro
 * Descripción del programa: Conceptos especiales se creo con el fin de definir
 * cuales conceptos son de un tipo en especifico.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConceptosEspecialesDAO;
import com.mef.erp.modelo.entidad.ConceptosEspeciales;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoConceptosEspeciales;
import java.util.List;

public class ServicioConceptosEspeciales implements ServicioConceptosEspecialesIF {

    private ConceptosEspecialesDAO conceptosEspecialesDAO;

    public Mensaje agregar(ConceptosEspeciales entity, String uuidCxn) {
        return conceptosEspecialesDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ConceptosEspeciales entity, String uuidCxn) {
        return conceptosEspecialesDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ConceptosEspeciales entity, String uuidCxn) {
        return conceptosEspecialesDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getConceptosEspecialesPorTipo(TipoConceptosEspeciales claveTipo, String uuidCxn) {
        return conceptosEspecialesDAO.getConceptosEspecialesPorTipo(claveTipo, uuidCxn);
    }

    public Mensaje getExisteConceptosEspecialesPorTipoYconcepto(String claveConceptoDeNomina, TipoConceptosEspeciales claveTipo, String uuidCxn) {
        return conceptosEspecialesDAO.getExisteConceptosEspecialesPorTipoYconcepto(claveConceptoDeNomina, claveTipo, uuidCxn);
    }

    public Mensaje saveDeleteConceptosEspeciales(List<ConceptosEspeciales> AgreModif, Object[] clavesDelete, String uuidCxn) {
        return conceptosEspecialesDAO.saveDeleteConceptosEspeciales(AgreModif, clavesDelete, uuidCxn);
    }

    public Mensaje getConceptosNominaClavesPorTipo(TipoConceptosEspeciales claveTipo, String uuidCxn) {
        return conceptosEspecialesDAO.getConceptosNominaClavesPorTipo(claveTipo, uuidCxn);
    }

    public Mensaje getConceptosEspecialesAll(String uuidCxn) {
        return conceptosEspecialesDAO.getConceptosEspecialesAll(uuidCxn);
    }

    public ConceptosEspecialesDAO getConceptosEspecialesDAO() {
        return conceptosEspecialesDAO;
    }

    public void setConceptosEspecialesDAO(ConceptosEspecialesDAO conceptosEspecialesDAO) {
        this.conceptosEspecialesDAO = conceptosEspecialesDAO;
    }
}
