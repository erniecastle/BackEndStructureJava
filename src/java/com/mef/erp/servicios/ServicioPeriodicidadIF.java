/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 16/06/2011 Compañía:
 * Exito Software. Descripción del programa: Interfaface para servicio de tipos
 * de nomina
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Periodicidad;
import java.util.List;

public interface ServicioPeriodicidadIF {

    /*List<Periodicidad>*/
    Mensaje getPeriodicidadAll(String uuidCxn);

    /*Periodicidad*/
    Mensaje getPeriodicidadPorClave(String clave, String uuidCxn);

    /*Periodicidad*/
    Mensaje agregar(Periodicidad entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Periodicidad entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Periodicidad entity, String uuidCxn);

    /*List<Periodicidad>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<Periodicidad>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<Periodicidad>*/
    Mensaje saveDeletePeriodicidad(List<Periodicidad> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
