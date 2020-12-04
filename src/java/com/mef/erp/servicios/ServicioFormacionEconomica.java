/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.FormacionEconomicaDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.FormacionEconomica;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public class ServicioFormacionEconomica implements ServicioFormacionEconomicaIF {

    private FormacionEconomicaDAO formacionEconomicaDAO;

    

    public Mensaje agregar(FormacionEconomica entity, String uuidCxn) {//JSA01
        return formacionEconomicaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(FormacionEconomica entity, String uuidCxn) {//JSA01
        return formacionEconomicaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(FormacionEconomica entity, String uuidCxn) {//JSA01
        return formacionEconomicaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getFormacionEconomicaAll(String uuidCxn) {//JSA01
        return formacionEconomicaDAO.getFormacionEconomicaAll(uuidCxn);
    }

    public Mensaje getFormacionEconomicaPorId(Long id, String uuidCxn) {//JSA01
        return formacionEconomicaDAO.getFormacionEconomicaPorId(id, uuidCxn);
    }

    public Mensaje getFormacionEconomicaPorEmpleados(Empleados b, String uuidCxn) {//JSA01
        return formacionEconomicaDAO.getFormacionEconomicaPorEmpleados(b, uuidCxn);
    }

    public Mensaje getFormacionEconomicaPorIDEmpleado(Long clave, String uuidCxn) {//JSA01
        return formacionEconomicaDAO.getFormacionEconomicaPorIDEmpleado(clave, uuidCxn);
    }

    public Mensaje EliminaFormacionEconomicaPorEmpleado(Empleados banco, String uuidCxn) {//JSA01
        return formacionEconomicaDAO.EliminaFormacionEconomicaPorEmpleado(banco, uuidCxn);
    }

    public FormacionEconomicaDAO getFormacionEconomicaDAO() {
        return formacionEconomicaDAO;
    }

    public void setFormacionEconomicaDAO(FormacionEconomicaDAO formacionEconomicaDAO) {
        this.formacionEconomicaDAO = formacionEconomicaDAO;
    }
}
