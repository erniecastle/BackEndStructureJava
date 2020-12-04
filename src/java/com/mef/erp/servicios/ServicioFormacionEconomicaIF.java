/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.FormacionEconomica;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ServicioFormacionEconomicaIF {

    /*FormacionEconomica*/
    public Mensaje agregar(FormacionEconomica entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(FormacionEconomica entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(FormacionEconomica entity, String uuidCxn);

    /*List<FormacionEconomica>*/
    public Mensaje getFormacionEconomicaAll(String uuidCxn);

    /*FormacionEconomica*/
    public Mensaje getFormacionEconomicaPorId(Long id, String uuidCxn);

    /*List<FormacionEconomica>*/
    public Mensaje getFormacionEconomicaPorEmpleados(Empleados b, String uuidCxn);

    /*List<FormacionEconomica>*/
    public Mensaje getFormacionEconomicaPorIDEmpleado(Long clave, String uuidCxn);

    /*boolean*/
    public Mensaje EliminaFormacionEconomicaPorEmpleado(Empleados empleado, String uuidCxn);

}
