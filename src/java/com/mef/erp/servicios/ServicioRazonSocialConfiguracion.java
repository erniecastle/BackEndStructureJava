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

import com.mef.erp.modelo.RazonSocialConfiguracionDAO;
import com.mef.erp.modelo.entidad.Mensaje;

public class ServicioRazonSocialConfiguracion implements ServicioRazonSocialConfiguracionIF {

    private RazonSocialConfiguracionDAO razonSocialConfiguracionDAO;

    public Mensaje getRazonSocialConfiguracionAll(String uuidCxnMaestra) {
        return getRazonSocialConfiguracionDAO().getRazonSocialConfiguracionAll(uuidCxnMaestra);
    }

    public Mensaje getRazonSocialConfiguracionPorUsuario(String claves, String uuidCxnMaestra) {
        return getRazonSocialConfiguracionDAO().getRazonSocialConfiguracionPorUsuario(claves, uuidCxnMaestra);
    }

    public Mensaje getRazonSocialConfiguracionPorClave(String clavesRazonSocial, String claveUsuario, String uuidCxnMaestra) {
        return getRazonSocialConfiguracionDAO().getRazonSocialConfiguracionPorClave(clavesRazonSocial, claveUsuario, uuidCxnMaestra);
    }

    public Mensaje getRazonSocialConfiguracionPorRazonSocial(String claveRazonSocial, String uuidCxnMaestra) {
        return getRazonSocialConfiguracionDAO().getRazonSocialConfiguracionPorRazonSocial(claveRazonSocial, uuidCxnMaestra);
    }

    public RazonSocialConfiguracionDAO getRazonSocialConfiguracionDAO() {
        return razonSocialConfiguracionDAO;
    }

    public void setRazonSocialConfiguracionDAO(RazonSocialConfiguracionDAO razonSocialConfiguracionDAO) {
        this.razonSocialConfiguracionDAO = razonSocialConfiguracionDAO;
    }

}
