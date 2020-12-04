/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoReporteFijo;
import java.util.Map;

/**
 *
 * @author Ernesto
 */
public interface ServicioReporteFijoIF {

    /*JasperPrint*/
    public Mensaje fillParametrosReporte(byte[] fileReport, String uuidCxn);

    /*byte[]*/
    public Mensaje loadReportesFijos(String ruta, TipoReporteFijo nombreReporte,
            Map<String, Object> valoresFiltro, Map<String, Object> valoresTemplate,
            Map<String, Object> valoresSubreporte1, boolean agrupar, Object[] exportaReporte, String uuidCxn);

    /*boolean*/
    public Mensaje existeUbicacionReporteSistemas(String ruta);

}
