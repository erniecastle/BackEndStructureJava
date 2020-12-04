/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteDatosIncluir;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface ServicioReporteDatosIncluirIF {

    /*ReporteDatosIncluir*/
    Mensaje agregar(ReporteDatosIncluir entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje actualizar(ReporteDatosIncluir entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje eliminar(ReporteDatosIncluir entity, String uuidCxnMaestra);

    /*List<ReporteDatosIncluir>*/
    Mensaje getReporteDatosIncluirAll(String uuidCxnMaestra);

    /*ReporteDatosIncluir*/
    Mensaje getReporteDatosIncluirPorClave(String clave, String uuidCxnMaestra);

    /*List<ReporteDatosIncluir>*/
    Mensaje saveDeleteReporteDatosIncluir(List<ReporteDatosIncluir> entitysCambios, Object[] eliminados, String uuidCxnMaestra);

    /*List<ReporteDatosIncluir>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    /*List<ReporteDatosIncluir>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    /*int*/
    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);
}
