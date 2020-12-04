/**
 * @author: Dayane Rocha
 * Fecha de Creación: 15/06/2013
 * Compañía: Finesoft.
 * Descripción del programa: Interface para servicio Parentesco
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: 
 * Autor: 
 * Fecha: 
 * Descripción: 
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Parentesco;
import java.util.List;

/**
 *
 * @author Dayane
 */
public interface ServicioParentescoIF {

    /*Parentesco*/
    Mensaje agregar(Parentesco entity,  String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Parentesco entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Parentesco entity, String uuidCxn);

    /*List<Parentesco>*/
    Mensaje getParentescoAll(String uuidCxn);

    /*Parentesco*/
    Mensaje getParentescosPorClave(String clave, String uuidCxn);

    /*List<Parentesco>*/
    public Mensaje consultaPorFiltrosParentescos(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<Parentesco>*/
    public Mensaje consultaPorRangosParentescos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    public Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<Parentesco>*/
    public Mensaje saveDeleteParentesco(List<Parentesco> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
    
}
