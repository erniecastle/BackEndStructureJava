/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ElementoExternoDAO;
import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.ElementoExterno;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public class ServicioElementoExterno implements ServicioElementoExternoIF {

    private ElementoExternoDAO elementoExternoDAO;
  

    public Mensaje getElementoExAll(String uuidCxn) {
        return elementoExternoDAO.getElementoExAll(uuidCxn);
    }

    public Mensaje agregar(ElementoExterno entity, String uuidCxn) {
        return elementoExternoDAO.agregar(entity, uuidCxn);
    }

    public Mensaje eliminar(ElementoExterno entity, String uuidCxn) {
        return elementoExternoDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje actualizar(ElementoExterno entity, String uuidCxn) {
        return elementoExternoDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje getElementoExPorContenedor(Contenedor entity, String uuidCxn) {
        return elementoExternoDAO.getElementoExPorContenedor(entity, uuidCxn);
    }

    public Mensaje SaveElementoExterno(List<ElementoExterno> e, String uuidCxn) {
        return elementoExternoDAO.SaveElementoExterno(e, uuidCxn);
    }

    public Mensaje DeleteElementoExterno(List<ElementoExterno> e, String uuidCxn) {
        return elementoExternoDAO.DeleteElementoExterno(e, uuidCxn);
    }

    public ElementoExternoDAO getElementoExternoDAO() {
        return elementoExternoDAO;
    }

    public void setElementoExternoDAO(ElementoExternoDAO elementoExternoDAO) {
        this.elementoExternoDAO = elementoExternoDAO;
    }
}
