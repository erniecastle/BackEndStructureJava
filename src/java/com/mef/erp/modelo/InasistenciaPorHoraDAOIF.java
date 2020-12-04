/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 01/12/2011
 * Compañía: Exito Software.
 * Descripción del programa: interface de clase de InasistenciaPorHora para llamados a metodos de HIBERNATE
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
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.InasistenciaPorHora;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;
import java.util.List;

public interface InasistenciaPorHoraDAOIF extends GenericDAO<Object, Long> {

    Mensaje getInasistenciaPorHoraAll(String uuidCxn);

    Mensaje saveDeleteInasistenciaPorHora(List<InasistenciaPorHora> AgreModif, Object[] clavesDelete, String uuidCxn);
    
    Mensaje getInasistenciaPorNominaPeriodo(String claveTipoNomina, String claveRazonSocial, Long idPeriodo, String uuidCxn);
}
