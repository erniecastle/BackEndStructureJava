/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ExperienciaLaboralExterna;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ServicioExperienciaLaboralExternaIF {

    /*ExperienciaLaboralExterna*/
    public Mensaje agregar(ExperienciaLaboralExterna entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ExperienciaLaboralExterna entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ExperienciaLaboralExterna entity, String uuidCxn);

    /*List<ExperienciaLaboralExterna>*/
    public Mensaje getExperienciaLaboralExternaAll(String uuidCxn);

    /*ExperienciaLaboralExterna*/
    public Mensaje getExperienciaLaboralExternaPorId(Long id, String uuidCxn);

    /*List<ExperienciaLaboralExterna>*/
    public Mensaje getExperienciaLaboralExternaPorEmpleados(Empleados b, String uuidCxn);

    /*List<ExperienciaLaboralExterna>*/
    public Mensaje getExperienciaLaboralExternaPorIDEmpleado(Long clave, String uuidCxn);

    /*boolean*/
    public Mensaje EliminaExperienciaLaboralExternaPorEmpleado(Empleados empleado, String uuidCxn);

}
