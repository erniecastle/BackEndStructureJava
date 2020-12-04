/**
 * @author: Jose Armando
 * Compa��a: Macropro. 
 * Descripci�n del programa: Esta clase es para las consultas especiales.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: VIC01 Trabajo: Macropro
 * Autor: Victor Lopez
 * Fecha: 04/10/2012
 * Descripci�n:Metodos para los criterios de consulta
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author xp
 */
public interface ConsultaGenericaEspecialesDAOIF extends GenericDAO<Object, Integer> {

    public Mensaje existeClave(String identificador, String[] campowhere, Object[] valoreswhere, String uuidCxn, String uuidCxnMaestra);

    public Mensaje consultaPorRangosFiltro(String identificador, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, String uuidCxn, String uuidCxnMaestra);
    
    Mensaje obtenerDatosCriterioConsulta(String[] tablas, String[] camposMostrar, String[] camposWhere, Object[] valoresWhere, String[] camposOrden, String uuidCxn); //VIC01
}
