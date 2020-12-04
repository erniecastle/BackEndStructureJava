/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteDatosIncluir;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface ReporteDatosIncluirDAOIF {

    Mensaje agregar(ReporteDatosIncluir entity, String uuidCxnMaestra);

    Mensaje actualizar(ReporteDatosIncluir entity, String uuidCxnMaestra);

    Mensaje eliminar(ReporteDatosIncluir entity, String uuidCxnMaestra);

    Mensaje getReporteDatosIncluirAll(String uuidCxnMaestra);

    Mensaje getReporteDatosIncluirPorClave(String clave, String uuidCxnMaestra);

    Mensaje saveDeleteReporteDatosIncluir(List<ReporteDatosIncluir> entitysCambios, Object[] eliminados, String uuidCxnMaestra);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    Mensaje consultaPorFiltrosReporte(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);
}
