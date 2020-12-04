/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 21/06/2011 Compañía:
 * Exito Software. Descripción del programa: clase para servicio de
 * Departamentos
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Departamentos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarollo 15
 */
public interface ServicioDepartamentosIF {

    /*List<Departamentos>*/
    Mensaje getDepartamentosAll(String claveRazonesSocial, String uuidCxn);

    /*Departamentos*/
    Mensaje getDepartamentosPorClave(String clave, String claveRazonesSocial, String uuidCxn);

    /*Departamentos*/
    Mensaje agregar(Departamentos entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Departamentos entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Departamentos entity, String uuidCxn);

    /*List<Departamentos>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<Departamentos>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<Departamentos>*/
    Mensaje saveDeleteDepartamentos(List<Departamentos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}
