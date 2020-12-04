/**
 * @author: Victor Lopez Fecha de Creación: 05/12/2016 Compañía: Macropro
 * Descripción del programa: Interface para servicio InasistenciaPorHora
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.InasistenciaPorHora;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;
import java.util.List;

public interface ServicioInasistenciaPorHoraIF {

    /*List<InasistenciaPorHora>*/
    Mensaje getInasistenciaPorHoraAll(String uuidCxn);

    /*InasistenciaPorHora*/
    Mensaje saveDeleteInasistenciaPorHora(List<InasistenciaPorHora> AgreModif, Object[] clavesDelete, String uuidCxn);
    
    /*List<InasistenciaPorHora>*/
    Mensaje getInasistenciaPorNominaPeriodo(String claveTipoNomina, String claveRazonSocial, Long idPeriodo, String uuidCxn);

}
