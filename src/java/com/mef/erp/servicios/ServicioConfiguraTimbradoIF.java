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

import com.mef.erp.modelo.entidad.ConfiguraTimbrado;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioConfiguraTimbradoIF {

    /*ConfiguraTimbrado*/
    public Mensaje agregar(ConfiguraTimbrado entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ConfiguraTimbrado entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ConfiguraTimbrado entity, String uuidCxn);

    /*List<ConfiguraTimbrado> */
    public Mensaje getConfiguraTimbradoAll(String uuidCxn);

    /*List<ConfiguraTimbrado> */
    public Mensaje saveConfiguraTimbrado(List<ConfiguraTimbrado> entitysCambios, String uuidCxn);

    /*ConfiguraTimbrado*/
    public Mensaje getConfiguraTimbradoPrincipal(ConfiguraTimbrado configuraTimbrado, String uuidCxn);
    
}
