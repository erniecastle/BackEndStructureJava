/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 15/03/2011
 * Compañía: Exito Software.
 * Descripción del programa: Interface para servicio codigos postales
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01
 * Autor: Abraham Daniel Arjona Peraza
 * Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Cp;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioCPIF {

    /*Cp*/
    Mensaje agregar(Cp entity,String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Cp entity,String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Cp entity,String uuidCxn);

    /*List<Cp>*/
    Mensaje getCpAll(String uuidCxn);

    /*Cp*/
    Mensaje getCpPorClave(String clave,String uuidCxn);//AAP01

    /*List<Cp>*/
    Mensaje getCpPorCiudades(String claveCiudad,String uuidCxn);

    /*List<Cp>*/
    Mensaje getCpPorMunicipio(String claveMunicipio,String uuidCxn);

    /*List<Cp>*/
    Mensaje getCpPorEstado(String claveEstado,String uuidCxn);

    /*List<Cp>*/
    Mensaje getCpPorPais(String clavePais,String uuidCxn);

    /*List<Cp>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango,String uuidCxn);

    /*List<Cp>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango,String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor,String uuidCxn);

    /*List<Cp>*/
    Mensaje saveDeleteCp(List<Cp> entitysCambios, Object[] clavesDelete, int rango,String uuidCxn);
    
  
}
