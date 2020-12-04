/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: interface
 * Concepto de Nomina para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ParaConcepDeNom;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ParametroConceptosDeNominaDAOIF {

    Mensaje agregar(ParaConcepDeNom entity, String uuidCxn);//JSA01

    Mensaje actualizar(ParaConcepDeNom entity, String uuidCxn);//JSA01

    Mensaje eliminar(ParaConcepDeNom entity, String uuidCxn);//JSA01

    Mensaje getParametroConceptosDeNominaAll(String uuidCxn);//JSA01

    Mensaje getParametroConceptosDeNominaPorClave(String clave, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosParametros(String query, Object[] campos, Object[] valores, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje agregarListaParametroConceptosDeNomina(List<ParaConcepDeNom> entitys, int rango, String uuidCxn);//JSA01

    Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);//JSA01
}
