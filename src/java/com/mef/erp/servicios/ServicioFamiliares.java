/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.FamiliaresDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Familiares;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public class ServicioFamiliares implements ServicioFamiliaresIF {

    private FamiliaresDAO familiaresDAO;
  

    public Mensaje agregar(Familiares entity, String uuidCxn) {//JSA01
        return familiaresDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Familiares entity, String uuidCxn) {//JSA01
        return familiaresDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Familiares entity, String uuidCxn) {//JSA01
        return familiaresDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getFamiliaresAll(String uuidCxn) {//JSA01
        return familiaresDAO.getFamiliaresAll(uuidCxn);
    }

    public Mensaje getFamiliaresPorId(Long id, String uuidCxn) {//JSA01
        return familiaresDAO.getFamiliaresPorId(id, uuidCxn);
    }

    public Mensaje getFamiliaresPorEmpleados(Empleados b, String uuidCxn) {//JSA01
        return familiaresDAO.getFamiliaresPorEmpleados(b, uuidCxn);
    }

    public Mensaje getFamiliaresPorIDEmpleado(Long clave, String uuidCxn) {//JSA01
        return familiaresDAO.getFamiliaresPorIDEmpleado(clave, uuidCxn);
    }

    public Mensaje EliminaFamiliaresPorEmpleado(Empleados banco, String uuidCxn) {//JSA01
        return familiaresDAO.EliminaFamiliaresPorEmpleado(banco, uuidCxn);
    }

    public FamiliaresDAO getFamiliaresDAO() {
        return familiaresDAO;
    }

    public void setFamiliaresDAO(FamiliaresDAO familiaresDAO) {
        this.familiaresDAO = familiaresDAO;
    }

}
