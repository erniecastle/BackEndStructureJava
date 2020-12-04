/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.cfdi.ConfigConceptosSat;
import java.util.List;

/**
 *
 * @author victor
 */
public interface ConfigConceptosSatDAOIF {

    Mensaje agregar(ConfigConceptosSat entity, String uuidCxn);

    Mensaje actualizar(ConfigConceptosSat entity, String uuidCxn);

    Mensaje eliminar(ConfigConceptosSat entity, String uuidCxn);

    Mensaje getConfigConceptosSatAll(String uuidCxn);

    Mensaje getConfigConceptosSatPorClave(String clave, String uuidCxn);

    Mensaje saveDeleteConfigConceptosSat(List<ConfigConceptosSat> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);
    
    Mensaje eliminaPorClaveConceptoNomina(String claveConcepto, String uuidCxn);
}
