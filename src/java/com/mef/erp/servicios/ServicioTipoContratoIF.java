/**
 * @author: Jose Armando Fecha de Creación: 19/06/2012 Compañía: Exito Software.
 * Descripción del programa: Interface para servicio Tipo de contrato
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoContrato;
import java.util.List;

public interface ServicioTipoContratoIF {

    /*TipoContrato*/
    Mensaje agregar(TipoContrato entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(TipoContrato entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(TipoContrato entity, String uuidCxn);

    /*List<TipoContrato>*/
    Mensaje getTipoContratoAll(String uuidCxn);

    /*TipoContrato*/
    Mensaje getTipoContratoPorClave(String clave, String uuidCxn);

    /*List<TipoContrato>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<TipoContrato>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<TipoContrato>*/
    Mensaje saveDeleteTipoContrato(List<TipoContrato> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
