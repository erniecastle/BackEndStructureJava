/**
 * @author: Victor Lopez
 * Compañía: Macropro.
 * Descripción del programa: clase centro de costos para llamados a metodos de HIBERNATE
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

import com.mef.erp.modelo.entidad.CentroDeCosto;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface CentroDeCostosIF {

    Mensaje agregar(CentroDeCosto entity, String uuidCxn);//JSA01

    Mensaje actualizar(CentroDeCosto entity, String uuidCxn);//JSA01

    Mensaje eliminar(CentroDeCosto entity, String uuidCxn);//JSA01

    Mensaje getCentroDeCostoAll(String claveRazonesSocial, String uuidCxn);//JSA01

    Mensaje getCentroDeCostoPorClave(String clave, String claveRazonesSocial, String uuidCxn);//JSA01

    Mensaje getCentroDeCostoPorClaveYRazon(String clave, String claveRazon, String uuidCxn);//JSA01

    Mensaje consultaPorRangosCentroCosto(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosCentroCosto(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje saveDeleteCentroDeCosto(List<CentroDeCosto> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);//JSA01
}
