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
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ConfigFoliacion;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioConfigFoliacionIF {

    /*ConfigFoliacion*/
    public Mensaje agregar(ConfigFoliacion entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ConfigFoliacion entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ConfigFoliacion entity, String uuidCxn);

    /*List<ConfigFoliacion>*/
    public Mensaje getConfigFoliacionAll(String uuidCxn);

    /*ConfigFoliacion*/
    public Mensaje getConfigFoliacionPorClave(String tabla, String campoClave, String uuidCxn);

    /*List<ConfigFoliacion>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<ConfigFoliacion>*/
    Mensaje saveDeleteConfigFoliacion(List<ConfigFoliacion> entitysCambios, Object[] idDelete, int rango, String uuidCxn);
    
}
