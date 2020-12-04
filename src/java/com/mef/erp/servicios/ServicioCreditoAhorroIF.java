/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 16/08/2011 Compañía:
 * Exito Software. Descripción del programa: Interface para servicio de Creditos
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.CreditoAhorro;
import com.mef.erp.modelo.entidad.Mensaje;

public interface ServicioCreditoAhorroIF {

    /*CreditoAhorro*/
    Mensaje agregar(CreditoAhorro entity,String uuidCxn);

    /*boolean*/
    Mensaje actualizar(CreditoAhorro entity,String uuidCxn);

    /*boolean*/
    Mensaje eliminar(CreditoAhorro entity,String uuidCxn);

    /*List<CreditoAhorro>*/
    Mensaje getCreditoAhorroAll(String claveRazonesSociales, String tipoConfiguracion,String uuidCxn);

    /*CreditoAhorro*/
    Mensaje getCreditoAhorroPorClave(String clave, String claveRazonesSociales, String tipoConfiguracion,String uuidCxn);

    
}
