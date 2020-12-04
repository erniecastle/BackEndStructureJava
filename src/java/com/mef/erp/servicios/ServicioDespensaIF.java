/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Despensa;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;

/**
 *
 * @author daniel
 */
public interface ServicioDespensaIF {
    
    /*Despensa*/
    public Mensaje agregar(Despensa entity,String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Despensa entity,String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Despensa entity,String uuidCxn);

    /*List<Despensa>*/
    public Mensaje getDespensaAll(String uuidCxn);

    /*Despensa*/
    public Mensaje getDespensaPorClave(Date clave,String uuidCxn);
    
  
}
