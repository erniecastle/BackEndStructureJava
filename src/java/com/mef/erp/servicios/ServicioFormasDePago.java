/**
 * @author: Ernesto Castillo Fecha de Creación: 25/06/2012 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio Formas de pago
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.FormasDePagoDAO;
import com.mef.erp.modelo.entidad.Mensaje;

public class ServicioFormasDePago implements ServicioFormasDePagoIF {

    private FormasDePagoDAO formasDePagoDAO;
  

    public Mensaje getFormasDePagoAll(String uuidCxn) {
        return formasDePagoDAO.getFormasDePagoAll(uuidCxn);
    }

    public FormasDePagoDAO getFormasDePagoDAO() {
        return formasDePagoDAO;
    }

    public void setFormasDePagoDAO(FormasDePagoDAO formasDePagoDAO) {

        this.formasDePagoDAO = formasDePagoDAO;
    }
}
