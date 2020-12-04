/**
 * @author: Ernesto castillo
 * Fecha de Creación: 13/04/2011
 * Compañía: Exito Software.
 * Descripción del programa: inteface de clase de Registro patronal para llamados a 
 * metodos de HIBERNATE
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

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Primas;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import java.util.List;

public interface RegistroPatronalIF {

    Mensaje agregar(RegistroPatronal entity, String uuidCxn);//JSA01

    Mensaje actualizar(RegistroPatronal entity, String uuidCxn);//JSA01

    Mensaje eliminar(RegistroPatronal entity, String uuidCxn);//JSA01

    Mensaje getRegistroPatronalAll(String claveRazonesSocial, String uuidCxn);//JSA01

    Mensaje getRegistroPatronalPorClave(String clave,String claveRazonesSocial, String uuidCxn);//JSA01

    Mensaje SaveRegistroPatronal(List<Primas> agrega, Object[] eliminados, RegistroPatronal entity, String uuidCxn);//JSA01

    Mensaje UpdateRegistroPatronal(List<Primas> agrega, Object[] eliminados, RegistroPatronal entity, String uuidCxn);//JSA01

    Mensaje DeleteRegistroPatronal(RegistroPatronal entity, String uuidCxn);//JSA01

    Mensaje consultaPorRangosPatronal(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosPatronal(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje agregarListaRegistrosPatronales(List<RegistroPatronal> cambios, List<RegistroPatronal> temporales, List<Primas> cambioprima,
            Object[] clavesDelete, Object[] clavesPrimasDelete, int rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje deleteListClavesRegistroPatronal(Object[] valores, String uuidCxn);//JSA01
}
