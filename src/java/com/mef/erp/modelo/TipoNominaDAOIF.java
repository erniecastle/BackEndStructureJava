/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 15/06/2011 Compañía:
 * Exito Software. Descripción del programa: inteface de clase para llamados a
 * metodos de HIBERNATE
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
import com.mef.erp.modelo.entidad.TipoNomina;
import java.util.List;

public interface TipoNominaDAOIF {

    Mensaje getTipoNominaAll(String uuidCxn);//JSA01

    Mensaje getTipoNominaPorClave(String clave, String uuidCxn);//JSA01

    Mensaje agregar(TipoNomina entity, String uuidCxn);//JSA01

    Mensaje actualizar(TipoNomina entity, String uuidCxn);//JSA01

    Mensaje eliminar(TipoNomina entity, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosTipoNomina(String query, Object[] campos, Object[] valores, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje saveDeleteTipoNomina(List<TipoNomina> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);//JSA01

    Mensaje existeClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn);
}
