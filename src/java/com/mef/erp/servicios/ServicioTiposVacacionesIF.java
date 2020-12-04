/**
 * @author: Victor Lopez Fecha de Creación: 28/04/2016 Compañía: MacroPro. 
 * Descripción del programa: Interfaface para servicio de tipos
 * de vacaciones
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
import com.mef.erp.modelo.entidad.TiposVacaciones;
import java.util.List;

public interface ServicioTiposVacacionesIF {

    /*List<TiposVacaciones>*/
    Mensaje getTiposVacacionesAll(String uuidCxn);

    /*TiposVacaciones*/
    Mensaje getTiposVacacionesPorClave(String clave, String uuidCxn);

    /*TiposVacaciones*/
    Mensaje agregar(TiposVacaciones entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(TiposVacaciones entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(TiposVacaciones entity, String uuidCxn);

    /*List<TiposVacaciones>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<TiposVacaciones>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<TiposVacaciones>*/
    Mensaje saveDeleteTiposVacaciones(List<TiposVacaciones> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    /*TiposVacaciones*/
    public Mensaje existeClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn);
}
