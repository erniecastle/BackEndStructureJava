/**
 * @author: Ernesto castillo
 * Fecha de Creación: 15/03/2011
 * Compañía: Exito Software.
 * Descripción del programa: inteface de clase de ciudades para llamados a 
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01
 * Autor: Abraham Daniel Arjona Peraza
 * Fecha: 29/07/2011
 * Descripción: Se cambió a String la clave de ciudad
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:10/11/2011
 * Descripción:Se cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Ciudades;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface CiudadesDAOIF extends GenericDAO<Ciudades, String> {

    //JSA01
    Mensaje agregar(Ciudades entity, String uuidCxn);

    //JSA01
    Mensaje actualizar(Ciudades entity, String uuidCxn);

    //JSA01
    Mensaje eliminar(Ciudades entity, String uuidCxn);

    Mensaje getCiudadesAll(String uuidCxn);

    Mensaje getCiudadesPorClave(String clave, String uuidCxn);//AAP01

    Mensaje getCiudadesPorMunicipio(String claveMunicipio, String uuidCxn);

    Mensaje getCiudadesPorEstado(String claveEstado, String uuidCxn);

    Mensaje getCiudadesPorPais(String clavePais, String uuidCxn);

    //JSA01
    Mensaje consultaPorFiltrosCiudades(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    //JSA01
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    //JSA01
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    //JSA01
    Mensaje saveDeleteCiudades(List<Ciudades> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
