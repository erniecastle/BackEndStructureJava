
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.SemaforoCalculoNomina;

public interface ServicioSemaforoCalculoNominaIF {

    Mensaje agregar(SemaforoCalculoNomina entity, String uuidCxn);
    
    /*boolean*/
    Mensaje eliminar(SemaforoCalculoNomina entity, String uuidCxn);
}
