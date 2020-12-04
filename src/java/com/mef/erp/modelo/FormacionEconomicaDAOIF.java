/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.FormacionEconomica;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface FormacionEconomicaDAOIF {
    public Mensaje agregar(FormacionEconomica entity , String uuidCxn);

    public Mensaje actualizar(FormacionEconomica entity , String uuidCxn);

    public Mensaje eliminar(FormacionEconomica entity , String uuidCxn);

    public Mensaje getFormacionEconomicaAll(String uuidCxn);

    public Mensaje getFormacionEconomicaPorId(Long id, String uuidCxn);

    public Mensaje getFormacionEconomicaPorEmpleados(Empleados b , String uuidCxn);

    public Mensaje getFormacionEconomicaPorIDEmpleado(Long clave , String uuidCxn);

    public Mensaje EliminaFormacionEconomicaPorEmpleado(Empleados empleado , String uuidCxn);
}
