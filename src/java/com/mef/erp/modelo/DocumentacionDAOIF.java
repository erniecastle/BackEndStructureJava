/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Documentacion;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface DocumentacionDAOIF {

    public Mensaje agregar(Documentacion entity, String uuidCxn);

    public Mensaje actualizar(Documentacion entity, String uuidCxn);

    public Mensaje eliminar(Documentacion entity, String uuidCxn);

    public Mensaje getDocumentacionAll(String uuidCxn);

    public Mensaje getDocumentacionPorId(Long id, String uuidCxn);

    public Mensaje getDocumentacionPorEmpleados(Empleados e, String uuidCxn);

    public Mensaje getDocumentacionPorIDEmpleado(Long clave, String uuidCxn);

    public Mensaje EliminaDocumentacionPorEmpleado(Empleados empleado, String uuidCxn);
}
