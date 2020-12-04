/**
 * @author: Victor Lopez
 * Compañía: Macropro.
 * Descripción del programa: interface BaseAfectadaGrupoDAOIF para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:10/11/2011
 * Descripción:Se cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.BaseAfectadaGrupo;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface BaseAfectadaGrupoDAOIF {

    Mensaje agregar(BaseAfectadaGrupo entity, String uuidCxn);//JSA01

    Mensaje actualizar(BaseAfectadaGrupo entity, String uuidCxn);//JSA01

    Mensaje eliminar(BaseAfectadaGrupo entity, String uuidCxn);//JSA01

    Mensaje getBaseAfectadaGrupoAll(String uuidCxn);//JSA01

    Mensaje getBaseAfectadaGrupoPorClave(String clave, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje agregarListaBaseAfectadaGrupo(List<BaseAfectadaGrupo> entitys, int rango, String uuidCxn);//JSA01

    Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);//JSA01
}
