/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.IncidenciasDAO;
import com.mef.erp.modelo.entidad.Incidencias;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public class ServicioIncidencias implements ServicioIncidenciasIF {

    private IncidenciasDAO incidenciasDAO;

    public IncidenciasDAO getIncidenciasDAO() {

        return incidenciasDAO;
    }

    public void setIncidenciasDAO(IncidenciasDAO incidenciasDAO) {
        this.incidenciasDAO = incidenciasDAO;
    }

    public Mensaje agregar(Incidencias entity, String uuidCxn) {

        return incidenciasDAO.agregar(entity,uuidCxn);
    }

    public Mensaje actualizar(Incidencias entity, String uuidCxn) {

        return incidenciasDAO.actualizar(entity,uuidCxn);
    }

    public Mensaje eliminar(Incidencias entity, String uuidCxn) {

        return incidenciasDAO.eliminar(entity,uuidCxn);
    }

    public Mensaje getIncidenciasAll(String uuidCxn) {

        return incidenciasDAO.getIncidenciasAll(uuidCxn);
    }

}
