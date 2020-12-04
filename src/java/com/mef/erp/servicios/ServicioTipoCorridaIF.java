/**
 * @author: Victor Lopez
 * Fecha de Creación: 27/09/2011
 * Compañía: MacroPro.
 * Descripción del programa: interface servicio tipo corrida
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
import com.mef.erp.modelo.entidad.TipoCorrida;
import java.util.List;

public interface ServicioTipoCorridaIF {

    /*TipoCorrida*/
    Mensaje agregar(TipoCorrida entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(TipoCorrida entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(TipoCorrida entity, String uuidCxn);

    /*List<TipoCorrida>*/
    Mensaje getTipoCorridaAll( String uuidCxn);

    /*TipoCorrida*/
    Mensaje getTipoCorridaPorClave(String clave, String uuidCxn);

    /*List<TipoCorrida>*/
    public Mensaje consultaPorFiltrosTipoCorrida(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<TipoCorrida>*/
    public Mensaje consultaPorRangosTipoCorrida(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    public Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<TipoCorrida>*/
    public Mensaje saveDeleteTipoCorrida(List<TipoCorrida> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
