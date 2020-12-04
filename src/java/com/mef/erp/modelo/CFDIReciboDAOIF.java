/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.cfdi.CFDIRecibo;
import java.util.List;

public interface CFDIReciboDAOIF {

    Mensaje agregar(CFDIRecibo entity, String uuidCxn);

    Mensaje actualizar(CFDIRecibo entity, String uuidCxn);

    Mensaje eliminar(CFDIRecibo entity, String uuidCxn);

    Mensaje getCFDIReciboAll(String uuidCxn);

    Mensaje saveDeleteCFDIRecibo(List<CFDIRecibo> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);
}
