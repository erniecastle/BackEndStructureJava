/**
 * @author: Armando Fecha de Creación: 04/03/2014 Compañía: Macropro.
 * Descripción del programa: Entidad para guardas las configuraciones de los
 * timbres.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConfiguraTimbradoDAO;
import com.mef.erp.modelo.entidad.ConfiguraTimbrado;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioConfiguraTimbrado implements ServicioConfiguraTimbradoIF {

    private ConfiguraTimbradoDAO configuraTimbradoDAO;

    public Mensaje agregar(ConfiguraTimbrado entity, String uuidCxn) {
        return getConfiguraTimbradoDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ConfiguraTimbrado entity, String uuidCxn) {
        return getConfiguraTimbradoDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ConfiguraTimbrado entity, String uuidCxn) {
        return getConfiguraTimbradoDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getConfiguraTimbradoAll(String uuidCxn) {
        return getConfiguraTimbradoDAO().getConfiguraTimbradoAll(uuidCxn);
    }

    public Mensaje saveConfiguraTimbrado(List<ConfiguraTimbrado> entitysCambios, String uuidCxn) {
        return getConfiguraTimbradoDAO().saveConfiguraTimbrado(entitysCambios, uuidCxn);
    }

    public Mensaje getConfiguraTimbradoPrincipal(ConfiguraTimbrado configuraTimbrado, String uuidCxn) {
        return getConfiguraTimbradoDAO().getConfiguraTimbradoPrincipal(configuraTimbrado, uuidCxn);
    }

    public ConfiguraTimbradoDAO getConfiguraTimbradoDAO() {
        return configuraTimbradoDAO;
    }

    public void setConfiguraTimbradoDAO(ConfiguraTimbradoDAO configuraTimbradoDAO) {
        this.configuraTimbradoDAO = configuraTimbradoDAO;
    }
}
