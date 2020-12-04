/**
 * @author: Jose Armando Sanchez Fecha de Creación: --/--/2011 Compañía: Exito
 * Software. Descripción del programa: inteface con metodos para servicio de
 * consulta generica.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:VIC01 Autor:Victor Lopez Fecha:28/11/2011 Descripción:Se agregaron
 * Interfaces obtenerClaveStringMax y obtenerClaveNuericaMax
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 22/12/2011
 * Descripción: Se Agrego metodo getFechaSistema
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 19/07/2012
 * Descripción: Se Agrego metodo consultaPorRangosFiltros
 * -----------------------------------------------------------------------------
 * Clave: VIC02 Autor: Victor Lopez Fecha: 19/06/2012 Descripción: Se Agrego
 * metodo consultaPorRangosOrden, consultaAllOrden
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CampoOrden;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public interface ConsultaGenericaDAOIF extends GenericDAO<Object, Integer> {

    Mensaje getDataAll(String tabla, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje getDataAllFiltro(String tabla, String[] campos, Object[] valores, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje consultaPorFiltrosGenerico(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje consultaPorRangos(String tabla, Integer inicio, Integer rango, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje consultaPorRangosFiltro(String tabla, Integer inicio, Integer rango, String[] campos, Object[] valores, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje consultaPorRangosFiltros(String tabla, Integer inicio, Integer rango, String[] campos, Object[] valores, String queryAntesDeFrom, String queryOrden, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra);//JEVC

    Mensaje existeDatoGenerico(String tabla, String campo, Object valor, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje existeDatoList(String[] tabla, String[] campo, Object[] valor, String uuidCxn, String uuidCxnMaestra);

    Mensaje existeValoresEntidad(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra);

    Mensaje existeClaveGenerico(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje selectExisteClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn, String uuidCxnMaestra);//JEVC

    Mensaje existenClavesGenerico(String tabla, String[] campos, Object[] valores, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje existenClavesConOrden(String tabla, String[] campo, Object[] valores, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra);//JEVC

    Mensaje obtenerClaveStringMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere, String uuidCxn, String uuidCxnMaestra);//VIC01

    Mensaje obtenerClaveNumericaMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere, String uuidCxn, String uuidCxnMaestra);//VIC01

    Date getFechaSistema();//JEVC

    Mensaje consultaPorRangosConOrdenado(String tabla, Integer inicio, Integer rango, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra); //VIC02

    Mensaje consultaAllConOrdenado(String tabla, String[] camposWhere, Object[] valoresWhere, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra); //VIC02

    Mensaje consultaAllConOrdenado(String tabla, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra); //VIC02

    Mensaje consultaPorRangoConFiltroYOrdenado(String tabla, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra);  //VIC02

    Mensaje getObject(String tabla, String campo, Object valor, String[] camposFiltro, Object[] valoresFiltro, String queryAntesDeFrom, String uuidCxn, String uuidCxnMaestra);
}
