/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.ExperienciaLaboralInterna;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ServicioExperienciaLaboralInternaIF {

    /*ExperienciaLaboralInterna*/
    public Mensaje agregar(ExperienciaLaboralInterna entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ExperienciaLaboralInterna entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ExperienciaLaboralInterna entity, String uuidCxn);

    /*List<ExperienciaLaboralInterna>*/
    public Mensaje getExperienciaLaboralInternaAll(String uuidCxn);

    /*ExperienciaLaboralInterna*/
    public Mensaje getExperienciaLaboralInternaPorId(Long id, String uuidCxn);

    /*List<ExperienciaLaboralInterna>*/
    public Mensaje getExperienciaLaboralInternaPorEmpleados(Empleados b, String uuidCxn);

    /*List<ExperienciaLaboralInterna>*/
    public Mensaje getExperienciaLaboralInternaPorIDEmpleado(Long clave, String uuidCxn);

    /*boolean*/
    public Mensaje EliminaExperienciaLaboralInternaPorEmpleado(Empleados empleado, String uuidCxn);

 
}
