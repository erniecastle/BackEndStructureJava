/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Documentacion;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ServicioDocumentacionIF {

    /*Documentacion*/
    public Mensaje agregar(Documentacion entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Documentacion entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Documentacion entity, String uuidCxn);

    /*List<Documentacion>*/
    public Mensaje getDocumentacionAll(String uuidCxn);

    /*Documentacion*/
    public Mensaje getDocumentacionPorId(Long id, String uuidCxn);

    /*List<Documentacion>*/
    public Mensaje getDocumentacionPorEmpleados(Empleados e, String uuidCxn);

    /*List<Documentacion>*/
    public Mensaje getDocumentacionPorIDEmpleado(Long clave, String uuidCxn);

    /*boolean*/
    public Mensaje EliminaDocumentacionPorEmpleado(Empleados empleado, String uuidCxn);

    
}
