/**
 * @author: Jose Armando Compa��a: Macropro. Descripci�n del programa: Esta
 * clase es para las consultas especiales.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripci�n:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author xp
 */
public interface ServicioConsultaGenericaEspecialesIF {

    /*Object*/
    public Mensaje existeClave(String identificador, String[] campowhere, Object[] valoreswhere, String uuidCxn, String uuidCxnMaestra);

    /*List<?> */
    public Mensaje consultaPorRangosFiltro(String identificador, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, String uuidCxn, String uuidCxnMaestra);

    /*List<?> */
    Mensaje obtenerDatosCriterioConsulta(String[] tablas, String[] camposMostrar, String[] camposWhere, Object[] valoresWhere, String[] camposOrden, String uuidCxn);

}
