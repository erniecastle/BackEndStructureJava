/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.cfdi.CFDIRecibo;
import java.util.List;

public interface ServicioCFDIReciboIF {

    /*CFDIRecibo*/
    Mensaje agregar(CFDIRecibo entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(CFDIRecibo entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(CFDIRecibo entity, String uuidCxn);

    /*List<CFDIRecibo>*/
    Mensaje getCFDIReciboAll(String uuidCxn);

    /*List<CFDIRecibo>*/
    Mensaje saveDeleteCFDIRecibo(List<CFDIRecibo> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    /*List<CFDIRecibo>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);
    
}
