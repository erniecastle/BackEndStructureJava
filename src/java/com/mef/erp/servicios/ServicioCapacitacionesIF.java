/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Capacitaciones;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ServicioCapacitacionesIF {
    
    /*Capacitaciones*/
    public Mensaje agregar(Capacitaciones entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Capacitaciones entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Capacitaciones entity, String uuidCxn);

    /*List<Capacitaciones>*/
    public Mensaje getCapacitacionesAll(String uuidCxn);

    /*Capacitaciones*/
    public Mensaje getCapacitacionesPorId(Long id, String uuidCxn);

    /*List<Capacitaciones>*/
    public Mensaje getCapacitacionesPorEmpleados(Empleados b, String uuidCxn);

    /*List<Capacitaciones>*/
    public Mensaje getCapacitacionesPorIDEmpleado(Long clave, String uuidCxn);
    
    /*boolean*/
    public Mensaje EliminaCapacitacionesPorEmpleado(Empleados empleado, String uuidCxn);

}
