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
import com.mef.erp.modelo.entidad.TipoCorrida;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface TipoCorridaDAOIF {

    Mensaje agregar(TipoCorrida entity, String uuidCxn);

    public Mensaje actualizar(TipoCorrida entity, String uuidCxn);

    public Mensaje eliminar(TipoCorrida entity, String uuidCxn);

    Mensaje getTipoCorridaAll(String uuidCxn);

    Mensaje getTipoCorridaPorClave(String clave, String uuidCxn);

    public Mensaje consultaPorFiltrosTipoCorrida(String query, Object[] campos, Object[] valores, String uuidCxn);

    public Mensaje consultaPorRangosTipoCorrida(Integer inicio, Integer rango, String uuidCxn);

    public Mensaje existeDato(String campo, Object valor, String uuidCxn);

    public Mensaje saveDeleteTipoCorrida(List<TipoCorrida> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}
