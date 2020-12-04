
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.SemaforoTimbradoPac;

public interface ServicioSemaforoTimbradoPacIF {

    Mensaje agregar(SemaforoTimbradoPac entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(SemaforoTimbradoPac entity, String uuidCxn);
}
