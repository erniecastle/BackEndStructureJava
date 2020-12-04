/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.DocumentacionDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Documentacion;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public class ServicioDocumentacion implements ServicioDocumentacionIF {

    private DocumentacionDAO documentacionDAO;

    public Mensaje agregar(Documentacion entity, String uuidCxn) {//JSA01

        return documentacionDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Documentacion entity, String uuidCxn) {//JSA01

        return documentacionDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Documentacion entity, String uuidCxn) {//JSA01

        return documentacionDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getDocumentacionAll(String uuidCxn) {//JSA01

        return documentacionDAO.getDocumentacionAll(uuidCxn);
    }

    public Mensaje getDocumentacionPorId(Long id, String uuidCxn) {//JSA01

        return documentacionDAO.getDocumentacionPorId(id, uuidCxn);
    }

    public Mensaje getDocumentacionPorEmpleados(Empleados e, String uuidCxn) {//JSA01

        return documentacionDAO.getDocumentacionPorEmpleados(e, uuidCxn);
    }

    public Mensaje getDocumentacionPorIDEmpleado(Long clave, String uuidCxn) {//JSA01

        return documentacionDAO.getDocumentacionPorIDEmpleado(clave, uuidCxn);
    }

    public Mensaje EliminaDocumentacionPorEmpleado(Empleados empleado, String uuidCxn) {//JSA01

        return documentacionDAO.EliminaDocumentacionPorEmpleado(empleado, uuidCxn);
    }

    public DocumentacionDAO getDocumentacionDAO() {
        return documentacionDAO;
    }

    public void setDocumentacionDAO(DocumentacionDAO familiaresDAO) {
        this.documentacionDAO = familiaresDAO;
    }
}
