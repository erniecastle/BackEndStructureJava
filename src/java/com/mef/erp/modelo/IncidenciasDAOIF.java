/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Incidencias;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface IncidenciasDAOIF extends GenericDAO<Incidencias, Long> {

    public Mensaje agregar(Incidencias entity, String uuidCxn);

    public Mensaje actualizar(Incidencias entity, String uuidCxn);

    public Mensaje eliminar(Incidencias entity, String uuidCxn);

    public Mensaje getIncidenciasAll(String uuidCxn);
}
