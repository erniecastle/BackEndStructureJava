/**
 * @author: Armando Fecha de Creación: 03/06/2014 Compañía:Macropro Descripción
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;

public interface ServicioRazonSocialConfiguracionIF {

    /*List<RazonSocialConfiguracion> */
    public Mensaje getRazonSocialConfiguracionAll(String uuidCxnMaestra);

    /*List<RazonSocialConfiguracion> */
    public Mensaje getRazonSocialConfiguracionPorUsuario(String claves, String uuidCxnMaestra);

    /*RazonSocialConfiguracion*/
    public Mensaje getRazonSocialConfiguracionPorClave(String clavesRazonSocial, String claveUsuario, String uuidCxnMaestra);

    /*List<RazonSocialConfiguracion> */
    public Mensaje getRazonSocialConfiguracionPorRazonSocial(String claveRazonSocial, String uuidCxnMaestra);
}
