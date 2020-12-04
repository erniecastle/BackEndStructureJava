/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ExperienciaLaboralExternaDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.ExperienciaLaboralExterna;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioExperienciaLaboralExterna implements ServicioExperienciaLaboralExternaIF {

    private ExperienciaLaboralExternaDAO experienciaLaboralExternaDAO;

  

    public Mensaje agregar(ExperienciaLaboralExterna entity, String uuidCxn) {
        return experienciaLaboralExternaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ExperienciaLaboralExterna entity, String uuidCxn) {
        return experienciaLaboralExternaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ExperienciaLaboralExterna entity, String uuidCxn) {
        return experienciaLaboralExternaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getExperienciaLaboralExternaAll(String uuidCxn) {
        return experienciaLaboralExternaDAO.getExperienciaLaboralExternaAll(uuidCxn);
    }

    public Mensaje getExperienciaLaboralExternaPorId(Long id, String uuidCxn) {
        return experienciaLaboralExternaDAO.getExperienciaLaboralExternaPorId(id, uuidCxn);
    }

    public Mensaje getExperienciaLaboralExternaPorEmpleados(Empleados e, String uuidCxn) {
        return experienciaLaboralExternaDAO.getExperienciaLaboralExternaPorEmpleados(e, uuidCxn);
    }

    public Mensaje getExperienciaLaboralExternaPorIDEmpleado(Long clave, String uuidCxn) {
        return experienciaLaboralExternaDAO.getExperienciaLaboralExternaPorIDEmpleado(clave, uuidCxn);
    }

    public Mensaje EliminaExperienciaLaboralExternaPorEmpleado(Empleados banco, String uuidCxn) {
        return experienciaLaboralExternaDAO.EliminaExperienciaLaboralExternaPorEmpleado(banco, uuidCxn);
    }

    public ExperienciaLaboralExternaDAO getExperienciaLaboralExternaDAO() {
        return experienciaLaboralExternaDAO;
    }

    public void setExperienciaLaboralExternaDAO(ExperienciaLaboralExternaDAO familiaresDAO) {
        this.experienciaLaboralExternaDAO = familiaresDAO;
    }

}
