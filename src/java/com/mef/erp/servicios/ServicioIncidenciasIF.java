/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Incidencias;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ServicioIncidenciasIF {

    /*Incidencias*/
    public Mensaje agregar(Incidencias entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Incidencias entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Incidencias entity, String uuidCxn);

    /*List<Incidencias>*/
    public Mensaje getIncidenciasAll(String uuidCxn);

}
