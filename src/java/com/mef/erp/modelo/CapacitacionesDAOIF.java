/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Capacitaciones;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface CapacitacionesDAOIF {
    public Mensaje agregar(Capacitaciones entity, String uuidCxn);

    public Mensaje actualizar(Capacitaciones entity, String uuidCxn);

    public Mensaje eliminar(Capacitaciones entity, String uuidCxn);

    public Mensaje getCapacitacionesAll(String uuidCxn);

    public Mensaje getCapacitacionesPorId(Long id, String uuidCxn);

    public Mensaje getCapacitacionesPorEmpleados(Empleados e, String uuidCxn);

    public Mensaje getCapacitacionesPorIDEmpleado(Long clave, String uuidCxn);

    public Mensaje EliminaCapacitacionesPorEmpleado(Empleados empleado, String uuidCxn);
}
