/**
 * @author: Victor Fecha de Creación: 15/03/2011 Compañía: Macropro. Descripción
 * del programa: clase SERVICIO ConsultaGenerica, para llamados a metodos de
 * objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 25/08/2011
 * Descripción: Se agregó metodo consultaPorRangosFiltro() para realizar la
 * consulta general con filtros
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 27/08/2011
 * Descripción: Se agregó metodo getDataAllFiltro() para realizar los paginados
 * basados en un filtro
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 22/03/2011
 * Descripción: Se agregó metodo getObject() para consultar Objetos de
 * diferentes Entidades
 * -----------------------------------------------------------------------------
 * Clave:VIC01 Autor:Victor Lopez Fecha:28/11/2011 Descripción:Se agregaron
 * Interfaces obtenerClaveStringMax y obtenerClaveNuericaMax
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 22/12/2011
 * Descripción: Se Agrego metodo getFechaSistema
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 19/07/2012
 * Descripción: Se Agrego metodo consultaPorRangosFiltros() para query
 * personalizada
 * -----------------------------------------------------------------------------
 * Clave: VIC02 Autor: Victor Lopez Fecha: 19/06/2012 Descripción: Se Agrego
 * metodo consultaPorRangosOrden, consultaAllOrden
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 19/07/2012
 * Descripción: Se Agrego metodo consultaPorRangosFiltros() para query
 * personalizada
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.CampoOrden;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;

public interface ServicioConsultaGenericaIF {

    /*List<?>*/
    Mensaje getDataAll(String tabla, String uuidCxn, String uuidCxnMaestra);

    /*List<?>*/
    Mensaje getDataAllFiltro(String tabla, String[] campos, Object[] valores, String uuidCxn, String uuidCxnMaestra);

    /*List<?>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn, String uuidCxnMaestra);

    /*List<?>*/
    Mensaje consultaPorRangos(String tabla, Integer inicio, Integer rango, String uuidCxn, String uuidCxnMaestra);

    /*List<?>*/
    Mensaje consultaPorRangosFiltro(String tabla, Integer inicio, Integer rango, String[] campos, Object[] valores, String uuidCxn, String uuidCxnMaestra);

    /*List<?>*/
    Mensaje consultaPorRangosFiltros(String tabla, Integer inicio, Integer rango, String[] campos, Object[] valores, String queryAntesDeFrom, String queryOrden, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra);//JEVC

    /*Boolean*/
    Mensaje existeDato(String tabla, String campo, Object valor, String uuidCxn, String uuidCxnMaestra);

    /*Object[]{String,Boolean}*/
    Mensaje existeDatoList(String[] tabla, String[] campo, Object[] valor, String uuidCxn, String uuidCxnMaestra); 
    
    /*Boolean*/
    Mensaje existeValoresEntidad(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra);

    /*Object*/
    Mensaje existeClave(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra);

    /*List<?>*/
    Mensaje existenClaves(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra);

    /*List<?>*/
    Mensaje existenClavesConOrden(String tabla, String[] campo, Object[] valores, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra);//JEVC01

    /*Object*/
    Mensaje selectExisteClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn, String uuidCxnMaestra);

    /*String*/
    Mensaje obtenerClaveStringMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere, String uuidCxn, String uuidCxnMaestra);//VIC01

    /*Object*/
    Mensaje obtenerClaveNumericaMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere, String uuidCxn, String uuidCxnMaestra);//VIC01

    Date getFechaSistema();

    /*List<?>*/
    Mensaje consultaPorRangosConOrdenado(String tabla, Integer inicio, Integer rango, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra); //VIC02

    /*List<?>*/
    Mensaje consultaAllConOrden(String tabla, String[] camposWhere, Object[] valoresWhere, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra); //VIC02

    /*List<?>*/
    Mensaje consultaAllConOrdenado(String tabla, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra); //VIC02

    /*List<?>*/
    Mensaje consultaPorRangoConFiltroYOrdenado(String tabla, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra); //VIC02

    /*Object*/
    Mensaje getObject(String tabla, String campo, Object valor, String[] camposFiltro, Object[] valoresFiltro, String queryAntesDeFrom, String uuidCxn, String uuidCxnMaestra);
    
   
}
