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

import com.mef.erp.modelo.ConfigFoliacionDAO;
import com.mef.erp.modelo.entidad.ConfigFoliacion;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioConfigFoliacion implements ServicioConfigFoliacionIF {

    private ConfigFoliacionDAO configFoliacionDAO;

    public Mensaje agregar(ConfigFoliacion entity, String uuidCxn) {
        return getConfigFoliacionDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ConfigFoliacion entity, String uuidCxn) {
        return getConfigFoliacionDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ConfigFoliacion entity, String uuidCxn) {
        return getConfigFoliacionDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getConfigFoliacionAll(String uuidCxn) {
        return getConfigFoliacionDAO().getConfigFoliacionAll(uuidCxn);
    }

    public Mensaje getConfigFoliacionPorClave(String tabla, String campoClave, String uuidCxn) {
        return getConfigFoliacionDAO().getConfigFoliacionPorClave(tabla, campoClave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return getConfigFoliacionDAO().consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return getConfigFoliacionDAO().existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteConfigFoliacion(List<ConfigFoliacion> entitysCambios, Object[] idDelete, int rango, String uuidCxn) {
        return getConfigFoliacionDAO().saveDeleteConfigFoliacion(entitysCambios, idDelete, rango, uuidCxn);
    }

    public ConfigFoliacionDAO getConfigFoliacionDAO() {
        return configFoliacionDAO;
    }

    public void setConfigFoliacionDAO(ConfigFoliacionDAO categoriasPuestosDAO) {
        this.configFoliacionDAO = categoriasPuestosDAO;
    }

   
}
