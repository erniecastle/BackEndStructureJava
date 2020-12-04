/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteFuenteDatos;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ReporteFuenteDatosDAOIF {

    Mensaje agregar(ReporteFuenteDatos entity, String uuidCxnMaestra);

    Mensaje actualizar(ReporteFuenteDatos entity, String uuidCxnMaestra);

    Mensaje eliminar(ReporteFuenteDatos entity, String uuidCxnMaestra);

    Mensaje getReporteFuenteDatosAll(String uuidCxnMaestra);

    Mensaje getReporteFuenteDatosPorClave(String clave, String uuidCxnMaestra);

    Mensaje saveDeleteReporteFuenteDatos(List<ReporteFuenteDatos> entitysCambios, Object[] eliminados, String uuidCxnMaestra);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    Mensaje consultaPorFiltrosFuente(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);
}
