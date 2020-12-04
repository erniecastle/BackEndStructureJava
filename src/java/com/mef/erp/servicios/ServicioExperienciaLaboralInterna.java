/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ExperienciaLaboralInternaDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.ExperienciaLaboralInterna;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioExperienciaLaboralInterna implements ServicioExperienciaLaboralInternaIF {

    private ExperienciaLaboralInternaDAO experienciaLaboralInternaDAO;
   

    public Mensaje agregar(ExperienciaLaboralInterna entity, String uuidCxn) {
        return experienciaLaboralInternaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ExperienciaLaboralInterna entity, String uuidCxn) {
        return experienciaLaboralInternaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ExperienciaLaboralInterna entity, String uuidCxn) {
        return experienciaLaboralInternaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getExperienciaLaboralInternaAll(String uuidCxn) {
        return experienciaLaboralInternaDAO.getExperienciaLaboralInternaAll(uuidCxn);
    }

    public Mensaje getExperienciaLaboralInternaPorId(Long id, String uuidCxn) {
     
        return experienciaLaboralInternaDAO.getExperienciaLaboralInternaPorId(id, uuidCxn);
    }

    public Mensaje getExperienciaLaboralInternaPorEmpleados(Empleados e, String uuidCxn) {
        return experienciaLaboralInternaDAO.getExperienciaLaboralInternaPorEmpleados(e, uuidCxn);
    }

    public Mensaje getExperienciaLaboralInternaPorIDEmpleado(Long clave, String uuidCxn) {
        return experienciaLaboralInternaDAO.getExperienciaLaboralInternaPorIDEmpleado(clave, uuidCxn);
    }

    public Mensaje EliminaExperienciaLaboralInternaPorEmpleado(Empleados banco, String uuidCxn) {
        return experienciaLaboralInternaDAO.EliminaExperienciaLaboralInternaPorEmpleado(banco, uuidCxn);
    }

    public ExperienciaLaboralInternaDAO getExperienciaLaboralInternaDAO() {
        return experienciaLaboralInternaDAO;
    }

    public void setExperienciaLaboralInternaDAO(ExperienciaLaboralInternaDAO familiaresDAO) {
        this.experienciaLaboralInternaDAO = familiaresDAO;
    }
}
