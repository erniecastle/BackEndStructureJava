/**
 * @author: Ernesto Castillo Fecha de Creación: 13/04/2011 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio cruce
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.CentroDeCosto;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioCentroDeCostosIF {

    /*CentroDeCosto*/
    Mensaje agregar(CentroDeCosto entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(CentroDeCosto entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(CentroDeCosto entity, String uuidCxn);

    /*List<CentroDeCosto>*/
    Mensaje getCentroDeCostoAll(String claveRazonesSocial, String uuidCxn);

    /*CentroDeCosto*/
    Mensaje getCentroDeCostoPorClave(String clave, String claveRazonesSocial, String uuidCxn);

    /*CentroDeCosto*/
    Mensaje getCentroDeCostoPorClaveYRazon(String clave, String claveRazon, String uuidCxn);

    /*List<CentroDeCosto>*/
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);

    /*List<CentroDeCosto>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<CentroDeCosto>*/
    Mensaje saveDeleteCentroDeCosto(List<CentroDeCosto> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
