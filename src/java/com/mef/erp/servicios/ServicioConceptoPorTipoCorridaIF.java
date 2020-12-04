/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ConceptoPorTipoCorrida;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoCorrida;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioConceptoPorTipoCorridaIF {

    /*ConceptoPorTipoCorrida*/
    Mensaje agregar(ConceptoPorTipoCorrida entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(ConceptoPorTipoCorrida entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(ConceptoPorTipoCorrida entity, String uuidCxn);

    /*List<ConceptoPorTipoCorrida>*/
    Mensaje getConceptoPorTipoCorridaAll(String uuidCxn);

    /*List<ConceptoPorTipoCorrida>*/
    Mensaje getConceptoPorTipoCorrida(TipoCorrida tipoCorrida, String uuidCxn);

    /*List<ConceptoPorTipoCorrida>*/
    Mensaje getConceptoPorTipoCorridaMostrarActivo(String tipoCorrida, String uuidCxn);

    /*List<ConceptoPorTipoCorrida>*/
    Mensaje saveDeleteConceptoPorTipoCorrida(List<ConceptoPorTipoCorrida> entitysCambios, Object[] eliminados, String uuidCxn);

    /*List<ConceptoPorTipoCorrida>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

}
