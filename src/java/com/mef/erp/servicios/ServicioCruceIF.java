/**
 * @author: Ernesto Castillo Fecha de Creación: 27/05/2011 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio de cruce
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Cruce;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioCruceIF {

    /*Cruce*/
    Mensaje agregar(String uuidCxn, Cruce entity);

    /*boolean*/
    Mensaje actualizar(String uuidCxn, Cruce entity);

    /*boolean*/
    Mensaje eliminar(String uuidCxn, Cruce entity);

    /*List<Cruce>*/
    Mensaje getCruceAll(String uuidCxn);

    /*boolean*/
    Mensaje existeClaveElemento(String uuidCxn, String claveelemento, String elemento, Long parametro);

    /*List<Cruce>*/
    Mensaje getCrucePorParametros(String uuidCxn, Long claveParametro);

    /*List<Cruce>*/
    Mensaje getCrucePorElemento(String uuidCxn, String elemento);

    /*List<Cruce>*/
    Mensaje getCrucePorParamElemento(String uuidCxn, Long claveParametro, String elemento);

    /*List<Cruce>*/
    Mensaje getCrucePorElemeYClave(String uuidCxn, String elemento, String claveelemento);

    /*List<Cruce>*/
    Mensaje getCrucePorParaElemeYClave(String uuidCxn, Long claveParametro, String elemento, String claveelemento);

    /*Cruce*/
    Mensaje SaveDeleteCruces(String uuidCxn, List<Cruce> AgreModif, List<Cruce> Ordenados, Object[] clavesDelete);

    /*boolean*/
    Mensaje SaveCruces(String uuidCxn, List<Cruce> c);

    /*boolean*/
    Mensaje DeleteCruces(String uuidCxn, List<Cruce> c);
    
  
}
