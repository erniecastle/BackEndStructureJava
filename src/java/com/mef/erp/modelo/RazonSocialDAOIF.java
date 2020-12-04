/**
 * @author: Armando Fecha de Creación: 20/09/2011 Compañía:Macropro Descripción
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 24/11/2012 Descripción: Se agrego el
 * metodo getRazonPorClave
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonSocial;

public interface RazonSocialDAOIF extends GenericDAO<RazonSocial, String> {

    public Mensaje agregar(RazonSocial entity, String uuidCxnMaestra);

    public Mensaje actualizar(RazonSocial entity, String uuidCxnMaestra);

    public Mensaje eliminar(RazonSocial entity, String uuidCxnMaestra);

    public Mensaje getRazonSocialAll(String uuidCxnMaestra);

    public Mensaje getRazonPorClave(String claves, String uuidCxnMaestra);//JSA01
}
