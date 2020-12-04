/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.ExperienciaLaboralExterna;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ExperienciaLaboralExternaDAOIF {

    public Mensaje agregar(ExperienciaLaboralExterna entity, String uuidCxn);

    public Mensaje actualizar(ExperienciaLaboralExterna entity, String uuidCxn);

    public Mensaje eliminar(ExperienciaLaboralExterna entity, String uuidCxn);

    public Mensaje getExperienciaLaboralExternaAll(String uuidCxn);

    public Mensaje getExperienciaLaboralExternaPorId(Long id, String uuidCxn);

    public Mensaje getExperienciaLaboralExternaPorEmpleados(Empleados e, String uuidCxn);

    public Mensaje getExperienciaLaboralExternaPorIDEmpleado(Long clave, String uuidCxn);

    public Mensaje EliminaExperienciaLaboralExternaPorEmpleado(Empleados empleado, String uuidCxn);
}
