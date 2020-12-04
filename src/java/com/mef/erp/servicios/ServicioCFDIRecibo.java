/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CFDIReciboDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.cfdi.CFDIRecibo;
import java.util.List;

public class ServicioCFDIRecibo implements ServicioCFDIReciboIF {

    private CFDIReciboDAO cfdiReciboDAO;

    public Mensaje agregar(CFDIRecibo entity, String uuidCxn) {
        return getCfdiReciboDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(CFDIRecibo entity, String uuidCxn) {
        return getCfdiReciboDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(CFDIRecibo entity, String uuidCxn) {
        return getCfdiReciboDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getCFDIReciboAll(String uuidCxn) {
        return getCfdiReciboDAO().getCFDIReciboAll(uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return getCfdiReciboDAO().consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return getCfdiReciboDAO().existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteCFDIRecibo(List<CFDIRecibo> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return getCfdiReciboDAO().saveDeleteCFDIRecibo(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public CFDIReciboDAO getCfdiReciboDAO() {
        return cfdiReciboDAO;
    }

    public void setCfdiReciboDAO(CFDIReciboDAO cfdiReciboDAO) {
        this.cfdiReciboDAO = cfdiReciboDAO;
    }

}
