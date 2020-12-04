/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.cfdi.ConfigConceptosSat;
import java.util.List;

public interface ServicioConfigConceptosSatIF {

    /*ConfigConceptosSat*/
    Mensaje agregar(ConfigConceptosSat entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(ConfigConceptosSat entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(ConfigConceptosSat entity, String uuidCxn);

    /*List<ConfigConceptosSat> */
    Mensaje getConfigConceptosSatAll(String uuidCxn);

    /*List<ConfigConceptosSat> */
    Mensaje saveDeleteConfigConceptosSat(List<ConfigConceptosSat> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    /*List<ConfigConceptosSat> */
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);
    
    /*boolean*/
    Mensaje eliminaPorClaveConceptoNomina(String claveConcepto, String uuidCxn);

}
