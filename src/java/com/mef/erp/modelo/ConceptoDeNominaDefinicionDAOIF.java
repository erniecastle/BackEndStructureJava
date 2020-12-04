/**
 * @author: Victor Lopez
 * Compañía: Macropro.
 * Descripción del programa: interface ConceptoDeNominaDefinicionDAOIF para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:10/11/2011
 * Descripción:Se cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02
 * Autor:Jose Armando
 * Fecha:29/08/2012
 * Descripción:Se agrego el metodo getConceptoDeNominaDefinicionPorClaves
 * para cuando se ocupe regresar los conceptos actuales de algunas claves.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.BaseAfecConcepNom;
import com.mef.erp.modelo.entidad.ConcepNomDefi;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ConceptoDeNominaDefinicionDAOIF {

    Mensaje agregar(ConcepNomDefi entity, String uuidCxn);//JSA01

    Mensaje actualizar(ConcepNomDefi entity, String uuidCxn);//JSA01

    Mensaje eliminar(ConcepNomDefi entity, String uuidCxn);//JSA01

    Mensaje getConceptoDeNominaDefinicionAll(String uuidCxn);//JSA01

    Mensaje getConceptoDeNominaDefinicionPorClave(String clave, String claveTipoCorrida, String uuidCxn);//JSA01

    Mensaje getConceptoDeNominaDefinicionPorTipoCorrida(String claveTipoCorrida, String uuidCxn);
    
    Mensaje getConceptoDeNominaDefinicionConCuentaContable(String uuidCxn);

    public Mensaje getConceptoDeNominaDefinicionPorClaves(Object[] claves, String uuidCxn);//JSA02

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosConceptos(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje agregaConceptoNominaBaseAfectadas(ConcepNomDefi entity, List<BaseAfecConcepNom> eliminadasAfectadaConceptoNominas, String uuidCxn);//JSA01

    Mensaje claveDescripcionConceptos(String uuidCxn);
}
