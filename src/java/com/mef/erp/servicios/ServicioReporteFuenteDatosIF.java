/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteFuenteDatos;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface ServicioReporteFuenteDatosIF {

    /*ReporteFuenteDatos*/
    Mensaje agregar(ReporteFuenteDatos entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje actualizar(ReporteFuenteDatos entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje eliminar(ReporteFuenteDatos entity, String uuidCxnMaestra);

    /*List<ReporteFuenteDatos>*/
    Mensaje getReporteFuenteDatosAll(String uuidCxnMaestra);

    /*ReporteFuenteDatos*/
    Mensaje getReporteFuenteDatosPorClave(String clave, String uuidCxnMaestra);

    /*List<ReporteFuenteDatos>*/
    Mensaje saveDeleteReporteFuenteDatos(List<ReporteFuenteDatos> entitysCambios, Object[] eliminados, String uuidCxnMaestra);

    /*List<ReporteFuenteDatos>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    /*List<ReporteFuenteDatos>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    /*int*/
    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);
}
