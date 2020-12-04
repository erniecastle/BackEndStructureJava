/**
 * @author: Jose Armando Fecha de Creación: 19/06/2012 Compañía: Exito Software.
 * Descripción del programa: Interface para servicio Tipo de contrato
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoContrato;
import java.util.List;

public interface TipoContratoDAOIF extends GenericDAO<TipoContrato, String> {//AAP01

    Mensaje agregar(TipoContrato entity, String uuidCxn);

    public Mensaje actualizar(TipoContrato entity, String uuidCxn);

    public Mensaje eliminar(TipoContrato entity, String uuidCxn);

    Mensaje getTipoContratoAll(String uuidCxn);

    Mensaje getTipoContratoPorClave(String clave, String uuidCxn);//AAP01

    public Mensaje consultaPorFiltrosTipoContrato(String query, Object[] campos, Object[] valores, String uuidCxn);

    public Mensaje consultaPorRangosTipoContrato(Integer inicio, Integer rango, String uuidCxn);

    public Mensaje existeDato(String campo, Object valor, String uuidCxn);

    public Mensaje saveDeleteTipoContrato(List<TipoContrato> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}
