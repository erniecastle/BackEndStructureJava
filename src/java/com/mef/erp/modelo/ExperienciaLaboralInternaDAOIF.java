/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.ExperienciaLaboralInterna;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ExperienciaLaboralInternaDAOIF {

    public Mensaje agregar(ExperienciaLaboralInterna entity, String uuidCxn);

    public Mensaje actualizar(ExperienciaLaboralInterna entity, String uuidCxn);

    public Mensaje eliminar(ExperienciaLaboralInterna entity, String uuidCxn);

    public Mensaje getExperienciaLaboralInternaAll(String uuidCxn);

    public Mensaje getExperienciaLaboralInternaPorId(Long id, String uuidCxn);

    public Mensaje getExperienciaLaboralInternaPorEmpleados(Empleados e, String uuidCxn);

    public Mensaje getExperienciaLaboralInternaPorIDEmpleado(Long clave, String uuidCxn);

    public Mensaje EliminaExperienciaLaboralInternaPorEmpleado(Empleados empleado, String uuidCxn);
}
