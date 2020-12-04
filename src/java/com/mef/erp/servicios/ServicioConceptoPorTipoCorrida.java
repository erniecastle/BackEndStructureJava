/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConceptoPorTipoCorridaDAO;
import com.mef.erp.modelo.entidad.ConceptoPorTipoCorrida;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoCorrida;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioConceptoPorTipoCorrida implements ServicioConceptoPorTipoCorridaIF {

    private ConceptoPorTipoCorridaDAO configConcepPorTipCorridaDAO;

    public Mensaje agregar(ConceptoPorTipoCorrida entity, String uuidCxn) {
        return configConcepPorTipCorridaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ConceptoPorTipoCorrida entity, String uuidCxn) {
        return configConcepPorTipCorridaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ConceptoPorTipoCorrida entity, String uuidCxn) {
        return configConcepPorTipCorridaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getConceptoPorTipoCorridaAll(String uuidCxn) {
        return configConcepPorTipCorridaDAO.getConceptoPorTipoCorridaAll(uuidCxn);
    }

    public Mensaje getConceptoPorTipoCorrida(TipoCorrida tipoCorrida, String uuidCxn) {
        return configConcepPorTipCorridaDAO.getConceptoPorTipoCorrida(tipoCorrida, uuidCxn);
    }

    public Mensaje getConceptoPorTipoCorridaMostrarActivo(String tipoCorrida, String uuidCxn) {
        return configConcepPorTipCorridaDAO.getConceptoPorTipoCorridaMostrarActivo(tipoCorrida, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return configConcepPorTipCorridaDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return configConcepPorTipCorridaDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteConceptoPorTipoCorrida(List<ConceptoPorTipoCorrida> entitysCambios, Object[] eliminados, String uuidCxn) {
        return configConcepPorTipCorridaDAO.saveDeleteConceptoPorTipoCorrida(entitysCambios, eliminados, uuidCxn);
    }

    public ConceptoPorTipoCorridaDAO getConceptoPorTipoCorridaDAO() {
        return configConcepPorTipCorridaDAO;
    }

    public void setConceptoPorTipoCorridaDAO(ConceptoPorTipoCorridaDAO configConcepPorTipCorridaDAO) {
        this.configConcepPorTipCorridaDAO = configConcepPorTipCorridaDAO;
    }
}
