/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ElementosAplicacionDAO;
import com.mef.erp.modelo.entidad.ElementosAplicacion;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public class ServicioElementosAplicacion implements ServicioElementosAplicacionIF {

    private ElementosAplicacionDAO elementosAplicacionDAO;
 
    public Mensaje getElementosAplicacionAll(String uuidCxn) {
        return elementosAplicacionDAO.getElementosAplicacionAll(uuidCxn);
    }

    public Mensaje getElementosAplicacionHert(String uuidCxn, long nodoPadre) {
        return elementosAplicacionDAO.getElementosAplicacionHert(uuidCxn, nodoPadre);
    }

    public Mensaje getElementosAplicacionPorClave(String uuidCxn, String clave, long parentID) {
        return elementosAplicacionDAO.getElementosAplicacionPorClave(uuidCxn, clave, parentID);
    }

    public Mensaje guardarElementosAplicacion(String uuidCxn, List<ElementosAplicacion> agrega, Object[] eliminados) {
        return elementosAplicacionDAO.guardarElementosAplicacion(uuidCxn, agrega, eliminados);
    }

    public Mensaje getElementosAplicacionMaximo(String uuidCxn) {
        return elementosAplicacionDAO.getElementosAplicacionMaximo(uuidCxn);
    }

    public ElementosAplicacionDAO getElementosAplicacionDAO() {
        return elementosAplicacionDAO;
    }

    public void setElementosAplicacionDAO(ElementosAplicacionDAO ElementosAplicacionDAO) {
        this.elementosAplicacionDAO = ElementosAplicacionDAO;
    }
}
