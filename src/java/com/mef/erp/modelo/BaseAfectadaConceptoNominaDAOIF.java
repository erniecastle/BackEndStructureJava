/**
 * @author: Victor Lopez
 * Compañía: Macropro.
 * Descripción del programa: interface BaseAfectadaConceptoNominaDAOIF para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: 
 * Autor: 
 * Fecha: 
 * Descripción: 
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.BaseAfecConcepNom;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface BaseAfectadaConceptoNominaDAOIF {

    Mensaje agregar(BaseAfecConcepNom entity, String uuidCxn);

    Mensaje actualizar(BaseAfecConcepNom entity, String uuidCxn);

    Mensaje eliminar(BaseAfecConcepNom entity, String uuidCxn);

    Mensaje getBaseAfectadaConceptoNominaAll(String uuidCxn);

    Mensaje getBaseAfectadaConceptoNominaPorClave(String clave, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje agregarListaBaseAfectadaConceptoNomina(List<BaseAfecConcepNom> entitys, int rango, String uuidCxn);

    Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);
}
