/**
 * @author: Armando Fecha de Creación: 20/09/2011 Compañía:Macropro Descripción
 * del programa: Este servicio es utilizado para la razonSocial existentes en la
 * MEFMaster
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:24/11/2012 Descripción:Se agrego el
 * metodo getRazonPorClave
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.RazonSocialDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonSocial;

public class ServicioRazonSocial implements ServicioRazonSocialIF {

    private RazonSocialDAO razonSocialDAO;

    public Mensaje agregar(RazonSocial entity, String uuidCxnMaestra) {
        return razonSocialDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(RazonSocial entity, String uuidCxnMaestra) {
        return razonSocialDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(RazonSocial entity, String uuidCxnMaestra) {
        return razonSocialDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje getRazonSocialAll(String uuidCxnMaestra) {
        return getRazonSocialDAO().getRazonSocialAll(uuidCxnMaestra);
    }

    public Mensaje getRazonPorClave(String claves, String uuidCxnMaestra) {
        return razonSocialDAO.getRazonPorClave(claves, uuidCxnMaestra);
    }

    public RazonSocialDAO getRazonSocialDAO() {
        return razonSocialDAO;
    }

    public void setRazonSocialDAO(RazonSocialDAO RazonSocialDAO) {
        this.razonSocialDAO = RazonSocialDAO;
    }
}
