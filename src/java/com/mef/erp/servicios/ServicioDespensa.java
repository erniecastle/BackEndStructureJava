/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.DespensaDAO;
import com.mef.erp.modelo.entidad.Despensa;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;

/**
 *
 * @author daniel
 */
public class ServicioDespensa implements ServicioDespensaIF {

    private DespensaDAO despensaDAO;
  

    public DespensaDAO getDespensaDAO() {
        return despensaDAO;
    }

    public void setDespensaDAO(DespensaDAO despensaDAO) {
        this.despensaDAO = despensaDAO;
    }

    public Mensaje agregar(Despensa entity, String uuidCxn) {
       
        return despensaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Despensa entity, String uuidCxn) {
    
        return despensaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Despensa entity, String uuidCxn) {
      
        return despensaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getDespensaAll(String uuidCxn) {
   
        return despensaDAO.getDespensaAll(uuidCxn);
    }

    public Mensaje getDespensaPorClave(Date clave, String uuidCxn) {
       
        return despensaDAO.getDespensaPorClave(clave, uuidCxn);
    }

}
