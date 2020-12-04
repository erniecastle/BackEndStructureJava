/**
 * @author: Armando Fecha de Creación: 20/09/2011 Compañía:Macropro Descripción
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonSocialConfiguracion;

public interface RazonSocialConfiguracionDAOIF extends GenericDAO<RazonSocialConfiguracion, Long> {

    public Mensaje getRazonSocialConfiguracionAll(String uuidCxnMaestra);

    public Mensaje getRazonSocialConfiguracionPorUsuario(String claves, String uuidCxnMaestra);

    public Mensaje getRazonSocialConfiguracionPorClave(String clavesRazonSocial, String claveUsuario, String uuidCxnMaestra);

    public Mensaje getRazonSocialConfiguracionPorRazonSocial(String claveRazonSocial, String uuidCxnMaestra);
}
