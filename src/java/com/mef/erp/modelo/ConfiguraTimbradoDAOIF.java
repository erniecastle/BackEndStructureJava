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
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ConfiguraTimbrado;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ConfiguraTimbradoDAOIF extends GenericDAO<ConfiguraTimbrado, String> {

    Mensaje agregar(ConfiguraTimbrado entity, String uuidCxn);

    Mensaje actualizar(ConfiguraTimbrado entity, String uuidCxn);

    Mensaje eliminar(ConfiguraTimbrado entity, String uuidCxn);

    Mensaje getConfiguraTimbradoAll(String uuidCxn);

    Mensaje saveConfiguraTimbrado(List<ConfiguraTimbrado> entitysCambios, String uuidCxn);

    Mensaje getConfiguraTimbradoPrincipal(ConfiguraTimbrado configuraTimbrado, String uuidCxn);
}
