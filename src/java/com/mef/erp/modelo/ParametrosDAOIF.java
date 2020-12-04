/**
 * @author: Ernesto castillo Fecha de Creación: 06/06/2011 Compañía: Exito
 * Software. Descripción del programa: inteface de clase de Paises para llamados
 * a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:17/07/2012 Descripción:
 * Se agrego el metodo getParametrosPorModuloYClave(String claveModulo, Object[]
 * clavesParametros, String uuidCxn);
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Parametros;
import java.util.List;

public interface ParametrosDAOIF extends GenericDAO<Parametros, Long> {

    Mensaje agregar(Parametros entity, String uuidCxn);//JSA01

    Mensaje actualizar(Parametros entity, String uuidCxn);//JSA01

    Mensaje eliminar(Parametros entity, String uuidCxn);//JSA01

    Mensaje getParametrosAll(String uuidCxn);//JSA01

    Mensaje getParametrosPorClave(Long clave, String uuidCxn);//JSA01

    Mensaje getParametrosPorModulo(Integer id, String uuidCxn);//JSA01

    Mensaje getParametrosPorModuloAsc(Integer id, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosParametro(String query, Object[] campos, Object[] valores, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

//    void SaveParametros(List<Parametros> p, String uuidCxn);//JSA01
//
//    void DeleteParametros(List<Parametros> p, String uuidCxn);//JSA01
    Mensaje getParametrosPorModuloYClave(String claveModulo, Long clave, String uuidCxn);//JSA01

    Mensaje getParametrosPorModuloYClaves(String claveModulo, Object[] clavesParametros, String uuidCxn);//JSA02

    Mensaje getParametrosYListCrucePorModuloYClaves(String claveModulo, Object[] clavesParametros, String uuidCxn);

    Mensaje getParametrosPorModuloYParametro(String nombreModulo, String nombreParametro, String uuidCxn);//JSA01

    public Mensaje saveDeleteParametros(List<Parametros> AgreModif, List<Parametros> eliminados, int rango, String uuidCxn);//DRO01

    public Mensaje existeParametro(Long claveParametros, Long idClave, String uuidCxn);

}
