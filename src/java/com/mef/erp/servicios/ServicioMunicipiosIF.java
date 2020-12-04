/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 18/03/2011
 * Compañía: Exito Software.
 * Descripción del programa: Interface para servicio cruce
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

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Municipios;
import java.util.List;

public interface ServicioMunicipiosIF {

    /*List<Municipios>*/
    Mensaje getMunicipiosAll(String uuidCxn);

    /*Municipios*/
    Mensaje getMunicipiosPorClave(String clave, String uuidCxn);//AAP01

    /*Municipios*/
    Mensaje agregar(Municipios entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Municipios entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Municipios entity, String uuidCxn);

    /*List<Municipios>*/
    Mensaje getMunicipiosPorEstado(String claveEstados, String uuidCxn);

    /*List<Municipios>*/
    Mensaje getMunicipiosPorPais(String clavePais, String uuidCxn);

    /*List<Municipios>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<Municipios>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<Municipios>*/
    Mensaje saveDeleteMunicipios(List<Municipios> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
    

}
