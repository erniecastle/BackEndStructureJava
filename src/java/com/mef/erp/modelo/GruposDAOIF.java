/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa: clase grupos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.BaseAfectadaGrupo;
import com.mef.erp.modelo.entidad.Grupo;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface GruposDAOIF extends GenericDAO<Grupo, Long> {

    Mensaje agregar(Grupo entity, String uuidCxn);//JSA01

    Mensaje actualizar(Grupo entity, String uuidCxn);//JSA01

    Mensaje eliminar(Grupo entity, String uuidCxn);//JSA01

    Mensaje getGruposALL(String uuidCxn);//JSA01

    Mensaje getGruposPorClave(String clave, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosGrupo(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje agregarListaGrupos(List<Grupo> entitys, int rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);//JSA01

    Mensaje agregaGruposBaseAfectadas(Grupo entity, List<BaseAfectadaGrupo> eliminadasAfectadaGrupos, String uuidCxn);//JSA01
}
