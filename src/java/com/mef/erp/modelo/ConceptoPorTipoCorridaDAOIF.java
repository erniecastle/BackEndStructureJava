/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ConceptoPorTipoCorrida;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoCorrida;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ConceptoPorTipoCorridaDAOIF {
    Mensaje agregar(ConceptoPorTipoCorrida entity, String uuidCxn);

    Mensaje actualizar(ConceptoPorTipoCorrida entity, String uuidCxn);

    Mensaje eliminar(ConceptoPorTipoCorrida entity, String uuidCxn);

    Mensaje getConceptoPorTipoCorridaAll(String uuidCxn);

    Mensaje getConceptoPorTipoCorrida(TipoCorrida tipoCorrida, String uuidCxn);
    
    Mensaje saveDeleteConceptoPorTipoCorrida(List<ConceptoPorTipoCorrida> entitysCambios, Object[] eliminados, String uuidCxn);

    Mensaje getConceptoPorTipoCorridaMostrarActivo(String tipoCorrida, String uuidCxn);
    
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);
}
