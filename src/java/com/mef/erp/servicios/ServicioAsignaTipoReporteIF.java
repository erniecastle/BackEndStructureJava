/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.AsignaTipoReporte;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoReporte;
import java.util.List;

public interface ServicioAsignaTipoReporteIF {

    /*AsignaTipoReporte*/
    Mensaje agregar(AsignaTipoReporte entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(AsignaTipoReporte entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(AsignaTipoReporte entity, String uuidCxn);

    /*List<AsignaTipoReporte>*/
    Mensaje getAsignaTipoReporteAll(String uuidCxn);

    /*AsignaTipoReporte*/
    Mensaje getAsignaPorTipoReporteDinamico(String claveReporteDinamico, String uuidCxn);

    /*AsignaTipoReporte*/
    Mensaje getAsignaPorTipoReporte(TipoReporte tipoReporte, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<AsignaTipoReporte>*/
    Mensaje saveDeleteAsignaTipoReporte(List<AsignaTipoReporte> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
