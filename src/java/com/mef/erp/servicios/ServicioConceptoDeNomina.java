/**
 * @author: Victor Lopez Fecha de Creación: 26/09/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO conceptos de nomina, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConceptoDeNominaDAO;
import com.mef.erp.modelo.entidad.ConceptoDeNomina;
import com.mef.erp.modelo.entidad.Mensaje;

public class ServicioConceptoDeNomina implements ServicioConceptoDeNominaIF {

    private ConceptoDeNominaDAO conceptoDeNominaDAO;

    public Mensaje agregar(ConceptoDeNomina entity, String uuidCxn) {//JSA01
        return conceptoDeNominaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ConceptoDeNomina entity, String uuidCxn) {//JSA01
        return conceptoDeNominaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ConceptoDeNomina entity, String uuidCxn) {//JSA01
        return conceptoDeNominaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getConceptoDeNominaAll(String uuidCxn) {//JSA01
        return conceptoDeNominaDAO.getConceptoDeNominaAll(uuidCxn);
    }

    public Mensaje getConceptoDeNominaPorClave(String clave, String uuidCxn) {//JSA01
        return conceptoDeNominaDAO.getConceptoDeNominaPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return conceptoDeNominaDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return conceptoDeNominaDAO.existeDato(campo, valor, uuidCxn);
    }

    public ConceptoDeNominaDAO getConceptoDeNominaDAO() {
        return conceptoDeNominaDAO;
    }

    public void setConceptoDeNominaDAO(ConceptoDeNominaDAO conceptoDeNominaDAO) {
        this.conceptoDeNominaDAO = conceptoDeNominaDAO;
    }
}
