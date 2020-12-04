/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: interface servicio tabla base nomina
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:29/08/2012 Descripción:Se agrego el
 * metodo getConceptoDeNominaDefinicionPorClaves para cuando se ocupe regresar
 * los conceptos actuales de algunas claves.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.BaseAfecConcepNom;
import com.mef.erp.modelo.entidad.ConcepNomDefi;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioConceptoDeNominaDefinicionIF {

    /*ConcepNomDefi*/
    Mensaje agregar(ConcepNomDefi entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(ConcepNomDefi entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(ConcepNomDefi entity, String uuidCxn);

    /*List<ConcepNomDefi>*/
    Mensaje getConceptoDeNominaDefinicionAll(String uuidCxn);

    /*ConcepNomDefi*/
    Mensaje getConceptoDeNominaDefinicionPorClave(String clave, String claveTipoCorrida, String uuidCxn);

    /*List<ConcepNomDefi>*/
    Mensaje getConceptoDeNominaDefinicionPorTipoCorrida(String claveTipoCorrida, String uuidCxn);
    
     /*List<ConcepNomDefi>*/
    Mensaje getConceptoDeNominaDefinicionConCuentaContable(String uuidCxn);

    /*List<ConcepNomDefi>*/
    public Mensaje getConceptoDeNominaDefinicionPorClaves(Object[] claves, String uuidCxn);//JSA01

    /*List<ConcepNomDefi>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);
    
    /*List<ConcepNomDefi>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*Object*/
    Mensaje agregaConceptoNominaBaseAfectadas(ConcepNomDefi entity, List<BaseAfecConcepNom> eliminadasAfectadaConceptoNominas, String uuidCxn);

    /*Object*/
    Mensaje claveDescripcionConceptos(String uuidCxn);

}
