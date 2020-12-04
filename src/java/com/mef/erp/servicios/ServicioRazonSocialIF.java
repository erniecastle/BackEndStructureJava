/**
 * @author: Ernesto Castillo Fecha de Creación: 07/04/2011 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio cruce
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 24/11/2012 Descripción: se agrego el
 * metodo getRazonPorClave
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonSocial;

public interface ServicioRazonSocialIF {

    /*RazonSocial*/
    public Mensaje agregar(RazonSocial entity, String uuidCxnMaestra);

    /*Boolean*/
    public Mensaje actualizar(RazonSocial entity, String uuidCxnMaestra);

    /*Boolean*/
    public Mensaje eliminar(RazonSocial entity, String uuidCxnMaestra);

    /*List<RazonSocial>*/
    public Mensaje getRazonSocialAll(String uuidCxnMaestra);

    /*RazonSocial*/
    public Mensaje getRazonPorClave(String claves, String uuidCxnMaestra);//JSA01
}
