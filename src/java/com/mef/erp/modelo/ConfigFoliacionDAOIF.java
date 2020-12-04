/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ConfigFoliacion;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ConfigFoliacionDAOIF extends GenericDAO<ConfigFoliacion, Long> {

    public Mensaje agregar(ConfigFoliacion entity, String uuidCxn);

    public Mensaje actualizar(ConfigFoliacion entity, String uuidCxn);

    public Mensaje eliminar(ConfigFoliacion entity, String uuidCxn);

    public Mensaje getConfigFoliacionAll(String uuidCxn);

    public Mensaje getConfigFoliacionPorClave(String tabla,String campoClave, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteConfigFoliacion(List<ConfigFoliacion> entitysCambios, Object[] idDelete, int rango, String uuidCxn);
}
