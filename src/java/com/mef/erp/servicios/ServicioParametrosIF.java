/**
 * @author: Ernesto Castillo Fecha de Creación: 03/05/2011 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio parametros
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:17/07/2012 Descripción:
 * Se agrego el metodo getParametrosPorModuloYClave(String claveModulo, Object[]
 * clavesParametros, String uuidCxn);
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Parametros;
import java.util.List;

public interface ServicioParametrosIF {

    /*Parametros*/
    Mensaje agregar(Parametros entity, String uuidCxn);

    /*boolen*/
    Mensaje actualizar(Parametros entity, String uuidCxn);

    /*boolen*/
    Mensaje eliminar(Parametros entity, String uuidCxn);

    /*List<Parametros>*/
    Mensaje getParametrosAll(String uuidCxn);

    /*Parametros*/
    Mensaje getParametrosPorClave(Long clave, String uuidCxn);

    /*List<Parametros>*/
    Mensaje getParametrosPorModulo(Integer id, String uuidCxn);

    /*List<Parametros>*/
    Mensaje getParametrosPorModuloAsc(Integer id, String uuidCxn);

    /*List<Parametros>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<Parametros>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*Parametros*/
    Mensaje getParametrosPorModuloYClave(String claveModulo, Long clave, String uuidCxn);

    /*List<Parametros>*/
    Mensaje getParametrosPorModuloYClaves(String claveModulo, Object[] clavesParametros, String uuidCxn);//JSA01

    /*List<Object[Parametro,List<Cruces>]>*/
    public Mensaje getParametrosYListCrucePorModuloYClaves(String claveModulo, Object[] clavesParametros, String uuidCxn);

    /*Parametros*/
    Mensaje getParametrosPorModuloYParametro(String nombreModulo, String nombreParametro, String uuidCxn);

    /*List<Parametros>*/
    public Mensaje saveDeleteParametros(List<Parametros> AgreModif, List<Parametros> eliminados, int rango, String uuidCxn);//DRO01

    /*Boolean*/
    public Mensaje existeParametro(Long claveParametros, Long idClave, String uuidCxn);

}
