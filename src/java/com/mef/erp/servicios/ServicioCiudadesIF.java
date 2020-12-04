/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 15/03/2011
 * Compañía: Exito Software.
 * Descripción del programa: Interface para servicio ciudades
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01
 * Autor: Jose Armando Sanchez Acosta   
 * Fecha: 21-07-2011
 * Descripción: Se cambio el tipo de dato de los metodos de eliminar y actualizar.
 * -----------------------------------------------------------------------------
 * Clave: AAP01
 * Autor: Abraham Daniel Arjona Peraza
 * Fecha: 29/07/2011
 * Descripción: Se cambió a String la clave de ciudad
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Ciudades;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioCiudadesIF {

    /*Ciudades*/
    Mensaje agregar(Ciudades entity, String uuidCxn);

    //JSA01
    /*boolean*/
    Mensaje actualizar(Ciudades entity, String uuidCxn);

    //JSA01
    /*boolean*/
    Mensaje eliminar(Ciudades entity, String uuidCxn);

    /*List<Ciudades>*/
    Mensaje getCiudadesAll(String uuidCxn);

    /*Ciudades*/
    Mensaje getCiudadesPorClave(String clave, String uuidCxn);//AAP01

    /*List<Ciudades>*/
    Mensaje getCiudadesPorMunicipio(String claveMunicipio, String uuidCxn);

    /*List<Ciudades>*/
    Mensaje getCiudadesPorEstado(String claveEstado, String uuidCxn);

    /*List<Ciudades>*/
    Mensaje getCiudadesPorPais(String clavePais, String uuidCxn);

    /*List<Ciudades>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<Ciudades>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);
    
    /*List<Ciudades>*/
    Mensaje saveDeleteCiudades(List<Ciudades> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
    
}
