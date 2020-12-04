/**
 * @author: Victor Lopez Fecha de Creación: 28/04/2016 Compañía: MacroPro. 
 * Descripción del programa: inteface de clase para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TiposVacaciones;
import java.util.List;

public interface TiposVacacionesDAOIF {

    Mensaje getTiposVacacionesAll(String uuidCxn);

    Mensaje getTiposVacacionesPorClave(String clave, String uuidCxn);

    Mensaje agregar(TiposVacaciones entity, String uuidCxn);

    Mensaje actualizar(TiposVacaciones entity, String uuidCxn);

    Mensaje eliminar(TiposVacaciones entity, String uuidCxn);

    Mensaje consultaPorFiltrosTiposVacaciones(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteTiposVacaciones(List<TiposVacaciones> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    Mensaje existeClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn);
}
