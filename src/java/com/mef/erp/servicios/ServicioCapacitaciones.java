/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CapacitacionesDAO;
import com.mef.erp.modelo.entidad.Capacitaciones;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public class ServicioCapacitaciones implements ServicioCapacitacionesIF {

    private CapacitacionesDAO capacitacionesDAO;

    public Mensaje agregar(Capacitaciones entity, String uuidCxn) {
        return capacitacionesDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Capacitaciones entity, String uuidCxn) {
        return capacitacionesDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Capacitaciones entity, String uuidCxn) {
        return capacitacionesDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getCapacitacionesAll(String uuidCxn) {
        return capacitacionesDAO.getCapacitacionesAll(uuidCxn);
    }

    public Mensaje getCapacitacionesPorId(Long id, String uuidCxn) {
        return capacitacionesDAO.getCapacitacionesPorId(id, uuidCxn);
    }

    public Mensaje getCapacitacionesPorEmpleados(Empleados e, String uuidCxn) {
        return capacitacionesDAO.getCapacitacionesPorEmpleados(e, uuidCxn);
    }

    public Mensaje getCapacitacionesPorIDEmpleado(Long clave, String uuidCxn) {
        return capacitacionesDAO.getCapacitacionesPorIDEmpleado(clave, uuidCxn);
    }

    public Mensaje EliminaCapacitacionesPorEmpleado(Empleados banco, String uuidCxn) {
        return capacitacionesDAO.EliminaCapacitacionesPorEmpleado(banco, uuidCxn);
    }

    public CapacitacionesDAO getCapacitacionesDAO() {
        return capacitacionesDAO;
    }

    public void setCapacitacionesDAO(CapacitacionesDAO familiaresDAO) {
        this.capacitacionesDAO = familiaresDAO;
    }

}
